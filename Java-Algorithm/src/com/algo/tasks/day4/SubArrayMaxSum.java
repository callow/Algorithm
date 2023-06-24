package com.algo.tasks.day4;

import com.algo.util.common.CommonArrayUtil;

/**
 * 返回数组中，子数组最大累加和？
 * 
 * https://leetcode.com/problems/maximum-subarray/
 *
 */
public class SubArrayMaxSum {

	/**
	 * 因为只依赖i-1位置,因此只需要一个变量滚过去就可以 省略dp[]
	 */
	public static int maxSubArray(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr)) {
			return 0; 
		}

		int pre = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int p1 = arr[i]; // 只有自己，不扩
			int p2 = arr[i] + pre; // 向左扩：最多扩到和i-1一样，因此+pre
			int cur = Math.max(p1, p2);
			max =  Math.max(max, cur);
			pre = cur;
		}
		return max;
	}
}
