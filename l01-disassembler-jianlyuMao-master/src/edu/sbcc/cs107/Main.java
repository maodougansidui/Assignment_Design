package edu.sbcc.cs107;

import java.io.IOException;

/**
 * @author Jianlyu Mao
 * 
 * This is code to run the disassembler on a sample hex file. You shouldn't need to change this code at all. Note that the
 * code only prints out assembly instructions that are present after the address 0x0400 and up until the machine code
 * 0xE7FE is found.
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		HexFile hf = new HexFile("sample1.hex");
		Disassembler d = new Disassembler();
		
		Halfword hw = null;  
		while ((hw = hf.getNextHalfword()) != null)  
		{ 
			if(hw.getAddress() >= 0x400) {
				System.out.printf("%s     %s\n", hw, d.dissassembleToString(hw));
				if(hw.getData() == 0xE7FE) {
					break;
				}
			}
		}
	}
}
