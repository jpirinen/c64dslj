package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedXBuilder;
import org.pirinen.c64dslj.builder.ImpZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ImpliedBuilder;
import org.pirinen.c64dslj.builder.ZeropageBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedXBuilder;
import org.pirinen.c64dslj.model.Instruction;

public class FluentImpZpZpxAbsAbxBuilder extends FluentMultiBuilderAdapter {

	ImpZpZpxAbsAbxBuilder<Instruction> ib;
	
	FluentImpZpZpxAbsAbxBuilder(FluentBuilder b, ImpZpZpxAbsAbxBuilder<Instruction> ib) {
		super(b);
		this.ib = ib;
	}
	
	@Override
	ImpliedBuilder<Instruction> getImpliedBuilder() {
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
