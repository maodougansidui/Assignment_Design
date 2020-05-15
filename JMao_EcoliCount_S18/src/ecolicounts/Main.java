package ecolicounts;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Main {

	public static void main(String[] args) throws IOException {
		String input = readLine();
		String dnaText = readFile(input);
		dnaText = dnaText.trim();

		char a = 'A';
		char c = 'C';
		char g = 'G';
		char t = 'T';

		int countA = 0;
		int countC = 0;
		int countG = 0;
		int countT = 0;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < dnaText.length(); i++) {
			char indexWord = dnaText.charAt(i);
			if (indexWord == a) {
				countA++;
			}
			if (indexWord == c) {
				countC++;
			}
			if (indexWord == g) {
				countG++;
			}
			if (indexWord == t) {
				countT++;
			}
		}

		sb.append("#A = " + countA + "\r\n" + "#C = " + countC + "\r\n" + "#G = " + countG + "\r\n" + "#T = " + countT);

		println(sb);

	}

}
