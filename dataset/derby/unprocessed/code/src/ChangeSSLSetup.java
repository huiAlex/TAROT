/*
 *
 * Derby - Class org.apache.derbyTesting.junit.ChageSSLSetup
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the License.
 */
package org.apache.derbyTesting.junit;

import junit.framework.Test;

/**
 * A decorator that changes the default ssl mode
 * for the current configuration. Its tearDown method restores
 * the previous configuration.
 * 
 */
final class ChangeSSLSetup extends ChangeConfigurationSetup {

    private final String ssl;

    ChangeSSLSetup(Test test, String ssl)
    {
        super(test);
        this.ssl = ssl;
    }
    
    TestConfiguration getNewConfiguration(TestConfiguration old)
    {
        return new TestConfiguration(old, ssl);
    }
}
