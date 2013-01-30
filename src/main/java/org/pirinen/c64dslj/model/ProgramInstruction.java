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

import java.io.IOException;

public class ProgramInstruction {
    private int position;
    private Instruction instruction;

    ProgramInstruction(int position, Instruction instruction) {
        this.position = position;
        this.instruction = instruction;
    }

    public int getPosition() {
        return position;
    }

    public int getNextPosition() throws IOException {
        return position + instruction.getLength();
    }

    public Instruction getInstruction() {
        return instruction;
    }

    Instruction getInstruction(int startingAddress) {
        if (instruction.containsAbsoluteLabel()) {
            instruction.setStartingAddress(startingAddress);
        }
        return instruction;
    }

    public int getAbsoluteAddress(int startingAddress) {
        return startingAddress + position;
    }

}
