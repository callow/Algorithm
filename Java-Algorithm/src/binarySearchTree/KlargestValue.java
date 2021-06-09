package binarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class KlargestValue {
		
	  // This is an input class. Do not edit.
	  static class BST {
	    public int value;
	    public BST left = null;
	    public BST right = null;
	
	    public BST(int value) {
	      this.value = value;
	    }
	  }

	  // ������������浹���ڶ���
	  public int findKthLargestValueInBst(BST tree, int k) {
		List<Integer> array = new ArrayList<Integer>();
	    return inOrderTraverse(tree,array).get(array.size() - k);
	  }
	  
	  // ��������������С��������
	  public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		  if (tree != null) {
		    	inOrderTraverse(tree.left, array);
				array.add(tree.value);
				inOrderTraverse(tree.right, array);
			}
		  return array;
	  }
}
