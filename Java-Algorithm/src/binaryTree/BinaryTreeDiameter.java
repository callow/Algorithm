package binaryTree;

public class BinaryTreeDiameter {

	// This is an input class. Do not edit.
	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	private static int diameter = 0;

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(1);
		tree.left = new BinaryTree(2);
		tree.left.left = new BinaryTree(4);
		tree.left.right = new BinaryTree(5);

		tree.right = new BinaryTree(3);
		tree.right.left = new BinaryTree(6);
		tree.right.right = new BinaryTree(7);
		System.out.println(binaryTreeDiameter(tree));
	}

	public static int binaryTreeDiameter(BinaryTree tree) {
		if (tree == null) {
			return 0;
		}
		diameter(tree);
		return diameter;
	}

	private static int diameter(BinaryTree tree) {
		if (tree == null) {
			return 0;
		}

		int largestLeft = diameter(tree.left);
		int largestRight = diameter(tree.right);

		diameter = Math.max(diameter, largestLeft + largestRight);

		return Math.max(largestLeft, largestRight) + 1;
	}
}
