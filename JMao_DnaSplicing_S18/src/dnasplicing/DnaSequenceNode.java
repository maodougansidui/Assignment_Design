package dnasplicing;

public class DnaSequenceNode {
	public String dnaSequence;
	public DnaSequenceNode previous;
	public DnaSequenceNode next;


	public DnaSequenceNode(String initialDnaSequence) {
		dnaSequence = initialDnaSequence;
	}

}
