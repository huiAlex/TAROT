/*

   Derby - Class org.apache.derby.impl.store.access.btree.index.D_B2IController

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

import org.apache.derby.impl.store.access.btree.D_BTreeController;

/**

Debugging class used to print debug information about a B2I.  

Code here can be used in SANE development builds but the class is not necessary
for a release so does not add footprint to a customer release.
See the DiagnosticableGeneric interface for more information.

Note that all the real work currently is inherited from 
it's parent, but this class needs to exist as the diagnostic interface
requires a class names D_XXX to provide information about XXX.

**/

public class D_B2IController extends D_BTreeController
{
}
