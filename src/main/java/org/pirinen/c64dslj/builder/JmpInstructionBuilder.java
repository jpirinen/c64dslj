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

import org.pirinen.c64dslj.model.Absolute;
import org.pirinen.c64dslj.model.AbsoluteLabelOperand;
import org.pirinen.c64dslj.model.Indirect;
import org.pirinen.c64dslj.model.Instruction;
import org.pirinen.c64dslj.model.Opcode;
import org.pirinen.c64dslj.model.TwoByteOperand;

public class JmpInstructionBuilder extends InstructionBuilder implements AbsIndLabelBuilder {

    public JmpInstructionBuilder(Opcode opcode) {
        super(opcode);
    }

    @Override
    public Instruction indirect(int value) {
        return new Instruction(getCommand(Indirect.class), new TwoByteOperand(value));
    }
    
    @Override
    public Instruction indirect(DataType<Integer> value) {
        return indirect(value.getValue());
    }

    @Override
    public Instruction absolute(int value) {
        return new Instruction(getCommand(Absolute.class), new TwoByteOperand(value));
    }

    @Override
    public Instruction absolute(String labelName) {
        return new Instruction(getCommand(Absolute.class), new AbsoluteLabelOperand(labelName), true);
    }

    @Override
    public Instruction absolute(DataType<Integer> value) {
        return new Instruction(getCommand(Absolute.class), new TwoByteOperand(value.getValue()));
    }

}
