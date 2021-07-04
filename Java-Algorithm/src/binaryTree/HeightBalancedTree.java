package binaryTree;

public class HeightBalancedTree {

	public boolean heightBalancedBinaryTree(BinaryTree tree) {
		TreeInfo treeInfo = getTreeInfo(tree);
		return treeInfo.isBalanced;
	}

	public TreeInfo getTreeInfo(BinaryTree node) {
		if (node == null) {
			return new TreeInfo(true, -1);
		}
		TreeInfo leftSubTree = getTreeInfo(node.left);
		TreeInfo rightSubTree = getTreeInfo(node.right);

		boolean heightOk = Math.abs(leftSubTree.height - rightSubTree.height) <= 1;
		boolean isBalanced = leftSubTree.isBalanced && rightSubTree.isBalanced && heightOk;
		int height = Math.max(leftSubTree.height, rightSubTree.height) + 1;
		return new TreeInfo(isBalanced, height);
	}

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	static class TreeInfo {
		public boolean isBalanced;
		public int height;

		public TreeInfo(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}

}
