/*

   Derby - Class org.apache.derby.iapi.sql.execute.CursorActivation

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

package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.sql.Activation;

/**
 * CursorActivation includes an additional method used on cursors.
 *
 */
public interface CursorActivation extends Activation {

	/**
	 * Returns the target result set for this activation,
	 * so that the current base row can be determined.
	 *
	 * @return the target ResultSet of this activation.
	 */
	CursorResultSet getTargetResultSet();

	/**
	 * Returns the cursor result set for this activation,
	 * so that the current row can be re-qualified, and
	 * so that the current row location can be determined.
	 */
	CursorResultSet getCursorResultSet();
}
