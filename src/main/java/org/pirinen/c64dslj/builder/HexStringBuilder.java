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

package org.pirinen.c64dslj.builder;

import java.io.ByteArrayOutputStream;

public class HexStringBuilder implements DataBuilder {
    
    private String[] hexStrings;
    
    private HexStringBuilder(String... hexStrings) {
        this.hexStrings = hexStrings;
    }
    
    public static Data hex(String... hexStrings) {
        return new HexStringBuilder(hexStrings).build();
    }
    
    
    @Override
    public Data build() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (String hs : hexStrings) {
            if (hs.length() % 2 != 0) {
                throw new RuntimeException("Each hex string lenght must be even");
            }
            for (int i=0;i<hs.length();i+=2) {
                String s = hs.substring(i,i+2);
                out.write(Integer.parseInt(s, 16));
            }
        }
        return new Data(out.toByteArray());
    }

}
