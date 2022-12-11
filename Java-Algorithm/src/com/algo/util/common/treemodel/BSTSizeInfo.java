package com.algo.util.common.treemodel;

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
