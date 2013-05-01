package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedXBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedYBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ImmediateBuilder;
import org.pirinen.c64dslj.builder.IndexedIndirectBuilder;
import org.pirinen.c64dslj.builder.IndirectedIndexedBuilder;
import org.pirinen.c64dslj.builder.ZeropageBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedXBuilder;
import org.pirinen.c64dslj.model.Instruction;

class FluentImmZpZpxIzxIzyAbsAbxAbyBuilder extends FluentMultiBuilderAdapter {
    private ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> ib;
    
    public FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(FluentBuilder b, ImmZpZpxIzxIzyAbsAbxAbyBuilder<Instruction> ib) {
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
    IndexedIndirectBuilder<Instruction> getIndexedIndirectBuilder() {
    	return ib;
    }
    
    @Override
    IndirectedIndexedBuilder<Instruction> getIndirectedIndexedBuilder() {
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
    
    @Override
    AbsoluteIndexedYBuilder<Instruction> getAbsoluteIndexedYBuilder() {
    	return ib;
    }
}