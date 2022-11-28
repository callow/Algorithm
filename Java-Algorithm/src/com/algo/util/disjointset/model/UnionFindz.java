package com.algo.util.disjointset.model;
/**
 * 
 * 使用数组实现并查集，构造器接收： m行 n列
 */

public class UnionFindz {
	private int[] parent;
	private int[] size;
	private int[] help;
	private final int row;
	private final int col;
	private int sets;

	public UnionFindz(int m, int n) {
		row = m;
		col = n;
		sets = 0;
		int len = row * col;
		parent = new int[len];
		size = new int[len];
		help = new int[len];
	}

	private int index(int r, int c) {
		return r * col + c;
	}

	private int find(int i) {
		int hi = 0;
		while (i != parent[i]) {
			help[hi++] = i;
			i = parent[i];
		}
		for (hi--; hi >= 0; hi--) {
			parent[help[hi]] = i;
		}
		return i;
	}

	private void union(int r1, int c1, int r2, int c2) {
		// 检查不越界
		if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
			return;
		}
		
		int i1 = index(r1, c1);
		int i2 = index(r2, c2);
		
		// 若两个有一个没初始化，没办法连 直接return
		if (size[i1] == 0 || size[i2] == 0) {
			return;
		}
		
		// 找各自的父亲
		int f1 = find(i1);
		int f2 = find(i2);
		
		if (f1 != f2) {
			// 合并
			if (size[f1] >= size[f2]) {
				size[f1] += size[f2];
				parent[f2] = f1;
			} else {
				size[f2] += size[f1];
				parent[f1] = f2;
			}
			// 合并后sets数量-1
			sets--;
		}
	}

	/**
	 * (r,c) 来了一个1 
	 */
	public int connect(int r, int c) {
		int index = index(r, c);
		if (size[index] == 0) { // 之前没有出现过，第一次来到这个地方
			parent[index] = index;
			size[index] = 1;
			sets++;
			
			// 看看我是否可以和我的上下左右合在一起
			union(r - 1, c, r, c);
			union(r + 1, c, r, c);
			union(r, c - 1, r, c);
			union(r, c + 1, r, c);
		}
		return sets;
	}
}
