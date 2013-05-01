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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.pirinen.c64dslj.builder.AbsIndBuilder;
import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.ImmZpAbsBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpyAbsAbyBuilder;
import org.pirinen.c64dslj.builder.ImpZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.JmpInstructionBuilder;
import org.pirinen.c64dslj.builder.JsrInstructionBuilder;
import org.pirinen.c64dslj.builder.MultiBuilder;
import org.pirinen.c64dslj.builder.RelativeBuilder;
import org.pirinen.c64dslj.builder.RelativeInstructionBuilder;
import org.pirinen.c64dslj.builder.ZpAbsBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsBuilder;
import org.pirinen.c64dslj.builder.ZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ZpZpyAbsBuilder;

public class Instruction {
    // Logical and arithmetic instruction
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> ORA = new MultiBuilder(Opcode.ORA);
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> AND = new MultiBuilder(Opcode.AND);
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> EOR = new MultiBuilder(Opcode.EOR);
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> ADC = new MultiBuilder(Opcode.ADC);
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> SBC = new MultiBuilder(Opcode.SBC);
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> CMP = new MultiBuilder(Opcode.CMP);
    public static final ImmZpAbsBuilder<Instruction> CPX = new MultiBuilder(Opcode.CPX);
    public static final ImmZpAbsBuilder<Instruction> CPY = new MultiBuilder(Opcode.CPY);
    public static final ZpZpxAbsAbxBuilder<Instruction> DEC = new MultiBuilder(Opcode.DEC);
    public static final Instruction DEX = new MultiBuilder(Opcode.DEX).implied();
    public static final Instruction DEY = new MultiBuilder(Opcode.DEY).implied();
    public static final ZpZpxAbsAbxBuilder<Instruction> INC = new MultiBuilder(Opcode.INC);
    public static final Instruction INX = new MultiBuilder(Opcode.INX).implied();
    public static final Instruction INY = new MultiBuilder(Opcode.INY).implied();
    public static final ImpZpZpxAbsAbxBuilder<Instruction> ASL = new MultiBuilder(Opcode.ASL);
    public static final ImpZpZpxAbsAbxBuilder<Instruction> ROL = new MultiBuilder(Opcode.ROL);
    public static final ImpZpZpxAbsAbxBuilder<Instruction> LSR = new MultiBuilder(Opcode.LSR);
    public static final ImpZpZpxAbsAbxBuilder<Instruction> ROR = new MultiBuilder(Opcode.ROR);
    // alternative implied versions
    public static final Instruction ASL_ = new MultiBuilder(Opcode.ASL).implied();
    public static final Instruction ROL_ = new MultiBuilder(Opcode.ROL).implied();
    public static final Instruction LSR_ = new MultiBuilder(Opcode.LSR).implied();
    public static final Instruction ROR_ = new MultiBuilder(Opcode.ROR).implied();
    // Move instructions
    public static final ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> LDA = new MultiBuilder(Opcode.LDA);
    public static final ZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> STA = new MultiBuilder(Opcode.STA);
    public static final ImmZpZpyAbsAbyBuilder<Instruction> LDX = new MultiBuilder(Opcode.LDX);
    public static final ZpZpyAbsBuilder<Instruction> STX = new MultiBuilder(Opcode.STX);
    public static final ImmZpZpxAbsAbxBuilder<Instruction> LDY = new MultiBuilder(Opcode.LDY);
    public static final ZpZpxAbsBuilder<Instruction> STY = new MultiBuilder(Opcode.STY);
    public static final Instruction TAX = new MultiBuilder(Opcode.TAX).implied();
    public static final Instruction TXA = new MultiBuilder(Opcode.TXA).implied();
    public static final Instruction TAY = new MultiBuilder(Opcode.TAY).implied();
    public static final Instruction TYA = new MultiBuilder(Opcode.TYA).implied();
    public static final Instruction TSX = new MultiBuilder(Opcode.TSX).implied();
    public static final Instruction TXS = new MultiBuilder(Opcode.TXS).implied();
    public static final Instruction PLA = new MultiBuilder(Opcode.PLA).implied();
    public static final Instruction PHA = new MultiBuilder(Opcode.PHA).implied();
    public static final Instruction PLP = new MultiBuilder(Opcode.PLP).implied();
    public static final Instruction PHP = new MultiBuilder(Opcode.PHP).implied();
    // jump/branch/flag instructions
    public static final RelativeBuilder<Instruction> BPL = new RelativeInstructionBuilder(Opcode.BPL);
    public static final RelativeBuilder<Instruction> BMI = new RelativeInstructionBuilder(Opcode.BMI);
    public static final RelativeBuilder<Instruction> BVC = new RelativeInstructionBuilder(Opcode.BVC);
    public static final RelativeBuilder<Instruction> BVS = new RelativeInstructionBuilder(Opcode.BVS);
    public static final RelativeBuilder<Instruction> BCC = new RelativeInstructionBuilder(Opcode.BCC);
    public static final RelativeBuilder<Instruction> BCS = new RelativeInstructionBuilder(Opcode.BCS);
    public static final RelativeBuilder<Instruction> BNE = new RelativeInstructionBuilder(Opcode.BNE);
    public static final RelativeBuilder<Instruction> BEQ = new RelativeInstructionBuilder(Opcode.BEQ);
    public static final Instruction BRK = new MultiBuilder(Opcode.BRK).implied();
    public static final Instruction RTI = new MultiBuilder(Opcode.RTI).implied();
    public static final AbsoluteBuilder<Instruction> JSR = new JsrInstructionBuilder(Opcode.JSR);
    public static final Instruction RTS = new MultiBuilder(Opcode.RTS).implied();
    public static final AbsIndBuilder<Instruction> JMP = new JmpInstructionBuilder(Opcode.JMP);
    public static final ZpAbsBuilder<Instruction> BIT = new MultiBuilder(Opcode.BIT);
    public static final Instruction CLC = new MultiBuilder(Opcode.CLC).implied();
    public static final Instruction SEC = new MultiBuilder(Opcode.SEC).implied();
    public static final Instruction CLD = new MultiBuilder(Opcode.CLD).implied();
    public static final Instruction SED = new MultiBuilder(Opcode.SED).implied();
    public static final Instruction CLI = new MultiBuilder(Opcode.CLI).implied();
    public static final Instruction SEI = new MultiBuilder(Opcode.SEI).implied();
    public static final Instruction CLV = new MultiBuilder(Opcode.CLV).implied();
    public static final Instruction NOP = new MultiBuilder(Opcode.NOP).implied();

    private Command command;
    private Operand operand;
    private boolean containsAbsoluteLabel;

    public Instruction(Command command, Operand operand) {
        this(command, operand, false);
    }

    public Instruction(Command command, Operand operand, boolean containsAbsoluteLabel) {
        this.command = command;
        this.operand = operand;
        this.containsAbsoluteLabel = containsAbsoluteLabel;
    }

    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(3);
        out.write(command.getOpcodeValue());
        out.write(operand.getData());
        return out.toByteArray();
    }
    
    public int getLength() {
        return operand.getLength()+1;
    }

    public Operand getOperand() {
        return operand;
    }

    public Command getCommand() {
        return command;
    }

    public boolean containsAbsoluteLabel() {
        return containsAbsoluteLabel;
    }

    void setStartingAddress(int startingAddress) {
        ((AbsoluteLabelOperand) operand).setStartingAddress(startingAddress);
    }

    @Override
    public String toString() {
        return command.toString() + " " + operand.toString();
    }
}
