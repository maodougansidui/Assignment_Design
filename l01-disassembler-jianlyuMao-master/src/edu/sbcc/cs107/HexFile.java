package edu.sbcc.cs107;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jianlyu Mao
 * CS 107: Disassembler Project
 *
 * This code implements working with a Hex file. The hex file format is documented 
 * at http://www.keil.com/support/docs/1584/
 */
public class HexFile {
	/**
	 * This is where you load the hex file. By making it an ArrayList you can easily traverse it in order.
	 */
	private ArrayList<String> hexFile = null; // Create an array of each elements contained one line in hex file.
	private File inHexF;				  // Create a File Object.	
	private int lineIndex=0;				// create an index to track down arraylist hexfile record.
	private int hwIndex=0; 					// for each line, half word index go through the data. 
	
	/* Add other variables here. */

	/**
	 * Constructor that loads the .hex file.
	 * 
	 * @param hexFileName
	 * @throws FileNotFoundException
	 */
	public HexFile(String hexFileName) throws FileNotFoundException {
		/* Your code here */
		try {
				inHexF= new File(hexFileName);				// load the hex file.
				Scanner input= new Scanner (inHexF);
				hexFile=new ArrayList<String>();		// Create Scanner Object and scan next line.
				while (input.hasNextLine()) {
					hexFile.add(input.nextLine());		// add every line to the array list hexFile.
				}
				input.close();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Pulls the length of the data bytes from an individual record.
	 * 
	 * This extracts the length of the data byte field from an individual 
	 * hex record. This is referred to as LL->Record Length in the documentation.
	 * 
	 * @param Hex file record (one line).
	 * @return record length.
	 */
	public int getDataBytesOfRecord(String record) {
		/* Your code here */
		String firstTwoD=record.substring(1, 3);		// extract record's 2nd and 3rd characters.
		int decimal=Integer.parseInt(firstTwoD,16);		// turn them into hexadecimal, then into decimal.
		return decimal;
	}
	
	/**
	 * Get the starting address of the data bytes.
	 * 
	 * Extracts the starting address for the data. This tells you where the data bytes 
	 * start and are referred to as AAAA->Address in the documentation.
	 * 
	 * @param Hex file record (one line).
	 * @return Starting address of where the data bytes go.
	 */
	public int getAddressOfRecord(String record) {
		/* Your code here */
		String add=record.substring(3, 7);			// same thing as function getDataBytesOfRecord.
		int decimal=Integer.parseInt(add,16);
		return decimal;
	}
	
	/**
	 * Gets the record type.
	 * 
	 * The record type tells you what the record can do and determines what happens
	 * to the data in the data field. This is referred to as DD->Data in the 
	 * documentation.
	 * 
	 * @param Hex file record (one line).
	 * @return Record type.
	 */
	public int getRecordType(String record) {
		/* Your code here */
		String dataType=record.substring(7, 9);		// same thing as function getDataBytesOfRecord.
		int decimal=Integer.parseInt(dataType,16);
		return decimal;
	}

	/**
	 * Returns the next halfword data byte.
	 * 
	 * This function will extract the next halfword from the Hex file. By repeatedly calling this
	 * function it will look like we are getting a series of halfwords. Behind the scenes we must 
	 * parse the HEX file so that we are extracting the data from the data files as well as indicating
	 * the correct address. This requires us to handle the various record types. Some record types
	 * can effect the address only. These need to be processed and skipped. Only data from recordType
	 * 0 will result in something returned. When finished processing null is returned.
	 * 
	 * @return Next halfword.
	 */
	public Halfword getNextHalfword() {
		/* Your code here */
		String line; int address = 0; String dat =null; Halfword hw; // initialize all the variables to avoid NullPointerException.
		if (lineIndex<hexFile.size()) {			// lineIndex refers to the line number in hex file.
			line=hexFile.get(lineIndex);		// get the line string.
			while (getRecordType(line)!=0) {	// this while loop will loop until find the required data type 00.
				lineIndex++;
				line=hexFile.get(lineIndex);
			}
			
			address=getAddressOfRecord(line);	// get the base address.
			dat=line.substring(9,line.length()-2).substring(4*hwIndex,4*hwIndex+4); // get the data in form XXXX, and halfword index get to the next.
			address+=hwIndex*2; 				// add the base address and byte length.
			dat=swap(dat);			// swap the first and last bytes. 
			
			
			hwIndex++;
			if (hwIndex*2>=getDataBytesOfRecord(line)) { // if the halfword index exceed the length, go to the next line.
				hwIndex=0;
				lineIndex++;
			}
		}else {
			lineIndex=0;				// if lineIndex ends at the last line. get back.
			return null;
		}
		
		hw=new Halfword(address,Integer.parseInt(dat,16));		// create an object called hw.
		
		return hw;
		
	}
	
	/**
	 * Returns the string after halfword got swapped.
	 * 
	 * @return String.
	 */
	
	public String swap(String hw) {			// This function will swap for this specific form XXXX.
		StringBuilder swapped= new StringBuilder();
		swapped.append(hw.substring(2, 4)).append(hw.substring(0,2));
		
		return swapped.toString();
	}
}
