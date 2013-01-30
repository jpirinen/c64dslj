package org.pirinen.c64dslj.model;

import org.junit.Assert;
import org.junit.Test;
import org.pirinen.c64dslj.model.Opcode;

public class OpcodeTest {

    @Test
    public void testSingleValueOpcode() throws Exception {
        Opcode dex = Opcode.DEX;
        Assert.assertEquals("DEX", dex.name());
    }

}
