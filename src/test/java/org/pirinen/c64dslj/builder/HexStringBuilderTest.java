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
