package com.algo.util.binarytree.model;

public class BSTSizeInfo {
	public int maxBSTSubtreeSize;
	public int allSize; // 子树的节点总数
	public int max;
	public int min;

	public BSTSizeInfo(int m, int a, int ma, int mi) {
		maxBSTSubtreeSize = m;
		allSize = a;
		max = ma;
		min = mi;
	}
}
