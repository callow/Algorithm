package binarySearchTree;

import java.util.List;

/**
 * ��һ��Array �Ǹ���ǰ��������ɵģ���������˳������BST
 * @author Song Lei
 * 
 * ֱ�ӵ���insert �������
 *
 */
public class ReconstructBST {

	  // This is an input class. Do not edit.
	  static class BST {
	    public int value;
	    public BST left = null;
	    public BST right = null;

	    public BST(int value) {
	      this.value = value;
	    }
	    
		public BST insert(int value) {
		      BST currentNode = this;
		      while (true) {
		    	  if (value < currentNode.value) { // insert to left
		    		  if (currentNode.left == null) { // ��ͷ��
		    			  currentNode.left = new BST(value); // ����
		    			  break;
		    		  } else  {
		    			  currentNode = currentNode.left; // ��������
		    		  }
		    	  } else { // insert to right
		    		  if (currentNode.right == null) { // ��ͷ��
		    			  currentNode.right = new BST(value); // ����
		    			  break;
		    		  } else {
		    			  currentNode = currentNode.right; // ��������
		    		  }
		    	  }
		      }
		      return this;
		    }
	  }

	  public BST reconstructBst(List<Integer> preOrderTraversalValues) {
		if (preOrderTraversalValues.isEmpty()) {
			return null;
		}
		BST tree = new BST(preOrderTraversalValues.get(0));
		for (int i = 1; i < preOrderTraversalValues.size(); i++) {
			tree.insert(preOrderTraversalValues.get(i));
		}
		return tree;
	  }
}
