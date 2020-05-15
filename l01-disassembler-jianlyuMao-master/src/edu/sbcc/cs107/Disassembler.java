package edu.sbcc.cs107;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * @author Jianlyu Mao
 * CS 107: Disassembler Project
 * 
 * This code implements the disassembler as well as pulling apart the Hex file. The hex file format
 * is documented at http://www.keil.com/support/docs/1584/
 */
public class Disassembler {
	
	// I will create a hashmap object to translate machine code to instructions.
	private HashMap<String,String> dissem= new HashMap<String,String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("0100000101","ADCS");
		put("0001110","ADDS");
		put("0100001011","CMN");
		put("0101011","LDRSB");
		put("00100","MOVS");			// this MOVs is for immediate.
		put("0000000000","MOVS");		// this MOVS is for register.
		put("1011101000","REV");
		put("1110011111111110","B");	// this is 0xE7FE
	}};

	/**
	 * Extracts the register operand from a halfword.
	 * 
	 * The register operand (e.g. r0) is used by many mnemonics and is embedded in the data halfword.
	 * It position is specified by the least significant bit and most significant bit. This value is
	 * extracted and concatenated with "r" to give us the desired register.
	 * 
	 * @param hw Halfword that contains the machine code data.
	 * @param lsBitPosition Encoded register value (LSB)
	 * @param msBitPosition Encoded register value (MSB)
	 * @return Register field designation (e.g. r1)
	 */
	public String getRegister(Halfword hw, int lsBitPosition, int msBitPosition) {
		/* Your code here */
		int dat=hw.getData();				// get the data from Halfword in form XXXX.
		String register=String.format("%16s",Integer.toBinaryString(dat)).replace(' ', '0');		// transform it in binary
		int len=register.length();
		register=register.substring(len-msBitPosition-1, len-lsBitPosition);	// because the lsBitPosition is at the end.
		dat=Integer.parseInt(register,2);		// output the decimal from binary we extract.
		
		return "r"+dat;
	}
	
	/**
	 * Extracts the immediate operand from a halfword.
	 * 
	 * Same as the getRegister function but returns the embedded immediate value (e.g. #4).
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	 * @param hw Halfword that contains the machine code data.
	 * @param lsBitPosition Encoded immediate value (LSB)
	 * @param msBitPosition Encoded immediate value (MSB)
	 * @return Immediate field designation (e.g. #12)
	 */
	public String getImmediate(Halfword hw, int lsBitPosition, int msBitPosition) {
		/* Your code here */
		int dat=hw.getData();				// Basically this function will do the same job as getRegister(), but what it
		String register=String.format("%16s",Integer.toBinaryString(dat)).replace(' ', '0');		// does is to find the immediate.
		int len=register.length();
		register=register.substring(len-msBitPosition-1, len-lsBitPosition);	
		dat=Integer.parseInt(register,2);		
		return "#"+dat;
	}

	/**
	 * Returns a formatted string consisting of the Mnemonic and Operands for the given halfword.
	 * 
	 * The halfword is decoded into its corresponding mnemonic and any optional operands. The return
	 * value is a formatted string with an 8 character wide field for the mnemonic (left justified) a
	 * single space and then any operands.
	 * 
	 * @param hw Halfword that contains the machine code data.
	 * @return Formatted string containing the mnemonic and any operands.
	 */
	public String dissassembleToString(Halfword hw) {			
		/* Your code here */
		
		String mnemonic=String.format("%16s",Integer.toBinaryString(hw.getData())).replace(' ', '0');
		// it occurs to me that function toBinaryString will automatically get rid of the leading 0 and cause a bug. So I make a little adjustment.
		
		ArrayList<String> listOfStr= new ArrayList<String>(); // here , a[0]= instructions, a[1]=r0, a[2]=r1, a[3]=r3,a[4]=imm;
		
		for (String i:dissem.keySet()) {					// loop through every keys in hashmap. (some of if cases are overlapped or the same 
															// and I will change them. )
			
			if (mnemonic.substring(0, 10).equals(i)) {		// case ADCS   case CMN		case MOVS (register)	case REV
				listOfStr.add(dissem.get(i));				// get ADCS
				listOfStr.add(getRegister(hw,0,2));			// get r0
				listOfStr.add(getRegister(hw,3,5));			// get r1
				
				mnemonic=String.format("%-9s%s, %s",listOfStr.get(0),listOfStr.get(1),listOfStr.get(2));
				listOfStr.clear(); 							// in case it causes some unknown bugs, I will clear every elements in arraylist.
				
			}else if (mnemonic.substring(0, 7).equals(i)) {		// basically do the same thing below.
				listOfStr.add(dissem.get(i));
				listOfStr.add(getRegister(hw,0,2));
				listOfStr.add(getRegister(hw,3,5));
				listOfStr.add(getImmediate(hw,6,8));
				listOfStr.add(getRegister(hw,6,8));
				
				if (listOfStr.get(0).equals("ADDS")) {
					mnemonic=String.format("%-9s%s, %s, %s",listOfStr.get(0),listOfStr.get(1),listOfStr.get(2),listOfStr.get(3));  // case ADDS
				}else {
					mnemonic=String.format("%-9s%s, [%s, %s]",listOfStr.get(0),listOfStr.get(1),listOfStr.get(2),listOfStr.get(4));		// case LDRSB
				}
				listOfStr.clear();
				
			}else if (mnemonic.substring(0,5).equals(i)) {			// case MOVS (immediate)
				listOfStr.add(dissem.get(i));
				listOfStr.add(getImmediate(hw,0,7));
				listOfStr.add(getRegister(hw,8,10));
				
				mnemonic=String.format("%-9s%s, %s",listOfStr.get(0),listOfStr.get(2),listOfStr.get(1));
				listOfStr.clear();
			}else if (mnemonic.equals(i)) {						// case B
				listOfStr.add(dissem.get(i));
				mnemonic=String.format("%-9s%s", listOfStr.get(0),".");
				listOfStr.clear();
			}
		}
		return mnemonic;
	}
	
}
