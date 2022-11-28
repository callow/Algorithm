package com.algo.util.disjointset.model;

/**
 * 
 * ʹ������ʵ�ֲ��鼯�����������գ� int N
 */
public class UnionFinds {
	// parent[i] = k �� i�ĸ�����k
	private int[] parent;
	// size[i] = k �� ���i�Ǵ���ڵ㣬size[i]�������壬����������
	// i���ڵļ��ϴ�С�Ƕ���
	private int[] size;
	// �����ṹ
	private int[] help;
	// һ���ж��ٸ�����
	private int sets;

	public UnionFinds(int N) {
		parent = new int[N];
		size = new int[N];
		help = new int[N];
		sets = N;
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	// ��i��ʼһֱ���ϣ����ϵ����������ϣ�����ڵ㣬����
	// �������Ҫ��·��ѹ��
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

	public void union(int i, int j) {
		int f1 = find(i);
		int f2 = find(j);
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

	public int numberOfSets() {
		return sets;
	}
}
