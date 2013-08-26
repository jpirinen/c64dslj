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

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class InstructionSetTest {

	@BeforeClass
	public static void init() {
		@SuppressWarnings("unused")
		Opcode opcode = Opcode.LDA;
	}

	@Test
	public void testGetAndSet() {
		InstructionSet.getInstance().add(
				new Command("DEX", 0xCA, new Implied(0xCA)));
		InstructionSet.getInstance().get(0xCA);
		Command c = InstructionSet.getInstance().get("DEX", Implied.class);
		Assert.assertNotNull(c);
	}

	@Test
	public void testGetAddressingModes() {
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
