package binarySearchTree;

public class BST {
	
	private int value;
	private BST left;
	private BST right;
	
	public BST(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public BST getLeft() {
		return left;
	}
	public void setLeft(BST left) {
		this.left = left;
	}
	public BST getRight() {
		return right;
	}
	public void setRight(BST right) {
		this.right = right;
	}
	
	// average: O(logn), worst: O(n)
	public BST insert(int value) {
      BST currentNode = this;
      while (true) {
    	  if (value < currentNode.getValue()) { // insert to left
    		  if (currentNode.getLeft() == null) { // 到头了
    			  currentNode.setLeft(new BST(value)); // 插入
    			  break;
    		  } else  {
    			  currentNode = currentNode.getLeft(); // 继续向左
    		  }
    	  } else { // insert to right
    		  if (currentNode.getRight() == null) { // 到头了
    			  currentNode.setRight(new BST(value)); // 插入
    			  break;
    		  } else {
    			  currentNode = currentNode.getRight(); // 继续向右
    		  }
    	  }
      }
      return this;
    }

	// average: O(logn), worst: O(n)
    public boolean contains(int value) {
      BST currentNode = this;
      while(currentNode != null) {
    	  if (value < currentNode.getValue()) { // go to left to search
    		  currentNode = currentNode.getLeft(); // explore the left
    	  } else if (value > currentNode.getValue()) { // go to right to search
    		  currentNode = currentNode.getRight(); // explore the right
    	  } else {
    		  return true; // find it!!
    	  }
      }
      return false;
    }
    
    public BST remove(int value) {
    	remove(value,null);
    	return this;
    }

    private BST remove(int value, BST parentNode) {
      BST currentNode = this;
      while (currentNode != null) {
    	  if (value < currentNode.getValue()) { // value on the left
    		  parentNode = currentNode;
    		  currentNode = currentNode.getLeft(); // go to left
    	  } else if (value > currentNode.getValue()) { // value on the right
    		  parentNode = currentNode;
    		  currentNode = currentNode.getRight(); // go to right
    	  } else { // 找到了
    		  if (currentNode.getLeft() != null && currentNode.getRight() != null) { // case 3，两个孩子
    			  currentNode.setValue(currentNode.getRight().getRightMinValue());
    			  currentNode.getRight().remove(currentNode.getValue(), parentNode);
    		  } 
    		  else if (parentNode == null) { // 当前节点没有parent node, 即 根节点！且只有一个孩子
    			  if (currentNode.getLeft() != null) { // 二层一个孩子在左边
    				  // 全部替换成左树值
    				  currentNode.setValue(currentNode.getLeft().getValue());
    				  currentNode.setRight(currentNode.getLeft().getRight());
    				  currentNode.setLeft(currentNode.getLeft().getLeft()); // setLeft放最最后，避免对前两行干扰
    			  } else if (currentNode.getRight() != null) { // 二层一个孩子在右边
    				  // 全部替换成右树值
    				  currentNode.setValue(currentNode.getRight().getValue());
    				  currentNode.setLeft(currentNode.getRight().getLeft());
    				  currentNode.setRight(currentNode.getRight().getRight()); // setRight放最最后，避免对前两行干扰
    			  } else { // 移除根节点，且无孩子, = 整个树都delete了
    				  currentNode = null;
    			  }
    		  }
    		  else if (parentNode.getLeft() == currentNode) { // 只有一个孩子，删除后，孩子嫁接到父节点
    			  parentNode.setLeft(currentNode.getLeft() != null ? currentNode.getLeft() : currentNode.getRight());
    		  }
    		  else if (parentNode.getRight() == currentNode) { // 只有一个孩子，删除后，孩子嫁接到父节点
    			  parentNode.setRight(currentNode.getLeft() != null ? currentNode.getLeft() : currentNode.getRight());
    		  }
    		  break;
    	  }
      }
      return this;
    }
    /**
     * 寻找右子树中最小值，也就是沿着右子树的left一直找下去，完全不去看任何right leaf
     */
    public int getRightMinValue() {
    	if (this.getLeft() == null) {
    		return this.getValue();
    	} else {
    		return this.getLeft().getRightMinValue();
    	}
    }

}
