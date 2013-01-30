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

public class Command {

    private String opcode;
    private Integer opcodeValue;
    private AddressingMode addressingMode;

    
    public Command(Integer opcodeValue) {
        this("???", opcodeValue, new None(opcodeValue));
    }
    
    public Command(String opcode, Integer opcodeValue, AddressingMode addressingMode) {
        this.opcode = opcode;
        this.opcodeValue = opcodeValue;
        this.addressingMode = addressingMode;
    }

    public String getOpcode() {
        return opcode;
    }

    public Integer getOpcodeValue() {
        return opcodeValue;
    }

    public AddressingMode getAddressingMode() {
        return addressingMode;
    }

    @Override
    public String toString() {
        return opcode + ":" + addressingMode.getClass().getSimpleName() + ":" + opcodeValue;
    }
}
