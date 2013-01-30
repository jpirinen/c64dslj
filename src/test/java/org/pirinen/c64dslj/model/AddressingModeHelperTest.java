package org.pirinen.c64dslj.model;

import org.junit.Assert;
import org.junit.Test;
import org.pirinen.c64dslj.model.AddressingMode;
import org.pirinen.c64dslj.model.AddressingModeHelper;
import org.pirinen.c64dslj.model.Immediate;
import org.pirinen.c64dslj.model.Implied;

public class AddressingModeHelperTest {
	
	@Test
	public void testImmediate() throws Exception {
		AddressingModeHelper helper = AddressingModeHelper.getInstance();
		AddressingMode mode = helper.get(0xA9);
		Assert.assertEquals(Immediate.class, mode.getClass());
	}
	
	@Test
	public void testImplied() throws Exception {
		AddressingModeHelper helper = AddressingModeHelper.getInstance();
		AddressingMode mode = helper.get(0xCA);
		Assert.assertEquals(Implied.class, mode.getClass());
	}
}
