/*

   Derby - Class org.apache.derby.iapi.store.raw.ScannedTransactionHandle

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

package org.apache.derby.iapi.store.raw;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.store.raw.log.LogInstant;
import java.io.InputStream;

public interface ScannedTransactionHandle
{
	Loggable getNextRecord()
		 throws StandardException;

    InputStream getOptionalData()
		 throws StandardException;

    LogInstant getThisInstant()
		 throws StandardException;

    LogInstant getLastInstant()
		 throws StandardException;

    LogInstant getFirstInstant()
		 throws StandardException;

    void close();
}
