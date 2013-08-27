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

package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedXBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ImmediateBuilder;
import org.pirinen.c64dslj.builder.ZeropageBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedXBuilder;
import org.pirinen.c64dslj.model.Instruction;

public class FluentImmZpZpxAbsAbxBuilder extends FluentMultiBuilderAdapter {

	private ImmZpZpxAbsAbxBuilder<Instruction> ib;

	FluentImmZpZpxAbsAbxBuilder(FluentBuilder b,
			ImmZpZpxAbsAbxBuilder<Instruction> ib) {
		super(b);
		this.ib = ib;
	}

	@Override
	ImmediateBuilder<Instruction> getImmediateBuilder() {
		return ib;
	}

	@Override
	ZeropageBuilder<Instruction> getZeropageBuilder() {
		return ib;
	}

	@Override
	ZeropageIndexedXBuilder<Instruction> getZeropageIndexedXBuilder() {
		return ib;
	}

	@Override
	AbsoluteBuilder<Instruction> getAbsoluteBuilder() {
		return ib;
	}

	@Override
	AbsoluteIndexedXBuilder<Instruction> getAbsoluteIndexedXBuilder() {
		return ib;
	}
}