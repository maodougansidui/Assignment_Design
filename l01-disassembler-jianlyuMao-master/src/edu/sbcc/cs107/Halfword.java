package edu.sbcc.cs107;

import java.lang.StringBuilder;

/**
 * @author Jianlyu Mao
 * CS 107: Disassembler Project
 *
 * This class is used to model a half-word of an object file. Each half-word must have an address as well as a data
 * value that can be disassembled into mnemonics and optional operands.
 * 
 * Note that the half-word is 16 bits but we are using a Java int which is typically 32 bits. Be sure to take that into
 * account when working with it.
 *
 */
public class Halfword {
	private int address;
	private int data;
	
	/**
	 * Constructor for a halfword.
	 * 
	 * @param address
	 * @param data
	 */
	public Halfword(int address, int data) {
		/* Your code here */
		this.address=address;
		this.data=data;
	}
	
	/** 
	 * toString method.
	 * 
	 * The format for the halfword is a hex value 8 characters wide (address), a single space, and a hex
	 * value four characters wide (data).
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/* Your code here */
		StringBuilder sb=new StringBuilder(); 		// initialize a new StringBuilder to concatenate strings.
		
        String addToS=String.format("%04X", address); // using String.format to convert integer to hexadecimal. And it will automatically
        
        String datToS=String.format("%04X", data);		// upper case it.		
        
        return sb.append("0000").append(addToS).append(" ").append(datToS).toString(); // Address will be 8 characters wide, such as 0000FFFF,
        																			   // and a single space, and 4 characters wide, such as 1111;
	}

	/**
	 * Get the address of the half-word.
	 * 
	 * @return
	 */
	public int getAddress() {
		/* Your code here */
		return address;
	}
	
	/**
	 * Get the data of the half-word.
	 * 
	 * @return
	 */
	public int getData() {
		/* Your code here */
		return data;
	}

}
