package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsIndBuilder;
import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.IndirectBuilder;
import org.pirinen.c64dslj.model.Instruction;

public class FluentAbsIndLabelBuilder extends FluentMultiBuilderAdapter {

	private AbsIndBuilder<Instruction> ib;
	
	FluentAbsIndLabelBuilder(FluentBuilder b, AbsIndBuilder<Instruction> ib) {
		super(b);
		this.ib = ib;
	}
	
	@Override
	AbsoluteBuilder<Instruction> getAbsoluteBuilder() {
		return ib;
	}
	
	@Override
	IndirectBuilder<Instruction> getIndirectBuilder() {
		return ib;
	}

}
