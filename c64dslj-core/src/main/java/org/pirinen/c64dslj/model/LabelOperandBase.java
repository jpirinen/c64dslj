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

public abstract class LabelOperandBase implements Operand {
    
    private String labelName;
    private int position;
    private int labelPosition;
    
    public LabelOperandBase(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelName() {
        return labelName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean isLabel() {
        return true;
    }

    @Override
    public String toDsl() {
        return "(\"" + getLabelName() + "\")";
    }

    public void setLabelPosition(int position) {
        labelPosition = position;
    }
    
    protected int getLabelPosition() {
        return labelPosition;
    }
}
