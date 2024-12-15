package dynamic_program.SubarrayMaxSum;
/**
 * 不能选相邻元素且有额外限制的最大累加和问题：给定一个数组nums，可以随意选择数字 但是不能选择相邻的数字，必须选k个数，返回小偷最小能力
 * 
 * https://leetcode.cn/problems/house-robber-iv/
 * 
 * 思路 ： 
 *  1. 能力上限 = 房屋最大金额， 能力下限 = 房屋最小金额
 *  2. 
 */
public class HouseRobberIV5 {

	public static int minCapability(int[] nums, int k) {
		int n = nums.length, l = nums[0], r = nums[0];
		for (int i = 1; i < n; i++) {
			l = Math.min(l, nums[i]);
			r = Math.max(r, nums[i]);
		}
		// l....r
		int m, ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			if (mostRob1(nums, n, m) >= k) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}
	
	// 盗贼能力为ability时 返回盗贼最多能窃取多少间房屋 （注意限制 : 不能窃取相邻房屋）
	/**
	 *  dp[i] = 0~i号房间最多能偷几间, 目标dp[n-1]
		 1. 不偷i号房间，则 dp[i] = dp[i-1]
		 2. 偷i号房间，则 dp[i] = num[i] + dp[i-2]
	 */
		
	public static int mostRob1(int[] nums, int n, int ability) {
		if (n == 1) { // 只有一个房间
			return nums[0] <= ability ? 1 : 0;
		}
		if (n == 2) {
			return (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;
		}
		int[] dp = new int[n];
		dp[0] = nums[0] <= ability ? 1 : 0;
		dp[1] = (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], (nums[i] <= ability ? 1 : 0) + dp[i - 2]);
		}
		return dp[n - 1];
	}
	
	// 空间压缩
	public static int mostRob2(int[] nums, int n, int ability) {
		if (n == 1) {
			return nums[0] <= ability ? 1 : 0;
		}
		if (n == 2) {
			return (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;
		}
		int prepre = nums[0] <= ability ? 1 : 0;
		int pre = (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;
		for (int i = 2, cur; i < n; i++) {
			cur = Math.max(pre, (nums[i] <= ability ? 1 : 0) + prepre);
			prepre = pre;
			pre = cur;
		}
		return pre;
	}
	
	
	// 继续贪心优化： 能偷就尽早偷 反正都是+1
	public static int mostRob3(int[] nums, int n, int ability) {
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] <= ability) {
				ans++;
				i++;
			}
		}
		return ans;
	}
}
