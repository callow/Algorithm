package dynamic_program.SubarrayMaxSum;
/**
 * 形数组的子数组最大累加和问题： 给定一个数组nums，长度为n， nums是一个环形数组，下标0和下标n-1是连在一起的 回环形数组中，子数组最大累加和
 * 
 * https://leetcode.cn/problems/maximum-sum-circular-subarray/
 * 
 * 思路： 
 *  1. 连续部分是不被隔断的
 *  2. 连续部分是被隔断的，即开头一部分，结尾一部分 = 整体累加和all - 最小累加和子数组minsum
 * 
 */
public class MaximumSumCircularSubarray3 {

	
	public static int maxSubarraySumCircular(int[] nums) {
		int n = nums.length, all = nums[0], maxsum = nums[0], minsum = nums[0];
		for (int i = 1, maxpre = nums[0], minpre = nums[0]; i < n; i++) {
			all += nums[i];
			
			// 最大累加和
			maxpre = Math.max(nums[i], nums[i] + maxpre);
			maxsum = Math.max(maxsum, maxpre);
			
			// 最小累加和
			minpre = Math.min(nums[i], nums[i] + minpre);
			minsum = Math.min(minsum, minpre);
		}
		// 1) maxsum
		// 2) all - minsum
		return all == minsum ? maxsum : Math.max(maxsum, all - minsum);
	}

}
