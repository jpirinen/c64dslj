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

import org.pirinen.c64dslj.model.AddressingMode;
import org.pirinen.c64dslj.model.Command;
import org.pirinen.c64dslj.model.InstructionSet;
import org.pirinen.c64dslj.model.Opcode;

public abstract class InstructionBuilder {
	private Opcode opcode;

	public InstructionBuilder(Opcode opcode) {
		this.opcode = opcode;
	}

	protected Command getCommand(Class<? extends AddressingMode> modeClass) {
		return InstructionSet.getInstance().get(opcode.name(), modeClass);
	}
}
