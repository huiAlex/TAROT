/*

   Derby - Class org.apache.derby.impl.store.access.btree.index.B2ITableLocking3

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

import org.apache.derby.iapi.store.access.ConglomerateController;

import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;

import org.apache.derby.impl.store.access.btree.OpenBTree;

/**

**/

class B2ITableLocking3 extends B2INoLocking
{
    /**************************************************************************
     * Constructors for This class:
     **************************************************************************
     */
    public B2ITableLocking3(
    Transaction             rawtran,
    int                     lock_level,
    LockingPolicy           locking_policy,
    ConglomerateController  base_cc,
    OpenBTree               open_btree)
    {
    }

    private B2ITableLocking3()
    {
    }
}
