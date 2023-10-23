package com.algo.tasks.day14;

import com.algo.util.common.CommonArrayUtil;

/**
 * 寻找最小的正整数， https://leetcode.com/problems/first-missing-positive/
 */
public class MissingNumber {

	public static int firstMissingPositive(int[] arr) {
		// l是盯着的位置
		// 0 ~ L-1有效区
		int L = 0;
		int R = arr.length;
		while (L != R) {
			if (arr[L] == L + 1) {
				L++;
			} else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) { // 垃圾的情况
				CommonArrayUtil.swap(arr, L, --R);
			} else {
				CommonArrayUtil.swap(arr, L, arr[L] - 1);
			}
		}
		return L + 1;
	}
}
