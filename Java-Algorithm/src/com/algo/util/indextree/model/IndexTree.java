package com.algo.util.indextree.model;

import com.algo.util.bit.BitUtil;

//下标从1开始！
public class IndexTree {

	private int[] tree;
	private int N;

	// 0位置弃而不用！
	public IndexTree(int size) {
		N = size;
		tree = new int[N + 1];
	}

	// 1~index 累加和是多少？
	public int sum(int index) {
		int ret = 0;
		while (index > 0) {
			ret += tree[index];
			index -= BitUtil.rightMostOne(index); // 每次去掉最右侧的1
		}
		return ret;
	}

	/**
	 * 把index位置的值增加一个d
	 */
	// index & -index : 提取出index最右侧的1出来
	// index :           0011001000
	// index & -index :  0000001000
	// tree[] = help[] ，此步骤是加了数字后找出tree中所有受牵连的位置，然后调整
	public void add(int index, int d) {
		while (index <= N) {
			tree[index] += d;
			index += BitUtil.rightMostOne(index);
		}
	}
}
