/*

   Derby - Class org.apache.derby.iapi.services.classfile.CONSTANT_Double_info

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

package org.apache.derby.iapi.services.classfile;

import org.apache.derby.iapi.services.classfile.VMDescriptor;

import java.io.IOException;

/** Double Constant - page 97 - Section 4.4.5 */
final class CONSTANT_Double_info extends ConstantPoolEntry {
	private final double value;

	CONSTANT_Double_info(double value) {
		super(VMDescriptor.CONSTANT_Double);
		doubleSlot = true; //See page 98.
		this.value = value;
	}

	public int hashCode() {
		return (int) value;
	}

	int classFileSize() {
		// 1 (tag) + 8 (double length)
		return 1 + 8;
	}
	void put(ClassFormatOutput out) throws IOException {
		super.put(out);
		out.writeDouble(value);
	}

	public boolean equals(Object other) {

		// check it is the right type
		if (other instanceof CONSTANT_Double_info) {
		
			return value == ((CONSTANT_Double_info) other).value;
		}

		return false;
	}
}


