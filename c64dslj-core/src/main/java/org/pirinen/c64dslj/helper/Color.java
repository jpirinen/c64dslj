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

package org.pirinen.c64dslj.helper;

import org.pirinen.c64dslj.builder.DataType;

public class Color implements DataType<Byte> {
    public static final Color BLACK = new Color(0);
    public static final Color WHITE = new Color(1);
    public static final Color RED = new Color(2);
    public static final Color CYAN = new Color(3);
    public static final Color PURPLE = new Color(4);
    public static final Color GREEN = new Color(5);
    public static final Color BLUE = new Color(6);
    public static final Color YELLOW = new Color(7);
    public static final Color ORANGE = new Color(8);
    public static final Color BROWN = new Color(9);
    public static final Color LIGHT_RED = new Color(10);
    public static final Color DARK_GREY = new Color(11);
    public static final Color GREY = new Color(12);
    public static final Color LIGHT_GREEN = new Color(13);
    public static final Color LIGHT_BLUE = new Color(14);
    public static final Color LIGHT_GREY = new Color(15);

    private int value;

    private Color(int value) {
        this.value = value;
    }

    @Override
    public Byte getValue() {
        return (byte) value;
    }
}
