package dnasplicing;

import static java.lang.System.*;

public class LinkedDnaStrand implements DnaStrand {

	DnaSequenceNode top;
	DnaSequenceNode cursor;
	int nodeCount;
	int appendCount;


	public LinkedDnaStrand(String dnaSequence) {
		// TODO Auto-generated constructor stub
		top = new DnaSequenceNode(dnaSequence);
		cursor = top;
		nodeCount = 1;
		appendCount = 0;

	}


	@Override
	public long getNucleotideCount() {
		// TODO Auto-generated method stub
		DnaSequenceNode node = top;
		long nuclCount = 0;
		while (node != null) {
			long number = node.dnaSequence.length();
			nuclCount = nuclCount + number;
			node = node.next;
		}
		return nuclCount;
	}


	@Override
	public void append(String dnaSequence) {
		// TODO Auto-generated method stub

		DnaSequenceNode newNode = new DnaSequenceNode(dnaSequence);
		if (!cursor.dnaSequence.equals("")) {
			newNode.previous = cursor;
			newNode.next = null;
			cursor.next = newNode;
			cursor = cursor.next;
			nodeCount++;
			appendCount++;
		} else {
			cursor = newNode;
			if (top.dnaSequence.equals("")) {
				top = cursor;
			}
		}

	}


	@Override
	public DnaStrand cutSplice(String enzyme, String splicee) {
		// TODO Auto-generated method stub
		int pos = 0;
		int start = 0;
		StringBuilder dna = new StringBuilder(top.dnaSequence);
		boolean first = true;
		LinkedDnaStrand spliced = null;

		while ((pos = dna.indexOf(enzyme, pos)) >= 0) {
			if (first) {
				spliced = new LinkedDnaStrand(dna.substring(start, pos));
				first = false;
			} else {
				spliced.append(dna.substring(start, pos));
			}
			start = pos + enzyme.length();
			spliced.append(splicee);
			pos++;
		}
		if (start < dna.length()) {
			if (spliced == null) {
				spliced = new LinkedDnaStrand("");
			} else {
				spliced.append(dna.substring(start));
			}
		}

		return spliced;
	}


	@Override
	public DnaStrand createReversedDnaStrand() {
		// TODO Auto-generated method stub
		DnaSequenceNode node = cursor;
		LinkedDnaStrand reverse = new LinkedDnaStrand("");
		while (node != null) {
			StringBuffer rString = new StringBuffer();
			for (int i = node.dnaSequence.length() - 1; i >= 0; i--) {
				rString.append(node.dnaSequence.charAt(i));
			}
			reverse.append(rString.toString());
			node = node.previous;
		}
		return reverse;
	}


	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return appendCount;
	}


	@Override
	public DnaSequenceNode getFirstNode() {
		// TODO Auto-generated method stub

		return top;
	}


	@Override
	public int getNodeCount() {
		// TODO Auto-generated method stub
		return nodeCount;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		DnaSequenceNode node = top;
		while (node != null) {
			sb.append(node.dnaSequence);
			node = node.next;
		}

		return sb.toString();
	}

}
