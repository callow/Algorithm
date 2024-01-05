package com.algo.util.PrefixSum;

import com.algo.util.PrefixSum.model.PrefixSumArray;

/**
 * 前缀和数组技巧
 */
public class PrefixSumUtil {

	/**
	 * 用o(1) 实现 L ~ R的累加和
	 */
	public static int sumRange(int[] arr, int left, int right) {
		PrefixSumArray prefixSum = new PrefixSumArray(arr); // 构建前缀和数组
		return prefixSum.sum[right + 1] - prefixSum.sum[left]; // 相减一下
	}
}
