/*

   Derby - Class org.apache.derby.client.net.ConnectionReplyInterface

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package org.apache.derby.client.net;

import org.apache.derby.client.am.ConnectionCallbackInterface;
import org.apache.derby.client.am.DisconnectException;

interface ConnectionReplyInterface {
    public void readCommitSubstitute(ConnectionCallbackInterface connection) throws DisconnectException;

    public void readLocalCommit(ConnectionCallbackInterface connection) throws DisconnectException;

    public void readLocalRollback(ConnectionCallbackInterface connection) throws DisconnectException;

    public void readLocalXAStart(ConnectionCallbackInterface connection) throws DisconnectException;

    public void readLocalXACommit(ConnectionCallbackInterface connection) throws DisconnectException;

    public void readLocalXARollback(ConnectionCallbackInterface connection) throws DisconnectException;
}
