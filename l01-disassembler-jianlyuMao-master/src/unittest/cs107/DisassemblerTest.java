package unittest.cs107;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.sbcc.cs107.Disassembler;
import edu.sbcc.cs107.Halfword;
import edu.sbcc.cs107.HexFile;

/**
 * @author Dean Nevins
 * 
 * This is some test code to see if your code is working correctly. It lightly tests
 *
 */
public class DisassemblerTest {
	private static HexFile hf = null;
	private static Disassembler d = null;
	private static float assignmentTotal = 20;
	private static float studentTotal = 0;
	
	@BeforeClass
	  public static void setUpClass() throws FileNotFoundException {
		hf = new HexFile("sample1.hex");
		d = new Disassembler();
	  }
	
	@AfterClass
	  public static void tearDownClass() {
		System.out.println(String.format("Total: %.0f/%.0f (%.0f%%)\n", studentTotal, assignmentTotal, studentTotal * 100 / assignmentTotal));
		System.out.println("Note that the score above is a portion of the total grade. Other factors included");
		System.out.println("in grading are comments, code structure, and the algorithms used to implement a");
		System.out.println("solution.");
	}

	@Test
	 public void testHalfwordConstructor() {
		Halfword hw = new Halfword(0xFFFF, 0x1234);
		assertTrue("Halfword data should be 0xFFFF", hw.getAddress() == 0xFFFF);
		studentTotal += 1;
		assertTrue("Halfword data should be 0x1234", hw.getData() == 0x1234);
		studentTotal += 1;
	 }
		
	@Test
	public void testHalfwordPrinting() {
		Halfword hw = new Halfword(0xFEED, 0xA5A5);
		assertEquals("Halfword formatting is not correct.", "0000FEED A5A5", hw.toString());
		studentTotal += 1;
	}
	
	@Test
	public void testHexFileGetRecordMethods() {
		String hexRecord = ":10246200464C5549442050524F46494C4500464C33";
		assertEquals("Record item \"LL\" isn't extracted properly.", 16, hf.getDataBytesOfRecord(hexRecord));
		studentTotal += 1;
		assertEquals("Record item \"AAAA\" isn't extracted properly.", 9314, hf.getAddressOfRecord(hexRecord));
		studentTotal += 1;
		assertEquals("Record item \"TT\" isn't extracted properly.", 0, hf.getRecordType(hexRecord));
		studentTotal += 1;
	}
	
	@Test
	public void testHexFileGetNextRecord() {
		assertEquals("Incorrect data field extracted from hex file", "00000000 0400", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "00000002 1000", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "00000004 00E5", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "00000006 0000", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "00000008 00E9", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "0000000A 0000", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "0000000C 00EB", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "0000000E 0000", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "00000010 00ED", hf.getNextHalfword().toString());
		studentTotal += 1;
		assertEquals("Incorrect data field extracted from hex file", "00000012 0000", hf.getNextHalfword().toString());
		studentTotal += 1;
	}

	@Test
	public void testDisassemblerGetRegister() {
		Halfword hw = new Halfword(0xFFFF, 0xAAA6);
		assertEquals("Register needs the letter and number to be correct.", "r3", d.getRegister(hw, 1, 3));
		studentTotal += 1;
	}

	@Test
	public void testDisassemblerGetImmediate() {
		Halfword hw = new Halfword(0xFFFF, 0xAAA6);
		assertEquals("Immediate needs the hash mark and number to be correct.", "#19", d.getImmediate(hw, 1, 5));
		studentTotal += 1;
	}

	@Test
	public void testDisassemblerDisassembleToString() {
		Halfword hw = new Halfword(0xFFFF, 0x4146);
		assertEquals("The terms and formatting must match exactly.", "ADCS     r6, r0", d.dissassembleToString(hw));
		studentTotal += 1;
		hw = new Halfword(0xFFFF, 0x0007);
		assertEquals("The terms and formatting must match exactly.", "MOVS     r7, r0", d.dissassembleToString(hw));
		studentTotal += 1;
	}

}


