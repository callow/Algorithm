package com.algo.util.PrefixSum;

import java.util.HashMap;

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
	
	
	/**
	 * 无序数组中，累加和为k的最长子数组. https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5
	 * 
	 * 若0 ～i = 1000 k=100，想得到以i结尾的子数组（和 = 100） = 求前缀和=900的最早出现在哪
	 */
	
	public static int longestSubArraySumK(int[] arr, int k) {
		
		// key : 某个前缀和  value : 这个前缀和最早出现的位置
		HashMap<Integer, Integer> map = new HashMap<>();
		map.clear();
		map.put(0, -1); // 重要 : 0这个前缀和，一个数字也没有的时候，就存在了
		int longestLength = 0;
		
		for (int i = 0, sum = 0; i < arr.length; i++) {
			sum += arr[i]; // 前缀和
			if (map.containsKey(sum - k)) { // 找到前缀和=900最早出现位置
				longestLength = Math.max(longestLength, i - map.get(sum - k));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return longestLength;
		
		
	}
}
