package org.pirinen.c64dslj.fluent;

import static org.pirinen.c64dslj.builder.ByteHex.*;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;
import org.pirinen.c64dslj.fluent.FluentBuilder;
import org.pirinen.c64dslj.model.Program;

public class FluentBuilderTest {
	
	@Test
    public void testSimpleProgram() throws Exception {
        FluentBuilder b = Program.withFluent();
        b.lda().immediate($01)
         .sta().absolute(53281)
         .rts();
        OutputStream out = new FileOutputStream("firstex.cbm");
        b.end(4096).toCbmFormat(out);
        out.close();
        //byte[] data = b.toCbmFormat(4096).getData();
        
    }
}
