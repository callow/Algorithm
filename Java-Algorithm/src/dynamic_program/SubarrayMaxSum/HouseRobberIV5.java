package dynamic_program.SubarrayMaxSum;
/**
 * ����ѡ����Ԫ�����ж������Ƶ�����ۼӺ����⣺����һ������nums����������ѡ������ ���ǲ���ѡ�����ڵ����֣�����ѡk����������С͵��С����
 * 
 * https://leetcode.cn/problems/house-robber-iv/
 * 
 * ˼· �� 
 *  1. �������� = �������� �������� = ������С���
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
	
	// ��������Ϊabilityʱ ���ص����������ȡ���ټ䷿�� ��ע������ : ������ȡ���ڷ��ݣ�
	/**
	 *  dp[i] = 0~i�ŷ��������͵����, Ŀ��dp[n-1]
		 1. ��͵i�ŷ��䣬�� dp[i] = dp[i-1]
		 2. ͵i�ŷ��䣬�� dp[i] = num[i] + dp[i-2]
	 */
		
	public static int mostRob1(int[] nums, int n, int ability) {
		if (n == 1) { // ֻ��һ������
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
	
	// �ռ�ѹ��
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
	
	
	// ����̰���Ż��� ��͵�;���͵ ��������+1
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
