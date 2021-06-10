package binarySearchTree;

import java.util.List;

/**
 * 给一个Array 是根据前序遍历生成的，根据数组顺序，生成BST
 * @author Song Lei
 * 
 * 直接调用insert 方法解决
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
		    		  if (currentNode.left == null) { // 到头了
		    			  currentNode.left = new BST(value); // 插入
		    			  break;
		    		  } else  {
		    			  currentNode = currentNode.left; // 继续向左
		    		  }
		    	  } else { // insert to right
		    		  if (currentNode.right == null) { // 到头了
		    			  currentNode.right = new BST(value); // 插入
		    			  break;
		    		  } else {
		    			  currentNode = currentNode.right; // 继续向右
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
