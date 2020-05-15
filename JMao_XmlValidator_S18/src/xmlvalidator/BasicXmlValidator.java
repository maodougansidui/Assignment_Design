package xmlvalidator;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicXmlValidator implements XmlValidator {

	private String[] output;


	@Override
	public String[] validate(String xmlDocument) {

		BasicStringStack stack = validFile(xmlDocument);
		int maxLength = 5;

		output = new String[maxLength];
		if (stack.getCount() == 0) {
			output = null;

		} else if (stack.getCount() == 1 && stack.peek(0).contains("/")) {
			output[0] = "Orphan closing tag";
			output[1] = stack.peek(0).substring(1);
			output[2] = getLineNumber(xmlDocument, stack.peek(0));

		} else if (stack.getCount() == 2 && !stack.peek(0).contains("/")) {
			output[0] = "Unclosed tag at end";
			output[1] = stack.peek(0);
			output[2] = getLineNumber(xmlDocument, stack.peek(0));

		} else if (stack.getCount() > 2) {
			output[0] = "Tag mismatch";
			output[1] = stack.peek(1);
			output[2] = getLineNumber(xmlDocument, stack.peek(1));
			output[3] = stack.peek(0).substring(1);
			output[4] = getLineNumber(xmlDocument, stack.peek(0));

		}
		return output;

	}


	public BasicStringStack validFile(String document) {
		BasicStringStack stack = new BasicStringStack();
		String[] tags = substringsBetween(document, "<", ">");

		ArrayList<String> sortedTags = new ArrayList<String>();

		for (String each : tags) {
			if (each.charAt(0) == '/') {
				sortedTags.add(each);
			} else if ((Character.isLetter(each.charAt(0))) && (each.charAt(each.length() - 1) != '/')) {
				sortedTags.add(each);
			}
		}

		stack.push(sortedTags.get(0));

		for (int i = 1; i < sortedTags.size(); i++) {
			String sortedTag = sortedTags.get(i);
			if (sortedTag.startsWith("/")) {
				if (stack.getCount() != 0) {
					if (stack.peek(0).contains(sortedTag.substring(1))) {
						stack.pop();
					} else {
						stack.push(sortedTag);
						break;
					}
				} else {
					stack.push(sortedTag);
				}

			} else {
				stack.push(sortedTag);
			}
		}

		return stack;

	}


	public String getLineNumber(String document, String input) {
		String[] lines = document.split("\r\n");

		int lineNumber = 0;

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(input)) {
				lineNumber = i + 1;
			}
		}
		if (lineNumber == 0) {
			return null;
		} else {
			return Integer.toString(lineNumber);
		}
	}


	@Override
	public String toString() {
		return Arrays.toString(output);
	}

}
