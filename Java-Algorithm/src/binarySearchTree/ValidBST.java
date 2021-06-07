package binarySearchTree;

/**
 * validate BST
 * @author Song Lei
 *
 */
public class ValidBST {
	public static void main(String[] args) {
		BST root = new ValidBST.BST(10);
	    root.left = new ValidBST.BST(5);
	    root.left.left = new ValidBST.BST(2);
	    root.left.left.left = new ValidBST.BST(1);
	    root.left.right = new ValidBST.BST(5);
	    root.right = new ValidBST.BST(15);
	    root.right.left = new ValidBST.BST(13);
	    root.right.left.right = new ValidBST.BST(14);
	    root.right.right = new ValidBST.BST(22);
	    
	    System.out.println(ValidBST.validateBst(root));
	}
	
	 public static boolean validateBst(BST tree) {
	    return validate(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
	  }
		
		public static boolean validate(BST tree, int min, int max) {
			if (tree == null) {
				return true;
			}
			if (tree.value < min || tree.value >= max) {
				return false;
			}
			return validate(tree.left,min,tree.value) && validate(tree.right,tree.value,max);
		}
		  static class BST {
			    public int value;
			    public BST left;
			    public BST right;

			    public BST(int value) {
			      this.value = value;
			    }
			  }
}
