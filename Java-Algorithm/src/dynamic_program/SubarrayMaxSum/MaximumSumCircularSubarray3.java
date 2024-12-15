package dynamic_program.SubarrayMaxSum;
/**
 * �����������������ۼӺ����⣺ ����һ������nums������Ϊn�� nums��һ���������飬�±�0���±�n-1������һ��� �ػ��������У�����������ۼӺ�
 * 
 * https://leetcode.cn/problems/maximum-sum-circular-subarray/
 * 
 * ˼·�� 
 *  1. ���������ǲ������ϵ�
 *  2. ���������Ǳ����ϵģ�����ͷһ���֣���βһ���� = �����ۼӺ�all - ��С�ۼӺ�������minsum
 * 
 */
public class MaximumSumCircularSubarray3 {

	
	public static int maxSubarraySumCircular(int[] nums) {
		int n = nums.length, all = nums[0], maxsum = nums[0], minsum = nums[0];
		for (int i = 1, maxpre = nums[0], minpre = nums[0]; i < n; i++) {
			all += nums[i];
			
			// ����ۼӺ�
			maxpre = Math.max(nums[i], nums[i] + maxpre);
			maxsum = Math.max(maxsum, maxpre);
			
			// ��С�ۼӺ�
			minpre = Math.min(nums[i], nums[i] + minpre);
			minsum = Math.min(minsum, minpre);
		}
		// 1) maxsum
		// 2) all - minsum
		return all == minsum ? maxsum : Math.max(maxsum, all - minsum);
	}

}
