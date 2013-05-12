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
import org.pirinen.c64dslj.builder.AbsoluteIndexedXBuilder;
import org.pirinen.c64dslj.builder.AbsoluteIndexedYBuilder;
import org.pirinen.c64dslj.builder.DataType;
import org.pirinen.c64dslj.builder.ImmZpAbsBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ImmZpZpyAbsAbyBuilder;
import org.pirinen.c64dslj.builder.ImmediateBuilder;
import org.pirinen.c64dslj.builder.ImpZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ImpliedBuilder;
import org.pirinen.c64dslj.builder.IndexedIndirectBuilder;
import org.pirinen.c64dslj.builder.IndirectBuilder;
import org.pirinen.c64dslj.builder.IndirectedIndexedBuilder;
import org.pirinen.c64dslj.builder.RelativeBuilder;
import org.pirinen.c64dslj.builder.ZeropageBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedXBuilder;
import org.pirinen.c64dslj.builder.ZeropageIndexedYBuilder;
import org.pirinen.c64dslj.builder.ZpAbsBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsAbxBuilder;
import org.pirinen.c64dslj.builder.ZpZpxAbsBuilder;
import org.pirinen.c64dslj.builder.ZpZpxIzxIzyAbsAbxAbyBuilder;
import org.pirinen.c64dslj.builder.ZpZpyAbsBuilder;
import org.pirinen.c64dslj.model.Instruction;

abstract class FluentMultiBuilder implements ImmZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder>,
        ImmZpAbsBuilder<FluentBuilder>, ZpZpxAbsAbxBuilder<FluentBuilder>, ImpZpZpxAbsAbxBuilder<FluentBuilder>,
        ImmZpZpyAbsAbyBuilder<FluentBuilder>, ZpZpxIzxIzyAbsAbxAbyBuilder<FluentBuilder>,
        ZpZpyAbsBuilder<FluentBuilder>, ZpZpxAbsBuilder<FluentBuilder>, ImmZpZpxAbsAbxBuilder<FluentBuilder>,
        ZpAbsBuilder<FluentBuilder>, AbsIndBuilder<FluentBuilder>, RelativeBuilder<FluentBuilder> {

    private FluentBuilder b;

    FluentMultiBuilder(FluentBuilder b) {
        this.b = b;
    }

    abstract ImmediateBuilder<Instruction> getImmediateBuilder();

    abstract ZeropageBuilder<Instruction> getZeropageBuilder();

    abstract ZeropageIndexedXBuilder<Instruction> getZeropageIndexedXBuilder();

    abstract ZeropageIndexedYBuilder<Instruction> getZeropageIndexedYBuilder();

    abstract IndexedIndirectBuilder<Instruction> getIndexedIndirectBuilder();

    abstract IndirectedIndexedBuilder<Instruction> getIndirectedIndexedBuilder();

    abstract AbsoluteBuilder<Instruction> getAbsoluteBuilder();

    abstract AbsoluteIndexedXBuilder<Instruction> getAbsoluteIndexedXBuilder();

    abstract AbsoluteIndexedYBuilder<Instruction> getAbsoluteIndexedYBuilder();

    abstract ImpliedBuilder<Instruction> getImpliedBuilder();

    abstract IndirectBuilder<Instruction> getIndirectBuilder();

    abstract RelativeBuilder<Instruction> getRelativeBuilder();

    @Override
    public FluentBuilder immediate(DataType<Byte> value) {
        b.getProgramBuilder().i(getImmediateBuilder().immediate(value));
        return b;
    }

    @Override
    public FluentBuilder immediate(int value) {
        b.getProgramBuilder().i(getImmediateBuilder().immediate(value));
        return b;
    }

    @Override
    public FluentBuilder zeropage(DataType<Byte> value) {
        b.getProgramBuilder().i(getZeropageBuilder().zeropage(value));
        return b;
    }

    @Override
    public FluentBuilder zeropage(int value) {
        b.getProgramBuilder().i(getZeropageBuilder().zeropage(value));
        return b;
    }

    @Override
    public FluentBuilder zeropage_X(DataType<Byte> value) {
        b.getProgramBuilder().i(getZeropageIndexedXBuilder().zeropage_X(value));
        return b;
    }

    @Override
    public FluentBuilder zeropage_X(int value) {
        b.getProgramBuilder().i(getZeropageIndexedXBuilder().zeropage_X(value));
        return b;
    }

    @Override
    public FluentBuilder zeropage_Y(DataType<Byte> value) {
        b.getProgramBuilder().i(getZeropageIndexedYBuilder().zeropage_Y(value));
        return b;
    }

    @Override
    public FluentBuilder zeropage_Y(int value) {
        b.getProgramBuilder().i(getZeropageIndexedYBuilder().zeropage_Y(value));
        return b;
    }

    @Override
    public FluentBuilder indexedIndirect_X(DataType<Byte> value) {
        b.getProgramBuilder().i(getIndexedIndirectBuilder().indexedIndirect_X(value));
        return b;
    }

    @Override
    public FluentBuilder indirectIndexed_Y(DataType<Byte> value) {
        b.getProgramBuilder().i(getIndirectedIndexedBuilder().indirectIndexed_Y(value));
        return b;
    }

    @Override
    public FluentBuilder absolute(DataType<Integer> value) {
        b.getProgramBuilder().i(getAbsoluteBuilder().absolute(value));
        return b;
    }

    @Override
    public FluentBuilder absolute(int value) {
        b.getProgramBuilder().i(getAbsoluteBuilder().absolute(value));
        return b;
    }

    @Override
    public FluentBuilder absolute(String label) {
        b.getProgramBuilder().i(getAbsoluteBuilder().absolute(label));
        return b;
    }

    @Override
    public FluentBuilder absolute_X(DataType<Integer> value) {
        b.getProgramBuilder().i(getAbsoluteIndexedXBuilder().absolute_X(value));
        return b;
    }

    @Override
    public FluentBuilder absolute_X(int value) {
        b.getProgramBuilder().i(getAbsoluteIndexedXBuilder().absolute_X(value));
        return b;
    }

    @Override
    public FluentBuilder absolute_Y(DataType<Integer> value) {
        b.getProgramBuilder().i(getAbsoluteIndexedYBuilder().absolute_Y(value));
        return b;
    }

    @Override
    public FluentBuilder absolute_Y(int value) {
        b.getProgramBuilder().i(getAbsoluteIndexedYBuilder().absolute_Y(value));
        return b;
    }

    @Override
    public FluentBuilder implied() {
        b.getProgramBuilder().i(getImpliedBuilder().implied());
        return b;
    }

    @Override
    public FluentBuilder indirect(DataType<Integer> value) {
        b.getProgramBuilder().i(getIndirectBuilder().indirect(value));
        return b;
    }

    @Override
    public FluentBuilder indirect(int value) {
        b.getProgramBuilder().i(getIndirectBuilder().indirect(value));
        return b;
    }

    @Override
    public FluentBuilder label(String label) {
        b.getProgramBuilder().i(getRelativeBuilder().label(label));
        return b;
    }

}
