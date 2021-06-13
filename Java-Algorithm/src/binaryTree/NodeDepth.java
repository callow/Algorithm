package binaryTree;

public class NodeDepth {

	public static void main(String[] args) {
		BinaryTree root = new NodeDepth.BinaryTree(1);
		root.left = new NodeDepth.BinaryTree(2);
		root.left.left = new NodeDepth.BinaryTree(4);
		root.left.left.left = new NodeDepth.BinaryTree(8);
		root.left.left.right = new NodeDepth.BinaryTree(9);
		root.left.right = new NodeDepth.BinaryTree(5);
		root.right = new NodeDepth.BinaryTree(3);
		root.right.left = new NodeDepth.BinaryTree(6);
		root.right.right = new NodeDepth.BinaryTree(7);
		int actual = NodeDepth.nodeDepths(root);
		// Utils.assertEquals(16, actual);
		System.out.println(actual);
	}

	public static int nodeDepths(BinaryTree root) {
		return getRootDepth(root, 0);
	}

	static int getRootDepth(BinaryTree root, int depth) {
		if (root == null) {
			return 0;
		}
		int leftDepths = getRootDepth(root.left, depth + 1);
		int rightDepths = getRootDepth(root.right, depth + 1);
		return depth + leftDepths + rightDepths;
	}

	static class BinaryTree {
		int value;
		BinaryTree left;
		BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
			left = null;
			right = null;
		}
	}
}
