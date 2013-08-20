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

import org.pirinen.c64dslj.builder.Data;
import org.pirinen.c64dslj.builder.DataBuilder;

public class SpriteBuilder implements DataBuilder {

	private Sprite sprite;

	public SpriteBuilder(Sprite sprite) {
		this.sprite = sprite;
	}

	public SpriteBuilder pixels(String... pixels) {
		sprite.setPixelStrings(pixels);
		return this;
	}

	@Override
	public Data build() {
		return new Data(sprite.getBytes());
	}

}
