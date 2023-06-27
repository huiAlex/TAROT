/*

   Derby - Class org.apache.derby.impl.store.access.UTF

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

package org.apache.derby.impl.store.access;

import org.apache.derby.shared.common.sanity.SanityManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.UserType;

/**
  A class that is used to store java.lang.Strings and provide
  ordering capability.

  @see org.apache.derby.iapi.services.io.FormatIdOutputStream
 **/

public class UTF extends UserType
{
    public UTF()
    {
    }

    public UTF(String value)
    {
        super(value);
    }

    /*
     * The following methods implement the Orderable protocol.
     */

    public int compare(DataValueDescriptor other)
    {
        if (SanityManager.DEBUG)
            SanityManager.ASSERT(other instanceof UTF);

        UTF arg = (UTF) other;

		return ((String) getObject()).compareTo((String) arg.getObject());

    }
}
