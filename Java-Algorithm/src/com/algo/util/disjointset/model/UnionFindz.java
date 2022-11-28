package com.algo.util.disjointset.model;
/**
 * 
 * ʹ������ʵ�ֲ��鼯�����������գ� m�� n��
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
		// ��鲻Խ��
		if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
			return;
		}
		
		int i1 = index(r1, c1);
		int i2 = index(r2, c2);
		
		// ��������һ��û��ʼ����û�취�� ֱ��return
		if (size[i1] == 0 || size[i2] == 0) {
			return;
		}
		
		// �Ҹ��Եĸ���
		int f1 = find(i1);
		int f2 = find(i2);
		
		if (f1 != f2) {
			// �ϲ�
			if (size[f1] >= size[f2]) {
				size[f1] += size[f2];
				parent[f2] = f1;
			} else {
				size[f2] += size[f1];
				parent[f1] = f2;
			}
			// �ϲ���sets����-1
			sets--;
		}
	}

	/**
	 * (r,c) ����һ��1 
	 */
	public int connect(int r, int c) {
		int index = index(r, c);
		if (size[index] == 0) { // ֮ǰû�г��ֹ�����һ����������ط�
			parent[index] = index;
			size[index] = 1;
			sets++;
			
			// �������Ƿ���Ժ��ҵ��������Һ���һ��
			union(r - 1, c, r, c);
			union(r + 1, c, r, c);
			union(r, c - 1, r, c);
			union(r, c + 1, r, c);
		}
		return sets;
	}
}
