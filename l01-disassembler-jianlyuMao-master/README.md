# L01: Disassembler #

**READ THIS WHOLE LAB BEFORE STARTING IT**

The purpose of this lab is to solidify the concept that unique bit patterns correspond to mnemonics (and their associated operands). Additionally, this lab also reinforces the idea of the utility of other languages. We are going to write a program in *Java* rather than assembly. It is helpful to see that languages are just a tool and can be used outside of their isolated classroom context.

## Lab Outcome ##

This lab will have you writing a simple disassembler which takes an [Intel Hex] formatted file as an input and prints out the mnemonics and their associated operands.

The [Intel Hex] format is used (along with other formats like AXF, and ELF) to load the machine code into the processors memory. The [Intel Hex] format has very little extra information in it; just the address of where the machine code goes and the machine code itself. The file is a text based file. A small sample of one looks like the following:

```
:020000040000FA
:1000000000040010E5000000E9000000EB00000023
:10001000ED000000EF000000F10000009EF3FFEF94
:10002000000000000000000000000000F3000000DD
:10003000F500000000000000F7000000F9000000DB
```

That format is translated into mnemonics like the following:

```
00000400 4145     ADCS     r5, r0
00000402 1C88     ADDS     r0, r1, #2
00000404 42E0     CMN      r0, r4
```

Note that the HEX code shown above *doesn't match* the translated code. The first column of the disassembly is the memory address in hexadecimal format. The second column is the machine code in hexadecimal format. The third column is the mnemonic and the fourth column is the operands.

We will only care about machine code that resides in memory addresses greater than 0x0400. We will also stop disassembling after we reach the machine code 0xE7FE which corresponds to a branch to yourself (B .).

### Important Notes ###
<ol>
<li>We will restrict our machine code to just a few **16-bit instructions** to make the problem more tractable. The instructions used are the following:
  <ul>
    <li>ADCS - (register) Encoding T1 (A7-187)
    <li>ADDS - (Immediate) Encoding T1 (A7-189)
    <li>CMN - (register) Encoding T1 (A7-227)
    <li>LDRSB - (register) Encoding T1 (A7-286)
    <li>MOVS - (Immediate) Encoding T1 (A7-312)
    <li>MOVS (register) - Encoding T2 (A7-314)
    <li>REV - Encoding T1 (A7-363)
  </ul>
The pages referenced come from the [ARM®v7-M Architecture Reference Manual]. The link goes to a PDF copy of the manual. **DO NOT PRINT THIS OUT**. There should also be a link on the desktop of the windows machines in the lab. You'll need to look up these definitions to understand the machine code pattern. Note that any of the above instructions can be combined in any order with varying (though legal) operands.

<li>The "programs" that will be present in the hex file may or may not make any sense but they will be legal hex files with machine code corresponding to the mnemonics listed above.

<li>We will show only code that appears after memory address 0x0400. This is so we can skip a bunch of code that isn't important.

<li>We will stop disassembling once we've decoded the machine code 0xE7FE. This corresponds to the mnemonic "B" and the operand ".".
</ol>

## Lab Procedure ##

If you are reading this then you should have accepted the assignment from github classroom and are reading this from your own repository. You can use GitHub Desktop to clone the project into a folder on your Windows PC.

### Import the Eclipse project ###

Once it's been cloned then import the project into Eclipse. Once that is done go through all of the source code and look for the name Leonard Euler and change that name to yours. Once that is completed do a commit and push that commit to your repo.

In the process of changing the author and project information be sure to look over the files to get an idea of what you'll need to implement.

### Implement the Disassembler ###

In order to implement the disassembler you'll need to understand several things before tackling it. You should implement `Halfword.java` first followed by `HexFile.java`, and finally `Disassembler.java`.

The Java source file `Halfword.java` implements a simple data structure which contains the address of the machine code as well as its address. This is used as a way of passing around machine code data.

The Java source file `HexFile.java` is used to implement the logic that parses the [Intel Hex] file. You should read the document describing the [Intel Hex] file and work through the examples **manually** so that you understand the file format and how it works. Once you understand it the implementation should become clearer. Also, note that references to "the documentation" refer to the [Intel Hex] file definition.

The Java source file `Disassembler.java` contains a method to disassemble a given Halfword in to a String as well as a few helper functions to make the string generation easier.

### Test the Disassembler Using JUnit ###

There is a set of [JUnit] tests that will run and the points you get from the tests will contribute to the final grade. More importantly, by testing as you implement you should be able to get a more robust final product.

The development should follow the path of:
1. Implement `Halfword.java`.
2. Test using the [JUnit] tests.
3. Once all have passed processed to the next module `HexFile.java`.
4. Rinse and repeat for all modules.

### Test the Disassembler ###

Once you've gotten all of the [JUnit] tests to work then you should run the main method in `Main.java`. This should give you the following output:

```
00000400 4145     ADCS     r5, r0
00000402 1C88     ADDS     r0, r1, #2
00000404 42E0     CMN      r0, r4
00000406 565F     LDRSB    r7, [r3, r1]
00000408 0008     MOVS     r0, r1
0000040A 0011     MOVS     r1, r2
0000040C 2000     MOVS     r0, #0
0000040E BA16     REV      r6, r2
00000410 E7FE     B        .
```

Notice that the disassembly starts at 0x0400 and terminates after the machine code 0xE7FE is processed. Your output format should look exactly like this (other than the font). In particular, the spacing should match this exactly.

## Submitting Your Assignment ##

A big advantage to using git (and GitHub) is that you don't have to submit anything if your assignment is completed on time. The system will automatically record the current master branch at the time the assignment is due. I will expect to see multiple commits to your repo which will show me that you've been working on the assignment.

Make sure that you have pushed all your changes to your repository since **the only work that counts is what you push to your repository**. After you've pushed your assignment to your repository you can go to the assignment page and type anything into the text box. You can say "I'm Done", your pipeline username, anything. The system will turn in your work automatically.

## Submitting Your Assignment Late ##

If you need to turn your assignment in late then you must type the first seven digits of the commit (technically its hash) you want to submit into the text box on the assignment page. You can find the first seven digits by looking on your repo page. It's the number shown in the red box.

![Git Commit Hash](img/commithash.png)

If you are not sure ask me or the LTAs for help.

## Grading the Assignment ##

Roughly 1/3<sup>rd</sup> of the assignment is given by the output of the unit test, 1/3<sup>rd</sup> given by the output of the test file, and 1/3<sup>rd</sup> from other considerations like the algorithm used to implement the various methods. If you have any questions please don't hesitate to ask me in class or the lab.

[JUnit]: http://junit.org/junit4/
[export_01]: http://wfs.sbcc.edu/faculty/dinevins/cs107/Lab%2001%20Disassembler/artwork/export_screen_01.png
[export_02]: http://wfs.sbcc.edu/faculty/dinevins/cs107/Lab%2001%20Disassembler/artwork/export_screen_02.png
[Intel Hex]: http://www.keil.com/support/docs/1584/
[ARM®v7-M Architecture Reference Manual]: http://wfs.sbcc.edu/faculty/dinevins/cs107/Reference%20Materials/DDI0403E_B_armv7m_arm.pdf
[fall2049_leuler_disassembler]: http://wfs.sbcc.edu/faculty/dinevins/cs107/Lab%2001%20Disassembler/fall2049_leuler_disassembler.zip
[Naming Your Project]: https://canvas.sbcc.edu/courses/9176/pages/naming-your-project
