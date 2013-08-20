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

import static org.pirinen.c64dslj.builder.ByteDec._111;
import static org.pirinen.c64dslj.builder.ByteDec._200;
import static org.pirinen.c64dslj.builder.ByteDec._222;
import static org.pirinen.c64dslj.builder.ByteDec._234;
import static org.pirinen.c64dslj.model.Instruction.AND;
import static org.pirinen.c64dslj.model.Instruction.EOR;
import static org.pirinen.c64dslj.model.Instruction.ORA;
import static org.pirinen.c64dslj.model.Instruction.SEI;
import static org.pirinen.c64dslj.model.Instruction.TAX;
import static org.pirinen.c64dslj.model.Instruction.TYA;

import org.junit.Assert;
import org.junit.Test;

public class InstructionTest {

	@Test
	public void tya() throws Exception {
		Instruction i = TYA;
		Assert.assertArrayEquals(new byte[] { (byte) 0x98 }, i.getBytes());
	}

	@Test
	public void sei() throws Exception {
		Instruction i = SEI;
		Assert.assertArrayEquals(new byte[] { (byte) 0x78 }, i.getBytes());
	}

	@Test
	public void tax() throws Exception {
		Instruction i = TAX;
		Assert.assertArrayEquals(new byte[] { (byte) 0xAA }, i.getBytes());
	}

	@Test
	public void oraImmediate() throws Exception {
		Instruction i = ORA.immediate(_234);
		Assert.assertArrayEquals(new byte[] { (byte) 0x09, (byte) 234 },
				i.getBytes());
	}

	@Test
	public void andZeroPage() throws Exception {
		Instruction i = AND.zeropage(_200);
		Assert.assertArrayEquals(new byte[] { (byte) 0x25, (byte) 200 },
				i.getBytes());
		i = AND.zeropage_X(_222);
		Assert.assertArrayEquals(new byte[] { (byte) 0x35, (byte) 222 },
				i.getBytes());
	}

	@Test
	public void eorZeroPageX() throws Exception {
		Instruction i = EOR.zeropage_X(_111);
		Assert.assertArrayEquals(new byte[] { (byte) 0x55, (byte) 111 },
				i.getBytes());
	}

}
