package com.algo.tasks.day7;
/**
 * 给定一个数组arr[], 返回如果排序后，相邻两数的最大差值 必须是O(N)
 * 
 * https://leetcode.com/problems/maximum-gap/
 * 
 * 思路： 鸽笼原理，桶排序假设
 */
public class MaxGap {
	
	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		// 抓取全数组的最小/最大值
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) { // 数组中当且仅当只有一种数
			return 0;
		}
		
		// 不止一种数，min~max一定有range,len个数据，len+1个桶
		boolean[] hasNum = new boolean[len + 1]; // i号桶是否进来过数字
		int[] maxs = new int[len + 1]; // i号桶收集的所有数字的最大值
		int[] mins = new int[len + 1]; // i号桶收集的所有数字的最小值
		
		
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		
		// 寻找跨桶的最大差值
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	/**
	 * 当前数字是num,整个范围在min ~max, 分成了len+1份。
	 * 
	 * 返回num进几号桶.
	 * 
	 * 最小值min ~ 最大值max，分成了len+1份，所以一份是：(max - min + 1) / (len + 1)
	 * 
	 * num是min~max中的排第几的数字(排名从0开始)，是第num - min个数字
	 * 
	   所以，num应该在哪一份里？在 (num - min) / ( (max - min + 1) / (len + 1))这一份里。

	   所以：(int) ((num - min) * (len + 1) / (max - min + 1))
	 */
	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}
}
