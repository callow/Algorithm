package com.algo.tasks.day12;
/**
 * ���ַ����У��ҵ�һ������Ϊm�������ִ���
 * 
 * https://leetcode.com/problems/permutation-in-string/
 *
 * ���� + hashmap
 */
public class ContainAllCharExactly {

	public static int containExactly3(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return -1;
		}
		char[] str2 = s2.toCharArray();
		int M = str2.length;
		int[] count = new int[256];
		for (int i = 0; i < M; i++) {
			count[str2[i]]++;
		}
		int all = M;
		char[] str1 = s1.toCharArray();
		int R = 0;
		// 0~M-1
		for (; R < M; R++) { // �����M���ַ������䴰�ڳ����γ�
			if (count[str1[R]]-- > 0) {
				all--;
			}
		}
		// ���ڳ����γ��ˣ���û���ж���Ч��Ч��������һ��λ��һ�����ж�
		// �������Ĺ��̣������ҽ�һ��������һ��
		for (; R < str1.length; R++) {
			if (all == 0) { // R-1
				return R - M;
			}
			if (count[str1[R]]-- > 0) {
				all--;
			}
			if (count[str1[R - M]]++ >= 0) {
				all++;
			}
		}
		return all == 0 ? R - M : -1;
	}
}
