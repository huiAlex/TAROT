/*

   Derby - Class org.apache.derbyTesting.functionTests.util.ShortHolder

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

package org.apache.derbyTesting.functionTests.util;

import java.io.Serializable;

/**
 * This class holds a short.  This class exists for basic testing of
 * user-defined types in JSQL.
 *
 */

public class ShortHolder implements Serializable
{
	short		value;

	/**
	 * Constructor for an ShortHolder
	 *
	 * @param value		The value of the short to store in the new object
	 */

	public ShortHolder(short value)
	{
		this.value = value;
	}

	/**
	 * Get the short value out of this object
	 *
	 * @return	The value of the short in this object
	 */

	public short getValue()
	{
		return value;
	}
}
