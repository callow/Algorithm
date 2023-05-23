package com.algo.util.suffix_array;

import com.algo.util.common.CommonStringUtil;

/**
 *  Dictionary order/Lexicographical order: �ֵ���
 *  
 *  ��׺������������
 *  
 *  ��ɢ���� ����Ͱ���ݾ޴����ɢ�ģ�����ʹ��encode�����Ż� ����Ͱ������<br> 
 *  
 *   [100��,5,90��,10��] -> ת���� [3,1,2,4]
 *
 *
 */
public class SuffixUtil {

	/**
	 * �ҳ�������/�Ӵ� ���ֵ�����ߵ��Ǹ�
	 */
	public static String lexicographicalMaxSubstring(String s) {
		if (CommonStringUtil.isEmpty(s)) {
			return s;
		}
		
		int n = s.length();
		char[] str = s.toCharArray();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (char cha : str) {
			min = Math.min(min, cha);
			max = Math.max(max, cha);
		}
		
		/**
		 * Ͱ�Ż���
		 * ԭ���飺 min=17��max=103 ��û��Ҫ׼��103��Ͱ ֻ��Ҫ׼��Ͱ��1 ~ 86�� ����103-17��Ͱ
		 * ����dc3��num Ҫͬʱ�任��[1....86]
		 * 
		 */
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = str[i] - min + 1;
		}
		
		DC3 dc3 = new DC3(arr, max - min + 1);
		
		return s.substring(dc3.sa[n - 1]);
	}
	
	/**
	 * ��s1����s2�� �����γ������ֵ���Ĵ���<br>
	 * N : s1���� <br>
	 * M : s2���� <br>
	 * O(N+M) + O(M^2)  <br>
	 * 
	 * 1. ���s1���ֵ��� > s2�ģ� s2�������ò���s1֮ǰ.
	 * ����Ŀ��ܲ���λ��s1��i��λ��
	 * ���ҵĿ��ܲ���λ��i��s2�ֳ���С��λ��
	 * s1 = 999999993299 , s2 = 994
	 *            l r
	 *  e.g: ������l�������ֺ�׺�ֵ��� < 994, ����s2�ֳ���С��λ����r,�����l~r�ľֲ����Լ��ɣ�
	 *    994993,999493,999943..
	 * 
	 * �� w = һ����Сascci code, ����һ���´�s1+w+s2 -> e.g: aabwab
	 * �����´������dc3 
	   ע��ȫ��ͳһ+2�Ϳ��Ա�֤>=1, w = 1
	 */
	public static String lexicographicalMaxInsert(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return s2;
		}
		if (s2 == null || s2.length() == 0) {
			return s1;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int min = str1[0];
		int max = str1[0];
		for (int i = 1; i < N; i++) {
			min = Math.min(min, str1[i]);
			max = Math.max(max, str1[i]);
		}
		for (int i = 0; i < M; i++) {
			min = Math.min(min, str2[i]);
			max = Math.max(max, str2[i]);
		}
		
		// ��������s1+w+s2
		int[] all = new int[N + M + 1];
		int index = 0;
		
		// s1
		for (int i = 0; i < N; i++) {
			all[index++] = str1[i] - min + 2;
		}
		all[index++] = 1; // w
		
		// s2
		for (int i = 0; i < M; i++) {
			all[index++] = str2[i] - min + 2;
		}
		
		
		DC3 dc3 = new DC3(all, max - min + 2); // ����dc3 �㶨�ֵ���
		int[] rank = dc3.rank;
		
		int comp = N + 1;
		for (int i = 0; i < N; i++) { // loop s1��λ��
			if (rank[i] < rank[comp]) { // �ҵ�s1�з�������������λ��l
				int best = CommonStringUtil.bestSplit(s1, s2, i); // �ҵ�s1�з�������������λ��r,���ҵ�s1����õĲ���λ��
				return s1.substring(0, best) + s2 + s1.substring(best);
			}
		}
		return s1 + s2;
		
	}
	
	/**
	 * num1 ��M�� num2 ��N�� ��Ԫ��<=9, ��2���д�������ѡK���������ش���������ֵĽ����<br>
	 * 
	 * e.g: [6,8,9,3], [9,6,4] -> 996,��Ϊ8��9�󲻿���ѡ��.<br>
	 * 
	 * ������1�� ��һ�������д�������ѡM��������ô��������
	 * ������2�� 2���������merge��һ������
	 */
	
	public static int[] lexicographicalMaxCreation(int[] nums1, int[] nums2, int k) {
		return nums2;
		
	}
	
}
