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

import org.pirinen.c64dslj.builder.AbsIndBuilder;
import org.pirinen.c64dslj.builder.AbsoluteBuilder;
import org.pirinen.c64dslj.builder.ImmZpAbsBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpyAbsAbyBuilder;
import org.pirinen.c64dslj.builder.ImpZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ProgramBuilder;
import org.pirinen.c64dslj.builder.RelativeBuilder;
import org.pirinen.c64dslj.builder.ZpAbsBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsBuilder;
import org.pirinen.c64dslj.builder.ZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ZpZpyAbsBuilder;
import org.pirinen.c64dslj.model.Instruction;
import org.pirinen.c64dslj.model.Program;

public class FluentBuilder {
    
    private ProgramBuilder p;
    
    public FluentBuilder(Program instance) {
       p = new ProgramBuilder(instance);       
    }
    
    ProgramBuilder getProgramBuilder() {
        return p;
    }
    
    /**
     * And operation (N,Z)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> and() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.AND);
    }
    
    /**
     * Add with carry (N,V,Z,C)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> adc() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.ADC);
    }
    
    /**
     * Compare accumulator (N,Z,C)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> cmp() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.CMP);
    }
    
    /**
     * Exclusive or operation (N,Z)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> eor() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.EOR);
    }
    
    /**
     * Load accumulator (N,Z)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> lda() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.LDA);
    }
    
    /**
     * Inclusive or operation (N,Z)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> ora() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.ORA);
    }
    
    /**
     * Subtract with carry (N,V,Z,C)
     * @return addressing mode builder
     */
    public ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> sbc() {
        return new FluentImmZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.SBC);
    }

    /**
     * Store accumulator
     * @return addressing mode builder
     */
    public ZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder> sta() {
        return new FluentZpZpxIzxIzyAbsAbxAbyBuilder(this, Instruction.STA);       
    }
    
    /**
     * Compare X register (N,Z,C)
     * @return addressing mode builder
     */
    public ImmZpAbsBuilder<FluentBuilder> cpx() {
    	return new FluentImmZpAbsBuilder(this, Instruction.CPX);
    }
    
    /**
     * Compare Y register (N,Z,C)
     * @return addressing mode builder
     */
    public ImmZpAbsBuilder<FluentBuilder> cpy() {
    	return new FluentImmZpAbsBuilder(this, Instruction.CPY);
    }
    
    /**
     * Increment a memory location (N,Z)
     * @return addressing mode builder
     */
    public ZpZpxAbsAbxBuilder<FluentBuilder> inc() {
    	return new FluentZpZpxAbsAbxBuilder(this, Instruction.INC);
    }
    
    /**
     * Decrement a memory location (N,Z)
     * @return addressing mode builder
     */
    public ZpZpxAbsAbxBuilder<FluentBuilder> dec() {
    	return new FluentZpZpxAbsAbxBuilder(this, Instruction.DEC);
    }
    
    /**
     * Arithmetic shift left (N,Z,C)
     * @return addressing mode builder
     */
    public ImpZpZpxAbsAbxBuilder<FluentBuilder> asl() {
		return new FluentImpZpZpxAbsAbxBuilder(this, Instruction.ASL);
	}
    
    /**
     * Rotate left (N,Z,C)
     * @return addressing mode builder
     */
    public ImpZpZpxAbsAbxBuilder<FluentBuilder> rol() {
		return new FluentImpZpZpxAbsAbxBuilder(this, Instruction.ROL);
	}
    
    /**
     * Logical shift left (N,Z,C)
     * @return addressing mode builder
     */
    public ImpZpZpxAbsAbxBuilder<FluentBuilder> lsr() {
		return new FluentImpZpZpxAbsAbxBuilder(this, Instruction.LSR);
	}
    
    /**
     * Rotate right (N,Z,C)
     * @return addressing mode builder
     */
    public ImpZpZpxAbsAbxBuilder<FluentBuilder> ror() {
		return new FluentImpZpZpxAbsAbxBuilder(this, Instruction.ROR);
	}
    
    /**
     * Load X register (N,Z)
     * @return addressing mode builder
     */
    public ImmZpZpyAbsAbyBuilder<FluentBuilder> ldx() {
    	return new FluentImmZpZpyAbsAbyBuilder(this, Instruction.LDX);
    }
    
    /**
     * Store X register
     * @return addressing mode builder
     */
    public ZpZpyAbsBuilder<FluentBuilder> sdx() {
    	return new FluentZpZpyAbsBuilder(this, Instruction.STX);
    }
    
    /**
     * Load Y register (N,Z)
     * @return addressing mode builder
     */
    public ImmZpZpxAbsAbxBuilder<FluentBuilder> ldy() {
    	return new FluentImmZpZpxAbsAbxBuilder(this, Instruction.LDY);
    }
    
    /**
     * Store Y register
     * @return addressing mode builder
     */
    public ZpZpxAbsBuilder<FluentBuilder> sty() {
    	return new FluentZpZpxAbsBuilder(this, Instruction.STY);
    }
    
    /**
     * Jump to another location
     * @return addressing mode builder
     */
    public AbsIndBuilder<FluentBuilder> jmp() {
		return new FluentAbsIndLabelBuilder(this, Instruction.JMP);
	}
    
    /**
     * Jump to a subroutine
     * @return addressing mode builder
     */
    public AbsoluteBuilder<FluentBuilder> jsr() {
    	return new FluentAbsoluteBuilder(this, Instruction.JSR);
    }
    
    /**
     * Bit test operation (N,V,Z)
     * @return addressing mode builder
     */
    public ZpAbsBuilder<FluentBuilder> bit() {
    	return new FluentZpAbsBuilder(this, Instruction.BIT);
    }
    
    /**
     * Branch if zero flag clear
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bne() {
		return new FluentRelativeBuilder(this, Instruction.BNE);
	}
    
    /**
     * Branch if zero flag set
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> beq() {
		return new FluentRelativeBuilder(this, Instruction.BEQ);
	}
    
    /**
     * Branch if negative flag clear
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bpl() {
		return new FluentRelativeBuilder(this, Instruction.BPL);
	}
    
    /**
     * Branch if negative flag set
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bmi() {
		return new FluentRelativeBuilder(this, Instruction.BMI);
	}
    
    /**
     * Branch if overflow flag clear
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bvc() {
		return new FluentRelativeBuilder(this, Instruction.BVC);
	}
    
    /**
     * Branch if overflow flag set
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bvs() {
		return new FluentRelativeBuilder(this, Instruction.BVS);
	}
    
    /**
     * Branch if carry flag clear
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bcc() {
		return new FluentRelativeBuilder(this, Instruction.BCC);
	}
    
    /**
     * Branch if carry flag set
     * @return addressing mode builder
     */
    public RelativeBuilder<FluentBuilder> bcs() {
		return new FluentRelativeBuilder(this, Instruction.BCS);
	}

    /**
     * Return from subroutine
     * @return instruction builder
     */
    public FluentBuilder rts() {
        p.i(Instruction.RTS);
        return this;
    }
    
    public Program end(int address) {
        return p.build(address);
    }

    /**
     * Set interrupt flag (I)
     * @return instruction builder
     */
	public FluentBuilder sei() {
		p.i(Instruction.SEI);
        return this;
	}

	/**
     * Clear interrupt flag (I)
     * @return instruction builder
     */
	public FluentBuilder cli() {
		p.i(Instruction.CLI);
		return this;
	}

	/**
     * Clear carry flag (C)
     * @return instruction builder
     */
	public FluentBuilder clc() {
		p.i(Instruction.CLC);
		return this;
	}
	
	public FluentBuilder label(String label) {
		p.label(label);
		return this;
	}

	/**
     * Rotate accumulator right (N,Z,C)
     * @return instruction builder
     */
	public FluentBuilder ror_() {
		p.i(Instruction.ROR_);
		return this;
	}
	
	/**
     * Rotate accumulator left (N,Z,C)
     * @return instruction builder
     */
	public FluentBuilder rol_() {
		p.i(Instruction.ROL_);
		return this;
	}
	
	/**
     * Accumulator arithmetic shift left (N,Z,C)
     * @return instruction builder
     */
	public FluentBuilder asl_() {
		p.i(Instruction.ASL_);
		return this;
	}
	
	/**
     * Accumulator logical shift left (N,Z,C)
     * @return instruction builder
     */
	public FluentBuilder lsr_() {
		p.i(Instruction.LSR_);
		return this;
	}

	/**
     * Decrement Y register (N,Z)
     * @return instruction builder
     */
	public FluentBuilder dey() {
		p.i(Instruction.DEY);
		return this;
	}
	
	/**
     * Decrement X register (N,Z)
     * @return instruction builder
     */
	public FluentBuilder dex() {
		p.i(Instruction.DEX);
		return this;
	}
	
	/**
     * Increment X register (N,Z)
     * @return instruction builder
     */
	public FluentBuilder inx() {
		p.i(Instruction.INX);
		return this;
	}
	
	/**
     * Increment Y register (N,Z)
     * @return instruction builder
     */
	public FluentBuilder iny() {
		p.i(Instruction.INY);
		return this;
	}
	
	/**
     * Transfer accumulator to X (N,Z)
     * @return instruction builder
     */
	public FluentBuilder tax() {
		p.i(Instruction.TAX);
		return this;
	}
	
	/**
     * Transfer X to accumulator (N,Z)
     * @return instruction builder
     */
	public FluentBuilder txa() {
		p.i(Instruction.TXA);
		return this;
	}
	
	/**
     * Transfer accumulator to Y (N,Z)
     * @return instruction builder
     */
	public FluentBuilder tay() {
		p.i(Instruction.TAY);
		return this;
	}
	
	/**
     * Transfer Y to accumulator (N,Z)
     * @return instruction builder
     */
	public FluentBuilder tya() {
		p.i(Instruction.TYA);
		return this;
	}
	
	/**
     * Transfer stack pointer to X (N,Z)
     * @return instruction builder
     */
	public FluentBuilder tsx() {
		p.i(Instruction.TSX);
		return this;
	}
	
	/**
     * Transfer X to stack pointer
     * @return instruction builder
     */
	public FluentBuilder txs() {
		p.i(Instruction.TXS);
		return this;
	}
	
	/**
     * Pull accumulator from stack (N,Z)
     * @return instruction builder
     */
	public FluentBuilder pla() {
		p.i(Instruction.PLA);
		return this;
	}
	
	/**
     * Push accumulator on stack
     * @return instruction builder
     */
	public FluentBuilder pha() {
		p.i(Instruction.PHA);
		return this;
	}
	
	/**
     * Pull status register from stack (all)
     * @return instruction builder
     */
	public FluentBuilder plp() {
		p.i(Instruction.PLP);
		return this;
	}
	
	/**
     * Push status register on stack
     * @return instruction builder
     */
	public FluentBuilder php() {
		p.i(Instruction.PHP);
		return this;
	}
	
	/**
     * Force a break (B)
     * @return instruction builder
     */
	public FluentBuilder brk() {
		p.i(Instruction.BRK);
		return this;
	}
	
	/**
     * Return from interrupt (All)
     * @return instruction builder
     */
	public FluentBuilder rti() {
		p.i(Instruction.RTI);
		return this;
	}
	
	/**
     * Set carry flag (C)
     * @return instruction builder
     */
	public FluentBuilder sec() {
		p.i(Instruction.SEC);
		return this;
	}
	
	/**
     * Clear decimal flag (D)
     * @return instruction builder
     */
	public FluentBuilder cld() {
		p.i(Instruction.CLD);
		return this;
	}
	
	/**
     * Set decimal flag (D)
     * @return instruction builder
     */
	public FluentBuilder sed() {
		p.i(Instruction.SED);
		return this;
	}
	
	/**
     * Clear overflow flag (V)
     * @return instruction builder
     */
	public FluentBuilder clv() {
		p.i(Instruction.CLV);
		return this;
	}
	
	/**
     * No operation
     * @return instruction builder
     */
	public FluentBuilder nop() {
		p.i(Instruction.NOP);
		return this;
	}
	
	public FluentBuilder data(int value) {
		p.oneByte(value);
		return this;
	}

	

	

	

	
} 