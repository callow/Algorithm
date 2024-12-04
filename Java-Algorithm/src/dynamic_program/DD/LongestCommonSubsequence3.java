package dynamic_program.DD;
/**
 * 最长公共子序列问题
 * 
 * https://leetcode.cn/problems/longest-common-subsequence/
 * 
 * 
 * if S1[0...6] VS S2[0...8]
 * 
 * Then 1) S1[0...5] VS S2[0...7]
 *      2) S1[0...5] VS S2[0...8]
 *      2) S1[0...6] VS S2[0...7]
 *      2) if S1[6] == S1[8] 去看 （S1[0...5] VS S2[0...7]） + 1
 * 
 * 学习：
 * 之所以用末尾匹配来判断而不是其他部分 是因为让各自S剩下的长度足够大(保留尽量长的长度)，来应对剩余的比较情况
 * 范围变大，LCS只可能变大，因此可以优化多余的较小分支
 * 任何递归都可以转变成dp表
 * 
 * 
 * 经典技巧：在比较容易越界的情况下，在定义入手减少边界判断
 * 
 */
public class LongestCommonSubsequence3 {

	// 暴力递归
	public static int longestCommonSubsequence1(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		return f1(s1, s2, n - 1, m - 1);
	}

	// s1[0....i1]与s2[0....i2]最长公共子序列长度
	public static int f1(char[] s1, char[] s2, int i1, int i2) {
		if (i1 < 0 || i2 < 0) { // str1中i1 < 0  => 已经没有了
			return 0;
		}
		int p1 = f1(s1, s2, i1 - 1, i2 - 1); // 2个字符串倒数第二个比较
		int p2 = f1(s1, s2, i1 - 1, i2); // 2个字符串 倒数第二个 vs 最后一个
		int p3 = f1(s1, s2, i1, i2 - 1);  // 2个字符串倒  最后一个 vs 倒数第二个
		int p4 = s1[i1] == s2[i2] ? (p1 + 1) : 0; // 2个字符串倒  如果最后一个匹配上了，则比较倒数第二个
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}
	
	
	//-----------------------------------------------------------------------
	
	
	// 为了避免很多边界讨论
	// 很多时候往往不用下标来定义尝试，而是用长度来定义尝试
	// 因为长度最短是0，而下标越界的话讨论起来就比较麻烦
	public static int longestCommonSubsequence2(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		return f2(s1, s2, n, m);
	}

	// s1[前缀长度为len1] vs s2[前缀长度为len2]
	// 最长公共子序列长度
	// len1 = 6 = 下标1~下标5
	public static int f2(char[] s1, char[] s2, int len1, int len2) {
		if (len1 == 0 || len2 == 0) {
			return 0;
		}
		int ans;
		if (s1[len1 - 1] == s2[len2 - 1]) {
			ans = f2(s1, s2, len1 - 1, len2 - 1) + 1;
		} else {
			ans = Math.max(f2(s1, s2, len1 - 1, len2), f2(s1, s2, len1, len2 - 1));
		}
		return ans;
	}

	//-------------------------------------------------------------------
	
	//记忆化搜索
	public static int longestCommonSubsequence3(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = -1;
			}
		}
		return f3(s1, s2, n, m, dp);
	}

	public static int f3(char[] s1, char[] s2, int len1, int len2, int[][] dp) {
		if (len1 == 0 || len2 == 0) {
			return 0;
		}
		if (dp[len1][len2] != -1) {
			return dp[len1][len2];
		}
		int ans;
		if (s1[len1 - 1] == s2[len2 - 1]) {
			ans = f3(s1, s2, len1 - 1, len2 - 1, dp) + 1;
		} else {
			ans = Math.max(f3(s1, s2, len1 - 1, len2, dp), f3(s1, s2, len1, len2 - 1, dp));
		}
		dp[len1][len2] = ans;
		return ans;
	}
	//-------------------------------------------------------------------
	
	//严格位置依赖
	public static int longestCommonSubsequence4(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		int[][] dp = new int[n + 1][m + 1];
		for (int len1 = 1; len1 <= n; len1++) {
			for (int len2 = 1; len2 <= m; len2++) {
				
				if (s1[len1 - 1] == s2[len2 - 1]) {
					dp[len1][len2] = 1 + dp[len1 - 1][len2 - 1];
				} else {
					dp[len1][len2] = Math.max(dp[len1 - 1][len2], dp[len1][len2 - 1]);
				}
			}
		}
		return dp[n][m];
	}
	
	//------------------------------------------------------------------------
	
	// 严格位置依赖的动态规划 + 空间压缩
	public static int longestCommonSubsequence5(String str1, String str2) {
		char[] s1, s2;
		if (str1.length() >= str2.length()) {
			s1 = str1.toCharArray();
			s2 = str2.toCharArray();
		} else {
			s1 = str2.toCharArray();
			s2 = str1.toCharArray();
		}
		int n = s1.length;
		int m = s2.length;
		
		int[] dp = new int[m + 1]; //一个数组滚下去
		for (int len1 = 1; len1 <= n; len1++) {
			int leftUp = 0, backup;
			for (int len2 = 1; len2 <= m; len2++) {
				backup = dp[len2];
				if (s1[len1 - 1] == s2[len2 - 1]) {
					dp[len2] = 1 + leftUp;
				} else {
					dp[len2] = Math.max(dp[len2], dp[len2 - 1]);
				}
				leftUp = backup;
			}
		}
		return dp[m];
	}
	
	
}
