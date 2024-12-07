package dynamic_program.DD;
/**
 * 不同的子序列： 给你两个字符串s和t ，统计并返回在s的子序列中t出现的个数
 * 
 * https://leetcode.cn/problems/distinct-subsequences/
 */
public class DistinctSubsequences6 {

	/**
	 * dp[n][m] 就是最后的解
	 * i个字符 其实下标最后一个是i-1
	 * dp[i][j] = s[前i个] 有几个子序列 = t[前j个]， 根据末尾字符讨论
		 1. 不要最后一个字符(s[i-1])，那么我还需要让剩下字符生成的子序列的能生成t[0..j-1],
		    即让s[0..i-2]去生成t[0..j-1]，也就是依赖关系是dp[i-1][j]
		 2. 要最后一个字符(s[i-1]) 则最后一个字符要和t最后一个对上才行 即 s[i-1]=t[j-1] 依赖关系是dp[i-1][j-1], 
		 	那么剩下的问题就是s中剩下的匹配t中剩下的 s[0..i-2] 匹配多少 t[0..j-2]
	 */
	public static int numDistinct1(String str, String target) {
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		// dp[i][j] :
		// s[前缀长度为i]的所有子序列中，有多少个子序列等于t[前缀长度为j]
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j];
				if (s[i - 1] == t[j - 1]) {
					dp[i][j] += dp[i - 1][j - 1];
				}
			}
		}
		return dp[n][m];
	}
	
	//-----------------------------------------
	
	// 空间压缩
	// 一个格子依赖上和左上
	// 从右往左填，自我更新，当前格子相当于自己上方的值，左边相当于左上角，然后更新完毕
	public static int numDistinct2(String str, String target) {
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		int[] dp = new int[m + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = m; j >= 1; j--) { // 从右往左刷
				if (s[i - 1] == t[j - 1]) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[m];
	}
}
