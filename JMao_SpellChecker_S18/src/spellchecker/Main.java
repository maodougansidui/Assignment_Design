package spellchecker;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {
	public static void main(String[] args) throws Exception {

		String how = "a\r\n" + "able\r\n" + "ancient\r\n" + "and\r\n" + "are\r\n" + "as\r\n" + "Astronomers \r\n"
				+ "back \r\n" + "called\r\n" + "chemical\r\n" + "cosmos \r\n" + "dozen\r\n" + "elements\r\n"
				+ "ever\r\n" + "explosions\r\n" + "have \r\n" + "helped\r\n" + "layers \r\n" + "markers\r\n"
				+ "measure\r\n" + "mile\r\n" + "most\r\n" + "of \r\n" + "peeled \r\n" + "reveal \r\n" + "scientists\r\n"
				+ "seed\r\n" + "seen\r\n" + "star\r\n" + "supernovas\r\n" + "the\r\n" + "them\r\n" + "These\r\n"
				+ "time \r\n" + "to \r\n" + "universe\r\n" + "use\r\n" + "with";
		String trimed = how.trim();

		String[] list = trimed.split("\r\n");

		Arrays.sort(list);

		String word = "explosins";

		for (String shut : list) {
			println(shut);
		}

		String[] pre = find(list, word);

		if (pre == null) {
			println("fuck yeah");
		} else {
			println(pre[0]);
			println(pre[1]);
		}
	}


	public static String[] find(String[] list, String word) {
		String[] preSuccWords = new String[2];

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
}