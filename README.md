c64dslj
=======

*A Domain-specific language for Commodore 64 in Java*

Write C64 assembler easily in an IDE in Java with the help of c64dslj.

License: Apache 2.0.

Building
--------

c64dslj can be built either with Maven or SBT.

Create fat jar with Maven
```
mvn install
```

or with SBT in SBT console
```
assembly
```

Either of these will generate one jar to `target` directory called `c64dslj-0.9.jar`.

Add the jar to your project libraries and start coding.

Examples
--------

Simple example:

```java
import static org.pirinen.c64dslj.builder.WordHex1.*;
import static org.pirinen.c64dslj.builder.WordHexD.*;
import static org.pirinen.c64dslj.helper.Color.*;
import static org.pirinen.c64dslj.model.Instruction.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.pirinen.c64dslj.model.Program;

public class FirstExample {
    
    public static void main(String[] args) throws IOException {
        Program p = Program.with()
                .i(LDA.immediate(BLACK))
                .i(STA.absolute ($d020))
                .i(STA.absolute ($d021))
                .i(RTS)
                .build($1000);
        OutputStream out = new FileOutputStream("firstex.cbm");
        p.toCbmFormat(out);
        out.close();
    }
}
```
The resulting file, firstex.cbm, can be loaded into a C64 emulator and run with SYS 4096.

There are more examples are in the org.pirinen.c64dslj.example package.

Quick tutorial
--------------

### Creating new programs with builder

Class `Program` is used to create a new C64 program.

`Program` contains the following methods:
  * `with` - static method which initiates new program builder
  * `instruction` or just `i` - adds an instruction to program
  * `label` or `l` - add a label which can be used as a reference
  * `oneByte` or `b` - add one data byte
  * `data` or `d` - add several bytes of data
  * `build` - returns a Program object 
  * `msb` - returns most significant byte of a label
  * `lsb` - returns least significant byte of a label

All of these methods are explained below.

### Main classes of c64dslj

In addition to `Program` class, the main classes of c64dslj are:
 * `Instruction`
 * `ByteHex` and `ByteDec`
 * `WordHex0` - `WordHexF`

As most Java DSLs, c64dslj relies heavily on *static imports* and the classes mentioned above are usually used via static import like this:
  
```java
import static org.pirinen.c64dslj.builder.ByteHex.*;
import static org.pirinen.c64dslj.builder.ByteDec.*;
import static org.pirinen.c64dslj.model.Instruction.*;
import static org.pirinen.c64dslj.builder.WordHex0.*;
```

`Instruction` class contains all 6510 instructions. c64dslj is implemented in an IDE friendly way, ie. when typing
```java
LDA.<ctrl+space>
```
methods are displayed according to addressing modes of LDA instruction in Eclipse.

`ByteHex` is a class containing static members $00, $01, ...,$ff. `ByteDec` contains static members _0, _1, ..., _255. These can be used instead of integer parameter where byte value is required.

For example, LDA immediate can be written in three ways:
```java
.i(LDA.immediate(15)) // Java int
.i(LDA.immediate($0f)) // ByteHex
.i(LDA.immediate(_15) // ByteDec
```

Zeropage memory locations can also be referenced with `ByteHex` or `ByteDec`:
```java
.i(STA.zeropage(2)) // Java int
.i(STA.zeropage($02)) // ByteHex
.i(STA.zeropage(_02)) // ByteDec
```

`WordHex0` contains static members $0100 - $01ff. Classes `WordHex1` - `WordHexF` contain the rest of C64 address space. These can be used when referencing two-byte memory addresses. 

STA absolute can be written in two ways:
```java
.i(STA.absolute(53280)) // Java int
.i(STA.absolute($d020)) // WordHexD
```

### Addressing modes

Each instruction has methods corresponding the addressing modes. The methods are:
 * immediate
 * indirect
 * absolute
 * absolute_Y
 * absolute_X
 * zeropage
 * zeropage_X
 * zeropage_Y
 * indexedIndirect_X
 * indirectIndexed_Y
 * implied

If an instruction has only implied addressing mode (TYA, SEI etc.) instruction does not contain any methods. It can be used simply like this:
```java
.i(TYA)
.i(SEI)
```

There are four instructions that have other addressing modes besides implied: ASL, ROL, LSR and ROR. For these instructions there are two possibilities for using implied addressing mode:
 * use method implied
 * use alternative instruction ending with underscore

For example, using ROL:
```java
.i(ROL_) // or
.i(ROL.implied())
```

### Labels

Labels can be used to forward and back refer to a memory location. Labels are created with
```java
.l("loop") // or
.label("loop")
```

Labels can be referenced in conditional operations, for example
```java
.i(BNE.label("loop"))
```

Labels can be used instead of addresses. Together with `oneByte` or `b` rudimentary "variables" can be created
```java
.l("spaceships")
.b($05)
...
.i(DEC.absolute("spaceships"))
.i(LDA.absolute("spaceships"))
.i(BNE.label("gameon"))
.i(JMP.absolute("gameover"))
```
Zeropage addresses cannot be referred with a label, ie. this is not currently possible:
```java
.i(STA.zeropage("value"))
```

MSB and LSB can be extracted from a label. Class `Program` contains static methods `msb` and `lsb` for that purpose. Again, they can be imported statically.
```java
import static org.pirinen.c64dslj.model.Program.msb;
import static org.pirinen.c64dslj.model.Program.lsb;
```
They are useful in initializing interrupt routines etc.
```java
...
.i(LDA.immediate(lsb("main")))
.i(STA.absolute ($0314))
.i(LDA.immediate(msb("main")))
.i(STA.absolute ($0315))
...
.l("main")
```
 

### Data

Data can be added with `d` or `data` method. Data always requires an address. Address of the data must point to memory address which is after the instructions. Overlapping with instructions and other data entries is checked during program build. Unused space between program and data entries is filled with zeroes.

Data can be inserted as a hex string(s):
```java
Program.with()
 .d(16384, hex(
     "1122aabb"
     "331243ff"))
 .build();


### Helpers

Class `Color` contains static members for all 16 colors of Commodore 64.

import static org.pirinen.c64dslj.helper.Color.*;
```java
.i(LDA.immediate(BLACK))
```

Sprites can be created with SpriteBuilder which generates Data

Example
```java
import static org.pirinen.c64dslj.helper.Sprite.monochromeSprite;
...
Program p = Program.with()
   .d($2000, monochromeSprite().pixels(
      "..XXXXX.......XXXXXXXX..", // 24 pixels
      // 21 rows altogether ... 
      ).build())
   .build($2000);
...
```

### Ideas for future development

 * More example programs
 * Colour sprites
 * Better error handling
 * More unit tests
 * Reverse engineering support, ie. convert C64 binary into DSL

That's all folks! Happy coding!





