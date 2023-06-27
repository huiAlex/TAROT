/*

   Derby - Class org.apache.derby.impl.store.raw.data.StoredRecordHeader

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.derby.impl.store.raw.data;

import org.apache.derby.shared.common.sanity.SanityManager;

import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;

import java.io.IOException;
import java.io.EOFException;

import java.io.OutputStream;

import org.apache.derby.iapi.services.io.CompressedNumber;

/**
    A class StoredPage uses to cache record headers by passing instances
    to BasePage, and to write stored versions of record headers.

    Format

    <PRE>

    1 byte          - status
    compressed int  - record identifier

    compressed long - overflow page } only if hasOverflow() is true
    compressed int  - overflow id   }     "        "           "

    compressed int  - first field   } only if hasFirstField set - otherwise 0

    compressed int  - number of fields in this portion - only if hasOverflow()
                                is false OR hasFirstField is true - otherwise 0
    </PRE>

*/

public final class StoredRecordHeader
{

    /**************************************************************************
     * Constants of the class
     **************************************************************************
     */

    /**
     * Status bits for the record header:
     *
     * RECORD_DELETED           - used to indicate the record has been deleted
     * RECORD_OVERFLOW          - used to indicate the record has been 
     *                            overflowed, it will point to the overflow 
     *                            page and ID
     * RECORD_HAS_FIRST_FIELD   - used to indicate that firstField is stored. 
     *                            When RECORD_OVERFLOW and 
     *                            RECORD_HAS_FIRST_FIELD both are set, part of 
     *                            record is on the page, the record header 
     *                            also stores the overflow pointer to the next 
     *                            part of the record.
     * RECORD_VALID_MASK        - A mask of valid bits that can be set 
     *                            currently, such that the following assert can
     *                            be made: 
     *                              ASSERT((status &amp; ~RECORD_VALID_MASK) == 0))
     **/
    private static final byte RECORD_DELETED = 0x01;
    private static final byte RECORD_OVERFLOW = 0x02;
    private static final byte RECORD_HAS_FIRST_FIELD = 0x04;
    private static final byte RECORD_VALID_MASK = 0x0f;

    /**
     * maximum length for row containing just an overflow pointer.
     * <p>
     * The maximum stored length of a row that just contains an overflow pointer
     * is 17 bytes:
     *   stored sizeof(status byte)       :  1 +
     *   stored sizeof(record id)         :  4 +
     *   max stored size overflow page ptr:  8 +
     *   max stored size overflow record id: 4  
     **/
    public static final int MAX_OVERFLOW_ONLY_REC_SIZE = 
            1 +                                     // stored status byte
            CompressedNumber.MAX_INT_STORED_SIZE  + // max stored record id size
            CompressedNumber.MAX_LONG_STORED_SIZE + // max stored overflow page 
            CompressedNumber.MAX_INT_STORED_SIZE;   // max stored overflow id


    /**************************************************************************
     * Fields of the class
     **************************************************************************
     */

    /**
     * Actual identifier of the record
     *
     * <BR> MT - Mutable
     **/
    protected int   id;

    /**
     * Status of the record.
     *
     * See above for description of fields:
     *     RECORD_DELETED
     *     RECORD_OVERFLOW
     *     RECORD_HAS_FIRST_FIELD
     *     RECORD_VALID_MASK
     *
     * <BR> MT - Mutable - single thread required.
     **/
    private byte status;

    /**
     * number of fields in the row.
     **/
    protected int numberFields;

    /**
     * A record handle that can represent the record, may be null.
     **/
    protected RecordHandle  handle;

    /**
     * Class which holds the fields {@code overflowId}, {@code overflowPage}
     * and {@code firstField}, which are not needed when there is no
     * overflow. These fields are factored out to save Java heap space (see
     * DERBY-3130).
     */
    private static class OverflowInfo {

        /** Create an empty {@code OverflowInfo} object. */
        private OverflowInfo() { }

        /** Create a copy of a {@code OverflowInfo} object. */
        private OverflowInfo(OverflowInfo from) {
            overflowId = from.overflowId;
            overflowPage = from.overflowPage;
            firstField = from.firstField;
        }

        /**
         * If (hasOverflow()) then this is the id of the row on page
         * overflowPage where the next portion of the row can be found. In this
         * case there are no "real" fields on this page.  This situation comes
         * about if a row has been updated such that the real first field no
         * longer fits on the head page.
         */
        private int overflowId;

        /**
         * If (hasOverflow()) then this is the page where where the next
         * portion of the row can be found. In this case there are no "real"
         * fields on this page.
         */
        private long overflowPage;

        /**
         * If (hasFirstField()) then this field is the number of the column in
         * the orginal row which is now stored as the first field in this row.
         * This row is 2nd through N'th portion of a long row.
         *
         * For example if a row has its first 3 fields on page 0 and its next 3
         * fields on page 1, then the record header of the row portion on page
         * 1 will have hasFirstField() set to true, and the value would be 4,
         * indicating that the 4th field of the row is stored as the 1st field
         * of the partial row portion stored on page 1.
         */
        private int firstField;
    }

    private OverflowInfo overflow;

    /**************************************************************************
     * Constructors for This class:
     **************************************************************************
     */
    public StoredRecordHeader() 
    {
    }

    public StoredRecordHeader(int id, int numberFields) 
    {
        setId(id);
        setNumberFields(numberFields);
    }

    public StoredRecordHeader(
    byte    data[],
    int     offset)
    {
        read(data, offset);
    }

    public StoredRecordHeader(StoredRecordHeader loadTargetFrom) 
    {
        this.status         = loadTargetFrom.status;
        this.id             = loadTargetFrom.id;
        this.numberFields   = loadTargetFrom.numberFields;
        handle              = null;

        if (loadTargetFrom.overflow != null) {
            overflow = new OverflowInfo(loadTargetFrom.overflow);
        }
    }

    /**************************************************************************
     * Public Accessor "Get" Methods of This class:
     **************************************************************************
     */

    /**
     * Get a record handle for the record.
     * <p>
     *
     * <BR> MT - single thread required
     **/
    protected RecordHandle getHandle(
    PageKey pageId, 
    int current_slot) 
    {
        if (handle == null)
            handle = new RecordId(pageId, id, current_slot);

        return handle;
    }

    /**
     * Get the record identifier
     *
     * <BR> MT - thread safe
     **/
    public final int getId() 
    {
        return id;
    }

    public int getNumberFields() 
    {
        return numberFields;
    }

    public long getOverflowPage() 
    {
        return overflow == null ? 0 : overflow.overflowPage;
    }

    public int getOverflowId() 
    {
        return overflow == null ? 0 : overflow.overflowId;
    }

    public int getFirstField() 
    {
        return overflow == null ? 0 : overflow.firstField;
    }

    public final boolean hasOverflow() 
    {
        return ((status & RECORD_OVERFLOW) == RECORD_OVERFLOW);
    }

    protected final boolean hasFirstField() 
    {
        return ((status & RECORD_HAS_FIRST_FIELD) == RECORD_HAS_FIRST_FIELD);
    }

    /**
     * Get the deleted state of the record.
     * <p>
     *
     * <BR> MT - single thread required
     **/
    public final boolean isDeleted() 
    {
        return ((status & RECORD_DELETED) == RECORD_DELETED);
    }


    /**
     * return the size of the record header.
     * <p>
     * Calculates the size of the record header, mostly used to allow a
     * reader to skip over the record header and position on the 1st field
     * of the record.
     * <p>
     * This low level routine is performance critical to processing lots of
     * rows, so calls to CompressNumber have been hand inlined.
     *
     * @return The length of the record header.
     **/
    public int size() 
    {
        // account for length of fieldDataLength field stored as a compressed
        // int plus one byte for status.  
        //
        //    int len = CompressedNumber.sizeInt(id) + 1;
        int len = 
          (id <= CompressedNumber.MAX_COMPRESSED_INT_ONE_BYTE) ?
              2 : 
          (id <= CompressedNumber.MAX_COMPRESSED_INT_TWO_BYTES) ?
              3 : 5;

        if ((status & (RECORD_OVERFLOW | RECORD_HAS_FIRST_FIELD)) == 0)
        {
            // usual case, not a record overflow and does not have first field
            len += 
              (numberFields <= CompressedNumber.MAX_COMPRESSED_INT_ONE_BYTE) ?
                  1 : 
              (numberFields <= CompressedNumber.MAX_COMPRESSED_INT_TWO_BYTES) ?
                  2 : 4;
        }
        else if ((status & RECORD_OVERFLOW) == 0)
        {
            // not overflow, and has first field set.
            len += CompressedNumber.sizeInt(numberFields);
            len += CompressedNumber.sizeInt(overflow.firstField);
        }
        else
        {
            // is an overflow field

            len += CompressedNumber.sizeLong(overflow.overflowPage);
            len += CompressedNumber.sizeInt(overflow.overflowId);

            if (hasFirstField())
            {
                len += CompressedNumber.sizeInt(overflow.firstField);
                len += CompressedNumber.sizeInt(numberFields);
            }
        }

        return len;
    }

    /**************************************************************************
     * Public Accessor "Set" Methods of This class:
     **************************************************************************
     */

    /**
     * Set the deleted state of the record.
     * <p>
     * return   1, if delete status from not deleted to deleted
     * return  -1, if delete status from deleted to not deleted
     * return   0, if status unchanged.
     *
     * <BR> MT - single thread required
     **/
    public int setDeleted(boolean deleteTrue) 
    {

        int retCode = 0;

        if (deleteTrue) 
        {
            if (!isDeleted()) 
            {
                // setting the bit from not deleted to deleted
                retCode = 1;
                status |= RECORD_DELETED;
            }
        } 
        else 
        {
            if (isDeleted()) 
            {
                // setting the bit from deleted to not deleted
                retCode = -1;
                status &= ~RECORD_DELETED;
            }
        }

        return(retCode);
    }

    public void setFirstField(int firstField) 
    {
        if (overflow == null) {
            overflow = new OverflowInfo();
        }
        overflow.firstField = firstField;
        status |= RECORD_HAS_FIRST_FIELD;
    }

    public final void setId(int id) 
    {
        this.id = id;
    }

    public void setOverflowDetails(RecordHandle overflowHandle) 
    {
        if (overflow == null) {
            overflow = new OverflowInfo();
        }
        overflow.overflowPage = overflowHandle.getPageNumber();
        overflow.overflowId = overflowHandle.getId();
    }

    public void setOverflowFields(StoredRecordHeader loadFromTarget)
    {
        if (overflow == null) {
            overflow = new OverflowInfo();
        }
        this.status = (byte) (loadFromTarget.status | RECORD_OVERFLOW);
        this.id             = loadFromTarget.id;
        this.numberFields   = loadFromTarget.numberFields;
        overflow.firstField = loadFromTarget.overflow.firstField;
        handle              = null;
    }


    public final void setNumberFields(int numberFields) 
    {
        this.numberFields = numberFields;
    }

    /**************************************************************************
     * Public Methods implmenting read/write of Storable Interface:
     **************************************************************************
     */
    public int write(OutputStream out) 
        throws IOException 
    {
        // check consistency of the status field - this has caught
        // byte writing corruptions in StoredPage in the past.
        if (SanityManager.DEBUG)
        {
            if ((status & ~RECORD_VALID_MASK) != 0)
                SanityManager.THROWASSERT(
                    "Invalid status in StoredRecordHeaader = " + status);
        }

        // write status
        int len = 1;
        out.write(status);

        // write id
        len += CompressedNumber.writeInt(out, id);
        

        // write overflow information for overflow record headers
        if (hasOverflow()) 
        {
            // if overflow bit is set, then write the overflow pointer info.
            len += CompressedNumber.writeLong(out, overflow.overflowPage);
            len += CompressedNumber.writeInt(out, overflow.overflowId);
        }

        // write first field info for long row parts
        if (hasFirstField()) 
        {
            len += CompressedNumber.writeInt(out, overflow.firstField);
        }

        // write number of fields, except in the case of a record header
        // which is solely a pointer to another row portion.
        //
        // see read
        if (!hasOverflow() || hasFirstField())
            len += CompressedNumber.writeInt(out, numberFields);

        return len;
    }

    public void read(java.io.ObjectInput in) 
        throws IOException 
    {

        // read status
        int s = in.read();
        if (s < 0) {
            throw new EOFException();
        }

        status = (byte) s;

        // check consistency of the status field - this has caught
        // byte writing corruptions in StoredPage in the past.
        if (SanityManager.DEBUG)
        {
            if ((s & ~RECORD_VALID_MASK) != 0)
                SanityManager.THROWASSERT(
                    "Invalid status in StoredRecordHeader = " + s);
        }

        // read the record id
        id = CompressedNumber.readInt(in);

        if (hasOverflow() || hasFirstField()) {
            overflow = new OverflowInfo();
        } else {
            overflow = null;
        }

        // initialize the overflow pointer based on status.
        if (hasOverflow()) 
        {
            overflow.overflowPage = CompressedNumber.readLong(in);
            overflow.overflowId   = CompressedNumber.readInt(in);

        } 

        // initialize the 1st field overflow pointer based on status.
        if (hasFirstField()) 
        {
            overflow.firstField = CompressedNumber.readInt(in);
        } 
    
        // In releases prior to 1.3 an overflow record was handled
        // by an overflow header pointing to a complete record on
        // another page. This header had the overflow bit set but not
        // the has first field bit. This header also did not have the
        // number of fields written out, but it can be seen as
        // a header with 0 fields and a first field of 0.
        if (!hasOverflow() || hasFirstField())
            numberFields = CompressedNumber.readInt(in);
        else
            numberFields = 0;

        handle = null;
    }

    private int readOverFlowPage(
    byte[]  data,
    int     offset)
    {
        int int_value = data[offset++];

        if ((int_value & ~0x3f) == 0)
        {
            // test for small case first - assuming this is usual case.
            // this is stored in 2 bytes.

            overflow.overflowPage = ((int_value << 8) | (data[offset] & 0xff));

            return(2);
        }
        else if ((int_value & 0x80) == 0)
        {
            // value is stored in 4 bytes.  only use low 6 bits from 1st byte.

            overflow.overflowPage =
                ((int_value      & 0x3f) << 24) |
                ((data[offset++] & 0xff) << 16) |
                ((data[offset++] & 0xff) <<  8) |
                ((data[offset]   & 0xff)      );

            return(4);

        } 
        else
        {
            // value is stored in 8 bytes.  only use low 6 bits from 1st byte.
            overflow.overflowPage =
                (((long) (int_value      & 0x7f)) << 56) |
                (((long) (data[offset++] & 0xff)) << 48) |
                (((long) (data[offset++] & 0xff)) << 40) |
                (((long) (data[offset++] & 0xff)) << 32) |
                (((long) (data[offset++] & 0xff)) << 24) |
                (((long) (data[offset++] & 0xff)) << 16) |
                (((long) (data[offset++] & 0xff)) <<  8) |
                (((long) (data[offset]   & 0xff))      );

            return(8);
        }
    }
    private int readOverFlowId(
    byte[]  data,
    int     offset)
    {
        int value = data[offset++];

        if ((value & ~0x3f) == 0)
        {
            // length stored in this byte.
            overflow.overflowId = value;

            return(1);
        }
        else if ((value & 0x80) == 0)
        {
            // length is stored in 2 bytes.  only use low 6 bits from 1st byte.

            overflow.overflowId =
                (((value & 0x3f) << 8) | (data[offset] & 0xff));

            return(2);
        }
        else
        {
            // length is stored in 4 bytes.  only use low 7 bits from 1st byte.
            overflow.overflowId =
                ((value          & 0x7f) << 24) |
                ((data[offset++] & 0xff) << 16) |
                ((data[offset++] & 0xff) <<  8) |
                ((data[offset]   & 0xff)      );

            return(4);
        }
    }
    private int readFirstField(
    byte[]  data,
    int     offset)
    {
        int value = data[offset++];

        if ((value & ~0x3f) == 0)
        {
            // length stored in this byte.
            overflow.firstField = value;

            return(1);
        }
        else if ((value & 0x80) == 0)
        {
            // length is stored in 2 bytes.  only use low 6 bits from 1st byte.

            overflow.firstField =
                (((value & 0x3f) << 8) | (data[offset] & 0xff));

            return(2);
        }
        else
        {
            // length is stored in 4 bytes.  only use low 7 bits from 1st byte.
            overflow.firstField =
                ((value          & 0x7f) << 24) |
                ((data[offset++] & 0xff) << 16) |
                ((data[offset++] & 0xff) <<  8) |
                ((data[offset]   & 0xff)      );

            return(4);
        }
    }
    private void readNumberFields(
    byte[]  data,
    int     offset)
    {
        int value = data[offset++];

        if ((value & ~0x3f) == 0)
        {
            // length stored in this byte.
            numberFields = value;
        }
        else if ((value & 0x80) == 0)
        {
            // length is stored in 2 bytes.  only use low 6 bits from 1st byte.

            numberFields = (((value & 0x3f) << 8) | (data[offset] & 0xff));
        }
        else
        {
            // length is stored in 4 bytes.  only use low 7 bits from 1st byte.
            numberFields = 
                ((value          & 0x7f) << 24) |
                ((data[offset++] & 0xff) << 16) |
                ((data[offset++] & 0xff) <<  8) |
                ((data[offset]   & 0xff)      );
        }
    }


    private void read(
    byte[]  data,
    int     offset)
    {
        status = data[offset++];

        int value = data[offset++];

        if ((value & ~0x3f) == 0)
        {
            // value stored in this byte.
            id = value;
        }
        else if ((value & 0x80) == 0)
        {
            // value is stored in 2 bytes.  only use low 6 bits from 1st byte.

            id = (((value & 0x3f) << 8) | (data[offset++] & 0xff));
        }
        else
        {
            // value is stored in 4 bytes.  only use low 7 bits from 1st byte.
            id = 
                ((value          & 0x7f) << 24) |
                ((data[offset++] & 0xff) << 16) |
                ((data[offset++] & 0xff) <<  8) |
                ((data[offset++] & 0xff)      );
        }

        if ((status & (RECORD_OVERFLOW | RECORD_HAS_FIRST_FIELD)) == 0)
        {
            // usual case, not a record overflow and does not have first field
            overflow = null;

            readNumberFields(data, offset);
        }
        else if ((status & RECORD_OVERFLOW) == 0)
        {
            // not overflow, and has first field set.
            overflow = new OverflowInfo();

            offset += readFirstField(data, offset);

            readNumberFields(data, offset);
        }
        else
        {
            // is an overflow field
            overflow = new OverflowInfo();

            offset += readOverFlowPage(data, offset);
            offset += readOverFlowId(data, offset);

            if (hasFirstField())
            {
                offset += readFirstField(data, offset);
                readNumberFields(data, offset);
            }
            else
            {
                numberFields = 0;
            }
        }

        handle = null;

        return;
    }

    /**
     * Return length on disk of the record id portion of the record header
     *
     * Record id is part of the record header and is stored in an internal
     * compressed format.  The length of this format depends on the value
     * of the record id.
     *
     * @return length of encoded record id on disk.
     **/
    public static final int getStoredSizeRecordId(int record_id)
    {
        return(CompressedNumber.sizeInt(record_id));
    }

    public String toString()
    {
        if (SanityManager.DEBUG)
        {
            String str = "recordHeader: Id=" + getId();
            
            str += "\n  isDeleted     = " + isDeleted();
            str += "\n  hasOverflow   = " + hasOverflow();
            str += "\n  hasFirstField = " + hasFirstField();
            str += "\n  numberFields  = " + getNumberFields();
            str += "\n  firstField    = " + getFirstField();
            str += "\n  overflowPage  = " + getOverflowPage();
            str += "\n  overflowId    = " + getOverflowId();
            str += "\n  header length = " + size();

            return str;
        }
        else
        {
            return null;
        }
    }
}
