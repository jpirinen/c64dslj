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

import static org.pirinen.c64dslj.model.Instruction.*;
import static org.pirinen.c64dslj.builder.WordHex0.*;
import static org.pirinen.c64dslj.builder.WordHexC.*;
import static org.pirinen.c64dslj.builder.WordHexD.*;
import static org.pirinen.c64dslj.builder.WordHexE.*;
import static org.pirinen.c64dslj.model.Program.msb;
import static org.pirinen.c64dslj.model.Program.lsb;
import static org.pirinen.c64dslj.builder.ByteHex.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.pirinen.c64dslj.model.Program;

public class InterruptExample {
    public static void main(String[] args) throws IOException {
       Program p = Program.with()
               .i(SEI)
               .i(LDA.immediate($7f))
               .i(STA.absolute ($dc0d))
               .i(AND.absolute ($d011))
               .i(STA.absolute ($d011))
               .i(LDA.immediate(lsb("main")))
               .i(STA.absolute ($0314))
               .i(LDA.immediate(msb("main")))
               .i(STA.absolute ($0315))
               .i(LDA.immediate($00))
               .i(STA.absolute ($d012))
               .i(STA.zeropage ($02))
               .i(LDA.immediate($01))
               .i(STA.absolute ($d01a))
               .i(CLI)
               .i(RTS)
               .label("main")
               .i(INC.absolute("counter"))
               .i(CLC)
               .i(LDA.absolute("counter"))
               .i(ROR_)
               .i(STA.absolute($d020))
               .i(ASL.absolute($d019))
               .i(JMP.absolute($ea31))
               .label("counter")
               .b($00)
               .build($c000); // SYS 49152 starts
       OutputStream out = new FileOutputStream("interruptex.cbm");
       p.toCbmFormat(out);
       out.close();
    } 
}
