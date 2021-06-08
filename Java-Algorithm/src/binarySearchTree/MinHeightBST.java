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
	// 前序遍历，在遍历之前就把value加进去
	public static BST build(List<Integer> array, BST tree, int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		int midValue = array.get(mid);
		
		if (null == tree) { // start of the whole tree
			tree = new BST(midValue);
		} else {
			tree.insert(midValue); // 自动决定插在哪里,我们从不用管
		}
		// 本节点的逻辑结束，开市递归
		build(array,tree,start, mid -1); // 左左左 不停构建左子树 对半再对半.... 前序遍历左子树
		build(array,tree,mid +1,end); // 右右右 不停构建右子树 对半再对半.... 前序遍历右子树
		return tree;
	}


}



