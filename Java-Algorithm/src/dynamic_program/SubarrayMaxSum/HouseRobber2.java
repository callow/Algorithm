package dynamic_program.SubarrayMaxSum;
/**
 * ����ѡ����Ԫ�ص�����ۼӺ����⣺����һ������num����������ѡ������ ���ǲ���ѡ�����ڵ����֣������ܵõ�������ۼӺ�
 * 
 * https://leetcode.cn/problems/house-robber/
 * 
 * 
 */
public class HouseRobber2 {

	
	// ��̬�滮: 
	// dp[i] : ��0~i��Χ�ϲ���ѡ������� ����ۼӺ� => dp[n-1]
	/**
	 *  ˼·�� 1. ��ѡiλ�ã�dp[i] = dp[i-1]
	 *  	 2. Ҫiλ�ã�
	 *  			dp[i] = num[i] - �Լ�����
	 *  			dp[i-1] = dp[i-2] - ��Ҫ����֮ǰ��
	 */
	public static int rob1(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		// dp[i] : nums[0...i]��Χ�Ͽ�������ѡ�����֣����ǲ���ѡ���������ܵõ�������ۼӺ�
		int[] dp = new int[n];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(nums[i], dp[i - 2] + nums[i]));
		}
		return dp[n - 1];
	}
	
	// -------------------------------------------------
	
	// �ռ�ѹ��
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
