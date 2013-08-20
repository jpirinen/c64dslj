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

package org.pirinen.c64dslj.example;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.pirinen.c64dslj.model.Program;

public class SidExample {

	public static void main(String[] args) throws Exception {
		Program p = Program.withFluent().sei().lda().immediate(0x7f).sta()
				.absolute(0xdc0d).and().absolute(0xd011).sta().absolute(0xd011)
				.lda().immediate(Program.addressLsb("main")).sta()
				.absolute(0x0314).lda().immediate(Program.addressMsb("main"))
				.sta().absolute(0x0315).lda().immediate(0).sta()
				.absolute(0xd012).sta().zeropage(2).lda().immediate(1).sta()
				.absolute(0xd01a).cli().rts().label("main").dec()
				.absolute("pwidth").lda().absolute("pwidth").sta()
				.absolute(0xd402).inc().absolute("freq").lda().absolute("freq")
				.sta().absolute(0xd401).beq().label("initialized").lda()
				.immediate(0x0f).sta().absolute(0xd418).lda().immediate(65)
				.sta().absolute(0xd404).lda().immediate(0x0f).sta()
				.absolute(0xd405).lda().immediate(0xf0).sta().absolute(0xd406)
				.lda().immediate(1).sta().absolute(0xd403).lda()
				.immediate(0xff).sta().absolute(0xd402).label("initialized")
				.asl().absolute(0xd019).jmp().absolute(0xea31).label("freq")
				.data(0).label("pwidth").data(0xff).end(4096);
		OutputStream out = new FileOutputStream("sidex.cbm");
		p.toCbmFormat(out);
		out.close();
	}
}
