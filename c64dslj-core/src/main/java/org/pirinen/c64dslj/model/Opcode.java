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


public enum Opcode {
    
    ADC("Add Memory to Accumulator with Carry", 0x69, 0x65, 0x75, 0x61, 0x71, 0x6D, 0x7D, 0x79),
    AND("'AND' Memory with Accumulator", 0x29, 0x25, 0x35, 0x21, 0x31, 0x2D, 0x3D, 0x39),
    ASL("Shift Left One Bit", 0x0A, 0x06, 0x16, 0x0E, 0x1E),
    BCC("Branch on Carry Clear", 0x90),
    BCS("Branch on Carry Set", 0xB0),
    BEQ("Branch on Result Zero", 0xF0),
    BIT("Test Bits in Memory with Accumulator", 0x24, 0x2C),
    BMI("Branch on Result Minus", 0x30),
    BNE("Branch on Result not Zero", 0xD0),
    BPL("Branch on Result Plus", 0x10),
    BRK("Force Break", 0x00),
    BVC("Branch on Overflow Clear", 0x50),
    BVS("Branch on Overflow Set", 0x70),
    CLC("Clear Carry Flag", 0x18),
    CLD("Clear Decimal Mode", 0xD8),
    CLI("Clear interrupt Disable Bit", 0x58),
    CLV("Clear Overflow Flag", 0xB8),
    CMP("Compare Memory and Accumulator", 0xC9, 0xC5, 0xD5, 0xC1, 0xD1, 0xCD, 0xDD, 0xD9),
    CPX("Compare Memory and Index X", 0xE0, 0xE4, 0xEC),
    CPY("Compare Memory and Index Y", 0xC0, 0xC4, 0xCC),
    DEC("Decrement Memory by One", 0xC6, 0xD6, 0xCE, 0xDE),
    ORA("'OR' Memory with Accumulator", 0x09, 0x05, 0x15, 0x01, 0x11, 0x0D, 0x1D),
    PHA("Push Accumulator on Stack", 0x48),
    PHP("Push Processor Status on Stack", 0x08),
    PLA("Pull Accumulator from Stack", 0x68),
    PLP("Pull Processor Status from Stack", 0x28),
    ROL("Rotate One Bit Left", 0x2A, 0x26, 0x36, 0x2E, 0x3E),
    ROR("Rotate One Bit Right", 0x6A, 0x66, 0x76, 0x6E, 0x7E),
    RTI("Return from Interrupt", 0x40),
    RTS("Return from Subroutine", 0x60),
    SBC("Subtract Memory from Accumulator with Borrow", 0xE9, 0xE5, 0xF5, 0xE1, 0xF1, 0xED, 0xFD, 0xF9),
    SEC("Set Carry Flag", 0x38),
    SED("Set Decimal Mode", 0xF8),
    SEI("Set Interrupt Disable Status", 0x78),
    STA("Store Accumulator in Memory", 0x85, 0x95, 0x81, 0x91, 0x8D, 0x9D, 0x99),
    STX("Store Index X in Memory", 0x86, 0x96, 0x8E),
    STY("Store Index Y in Memory", 0x84, 0x94, 0x8C),
    TAX("Transfer Accumulator to Index X", 0xAA),
    TAY("Transfer Accumulator to Index Y", 0xA8),
    TSX("Transfer Stack Pointer to Index X", 0xBA),
    TXA("Transfer Index X to Accumulator", 0x8A),
    TXS("Transfer Index X to Stack Pointer", 0x9A),
    TYA("Transfer Index Y to Accumulator", 0x98),
    DEX("Decrement Index X by One", 0xCA),
    DEY("Decrement Index Y by One", 0x88),
    EOR("'Exclusive-Or' Memory with Accumulator", 0x49, 0x45, 0x55, 0x41, 0x51, 0x4D, 0x5D, 0x59),
    INC("Increment Memory by One", 0xE6, 0xF6, 0xEE, 0xFE),
    INX("Increment Index X by One", 0xE8),
    INY("Increment Index Y by One", 0xC8),
    JMP("Jump to New Location", 0x4C, 0x6C),
    JSR("Jump to New Location Saving Return Address", 0x20),
    LDA("Load Accumulator with Memory", 0xA9, 0xA5, 0xB5, 0xA1, 0xB1, 0xAD, 0xBD, 0xB9),
    LDX("Load Index X with Memory", 0xA2, 0xA6, 0xB6, 0xAE, 0xBE),
    LDY("Load Index Y with Memory", 0xA0, 0xA4, 0xB4, 0xAC, 0xBC),
    LSR("Shift Right One Bit", 0x4A, 0x46, 0x56, 0x4E, 0x5E),
    NOP("No Operation", 0xEA);

    private AddressingModeHelper amh = AddressingModeHelper.getInstance();
    private InstructionSet is = InstructionSet.getInstance();
    private String desc;

    private Opcode(String desc, int... opcodes) {
        this.desc = desc;
        for (int opcode : opcodes) {
            AddressingMode mode = amh.get(opcode);
            is.add(new Command(name(), new Integer(opcode), mode));

        }
    }

    public String getDescription() {
        return desc;
    }

}
