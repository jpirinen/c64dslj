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

package org.pirinen.c64dslj.helper;

import java.io.ByteArrayOutputStream;

import org.pirinen.c64dslj.Util;

public class Sprite {
	private char pixelChar;
	private String pixelString;

	private Sprite(char pixelChar) {
		this.pixelChar = pixelChar;
	}

	public static SpriteBuilder monochromeSprite() {
		return monochromeSprite('X');
	}

	public static SpriteBuilder monochromeSprite(char pixel) {
		return new SpriteBuilder(new Sprite(pixel));
	}

	public void setPixelStrings(String[] pixels) {
		if (pixels.length != 21) {
			throw new RuntimeException(
					"Wrong number of pixel strings for sprite ("
							+ pixels.length + "), must be 21");
		}
		StringBuilder sb = new StringBuilder();
		for (String pixelRow : pixels) {
			sb.append(pixelRow);
		}
		pixelString = sb.toString();
	}

	public byte[] getBytes() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < pixelString.length(); i += 8) {
			out.write(Util.bitStringToInt(pixelString.substring(i, i + 8),
					pixelChar));
		}
		return out.toByteArray();
	}

}
