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

package org.pirinen.c64dslj;

import org.junit.Assert;
import org.junit.Test;
import org.pirinen.c64dslj.Util;

public class UtilTest {

    @Test
    public void testOffsetToByte() {
        Assert.assertEquals((byte) 0, Util.offsetToByte(0));
        Assert.assertEquals((byte) 1, Util.offsetToByte(1));
        Assert.assertEquals((byte) 0xFF, Util.offsetToByte(-1));
        Assert.assertEquals((byte) 0x80, Util.offsetToByte(-128));
        Assert.assertEquals((byte) 0x7F, Util.offsetToByte(127));
    }

    @Test
    public void testByteToOffset() {
        Assert.assertEquals(0, Util.byteToOffset(0));
        Assert.assertEquals((byte) 1, Util.byteToOffset(1));
        Assert.assertEquals(-1, Util.byteToOffset(0xff));
        Assert.assertEquals(-128, Util.byteToOffset(0x80));
        Assert.assertEquals(127, Util.byteToOffset(127));
    }

    @Test
    public void testBitstringToInt() {
        String bits = "X";
        Assert.assertEquals(1, Util.bitStringToInt(bits, 'X'));
        Assert.assertEquals(0, Util.bitStringToInt(bits, '.'));
        bits = "X..X";
        Assert.assertEquals(9, Util.bitStringToInt(bits, 'X'));
        bits = "XXXXXXXX";
        Assert.assertEquals(255, Util.bitStringToInt(bits, 'X'));
      
       
    }
}