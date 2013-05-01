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

public class TwoByteOperand extends OperandBase {
    private int value;
    private byte[] data = new byte[2];

    public TwoByteOperand(int value) {
        if (value < 0 || value > 65535) {
            throw new IllegalArgumentException("Two-byte operand value must be between 0 and 65535");
        }
        this.value = value;
        this.data[0] = (byte) ((value % 256) & 0xff);
        this.data[1] = (byte) (((value & 0xff00) >> 8) & 0xff);
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public String toDsl() {
        return "(" + value + ")";
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public int getLength() {
        return 2;
    }
}
