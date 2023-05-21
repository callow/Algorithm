package com.algo.util.suffix_array;

import com.algo.util.common.CommonStringUtil;

/**
 *  Dictionary order/Lexicographical order: �ֵ���
 *  
 *  ��׺������������
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
	 * N : s1����
	 * M : s2����
	 * O(N+M) + O(M^2) 
	 */
	public static String lexicographicalMaxInsert(String s1, String s2) {
		return s2;
		
	}
	
}
