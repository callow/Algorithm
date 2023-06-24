package com.algo.tasks.day4;

/**
 * 
 * ���ɳ���ΪSize�Ĵ�����飺 ��i < k < j, �� arr[i] + arr[j] <> 2 * arr[k]
 *
 */
public class MakeNo {

	// ���ɳ���Ϊsize�Ĵ������
	// ��꣺��������� i<k<j������ [i] + [j] != [k] * 2
	public static int[] makeNo(int size) {
		if (size == 1) {
			return new int[] { 1 }; // �����һ�����ӣ������1
		}
		// size
		// һ�볤�����
		// 7 : 4
		// 8 : 4
		// [4������] [3��ż]
		int halfSize = (size + 1) / 2; // ����ȡ��
		int[] base = makeNo(halfSize);
		// base -> �ȳ����������
		// base -> �ȳ�ż�������
		int[] ans = new int[size];
		int index = 0;
		for (; index < halfSize; index++) {
			ans[index] = base[index] * 2 - 1;
		}
		for (int i = 0; index < size; index++, i++) {
			ans[index] = base[i] * 2;
		}
		return ans;
	}
}
