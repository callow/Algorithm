package binarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Construct a BST from a sorted array
 *
 */
public class MinHeightBST {
		
	public static void main(String[] args) {
		int[] a = {1, 2, 5, 7, 10, 13, 14, 15, 22};
		List<Integer> as = new ArrayList<Integer>();
		for (int ad : a) {
			as.add(ad);
		}
		minHeightBst(as);
	}

	public static BST minHeightBst(List<Integer> array) {
	    return build(array,null,0, array.size() -1);
	}
	// ǰ��������ڱ���֮ǰ�Ͱ�value�ӽ�ȥ
	public static BST build(List<Integer> array, BST tree, int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		int midValue = array.get(mid);
		
		if (null == tree) { // start of the whole tree
			tree = new BST(midValue);
		} else {
			tree.insert(midValue); // �Զ�������������,���ǴӲ��ù�
		}
		// ���ڵ���߼����������еݹ�
		build(array,tree,start, mid -1); // ������ ��ͣ���������� �԰��ٶ԰�.... ǰ�����������
		build(array,tree,mid +1,end); // ������ ��ͣ���������� �԰��ٶ԰�.... ǰ�����������
		return tree;
	}


}



