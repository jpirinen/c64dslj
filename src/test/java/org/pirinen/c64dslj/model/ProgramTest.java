package org.pirinen.c64dslj.model;

import static org.pirinen.c64dslj.model.Instruction.*;
import static org.pirinen.c64dslj.builder.ByteDec.*;
import static org.pirinen.c64dslj.builder.HexStringBuilder.hex;
import static org.pirinen.c64dslj.model.Program.msb;
import static org.pirinen.c64dslj.model.Program.lsb;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class ProgramTest {
    // Please note, these examples are for testing the dsl and they 
    // do not necessarily make sense as c64 machine language programs
    
    @Test
    public void testAbsoluteLabel() throws Exception {
        Program p = Program.with()
          .b(20)
          .label("index")
          .b(10)
          .i(LDA.immediate(_0))
          .i(STA.absolute("index"))
          .i(RTS).build(4096);
        assertProgramData(p, 20, 10, 169, 0, 141, 1, 16, 96);
    }
    
    @Test
    public void testDoubleAbsoluteLabel() throws Exception {
        Program p = Program.with()
          .b(20)
          .label("index")
          .b(10)
          .i(LDA.immediate(_0))
          .i(STA.absolute("index"))
          .i(INC.absolute("index"))
          .i(RTS).build(4096);
        assertProgramData(p, 20, 10, 169, 0, 141, 1, 16, 238, 1, 16, 96);
    }
    
    @Test
    public void testAbsoluteLabelForwardRef() throws Exception {
        Program p = Program.with()
          .i(LDA.immediate(_0))
          .i(STA.absolute("index"))
          .i(RTS)
           .label("index")
          .b(10)
          .build(4096);
        assertProgramData(p, 169, 0, 141, 6, 16, 96, 10);
    }
    
    @Test
    public void testAbsoluteLabelDoubleForwardRef() throws Exception {
        Program p = Program.with()
          .i(LDA.immediate(_0))
          .i(STA.absolute("index"))
          .i(INC.absolute("index"))
          .i(RTS)
           .label("index")
          .b(10)
          .build(4096);
        assertProgramData(p, 169, 0, 141, 9, 16, 238, 9, 16, 96, 10);
    }
    
    @Test
    public void testMsbLsb() throws Exception {
        Program p = Program.with()
          .l("start")
          .i(LDA.immediate(_0))
          .i(STA.absolute(53280))
          .i(LDA.immediate(msb("start")))
          .i(LDA.immediate(lsb("start")))
          .i(RTS).build(8200);
        assertProgramData(p, 169, 0, 141, 32, 208, 169, 32, 169, 8, 96);
    }
    
    @Test
    public void testSimpleProgram() throws Exception {
        Program p = Program.with()
          .i(LDA.immediate(_0))
          .i(STA.absolute(53280))
          .i(RTS).build();
        assertProgramData(p, 169, 0, 141, 32, 208, 96);
    }
    
    @Test
    public void testLabelWithBne() throws Exception {
        Program p = Program.with()
          .i(LDA.immediate(_0))
          .i(TAX)
          .label("loop")
          .i(INC.absolute_X(1024))
          .i(INX)
          .i(BNE.label("loop"))
          .i(RTS)
          .build(4096);
        assertProgramData(p, 169, 0, 0xAA, 0xFE, 0x00, 4, 0xE8, 0xD0, 250, 96);
    }
    
    @Test
    public void testForwardLabel() throws Exception {
        Program p = Program.with()
                .i(LDA.immediate(_4))
                .i(STA.absolute(53280))
                .i(BNE.label("loop"))
                .i(STA.absolute(53281))
                .label("loop")
                .i(RTS)
                .build(4096);
        assertProgramData(p, 169, 4, 141, 32, 208, 0xD0, 3, 141, 33, 208, 96);
    }
    
    @Test
    public void testJmpBackward() throws Exception {
        Program p = Program.with()
                .label("loop")
                .i(INC.absolute(53280))
                .i(JMP.absolute("loop"))
                .build(4096);
        assertProgramData(p, 0xEE, 32, 208, 0x4C, 0, 16);
    }
    
    @Test
    public void testJmpForward() throws Exception {
        Program p = Program.with()
                .i(LDA.immediate(_0))
                .i(STA.absolute(53280))
                .i(JMP.absolute("loop"))
                .i(STA.absolute(53281))
                .label("loop")
                .i(RTS)
                .build(4096);
        assertProgramData(p, 169, 0, 141, 32, 208, 0x4C, 11, 16, 141, 33, 208, 96);
    }
    
    @Test
    public void testJmpAsFirstInstruction() throws Exception {
        Program p = Program.with()
                .i(JMP.absolute("subroutine1"))
                .i(RTS)
                .label("subroutine1")
                .i(LDY.immediate(_3))
                .i(STY.absolute(53280))
                .i(RTS)
                .build(16384);
        assertProgramData(p, 0x4C, 4, 64, 96, 160, 3, 140, 32, 208, 96);
    }
    
    @Test
    public void testLongerJumpForward() throws Exception {
        Program p = Program.with()
                .i(LDX.immediate(_0))
                .i(STX.absolute(53280))
                .i(JMP.absolute("loop"))
                .i(STX.absolute(53281))
                .i(INX)
                .i(STX.absolute(53280))
                .label("loop")
                .i(RTS)
                .i(BRK)
                .build(4096);
        assertProgramData(p, 162, 0, 142, 32, 208, 0x4C, 15, 16, 142, 33, 208, 232, 142, 32, 208, 96, 0);
    }
    
    @Test
    public void testForwardSubroutine() throws Exception {
        Program p = Program.with()
                .i(JSR.absolute("subroutine1"))
                .i(RTS)
                .label("subroutine1")
                .i(LDY.immediate(_3))
                .i(STY.absolute(53280))
                .i(RTS)
                .build(16384);
        assertProgramData(p, 32, 4, 64, 96, 160, 3, 140, 32, 208, 96);
    }
    
    @Test
    public void testBackwardSubroutine() throws Exception {
        Program p = Program.with()
                .label("subroutine1")
                .i(LDY.immediate(_16))
                .i(STY.absolute(53280))
                .i(RTS)
                .i(JSR.absolute("subroutine1"))
                .i(RTS)
                .build(16384);
        assertProgramData(p, 160, 16, 140, 32, 208, 96, 32, 0, 64, 96);
    }
    
    @Test
    public void testHexStringData() throws Exception {
        Program p = Program.with()
                .d(16384, hex("112220"))
                .i(RTS)
                .build(16380);
        assertProgramData(p, 96, 0, 0, 0, 17, 34, 32);
    }
    
    private void assertProgramData(Program actual, int... expected) throws IOException {
        byte[] data = actual.getData();
        for (int i=0;i<data.length;i++) {
            Assert.assertEquals("Values at position "+i+" do not match", expected[i], data[i]&0xff);
        }
    }
}
