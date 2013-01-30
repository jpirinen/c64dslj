package org.pirinen.c64dslj.model;

import static org.pirinen.c64dslj.builder.ByteDec.*;
import static org.pirinen.c64dslj.model.Instruction.*;

import org.junit.Assert;
import org.junit.Test;
import org.pirinen.c64dslj.model.Instruction;

public class InstructionTest {

    @Test
    public void tya() throws Exception {
        Instruction i = TYA;
        Assert.assertArrayEquals(new byte[] { (byte) 0x98 }, i.getBytes());
    }
    
    @Test
    public void sei() throws Exception {
        Instruction i = SEI;
        Assert.assertArrayEquals(new byte[] { (byte) 0x78 }, i.getBytes());
    }
    
    @Test
    public void tax() throws Exception {
        Instruction i = TAX;
        Assert.assertArrayEquals(new byte[] { (byte) 0xAA }, i.getBytes());
    }

    @Test
    public void oraImmediate() throws Exception {
        Instruction i = ORA.immediate(_234);
        Assert.assertArrayEquals(new byte[] { (byte) 0x09, (byte) 234 }, i.getBytes());
    }

    @Test
    public void andZeroPage() throws Exception {
        Instruction i = AND.zeropage(_200);
        Assert.assertArrayEquals(new byte[] { (byte) 0x25, (byte) 200 }, i.getBytes());
        i = AND.zeropage_X(_222);
        Assert.assertArrayEquals(new byte[] { (byte) 0x35, (byte) 222 }, i.getBytes());
    }

    @Test
    public void eorZeroPageX() throws Exception {
        Instruction i = EOR.zeropage_X(_111);
        Assert.assertArrayEquals(new byte[] { (byte) 0x55, (byte) 111 }, i.getBytes());
    }

}
