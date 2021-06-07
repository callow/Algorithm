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
			return true; // ������BST
		}

		if (tree.getValue() < min || tree.getValue() > max) {
			return false;
		}
		// ������������С���ֵΪ��ǰ���ڵ�ֵ���ж�
		boolean leftSubTreeValid = validateBSThelper(tree.getLeft(), min, tree.getValue());
		// ������������С��СֵΪ��ǰ�ڵ�ֵ���ж�
		boolean rightSubTreeValid = validateBSThelper(tree.getRight(), tree.getValue(), max);
		
		return leftSubTreeValid && rightSubTreeValid;
	}
}
