package com.algo.util.indextree.model;

import com.algo.util.bit.BitUtil;

//�±��1��ʼ��
public class IndexTree {

	private int[] tree;
	private int N;

	// 0λ���������ã�
	public IndexTree(int size) {
		N = size;
		tree = new int[N + 1];
	}

	// 1~index �ۼӺ��Ƕ��٣�
	public int sum(int index) {
		int ret = 0;
		while (index > 0) {
			ret += tree[index];
			index -= BitUtil.rightMostOne(index); // ÿ��ȥ�����Ҳ��1
		}
		return ret;
	}

	/**
	 * ��indexλ�õ�ֵ����һ��d
	 */
	// index & -index : ��ȡ��index���Ҳ��1����
	// index :           0011001000
	// index & -index :  0000001000
	// tree[] = help[] ���˲����Ǽ������ֺ��ҳ�tree��������ǣ����λ�ã�Ȼ�����
	public void add(int index, int d) {
		while (index <= N) {
			tree[index] += d;
			index += BitUtil.rightMostOne(index);
		}
	}
}
