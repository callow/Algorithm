package com.algo.util.suffix_array;

import com.algo.util.common.CommonStringUtil;

/**
 *  Dictionary order/Lexicographical order: 字典序
 *  
 *  后缀数组排序问题
 *
 *
 */
public class DC3Util {

	/**
	 * 找出子数组/子串 中字典序最高的那个
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
		 * 桶优化：
		 * 原数组： min=17，max=103 则，没必要准备103个桶 只需要准备桶：1 ~ 86， 即：103-17个桶
		 * 但是dc3的num 要同时变换成[1....86]
		 * 
		 */
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = str[i] - min + 1;
		}
		
		DC3 dc3 = new DC3(arr, max - min + 1);
		
		
		return s.substring(dc3.sa[n - 1]);
	}
	
}
