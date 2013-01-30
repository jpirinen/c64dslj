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


public abstract class AddressingMode {
    private int opcode;
    private int operandLength;
    private boolean isRelative;

    public AddressingMode(int opcode) {
        this(opcode, 0, false);
    }

    public AddressingMode(int opcode, boolean isRelative) {
        this(opcode, 0, isRelative);
    }

    public AddressingMode(int opcode, int operandLength) {
        this(opcode, operandLength, false);
    }

    public AddressingMode(int opcode, int operandLength, boolean isRelative) {
        this.opcode = opcode;
        this.operandLength = operandLength;
        this.isRelative = isRelative;
    }

    public int getOpcode() {
        return opcode;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + opcode;
    }

    public int getOperandLength() {
        return operandLength;
    }

    public boolean isRelative() {
        return isRelative;
    }
}
