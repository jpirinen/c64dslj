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

package org.pirinen.c64dslj.builder;

import org.pirinen.c64dslj.model.Absolute;
import org.pirinen.c64dslj.model.AbsoluteIndexedX;
import org.pirinen.c64dslj.model.AbsoluteIndexedY;
import org.pirinen.c64dslj.model.AbsoluteLabelOperand;
import org.pirinen.c64dslj.model.EmptyOperand;
import org.pirinen.c64dslj.model.Immediate;
import org.pirinen.c64dslj.model.Implied;
import org.pirinen.c64dslj.model.IndexedIndirect;
import org.pirinen.c64dslj.model.Indirect;
import org.pirinen.c64dslj.model.IndirectIndexed;
import org.pirinen.c64dslj.model.Instruction;
import org.pirinen.c64dslj.model.OneByteOperand;
import org.pirinen.c64dslj.model.Opcode;
import org.pirinen.c64dslj.model.Relative;
import org.pirinen.c64dslj.model.RelativeOperand;
import org.pirinen.c64dslj.model.TwoByteOperand;
import org.pirinen.c64dslj.model.Zeropage;
import org.pirinen.c64dslj.model.ZeropageIndexedX;
import org.pirinen.c64dslj.model.ZeropageIndexedY;

public class MultiBuilder extends InstructionBuilder implements
		ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction>,
		ImmZpAbsBuilder<Instruction>, ZpZpxAbsAbxBuilder<Instruction>,
		ImpZpZpxAbsAbxBuilder<Instruction>, ImmZpZpyAbsAbyBuilder<Instruction>,
		ZpZpxIzxIzyAbsAbxAbyBuilder<Instruction>, ZpZpyAbsBuilder<Instruction>,
		ZpZpxAbsBuilder<Instruction>, ImmZpZpxAbsAbxBuilder<Instruction>,
		ZpAbsBuilder<Instruction>, AbsIndBuilder<Instruction>,
		RelativeBuilder<Instruction> {

	public MultiBuilder(Opcode opcode) {
		super(opcode);
	}

	@Override
	public Instruction implied() {
		return new Instruction(getCommand(Implied.class), new EmptyOperand());
	}

	@Override
	public Instruction immediate(DataType<Byte> value) {
		return new Instruction(getCommand(Immediate.class), new OneByteOperand(
				value));
	}

	@Override
	public Instruction immediate(int value) {
		return new Instruction(getCommand(Immediate.class), new OneByteOperand(
				new ByteDec(value)));
	}

	@Override
	public Instruction indirect(int value) {
		return new Instruction(getCommand(Indirect.class), new TwoByteOperand(
				value));
	}

	@Override
	public Instruction indirect(DataType<Integer> value) {
		return indirect(value.getValue());
	}

	@Override
	public Instruction absolute_Y(int value) {
		return new Instruction(getCommand(AbsoluteIndexedY.class),
				new TwoByteOperand(value));
	}

	@Override
	public Instruction absolute_Y(DataType<Integer> value) {
		return absolute_Y(value.getValue());
	}

	@Override
	public Instruction absolute_Y(String labelName) {
		return new Instruction(getCommand(AbsoluteIndexedY.class),
				new AbsoluteLabelOperand(labelName), true);
	}

	@Override
	public Instruction absolute_X(int value) {
		return new Instruction(getCommand(AbsoluteIndexedX.class),
				new TwoByteOperand(value));
	}

	@Override
	public Instruction absolute_X(DataType<Integer> value) {
		return absolute_X(value.getValue());
	}

	@Override
	public Instruction absolute(int value) {
		return new Instruction(getCommand(Absolute.class), new TwoByteOperand(
				value));
	}

	@Override
	public Instruction absolute(DataType<Integer> value) {
		return absolute(value.getValue());
	}

	@Override
	public Instruction absolute(String labelName) {
		return new Instruction(getCommand(Absolute.class),
				new AbsoluteLabelOperand(labelName), true);
	}

	@Override
	public Instruction indirectIndexed_Y(DataType<Byte> value) {
		return new Instruction(getCommand(IndirectIndexed.class),
				new OneByteOperand(value));
	}

	@Override
	public Instruction indexedIndirect_X(DataType<Byte> value) {
		return new Instruction(getCommand(IndexedIndirect.class),
				new OneByteOperand(value));
	}

	@Override
	public Instruction zeropage_Y(DataType<Byte> value) {
		return new Instruction(getCommand(ZeropageIndexedY.class),
				new OneByteOperand(value));
	}

	@Override
	public Instruction zeropage_X(DataType<Byte> value) {
		return new Instruction(getCommand(ZeropageIndexedX.class),
				new OneByteOperand(value));
	}

	@Override
	public Instruction zeropage(DataType<Byte> value) {
		return new Instruction(getCommand(Zeropage.class), new OneByteOperand(
				value));
	}

	@Override
	public Instruction zeropage(int value) {
		return zeropage(new ByteDec(value));
	}

	@Override
	public Instruction zeropage_X(int value) {
		return zeropage_X(new ByteDec(value));
	}

	@Override
	public Instruction zeropage_Y(int value) {
		return zeropage_Y(new ByteDec(value));
	}

	@Override
	public Instruction label(String labelName) {
		return new Instruction(getCommand(Relative.class), new RelativeOperand(
				labelName));
	}
}