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
    		  if (currentNode.getLeft() == null) { // ��ͷ��
    			  currentNode.setLeft(new BST(value)); // ����
    			  break;
    		  } else  {
    			  currentNode = currentNode.getLeft(); // ��������
    		  }
    	  } else { // insert to right
    		  if (currentNode.getRight() == null) { // ��ͷ��
    			  currentNode.setRight(new BST(value)); // ����
    			  break;
    		  } else {
    			  currentNode = currentNode.getRight(); // ��������
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
    	  } else { // �ҵ���
    		  if (currentNode.getLeft() != null && currentNode.getRight() != null) { // case 3����������
    			  currentNode.setValue(currentNode.getRight().getRightMinValue());
    			  currentNode.getRight().remove(currentNode.getValue(), parentNode);
    		  } 
    		  else if (parentNode == null) { // ��ǰ�ڵ�û��parent node, �� ���ڵ㣡��ֻ��һ������
    			  if (currentNode.getLeft() != null) { // ����һ�����������
    				  // ȫ���滻������ֵ
    				  currentNode.setValue(currentNode.getLeft().getValue());
    				  currentNode.setRight(currentNode.getLeft().getRight());
    				  currentNode.setLeft(currentNode.getLeft().getLeft()); // setLeft������󣬱����ǰ���и���
    			  } else if (currentNode.getRight() != null) { // ����һ���������ұ�
    				  // ȫ���滻������ֵ
    				  currentNode.setValue(currentNode.getRight().getValue());
    				  currentNode.setLeft(currentNode.getRight().getLeft());
    				  currentNode.setRight(currentNode.getRight().getRight()); // setRight������󣬱����ǰ���и���
    			  } else { // �Ƴ����ڵ㣬���޺���, = ��������delete��
    				  currentNode = null;
    			  }
    		  }
    		  else if (parentNode.getLeft() == currentNode) { // ֻ��һ�����ӣ�ɾ���󣬺��Ӽ޽ӵ����ڵ�
    			  parentNode.setLeft(currentNode.getLeft() != null ? currentNode.getLeft() : currentNode.getRight());
    		  }
    		  else if (parentNode.getRight() == currentNode) { // ֻ��һ�����ӣ�ɾ���󣬺��Ӽ޽ӵ����ڵ�
    			  parentNode.setRight(currentNode.getLeft() != null ? currentNode.getLeft() : currentNode.getRight());
    		  }
    		  break;
    	  }
      }
      return this;
    }
    /**
     * Ѱ������������Сֵ��Ҳ����������������leftһֱ����ȥ����ȫ��ȥ���κ�right leaf
     */
    public int getRightMinValue() {
    	if (this.getLeft() == null) {
    		return this.getValue();
    	} else {
    		return this.getLeft().getRightMinValue();
    	}
    }

}
