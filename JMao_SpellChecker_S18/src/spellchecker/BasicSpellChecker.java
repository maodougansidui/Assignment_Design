package spellchecker;

import static sbcc.Core.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicSpellChecker implements SpellChecker {

	private Dictionary dict;
	private String text;
	private int index, indexOfList;
	private String[] words;


	@Override
	public void importDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		dict = new BasicDictionary();
		dict.importFile(filename);

	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		dict = new BasicDictionary();
		dict.load(filename);
	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		dict.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		// TODO Auto-generated method stub
		text = readFile(filename);

	}


	@Override
	public void saveDocument(String filename) throws Exception {
		// TODO Auto-generated method stub
		writeFile(filename, text);
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		// TODO Auto-generated method stub
		String[] spell = new String[4];
		if (continueFromPrevious == false) {
			index = 0;
			indexOfList = 0;
			words = text.split("\\s+");
			for (int i = 0; i < words.length; i++) {
				words[i] = words[i].replaceAll("[^\\w]", "");
			}
			while (index < text.length()) {
				String[] method = dict.find(words[indexOfList]);
				if (index == text.length() - 1) {
					return null;
				} else if (index == 0 && Character.isLetter(text.charAt(index))) {
					if (method == null) {
						index += words[indexOfList].length();
						indexOfList++;
					} else {
						spell[0] = words[indexOfList];
						spell[1] = Integer.toString(index);
						spell[2] = method[0];
						spell[3] = method[1];
						break;
					}
				} else if (!Character.isLetter(text.charAt(index - 1)) && Character.isLetter(text.charAt(index))) {
					if (method == null) {
						index += words[indexOfList].length();
						if (indexOfList != words.length - 1) {
							indexOfList++;
						}
					} else {
						spell[0] = words[indexOfList];
						spell[1] = Integer.toString(index);
						spell[2] = method[0];
						spell[3] = method[1];
						index += words[indexOfList].length();
						if (indexOfList != words.length - 1) {
							indexOfList++;
						}
						break;
					}
				} else {
					index++;
				}

			}

		} else {
			while (index < text.length()) {
				String[] method = dict.find(words[indexOfList]);
				if (index == text.length() - 1) {
					return null;
				} else if (!Character.isLetter(text.charAt(index - 1)) && Character.isLetter(text.charAt(index))) {
					if (method == null) {
						index += words[indexOfList].length();
						if (indexOfList != words.length - 1) {
							indexOfList++;
						}
					} else {
						spell[0] = words[indexOfList];
						spell[1] = Integer.toString(index);
						spell[2] = method[0];
						spell[3] = method[1];
						index += words[indexOfList].length();
						if (indexOfList != words.length - 1) {
							indexOfList++;
						}
						break;
					}
				} else {
					index++;
				}

			}

		}
		return spell;
	}


	@Override
	public void addWordToDictionary(String word) {
		// TODO Auto-generated method stub
		dict.add(word);

	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		// TODO Auto-generated method stub
		StringBuilder correctText = new StringBuilder(text);
		correctText.replace(startIndex, endIndex, replacementText);
		text = correctText.toString();
		if (indexOfList != 0) {
			int lengthChanges = replacementText.length() - words[indexOfList - 1].length();
			index += lengthChanges;
		}

	}

}
