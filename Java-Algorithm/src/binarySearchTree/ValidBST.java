package binarySearchTree;

/**
 * validate BST
 * @author Song Lei
 *
 */
public class ValidBST {
	public static void main(String[] args) {
		
	}
	
	public static boolean validateBst(BST tree) {
		return validateBSThelper(tree,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	private static boolean validateBSThelper(BST tree,int min,int max) {
		if (tree == null) {
			return true; // 完整的BST
		}

		if (tree.getValue() < min || tree.getValue() > max) {
			return false;
		}
		// 左子树不断缩小最大值为当前根节点值做判断
		boolean leftSubTreeValid = validateBSThelper(tree.getLeft(), min, tree.getValue());
		// 右子树不断缩小最小值为当前节点值做判断
		boolean rightSubTreeValid = validateBSThelper(tree.getRight(), tree.getValue(), max);
		
		return leftSubTreeValid && rightSubTreeValid;
	}
}
