package com.algo.util.array_sum;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * 数组累加和相关问题。
 *
 */
public class ArraySumUtil {

	/**
	 * 一个+数组，哪个子数组∑ = target且长度最长？<br>
	 * 解：每个位置开头怎样：双指针： 必须以left为开头的子数组累加和是target
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

	/**
	 * 一个+ - 0 数组，哪个子数组∑ = target且长度最长？<br>
	 * 
	 * 解： 每个位置结尾怎么样：<br>
	 * 
	 * sum - target = 前缀和能推的最长位置：
	 * 
	 * ∵ Σ(0~100) = 200, target = 30, then 某一个前缀和170出现在6位置 <br>
	 * ∴ 从7 ~ 100就是能推的最长，即100结尾最长能向左推到7位置得到答案30
	 * 
	 * 注：变换，e.g: 求子数组-1和1的数量一样多的最长子数组 => 将不是-1 1的数字变成0，即求target=0的时候的本题
	 * 
	 */
	public static int findLongestSubArray2(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key:前缀和
		// value : 0~value这个前缀和是最早出现key这个值的
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important

		int maxLen = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) { // 以i结尾的来审视
			sum += arr[i];
			if (map.containsKey(sum - target)) {
				int prefixSumIndex = map.get(sum - target);
				maxLen = Math.max(i - prefixSumIndex, maxLen);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i); // 将前缀和的index放入map
			}
		}
		return maxLen;
	}
}
