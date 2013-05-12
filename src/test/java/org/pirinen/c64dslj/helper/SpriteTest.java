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
        Assert.assertEquals(255, data[64]&0xff);
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
        Assert.assertEquals(255, data[64]&0xff);
        Assert.assertEquals(0, data[65]);
        Assert.assertEquals(-128, data[66]);    
        Assert.assertEquals(1, data[67]);   
        Assert.assertEquals(3, data[68]);
    }
    
    
    
    private static Data getSprite() {
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
