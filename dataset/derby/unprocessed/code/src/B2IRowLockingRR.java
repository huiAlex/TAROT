/*

   Derby - Class org.apache.derby.impl.store.access.btree.index.B2IRowLockingRR

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

package org.apache.derby.impl.store.access.btree.index;

import org.apache.derby.shared.common.sanity.SanityManager;

import org.apache.derby.iapi.error.StandardException; 

import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.types.RowLocation;

import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;

import org.apache.derby.iapi.types.DataValueDescriptor;

import org.apache.derby.impl.store.access.btree.BTree;
import org.apache.derby.impl.store.access.btree.BTreeLockingPolicy;
import org.apache.derby.impl.store.access.btree.BTreeRowPosition;
import org.apache.derby.impl.store.access.btree.OpenBTree;

/**

**/

class B2IRowLockingRR extends B2IRowLocking3 implements BTreeLockingPolicy
{

    /**************************************************************************
     * Constructors for This class:
     **************************************************************************
     */
    B2IRowLockingRR(
    Transaction             rawtran,
    int                     lock_level,
    LockingPolicy           locking_policy,
    ConglomerateController  base_cc,
    OpenBTree               open_btree)
    {
        super(rawtran, lock_level, locking_policy, base_cc, open_btree);
    }

    /**************************************************************************
     * Public Methods of This class:
     **************************************************************************
     */

    /**
     * Lock a row as part of doing the scan.
     * <p>
     * Lock the row at the given slot (or the previous row if slot is 0).
     * <p>
     * If this routine returns true all locks were acquired while maintaining
     * the latch on leaf.  If this routine returns false, locks may or may
     * not have been acquired, and the routine should be called again after
     * the client has researched the tree to reget the latch on the 
     * appropriate page.
     *
	 * @return Whether locks were acquired without releasing latch on leaf.
     *
     * @param open_btree        The open_btree to associate latches with - 
     *                          used if routine has to scan backward.
     * @param pos               The position of the row to lock.
     * @param lock_template     A scratch area to use to read in rows.
     * @param previous_key_lock Is this a previous key lock call?
     * @param forUpdate         Is the scan for update or for read only.
     *
	 * @exception  StandardException  Standard exception policy.
     **/
    public boolean lockScanRow(
    OpenBTree               open_btree,
    BTreeRowPosition        pos,
    FetchDescriptor         lock_fetch_desc,
    DataValueDescriptor[]   lock_template,
    RowLocation             lock_row_loc,
    boolean                 previous_key_lock,
    boolean                 forUpdate,
    int                     lock_operation)
		throws StandardException
    {
        // don't request row lock if this a previous key lock request, previous
        // key lock is not required in isolation level 2.
        return(
            _lockScanRow(
                open_btree,
                pos,
                !previous_key_lock, // request row lock iff not prev key lock 
                lock_fetch_desc, lock_template, lock_row_loc,
                previous_key_lock,
                forUpdate,
                lock_operation));
    }

    /**
     * Unlock a record after it has been locked for read.
     * <p>
     * In repeatable read only unlock records which "did not qualify".  For
     * example in a query like "select * from foo where a = 1" on a table
     * with no index it is only necessary to hold locks on rows where a=1, but
     * in the process of finding those rows the system will get locks on other
     * rows to verify they are committed before applying the qualifier.  Those
     * locks can be released under repeatable read isolation.
     * <p>
     * if it is forUpdate then get S lock and release U lock, else there is 
     * nothing to do in serializable - we keep the S locks until end of 
     * transaction.
     *
     * @param forUpdate         Is the scan for update or for read only.
     *
     **/
    public void unlockScanRecordAfterRead(
    BTreeRowPosition        pos,
    boolean                 forUpdate)
		throws StandardException
    {
        if (!pos.current_rh_qualified)
        {
            if (SanityManager.DEBUG)
            {
                SanityManager.ASSERT(
                    pos.current_lock_row_loc != null , "row_loc is null");
            }

            base_cc.unlockRowAfterRead(
                pos.current_lock_row_loc, forUpdate, pos.current_rh_qualified);
        }
    }
}
