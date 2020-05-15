package spellchecker;

import static sbcc.Core.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BinaryTree {

	public BinaryTreeNode sortedArrayToTree(String[] list, int start, int end) {

		// Base Case
		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		BinaryTreeNode cursor = new BinaryTreeNode(list[mid]);

		cursor.left = sortedArrayToTree(list, start, mid - 1);
		cursor.right = sortedArrayToTree(list, mid + 1, end);

		return cursor;
	}


	// Count how many words in dictionary.
	public int countNodes(BinaryTreeNode root) {
		int count = 0;

		if (root != null) {
			count++;
			count = count + countNodes(root.left);
			count = count + countNodes(root.right);
		}
		return count;
	}


	// save tree as a preorder to a String.

	public String saveTreePreOrder(BinaryTreeNode cursor) {

		StringBuilder lines = new StringBuilder();
		if (cursor == null) {
			return "";
		} else {
			lines.append(cursor.value).append("\r\n");
			lines.append(saveTreePreOrder(cursor.left));
			lines.append(saveTreePreOrder(cursor.right));
		}
		return lines.toString();
	}


	// insert new words into dictionary, duplicate ignored.

	public BinaryTreeNode insert(BinaryTreeNode cursor, String word) {

		if (cursor == null) {
			cursor = new BinaryTreeNode(word);
			return cursor;
		}

		if (word.compareTo(cursor.value) < 0) {
			cursor.left = insert(cursor.left, word);
		} else if (word.compareTo(cursor.value) > 0) {
			cursor.right = insert(cursor.right, word);
		}
		return cursor;

	}

}
