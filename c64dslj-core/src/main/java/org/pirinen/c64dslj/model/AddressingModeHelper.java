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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class AddressingModeHelper {
	private static AddressingModeHelper instance = new AddressingModeHelper();

	private Map<Set<Integer>, Class<? extends AddressingMode>> allModes;

	public static AddressingModeHelper getInstance() {
		return instance;
	}

	private AddressingModeHelper() {
		allModes = new HashMap<Set<Integer>, Class<? extends AddressingMode>>();
		allModes.put(ImmutableSet.of(0xCA, 0x88, 0xE8, 0xC8, 0x0A, 0x2A, 0x4A,
				0x6A, 0xAA, 0x8A, 0xA8, 0x98, 0xBA, 0x9A, 0x68, 0x48, 0x28,
				0x08, 0x00, 0x40, 0x60, 0x18, 0x38, 0xD8, 0xF8, 0x58, 0x78,
				0xB8, 0xEA), Implied.class);
		allModes.put(ImmutableSet.of(0x09, 0x29, 0x49, 0x69, 0xE9, 0xC9, 0xE0,
				0xC0, 0xA9, 0xA2, 0xA0), Immediate.class);
		allModes.put(ImmutableSet.of(0x05, 0x25, 0x45, 0x65, 0xE5, 0xC5, 0xE4,
				0xC4, 0xC6, 0xE6, 0x06, 0x26, 0x46, 0x66, 0xA5, 0x85, 0xA6,
				0x86, 0xA4, 0x84, 0x24), Zeropage.class);
		allModes.put(ImmutableSet.of(0x15, 0x35, 0x55, 0x75, 0xF5, 0xD5, 0xD6,
				0xF6, 0x16, 0x36, 0x56, 0x76, 0xB5, 0x95, 0xB4, 0x94),
				ZeropageIndexedX.class);
		allModes.put(ImmutableSet.of(0xB6, 0x96), ZeropageIndexedY.class);
		allModes.put(
				ImmutableSet.of(0x01, 0x21, 0x41, 0x61, 0xE1, 0xC1, 0xA1, 0x81),
				IndexedIndirect.class);
		allModes.put(
				ImmutableSet.of(0x11, 0x31, 0x51, 0x71, 0xF1, 0xD1, 0xB1, 0x91),
				IndirectIndexed.class);
		allModes.put(ImmutableSet.of(0x0D, 0x2D, 0x4D, 0x6D, 0xED, 0xCD, 0xEC,
				0xCC, 0xCE, 0xEE, 0x0E, 0x2E, 0x4E, 0x6E, 0xAD, 0x8D, 0xAE,
				0x8E, 0xAC, 0x8C, 0x20, 0x4C, 0x2C), Absolute.class);
		allModes.put(ImmutableSet.of(0x1D, 0x3D, 0x5D, 0x7D, 0xFD, 0xDD, 0xDE,
				0xFE, 0x1E, 0x3E, 0x5E, 0x7E, 0xBD, 0x9D, 0xBC),
				AbsoluteIndexedX.class);
		allModes.put(ImmutableSet.of(0x19, 0x39, 0x59, 0x79, 0xF9, 0xD9, 0xB9,
				0x99, 0xBE), AbsoluteIndexedY.class);
		allModes.put(ImmutableSet.of(0x6C), Indirect.class);
		allModes.put(
				ImmutableSet.of(0x10, 0x30, 0x50, 0x70, 0x90, 0xB0, 0xD0, 0xF0),
				Relative.class);
	}

	public AddressingMode get(int opcode) {
		for (Set<Integer> keys : allModes.keySet()) {
			if (keys.contains(opcode)) {
				Class<? extends AddressingMode> modeClass = allModes.get(keys);
				try {
					return modeClass.getConstructor(Integer.TYPE).newInstance(
							opcode);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (SecurityException e) {
					throw new RuntimeException(e);
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
