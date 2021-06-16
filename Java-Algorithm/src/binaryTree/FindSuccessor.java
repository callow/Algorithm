package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class FindSuccessor {
	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;
		public BinaryTree parent = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
		List<BinaryTree> list = new ArrayList<>();
		inOrderTraverse(tree, list);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != node) {
				continue;
			}
			if (i == list.size() - 1) {
				return null;
			}
			return list.get(i + 1);
		}
		return null;
	}

	public static void inOrderTraverse(BinaryTree tree, List<BinaryTree> list) {
		if (tree != null) {
			inOrderTraverse(tree.left, list);
			list.add(tree);
			inOrderTraverse(tree.right, list);
		}
	}

}
