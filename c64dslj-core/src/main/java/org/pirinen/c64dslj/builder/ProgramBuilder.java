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

import org.pirinen.c64dslj.model.Command;
import org.pirinen.c64dslj.model.EmptyOperand;
import org.pirinen.c64dslj.model.Instruction;
import org.pirinen.c64dslj.model.Label;
import org.pirinen.c64dslj.model.Program;

public class ProgramBuilder {
    
    private Program p;

    public ProgramBuilder(Program program) {
        p = program;
    }
    
    public ProgramBuilder b(DataType<Byte> value)  {
        return oneByte(value.getValue());
    }
    
    public ProgramBuilder oneByte(DataType<Byte> value)  {
        return oneByte(value.getValue());
    }
    
    public ProgramBuilder b(int value)  {
        return oneByte(value);
    }

    public ProgramBuilder oneByte(int value)  {
        return instruction(new Instruction(new Command(value), new EmptyOperand()));
    }
    
    public ProgramBuilder i(Instruction i)  {
        return instruction(i);
    }

    public ProgramBuilder label(String name) {
        return label(Label.name(name));
    }

    public ProgramBuilder l(String name)  {
        return label(Label.name(name));
    }

    public ProgramBuilder data(DataType<Integer> address, Data data)  {
        return data(address.getValue(), data);
    }
    
    public ProgramBuilder d(DataType<Integer> address, Data data)  {
        return data(address.getValue(), data);
    }
    
    public ProgramBuilder d(int address, Data data)  {
        return data(address, data);
    }

    public ProgramBuilder data(int address, Data data)  {
        p.add(address, data);
        return this;
    }

    public ProgramBuilder instruction(Instruction i)  {
        p.add(i);
        return this;
    }

    public ProgramBuilder label(Label l) {
        p.add(l);
        return this;
    }

    public Program build() {
        return p;
    }

    public Program build(DataType<Integer> startingAddress) {
        return build(startingAddress.getValue());
    }
    
    public Program build(int startingAddress) {
        p.setStartingAddress(startingAddress);
        return p;
    }

}
