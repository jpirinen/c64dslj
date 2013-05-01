package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.RelativeBuilder;
import org.pirinen.c64dslj.model.Instruction;

public class FluentRelativeBuilder extends FluentMultiBuilderAdapter {
	
	private RelativeBuilder<Instruction> ib;
	
	FluentRelativeBuilder(FluentBuilder b, RelativeBuilder<Instruction> ib) {
		super(b);
		this.ib = ib;
	}
	
	@Override
	RelativeBuilder<Instruction> getRelativeBuilder() {
		return ib;
	}
}
