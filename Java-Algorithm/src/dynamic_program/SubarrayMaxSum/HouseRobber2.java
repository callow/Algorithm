package dynamic_program.SubarrayMaxSum;
/**
 * 不能选相邻元素的最大累加和问题：给定一个数组num，可以随意选择数字 但是不能选择相邻的数字，返回能得到的最大累加和
 * 
 * https://leetcode.cn/problems/house-robber/
 * 
 * 
 */
public class HouseRobber2 {

	
	// 动态规划: 
	// dp[i] : 在0~i范围上不能选相邻情况 最大累加和 => dp[n-1]
	/**
	 *  思路： 1. 不选i位置，dp[i] = dp[i-1]
	 *  	 2. 要i位置，
	 *  			dp[i] = num[i] - 自己呆着
	 *  			dp[i-1] = dp[i-2] - 还要带上之前的
	 */
	public static int rob1(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		// dp[i] : nums[0...i]范围上可以随意选择数字，但是不能选相邻数，能得到的最大累加和
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(nums[i], dp[i - 2] + nums[i]));
		}
		return dp[n - 1];
	}
	
	// -------------------------------------------------
	
	// 空间压缩
	public static int rob2(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int prepre = nums[0];
		int pre = Math.max(nums[0], nums[1]);
		for (int i = 2, cur; i < n; i++) {
			cur = Math.max(pre, Math.max(nums[i], prepre + nums[i]));
			prepre = pre;
			pre = cur;
		}
		return pre;
	}

	
	
}
