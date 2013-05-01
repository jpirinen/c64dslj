package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.ImmZpAbsBuilder;
import org.pirinen.c64dslj.builder.ImmediateBuilder;
import org.pirinen.c64dslj.builder.ZeropageBuilder;
import org.pirinen.c64dslj.model.Instruction;

public class FluentImmZpAbsBuilder extends
		FluentMultiBuilderAdapter {

	private ImmZpAbsBuilder<Instruction> ib;
	
	FluentImmZpAbsBuilder(FluentBuilder b, ImmZpAbsBuilder<Instruction> ib) {
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
	AbsoluteBuilder<Instruction> getAbsoluteBuilder() {
		return ib;
	}
}
