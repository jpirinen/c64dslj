package org.pirinen.c64dslj.model;

public abstract class OperandBase implements Operand {
	@Override
	public boolean isLabel() {
		return false;
	}
}
