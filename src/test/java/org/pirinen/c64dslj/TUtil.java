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

import java.io.IOException;

import org.junit.Assert;
import org.pirinen.c64dslj.model.Program;

public class TUtil {
	public static void assertProgramData(Program actual, int... expected) throws IOException {
        byte[] data = actual.getData();
        for (int i=0;i<data.length;i++) {
            Assert.assertEquals("Values at position "+i+" do not match", expected[i], data[i]&0xff);
        }
    }
}
