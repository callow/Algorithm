package com.algo.util.array_sum;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * 数组累加和相关问题。
 *
 */
public class ArraySumUtil {

	/**
	 * 一个非负数组，哪个子数组∑ = target且长度最长？<br>
	 * 解：双指针： 必须以left为开头的子数组累加和是target
	 */
	public static int findLongestSubArray(int[] arr, int target) {
		if (CommonArrayUtil.isEmpty(arr) || target <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == target) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < target) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}
}
