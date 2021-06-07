package binarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BSTTraversal {
	/**
		10
		/ \
		5  15
	   / \  \
	   2  5  22
	  /
	  1
  **/
	public static void main(String[] args) {
		BST root = new BST(10);
	    root.setLeft(new BST(5));
	    root.getLeft().setLeft(new BST(2)) ;
	    root.getLeft().getLeft().setLeft(new BST(1));
	    root.getLeft().setRight(new BST(5));
	    root.setRight(new BST(15));
	    root.getRight().setRight(new BST(22));
	    
	    List<Integer> array = new ArrayList<Integer>();
	    inOrderTraverse(root, array);
	    System.out.println(array.toString());
	    array.clear();
	    
	    preOrderTraverse(root, array);
	    System.out.println(array.toString());
	    array.clear();
	    
	    postOrderTraverse(root, array);
	    System.out.println(array.toString());
	    array.clear();
	}
	
	// O(n) time , O(n) space
	  public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		if (tree != null) {
			// tree.getLeft().getLeft() 不停加进array里面
			inOrderTraverse(tree.getLeft(), array);
			array.add(tree.getValue());
			inOrderTraverse(tree.getRight(), array);
		}
		return array;
	  }

	  public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
			if (tree != null) {
				array.add(tree.getValue());
				// tree.getLeft().getLeft() 不停加进array里面
				preOrderTraverse(tree.getLeft(), array);
				preOrderTraverse(tree.getRight(), array);
			}
			return array;
	  }

	  public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
			if (tree != null) {
				// tree.getLeft().getLeft() 不停加进array里面
				postOrderTraverse(tree.getLeft(), array);
				postOrderTraverse(tree.getRight(), array);
				array.add(tree.getValue());
			}
			return array;
	  }
	  
	  
}
