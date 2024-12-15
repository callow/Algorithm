package dynamic_program.SubarrayMaxSum;
/**
 * ����������ۼӺ�: ����һ���������� nums ���طǿ������������ۼӺ�
 * 
 * https://leetcode.cn/problems/maximum-subarray/
 * 
 * ��˼·������ÿ��λ�ý�β��������������,2�����
 * 	��1. �Լ�����num[i] 2. Ҫ��������dp[i-1] + num[i]��
 */
public class MaximumSubarray1 {

	
	// ��̬�滮
	public static int maxSubArray1(int[] nums) {
		int n = nums.length;
		// dp[i] : �����������iλ�õ�������β���������������������ۼӺ�
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
	
	// �ռ�ѹ��
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
	 * �������⣺ ������������ۼӺ�Sum���ҷ��ش������������L��R
	 * 
	 * ˼·��ÿһ���Ƿ����pre> Sum���ǵĻ��͸���L R
	 * 
	 */
	public static int left;
	public static int right;
	public static int sum;
	public static void extra(int[] nums) {
		sum = Integer.MIN_VALUE;
		for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < nums.length; r++) {
			if (pre >= 0) { // = dp[i-1]
				// ����ǰ����ۼӺ�������ͼ
				// �ǾͲ�����ͷ
				pre += nums[r];
			} else {
				// ����ǰ����ۼӺ��Ѿ�������ͼ
				// �Ǿͻ���ͷ
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
