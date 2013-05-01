package org.pirinen.c64dslj.fluent;

import org.pirinen.c64dslj.builder.AbsIndBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ImpZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ProgramBuilder;
import org.pirinen.c64dslj.builder.RelativeBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.model.Instruction;
import org.pirinen.c64dslj.model.Program;

public class FluentBuilder {
    //private ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> ldaBuilder = new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.LDA);
    
    private ProgramBuilder p;
    
    public FluentBuilder(Program instance) {
       p = new ProgramBuilder(instance);
       
    }
    
    ProgramBuilder getProgramBuilder() {
        return p;
    }

    
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> and() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.AND);
    }
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> adc() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.ADC);
    }
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> cmp() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.CMP);
    }
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> eor() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.EOR);
    }
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> lda() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.LDA);
    }
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> ora() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.ORA);
    }
    
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> sbc() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.SBC);
    }

    public ZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> sta() {
        return new FluentZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.STA);       
    }
    
    public ZpZpxAbsAbxBuilder<FluentBuilder> inc() {
    	return new FluentZpZpxAbsAbxBuilder(this, Instruction.INC);
    }
    
    public ZpZpxAbsAbxBuilder<FluentBuilder> dec() {
    	return new FluentZpZpxAbsAbxBuilder(this, Instruction.DEC);
    }
    
    public ImpZpZpxAbsAbxBuilder<FluentBuilder> asl() {
		return new FluentImpZpZpxAbsAbxBuilder(this, Instruction.ASL);
	}
    
    public AbsIndBuilder<FluentBuilder> jmp() {
		return new FluentAbsIndLabelBuilder(this, Instruction.JMP);
	}
    
    public RelativeBuilder<FluentBuilder> bne() {
		return new FluentRelativeBuilder(this, Instruction.BNE);
	}
    
    public RelativeBuilder<FluentBuilder> beq() {
		return new FluentRelativeBuilder(this, Instruction.BEQ);
	}
    

    public FluentBuilder rts() {
        p.i(Instruction.RTS);
        return this;
    }
    
    public Program end(int address) {
        return p.build(address);
    }

	public FluentBuilder sei() {
		p.i(Instruction.SEI);
        return this;
	}

	public FluentBuilder cli() {
		p.i(Instruction.CLI);
		return this;
	}

	public FluentBuilder clc() {
		p.i(Instruction.CLC);
		return this;
	}
	
	public FluentBuilder label(String label) {
		p.label(label);
		return this;
	}

	public FluentBuilder ror_() {
		p.i(Instruction.ROR_);
		return this;
	}

	public FluentBuilder data(int value) {
		p.oneByte(value);
		return this;
	}

	

	

	

	
} 