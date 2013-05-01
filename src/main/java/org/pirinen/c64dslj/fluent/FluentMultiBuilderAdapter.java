package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedXBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedYBuilder;
import org.pirinen.c64dslj.builder.ImmediateBuilder;
import org.pirinen.c64dslj.builder.ImpliedBuilder;
import org.pirinen.c64dslj.builder.IndexedIndirectBuilder;
import org.pirinen.c64dslj.builder.IndirectBuilder;
import org.pirinen.c64dslj.builder.IndirectedIndexedBuilder;
import org.pirinen.c64dslj.builder.RelativeBuilder;
import org.pirinen.c64dslj.builder.ZeropageBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedXBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedYBuilder;
import org.pirinen.c64dslj.model.Instruction;

abstract class FluentMultiBuilderAdapter extends FluentMultiBuilder {

	FluentMultiBuilderAdapter(FluentBuilder b) {
		super(b);
	}

	@Override
	ImmediateBuilder<Instruction> getImmediateBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	ZeropageBuilder<Instruction> getZeropageBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	ZeropageIndexedXBuilder<Instruction> getZeropageIndexedXBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	ZeropageIndexedYBuilder<Instruction> getZeropageIndexedYBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	IndexedIndirectBuilder<Instruction> getIndexedIndirectBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	IndirectedIndexedBuilder<Instruction> getIndirectedIndexedBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	AbsoluteBuilder<Instruction> getAbsoluteBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	AbsoluteIndexedXBuilder<Instruction> getAbsoluteIndexedXBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	AbsoluteIndexedYBuilder<Instruction> getAbsoluteIndexedYBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	ImpliedBuilder<Instruction> getImpliedBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}

	@Override
	IndirectBuilder<Instruction> getIndirectBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}
	
	@Override
	RelativeBuilder<Instruction> getRelativeBuilder() {
		throw new UnsupportedOperationException("TODO test and implement");
	}
}
