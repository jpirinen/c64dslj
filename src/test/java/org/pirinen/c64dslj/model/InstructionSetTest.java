package org.pirinen.c64dslj.model;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pirinen.c64dslj.model.Command;
import org.pirinen.c64dslj.model.Implied;
import org.pirinen.c64dslj.model.InstructionSet;
import org.pirinen.c64dslj.model.Opcode;

public class InstructionSetTest {

    @BeforeClass
    public static void init() {
        @SuppressWarnings("unused")
        Opcode opcode = Opcode.LDA;
    }

    @Test
    public void testGetAndSet() throws Exception {
        InstructionSet.getInstance().add(new Command("DEX", 0xCA, new Implied(0xCA)));
        InstructionSet.getInstance().get(0xCA);
        Command c = InstructionSet.getInstance().get("DEX", Implied.class);
        Assert.assertNotNull(c);
    }

    @Test
    public void testGetAddressingModes() throws Exception {
        Command c = InstructionSet.getInstance().get(169);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x40);
        Assert.assertEquals(0, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x20);
        Assert.assertEquals(2, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x5E);
        Assert.assertEquals(2, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0xD9);
        Assert.assertEquals(2, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0xE1);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x71);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x90);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0xC4);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x36);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
        c = InstructionSet.getInstance().get(0x96);
        Assert.assertEquals(1, c.getAddressingMode().getOperandLength());
    }
}
