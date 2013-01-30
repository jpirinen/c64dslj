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

package org.pirinen.c64dslj;

public class Util {

    public static byte offsetToByte(int offset) {
        if (offset >= 0) {
            return (byte) (offset & 0xFF);
        }
        return (byte) ((~Math.abs(offset) + 1) & 0xFF);
    }

    public static int byteToOffset(int value) {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Value must be between 0 and 255 inclusive");
        }
        if ((value & 0x80) == 0) {
            return value;
        }
        return -(256 - value);
    }

    public static int bitStringToInt(String bits, char one) {
        int result = 0;
        int len = bits.length();
        for (int i = 0; i < len; i++) {
            if (bits.charAt(i) == one)
                result |= 1 << (len - i - 1);
        }
        return result;
    }
}
