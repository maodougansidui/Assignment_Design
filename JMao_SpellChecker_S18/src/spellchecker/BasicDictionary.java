package spellchecker;

import static sbcc.Core.*;

import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicDictionary implements Dictionary {

	private BinaryTreeNode rootOfTree;
	private String[] list;


	@Override
	public void importFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		list = readFile(filename).split("\r\n");
		int start = 0;
		int end = list.length - 1;
		rootOfTree = new BinaryTree().sortedArrayToTree(list, start, end);

	}


	@Override
	public void load(String filename) throws Exception {
		// TODO Auto-generated method stub

		list = readFile(filename).split("\r\n");
		for (String word : list) {
			rootOfTree = new BinaryTree().insert(rootOfTree, word);
		}

	}


	@Override
	public void save(String filename) throws Exception {
		// TODO Auto-generated method stub
		String text = new BinaryTree().saveTreePreOrder(rootOfTree);
		writeFile(filename, text.trim());
	}


	@Override
	public String[] find(String word) {
		// TODO Auto-generated method stub
		String[] preSuccWords = new String[2];
		list = new BinaryTree().saveTreePreOrder(rootOfTree).split("\r\n");
		Arrays.sort(list);
		for (int i = 0; i < list.length; i++) {
			if (word.compareToIgnoreCase(list[i].trim()) == 0) {
				return null;
			} else if (word.compareToIgnoreCase(list[0].trim()) < 0) {
				preSuccWords[0] = "";
				preSuccWords[1] = list[0].trim();

			} else if (word.compareToIgnoreCase(list[list.length - 1].trim()) > 0) {
				preSuccWords[0] = list[list.length - 1].trim();
				preSuccWords[1] = "";

			} else if (word.compareToIgnoreCase(list[i].trim()) > 0
					&& word.compareToIgnoreCase(list[i + 1].trim()) < 0) {
				preSuccWords[0] = list[i].trim();
				preSuccWords[1] = list[i + 1].trim();
			}
		}
		return preSuccWords;
	}


	@Override
	public void add(String word) {
		// TODO Auto-generated method stub
		rootOfTree = new BinaryTree().insert(rootOfTree, word);

	}


	@Override
	public BinaryTreeNode getRoot() {
		// TODO Auto-generated method stub

		return rootOfTree;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int numbers = new BinaryTree().countNodes(rootOfTree);
		return numbers;
	}

}
