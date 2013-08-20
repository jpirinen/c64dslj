/* Copyright 2013 Jukka Pirinen

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.pirinen.c64dslj.builder;

import org.pirinen.c64dslj.model.Label;
import org.pirinen.c64dslj.model.Program;

public class ReferenceDataType implements DataType<Byte> {

	private Program program;
	private String labelName;
	private boolean msb;

	public ReferenceDataType(Program program, String label, boolean msb) {
		this.program = program;
		this.labelName = label;
		this.msb = msb;
	}

	@Override
	public Byte getValue() {
		Label label = program.getLabel(labelName);
		int address = label.getPosition() + program.getStartingAddress();
		if (msb) {
			return (byte) ((address & 0xff00) >> 8);
		}
		return (byte) (address & 0xff);
	}

}
