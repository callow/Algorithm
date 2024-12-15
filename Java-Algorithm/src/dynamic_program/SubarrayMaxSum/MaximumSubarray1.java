package dynamic_program.SubarrayMaxSum;
/**
 * 子数组最大累加和: 给你一个整数数组 nums 返回非空子数组的最大累加和
 * 
 * https://leetcode.cn/problems/maximum-subarray/
 * 
 * 大思路：考察每个位置结尾往左延申最好情况,2种情况
 * 	（1. 自己呆着num[i] 2. 要往左延申dp[i-1] + num[i]）
 */
public class MaximumSubarray1 {

	
	// 动态规划
	public static int maxSubArray1(int[] nums) {
		int n = nums.length;
		// dp[i] : 子数组必须以i位置的数做结尾，往左能延伸出来的最大累加和
		int[] dp = new int[n];
		dp[0] = nums[0];
		int ans = nums[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}
	
	//------------------------------------------------------
	
	// 空间压缩
	public static int maxSubArray2(int[] nums) {
		int n = nums.length;
		int ans = nums[0];
		for (int i = 1, pre = nums[0]; i < n; i++) {
			pre = Math.max(nums[i], pre + nums[i]);
			ans = Math.max(ans, pre);
		}
		return ans;
	}
	
	//---------------------------------------------------------
	
	
	
	/**
	 * 附加问题： 不仅返回最大累加和Sum，且返回此子数组的区间L，R
	 * 
	 * 思路：每一步是否可以pre> Sum，是的话就更新L R
	 * 
	 */
	public static int left;
	public static int right;
	public static int sum;
	public static void extra(int[] nums) {
		sum = Integer.MIN_VALUE;
		for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < nums.length; r++) {
			if (pre >= 0) { // = dp[i-1]
				// 吸收前面的累加和有利可图
				// 那就不换开头
				pre += nums[r];
			} else {
				// 吸收前面的累加和已经无利可图
				// 那就换开头
				pre = nums[r];
				l = r;
			}
			if (pre > sum) {
				sum = pre;
				left = l;
				right = r;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
