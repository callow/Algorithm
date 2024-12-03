package dynamic_program.D;
/**
 * ����һ������ n �������ҳ������ص� n �� ����(ֻ���������� 2��3 �� 5 ��������)
 * 
 * https://leetcode.cn/problems/ugly-number-ii/
 * 
 * ֱ�������ݹ飬ֱ�Ӵ������ҵ��ƣ�DP��
 */
public class UglyNumber5 {

	// ��Ϊ��������2 or 3 or 5��������,�����κκ���ĳ�������ǰ�����*2 or *3 or *5 �õ�
	// ���Զ���3��ָ��(�±�)����һ��������������ָ����С���ĸ�min,Ȼ���ָ��������һ��������
	public static int nthUglyNumber(int n) {
		// dp 0 1 2 ...  n
		//      1 2 ...  ?
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2, i2 = 1, i3 = 1, i5 = 1, a, b, c, cur; i <= n; i++) {
			a = dp[i2] * 2;
			b = dp[i3] * 3;
			c = dp[i5] * 5;
			cur = Math.min(Math.min(a, b), c); // ��ǰ�ĳ���������dp[i]
			if (cur == a) {
				i2++;
			}
			if (cur == b) {
				i3++;
			}
			if (cur == c) {
				i5++;
			}
			dp[i] = cur;
		}
		return dp[n];
	}
}
