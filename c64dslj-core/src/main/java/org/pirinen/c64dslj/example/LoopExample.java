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

import static org.pirinen.c64dslj.builder.ByteHex.*;
import static org.pirinen.c64dslj.model.Instruction.*;
import static org.pirinen.c64dslj.builder.WordHex0.*;
import static org.pirinen.c64dslj.builder.WordHex1.*;
import static org.pirinen.c64dslj.builder.WordHexD.*;
import static org.pirinen.c64dslj.helper.Color.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.pirinen.c64dslj.model.Program;

public class LoopExample {
    
    public static void main(String[] args) throws IOException {
        Program p = Program.with()
                .i(LDX.immediate ($00))
                .l("loop")
                .i(LDA.immediate (LIGHT_BLUE))
                .i(STA.absolute_X($d800))
                .i(TXA)
                .i(STA.absolute_X($0400))
                .i(INX)
                .i(BNE.label("loop"))
                .i(RTS)
                .build($1000); // SYS 4096 starts
        OutputStream out = new FileOutputStream("loopex.cbm");
        p.toCbmFormat(out);
        out.close();
    }
}
