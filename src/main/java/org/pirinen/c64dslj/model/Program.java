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

package org.pirinen.c64dslj.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.pirinen.c64dslj.builder.Data;
import org.pirinen.c64dslj.builder.DataType;
import org.pirinen.c64dslj.builder.ProgramBuilder;
import org.pirinen.c64dslj.builder.ReferenceDataType;
import org.pirinen.c64dslj.fluent.FluentBuilder;

public class Program {

    private LinkedList<ProgramInstruction> instructions;
    private Map<String, Label> labels;
    private Map<String, List<LabelOperandBase>> labelOperands; // for forward branches
    private Map<Integer, Data> programData; // for sprites etc.
    
    private int startingAddress = -1;

    private static Program instance;
    
    public static ProgramBuilder with() {
       
        instance = new Program();
        return new ProgramBuilder(instance);
    }
    
    public static FluentBuilder withFluent() {
        instance = new Program();
        return new FluentBuilder(instance);
    }
    
    public static DataType<Byte> msb(String label) {
        DataType<Byte> dt = new ReferenceDataType(instance, label, true);
        return dt;
    }
    
    public static DataType<Byte> lsb(String label) {
        DataType<Byte> dt = new ReferenceDataType(instance, label, false);
        return dt;
    }

    private Program() {
        instructions = new LinkedList<ProgramInstruction>();
        labels = new HashMap<String, Label>();
        labelOperands = new HashMap<String, List<LabelOperandBase>>();
        programData = new TreeMap<Integer, Data>();
    }

    public void setStartingAddress(int startingAddress) {
        this.startingAddress = startingAddress;
    }

    public void add(Instruction i) {
        int position = 0;
        if (!instructions.isEmpty()) {
            ProgramInstruction previous = instructions.getLast();
            position = previous.getNextPosition();
        }
        if (i.getOperand().isLabel()) {
            handleLabelOperand(i, position);
        }
        instructions.add(new ProgramInstruction(position, i));
    }

    public Label getLabel(String name) {
        return labels.get(name);
    }
    
    public void add(int address, Data d) {
        programData.put(address, d);
    }

    private void handleLabelOperand(Instruction i, int position) {
        LabelOperandBase r = (LabelOperandBase) i.getOperand();
        String labelName = r.getLabelName();
        Label l = labels.get(labelName);
        if (l != null) {
            // label operand points backwards to an existing label
            r.setLabelPosition(l.getPosition());
            r.setPosition(position);
        } else {
            // label operand points forward, to a label to be presented
            List<LabelOperandBase> list = labelOperands.get(labelName);
            if (list == null) {
                list = new ArrayList<LabelOperandBase>();
                labelOperands.put(labelName, list);
            }
            r.setPosition(position);
            list.add(r);
            
        }
    }

    public void add(Label l) {
        if (labels.containsKey(l.getName())) {
            throw new RuntimeException("Label "+l.getName()+" already exists");
        }
        int pos = getLabelPosition();
        l.setPosition(pos);
        labels.put(l.getName(), l);
        List<LabelOperandBase> forwardOperands = labelOperands.get(l.getName());
        if (forwardOperands != null) {
            for (LabelOperandBase operand : forwardOperands) {
                operand.setLabelPosition(pos);
            }
        }
    }

    private int getLabelPosition() {
        if (instructions.isEmpty()) {
            return 0;
        }

        ProgramInstruction pi = instructions.getLast();
        return pi.getNextPosition();
    }

    public byte[] getData() throws IOException {
        if (containsAbsoluteLabel() && startingAddress < 0) {
            throw new RuntimeException("Instructions contain a label to absolute address, startAddress must be given");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (ProgramInstruction pi : instructions) {
            out.write(pi.getInstruction(startingAddress).getBytes());
        }
        return out.toByteArray();
    }

    private boolean containsAbsoluteLabel() {
        for (ProgramInstruction pi : instructions) {
            if (pi.getInstruction().containsAbsoluteLabel()) {
                return true;
            }
        }
        return false;
    }

    public void toCbmFormat(OutputStream out) throws IOException {
        if (startingAddress < 0) {
            throw new RuntimeException("Starting address must be given to program");
        }
        TwoByteOperand addr = new TwoByteOperand(startingAddress);
        out.write(addr.getData());
        int counter = startingAddress;
        for (ProgramInstruction pi : instructions) {
            byte[] instructionData = pi.getInstruction(startingAddress).getBytes();
            counter += instructionData.length;
            out.write(instructionData);
        }
        Set<Entry<Integer, Data>> dataEntries = programData.entrySet();
        for (Entry<Integer, Data> dataEntry : dataEntries) {
            int address = dataEntry.getKey();
            if (address < counter) {
                throw new RuntimeException("Data overlap at " + address);
            }
            fillWithZeroes(out, address - counter);
            counter += address - counter;
            byte[] data = dataEntry.getValue().getValue();
            counter += data.length;
            out.write(data);
        }
    }

    private static void fillWithZeroes(OutputStream out, int count) throws IOException {
        for (int i = 0; i < count; i++) {
            out.write(0);
        }
    }

    public int getStartingAddress() {
        return startingAddress;
    }
}
