package org.pirinen.c64dslj.builder;

import static org.pirinen.c64dslj.builder.HexStringBuilder.hex;

import org.junit.Assert;
import org.junit.Test;

public class HexStringBuilderTest {
    
    @Test
    public void testBuilder() {
        Data data = hex("010203");
        Assert.assertArrayEquals(new byte[]{1,2,3}, data.getValue());
        data = hex("0aff");
        Assert.assertArrayEquals(new byte[]{10,(byte)(255&0xff)}, data.getValue());
        data = hex("0aff","7f64");
        Assert.assertArrayEquals(new byte[]{10,(byte)(255&0xff),127,100}, data.getValue());
        
    }
}
