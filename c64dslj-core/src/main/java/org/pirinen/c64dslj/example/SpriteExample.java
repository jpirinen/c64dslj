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

import static org.pirinen.c64dslj.builder.ByteHex.$01;
import static org.pirinen.c64dslj.builder.ByteHex.$80;
import static org.pirinen.c64dslj.builder.WordHex0.$07f8;
import static org.pirinen.c64dslj.builder.WordHex1.$1000;
import static org.pirinen.c64dslj.builder.WordHex2.$2000;
import static org.pirinen.c64dslj.builder.WordHexD.$d000;
import static org.pirinen.c64dslj.builder.WordHexD.$d001;
import static org.pirinen.c64dslj.builder.WordHexD.$d015;
import static org.pirinen.c64dslj.builder.WordHexD.$d027;
import static org.pirinen.c64dslj.helper.Color.BLACK;
import static org.pirinen.c64dslj.helper.Sprite.monochromeSprite;
import static org.pirinen.c64dslj.model.Instruction.LDA;
import static org.pirinen.c64dslj.model.Instruction.RTS;
import static org.pirinen.c64dslj.model.Instruction.STA;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.pirinen.c64dslj.model.Program;

public class SpriteExample {

	public static void main(String[] args) throws IOException {
		Program p = Program
				.with()
				.i(LDA.immediate($80))
				.i(STA.absolute($d000))
				// set sprite coordinates
				.i(STA.absolute($d001))
				.i(STA.absolute($07f8))
				// set sprite address 64 * 128 = 8192
				.i(LDA.immediate($01))
				// enable sprite #1
				.i(STA.absolute($d015))
				.i(LDA.immediate(BLACK))
				// set sprite color
				.i(STA.absolute($d027))
				.i(RTS)
				.d($2000,
						monochromeSprite().pixels("........................",
								".......XXXXXXX..........",
								".....XXXXXXXXX..........",
								"....XXXXXXXXXX..........",
								"...XXXXXXXXXXX..........",
								"..XXXXXXXXXXXX..........",
								"..XXXXX.......XXXXXXXX..",
								".XXXXX........XXXXXXX...",
								".XXXX.........XXXXXX....",
								".XXXX.........XXXXX.....",
								".XXXX...................",
								".XXXX.........XXXXX.....",
								".XXXX.........XXXXXX....",
								".XXXXX........XXXXXXX...",
								"..XXXXX.......XXXXXXXX..",
								"..XXXXXXXXXXXX..........",
								"...XXXXXXXXXXX..........",
								"....XXXXXXXXXX..........",
								".....XXXXXXXXX..........",
								".......XXXXXXX..........",
								"........................").build())
				.build($1000);
		OutputStream out = new FileOutputStream("spriteex.cbm");
		p.toCbmFormat(out);
		out.close();
	}
}
