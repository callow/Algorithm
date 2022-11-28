package com.algo.util.disjointset.model;
/**
 * 
 * 使用数组实现并查集，构造器接收： char[][]
 */
public class UnionFindx {
	private int[] parent;
	private int[] size;
	private int[] help;
	private int col;
	private int sets;

	public UnionFindx(char[][] board) {
		col = board[0].length;
		sets = 0;
		int row = board.length;
		int len = row * col;
		parent = new int[len];
		size = new int[len];
		help = new int[len];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (board[r][c] == '1') {
					int i = index(r, c);
					parent[i] = i;
					size[i] = 1;
					sets++;
				}
			}
		}
	}

	// (r,c) -> i
	private int index(int r, int c) {
		return r * col + c;
	}

	// 原始位置 -> 下标
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

	public void union(int r1, int c1, int r2, int c2) {
		int i1 = index(r1, c1);
		int i2 = index(r2, c2);
		int f1 = find(i1);
		int f2 = find(i2);
		if (f1 != f2) {
			if (size[f1] >= size[f2]) {
				size[f1] += size[f2];
				parent[f2] = f1;
			} else {
				size[f2] += size[f1];
				parent[f1] = f2;
			}
			sets--;
		}
	}

	public int sets() {
		return sets;
	}

	
}
