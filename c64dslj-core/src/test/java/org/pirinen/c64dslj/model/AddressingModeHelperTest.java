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

package org.pirinen.c64dslj.model;

import org.junit.Assert;
import org.junit.Test;

public class AddressingModeHelperTest {

	@Test
	public void testImmediate() {
		AddressingModeHelper helper = AddressingModeHelper.getInstance();
		AddressingMode mode = helper.get(0xA9);
		Assert.assertEquals(Immediate.class, mode.getClass());
	}

	@Test
	public void testImplied() {
		AddressingModeHelper helper = AddressingModeHelper.getInstance();
		AddressingMode mode = helper.get(0xCA);
		Assert.assertEquals(Implied.class, mode.getClass());
	}
}
