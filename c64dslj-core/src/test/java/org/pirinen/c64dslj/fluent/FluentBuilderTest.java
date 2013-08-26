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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.pirinen.c64dslj.builder.ByteHex.$01;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.pirinen.c64dslj.TUtil;
import org.pirinen.c64dslj.model.Label;
import org.pirinen.c64dslj.model.Program;

public class FluentBuilderTest {

	@Test
	public void testSimpleProgram() throws Exception {
		FluentBuilder b = Program.withFluent();
		b.lda().immediate($01).sta().absolute(53281).rts();
		Program p = b.end(4096);
		TUtil.assertProgramData(p, 169, 1, 141, 33, 208, 96);
	}

	@Test
	public void ldaAbsoluteLabel() throws IOException {
		FluentBuilder b = Program.withFluent();
		Label label = Label.name("label");
		b.label(label.getName());
		b.lda().absolute(label.getName());
		Program p = b.end(4096);
		TUtil.assertProgramData(p, 173, 0, 16);
	}

	@Test
	public void ldaAbsoluteLabelPlusY() throws IOException {
		FluentBuilder b = Program.withFluent();
		Label label = Label.name("label");
		b.label(label.getName());
		b.lda().absolute_Y(label.getName());
		Program p = b.end(4096);
		TUtil.assertProgramData(p, 185, 0, 16);
	}

	/**
	 * Bug test, unignore and fix
	 */
	@Ignore
	@Test
	public void ldaAbsoluteLabelPlusYFailsIfLabelNotDefined() {
		FluentBuilder b = Program.withFluent();
		Label label = Label.name("label");
		b.lda().absolute_Y(label.getName());
		try {
			b.end(4096);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("todo", e.getMessage());
		}
	}

}
