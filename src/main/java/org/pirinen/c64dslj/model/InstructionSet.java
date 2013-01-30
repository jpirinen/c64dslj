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

package org.pirinen.c64dslj.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InstructionSet {
    private static InstructionSet instance = new InstructionSet();

    private Map<Integer, Command> instructions = new HashMap<Integer, Command>();

    public static InstructionSet getInstance() {
        return instance;
    }

    private InstructionSet() {

    }

    public void add(Command i) {
        instructions.put(i.getOpcodeValue(), i);
    }

    public Command get(int value) {
        return instructions.get(value);
    }

    public Command get(String opcode, Class<? extends AddressingMode> modeClass) {
        for (Entry<Integer, Command> entry : instructions.entrySet()) {
            Command cmd = entry.getValue();
            if (cmd.getAddressingMode().getClass().equals(modeClass) && cmd.getOpcode().equals(opcode)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "6502/6510 processor instruction set";
    }
}
