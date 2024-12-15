package dynamic_program.SubarrayMaxSum;
/**
 * ���������в���ѡ����Ԫ�ص�����ۼӺ����⣺ ����һ������nums������Ϊn nums��һ���������� ��������ѡ�����֣����ǲ���ѡ�����ڵ����� �����ܵõ�������ۼӺ�
 * 
 * https://leetcode.cn/problems/house-robber-ii/
 * 
 * ˼·��
 * 	1. ��Ҫ[0] λ�����֣�1 ~n-1 ����ۼӺ�
 *  2. Ҫ[0] λ�����֣�num[0] + 2~n-2����ۼӺ�
 */
public class HouseRobberII4 {

	
	public static int rob(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return nums[0];
		}
		return Math.max(best(nums, 1, n - 1), nums[0] + best(nums, 2, n - 2));
	}

	// nums[l....r]��Χ�ϣ�û�л��εĸ���
	// ���� : ��������ѡ�����֣�������ѡ���������ֵ�����£�����ۼӺ�
	public static int best(int[] nums, int l, int r) {
		if (l > r) {
			return 0;
		}
		if (l == r) {
			return nums[l];
		}
		if (l + 1 == r) {
			return Math.max(nums[l], nums[r]);
		}
		int prepre = nums[l];
		int pre = Math.max(nums[l], nums[l + 1]);
		for (int i = l + 2, cur; i <= r; i++) {
			cur = Math.max(pre, nums[i] + Math.max(0, prepre));
			prepre = pre;
			pre = cur;
		}
		return pre;
	}
}
