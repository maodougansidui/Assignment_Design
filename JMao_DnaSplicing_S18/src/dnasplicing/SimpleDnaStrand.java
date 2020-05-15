package dnasplicing;

public class SimpleDnaStrand implements DnaStrand {

	private StringBuilder nucleotides;

	private int appendCount;


	/**
	 * Create a strand representing an empty DNA strand, length of zero.
	 */
	public SimpleDnaStrand() {
		this("");
	}


	/**
	 * Create a strand representing dnaSequence. No error checking is done to see if s represents valid genomic/DNA
	 * data.
	 * 
	 * @param dnaSequence
	 *            Initial DNA sequence for this strand
	 */
	public SimpleDnaStrand(String dnaSequence) {
		nucleotides = new StringBuilder(dnaSequence);
	}


	public DnaStrand cutSplice(String enzyme, String splicee) {
		int pos = 0;
		int start = 0;
		StringBuilder search = nucleotides;
		boolean first = true;
		SimpleDnaStrand ret = null;

		/*
		 * The next line is very syntax-dense. .indexOf looks for the first index at which enzyme occurs, starting at
		 * pos. Saying pos = ... assigns the result of that operation to the pos variable; the value of pos is then
		 * compared against zero.
		 * 
		 * .indexOf returns -1 if enzyme can't be found. Therefore, this line is:
		 * 
		 * "While I can find enzyme, assign the location where it occurs to pos, and then execute the body of the loop."
		 */
		while ((pos = search.indexOf(enzyme, pos)) >= 0) {
			if (first) {
				ret = new SimpleDnaStrand(search.substring(start, pos));
				first = false;
			} else {
				ret.append(search.substring(start, pos));

			}
			start = pos + enzyme.length();
			ret.append(splicee);
			pos++;
		}

		if (start < search.length()) {
			// NOTE: This is an important special case! If the enzyme
			// is never found, return an empty String.
			if (ret == null) {
				ret = new SimpleDnaStrand("");
			} else {
				ret.append(search.substring(start));
			}
		}
		return ret;
	}


	/**
	 * Initialize this strand so that it represents the value of source. No error checking is performed.
	 * 
	 * @param source
	 *            is the source of this enzyme
	 */
	public void initialize(String dnaSequence) {
		nucleotides = new StringBuilder(dnaSequence);
	}


	/**
	 * Returns the number of nucleotides/base-pairs in this strand.
	 */
	public long getNucleotideCount() {
		return nucleotides.length();
	}


	@Override
	public String toString() {
		return nucleotides.toString();
	}


	/**
	 * Append a strand of dna data to this strand.
	 * 
	 * @param dnaSequence
	 *            is the String appended to this strand
	 */
	public void append(String dnaSequence) {
		nucleotides.append(dnaSequence);
		appendCount++;
	}


	public DnaStrand createReversedDnaStrand() {
		StringBuilder copy = new StringBuilder(nucleotides);
		SimpleDnaStrand ss = new SimpleDnaStrand();
		ss.nucleotides = copy;
		ss.nucleotides.reverse();
		return ss;
	}


	public int getAppendCount() {
		return appendCount;
	}


	@Override
	public DnaSequenceNode getFirstNode() {
		// Since this implementation does not contain a list of nodes, just return null.
		return null;
	}


	@Override
	public int getNodeCount() {
		// Since this implementation does not contain a list of nodes, just return 0.
		return 0;
	}

}
