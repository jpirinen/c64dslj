package org.pirinen.c64dslj.helper;

import static org.pirinen.c64dslj.helper.Sprite.monochromeSprite;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.pirinen.c64dslj.builder.Data;
import org.pirinen.c64dslj.model.Program;


public class SpriteTest {
    
    
    @Test
    public void testSpriteBuilder() throws IOException {
        Program p = Program.with()
                  .d(0x2000, getSprite())
                 .build(0x2000);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        p.toCbmFormat(out);
        byte[] data = out.toByteArray();
        Assert.assertEquals(-128, data[2]); 
        Assert.assertEquals(1, data[3]);    
        Assert.assertEquals(3, data[4]);
        Assert.assertEquals(64, data[10]);
        Assert.assertEquals(255, (int)(data[64]&0xff));
    }
    
    @Test
    public void testSpriteBuilderWith2Sprites() throws IOException {
        Program p = Program.with()
                  .d(0x2000, getSprite())
                  .d(0x2040, getSprite())
                 .build(0x2000);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        p.toCbmFormat(out);
        byte[] data = out.toByteArray();
        Assert.assertEquals(-128, data[2]); 
        Assert.assertEquals(1, data[3]);    
        Assert.assertEquals(3, data[4]);
        Assert.assertEquals(64, data[10]);
        Assert.assertEquals(0, data[63]);   
        Assert.assertEquals(255, (int)(data[64]&0xff));
        Assert.assertEquals(0, data[65]);
        Assert.assertEquals(-128, data[66]);    
        Assert.assertEquals(1, data[67]);   
        Assert.assertEquals(3, data[68]);
    }
    
    
    
    private Data getSprite() {
        return monochromeSprite().pixels( 
                  "X..............X......XX",
                  "........................",
                  ".................X......",
                  "........................",
                  "........................",
                  "........................",
                  "..........XXXX..........",
                  ".........XXXXXXXX.......",
                  "........XXX....XX.......",
                  "........XX.....XXX......",
                  "........XX.....XXX......",
                  "........XX.....XX.......",
                  ".........XXXXXXXX.......",
                  "..........XXXX..........",
                  "........................",
                  "........................",
                  "........................",
                  "........................",
                  "........................",
                  "........................",
                  "................XXXXXXXX"
                  ).build();
    }
}
