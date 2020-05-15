package dnasplicing;

public interface DnaStrand {

	/**
	 * NOTE: Your LinkedDnaStrand class must have a constructor that takes one parameter: String dnaSequence. When the
	 * constructor completes, your linked list should have just one node, and it should contain the passed-in
	 * dnaSequence. For example, if the following line of code was executed:
	 * 
	 * LinkedDnaStrand strand = new LinkedDnaStrand("GATTACA");
	 * 
	 * Then strand's linked list should look something like (previous pointers not shown):
	 * 
	 * first -> "GATTACA" -> null
	 * 
	 * The first line of this constructor should look like:
	 * 
	 * public LinkedDnaStrand(String dnaSequence) {
	 */

	/**
	 * @return The entire DNA sequence represented by this DnaStrand.
	 */
	public String toString();


	/**
	 * Returns the number of nucleotides in this strand.
	 * 
	 * @return the number of base-pairs in this strand
	 */
	public long getNucleotideCount();


	/**
	 * Appends the given dnaSequence on to the end of this DnaStrand. appendCount is incremented. Note: If this
	 * DnaStrand is empty, append() should just do the same thing as the constructor. In this special case, appendCount
	 * is not incremented.
	 * 
	 * @param dnaSequence
	 *            is the DNA string to append
	 */
	public void append(String dnaSequence);


	/**
	 * This method creates a <bold>new</bold> DnaStrand that is a clone of the current DnaStrand, but with every
	 * instance of enzyme replaced by splicee. For example, if the LinkedDnaStrand is instantiated with "TTGATCC", and
	 * cutSplice("GAT", "TTAAGG") is called, then the linked list should become something like (previous pointers not
	 * shown):
	 * 
	 * first -> "TT" -> "TTAAGG" -> "CC" -> null
	 * 
	 * <b>NOTE</b>: This method will only be called when the linke list has just one node, and it will only be called
	 * once for a DnaStrand. This means that you do not need to worry about searching for enzyme matches across node
	 * boundaries.
	 * 
	 * @param enzyme
	 *            is the DNA sequence to search for in this DnaStrand.
	 * 
	 * @param splicee
	 *            is the DNA sequence to append in place of the enzyme in the returned DnaStrand
	 * 
	 * @return A <bold>new</bold> strand leaving the original strand unchanged.
	 */
	public DnaStrand cutSplice(String enzyme, String splicee);


	/**
	 * Returns a <bold>new</bold> DnaStrand that is the reverse of this strand, e.g., if this DnaStrand contains "CGAT",
	 * then the returned DnaStrand should contain "TAGC".
	 * 
	 * @return A <bold>new</bold> strand containing a reversed DNA sequence.
	 */
	public DnaStrand createReversedDnaStrand();


	/**
	 * 
	 * @return The number of times that the DnaStrand has been appended via a call to append() or during the cutSplice()
	 *         operation. Note that the very first time that a DnaStrand is given a DNA sequence is not to be counted as
	 *         an append.
	 */
	public int getAppendCount();


	/**
	 * This is a utility method that allows the outside world direct access to the nodes in the linked list.
	 * 
	 * @return The first DnaSequenceNode in the linked list of nodes.
	 */
	public DnaSequenceNode getFirstNode();


	/**
	 * This is a utility method that allows the outside world to determine the number of nodes in the linked list.
	 * 
	 * @return
	 */
	public int getNodeCount();

}
