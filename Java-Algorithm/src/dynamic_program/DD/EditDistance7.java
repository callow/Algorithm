package dynamic_program.DD;
/**
 * 编辑距离问题：给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少代价
 			插入，替换，删除 代价都不同
   
   https://leetcode.cn/problems/edit-distance/
 *
 */
public class EditDistance7 {
	
	
	public int minDistance(String word1, String word2) {
		return editDistance1(word1, word2, 1, 1, 1);
	}
	
	// dp
	/**
	 * dp[i][j] : s1取前i个(s1[0..i-1])，彻底变成s2取前j个(s2[0..i-1])的最小代价
	 * dp[n][m] 就是最终解
	 * 围绕最后一个字符讨论：
	 * 	1. s1[i-1]参与：如何参与?
	 * 	  a. 最后变最后=> s[i-1]变成s2[j-1]
	 *       若 s[i-1] = s2[j-1], 则看s1[i-2] -> s2[i-2]... 依赖关系只是dp[i-1][j-1]
	 * 		 若 s[i-1] != s2[j-1], 则依赖关系dp[i-1][j-1]+替换代价
	 *    
	 *    b. 搞定之前，最后一个字符插入
	 *       s1[0...i-1] 搞定 s2[0..i-2] + 插入代价最后i-1，
	 *        依赖关系dp[i][j-1] + 插入代价
	 *       
	 *  2. s1[i-1]不参与：
	 *  	dp[i-1][j] + 删除代价
	 */
	public static int editDistance1(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[i][j] :
		// s1[前缀长度为i]想变成s2[前缀长度为j]，至少付出多少代价
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i * b;
		}
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j * a;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int p1 = Integer.MAX_VALUE;
				if (s1[i - 1] == s2[j - 1]) {
					p1 = dp[i - 1][j - 1];
				}
				int p2 = Integer.MAX_VALUE;
				if (s1[i - 1] != s2[j - 1]) {
					p2 = dp[i - 1][j - 1] + c;
				}
				int p3 = dp[i][j - 1] + a;
				int p4 = dp[i - 1][j] + b;
				dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
			}
		}
		return dp[n][m];
	}
	
	// 枚举小优化版
	public static int editDistance2(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[i][j] :
		// s1[前缀长度为i]想变成s2[前缀长度为j]，至少付出多少代价
		int[][] dp = new int[n + 1][m + 1];
		
		// 第1列：s1不同长度字符怎么变成s2一个字符也没有.只需删除就可以
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i * b;
		}
		// 第0行：s1一个字符也没有怎么变成s2不同长度字符，只需插入就可以
		   // 插入1 个， 插入2个，插入 3个..., n倍的插入代价
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j * a;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + b, dp[i][j - 1] + a), dp[i - 1][j - 1] + c);
				}
			}
		}
		return dp[n][m];
	}

	// 空间压缩
	public static int editDistance3(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		int[] dp = new int[m + 1];
		for (int j = 1; j <= m; j++) {
			dp[j] = j * a;
		}
		for (int i = 1, leftUp, backUp; i <= n; i++) {
			leftUp = (i - 1) * b;
			dp[0] = i * b;
			for (int j = 1; j <= m; j++) {
				backUp = dp[j];
				if (s1[i - 1] == s2[j - 1]) {
					dp[j] = leftUp;
				} else {
					dp[j] = Math.min(Math.min(dp[j] + b, dp[j - 1] + a), leftUp + c);
				}
				leftUp = backUp;
			}
		}
		return dp[m];
	}
	
	
}
