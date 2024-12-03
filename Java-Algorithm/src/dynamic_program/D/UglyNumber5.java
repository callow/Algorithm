package dynamic_program.D;
/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数(只包含质因数 2、3 或 5 的正整数)
 * 
 * https://leetcode.cn/problems/ugly-number-ii/
 * 
 * 直接跳过递归，直接从左往右递推（DP）
 */
public class UglyNumber5 {

	// 因为丑数都是2 or 3 or 5的质因子,所以任何后面的丑数都是前面丑数*2 or *3 or *5 得到
	// 所以定义3个指针(下标)，下一个丑数就是三个指针最小的哪个min,然后此指针跳到下一个丑数上
	public static int nthUglyNumber(int n) {
		// dp 0 1 2 ...  n
		//      1 2 ...  ?
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2, i2 = 1, i3 = 1, i5 = 1, a, b, c, cur; i <= n; i++) {
			a = dp[i2] * 2;
			b = dp[i3] * 3;
			c = dp[i5] * 5;
			cur = Math.min(Math.min(a, b), c); // 当前的丑数，填入dp[i]
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
