package dynamic_program.DD;
/**
 * 最长回文子序列: 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度
 * 
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 
 * 学习： 
 *  1. 逆序串 和 原始串的最长公共子序列
 *  2. 区间DP： dp的可变参数涉及区间，则要首尾尝试
 *  
 *  	若 S[L] != S[R]
 *  	  则看：[L ~ R-1]
 *   		  [l+1 ~ R]
 *      若 S[L] = S[R]
 *        则看： 2+[L+1 ~ R-1]
 *			 
 */
public class LongestPalindromicSubsequence4 {

	// 暴力递归
	public static int longestPalindromeSubseq1(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		return f1(s, 0, n - 1);
	}

	// s[l...r]最长回文子序列长度, 正常范围： l <= r
	public static int f1(char[] s, int l, int r) { // l~r 区间dp
		if (l == r) { // a
			return 1;
		}
		if (l + 1 == r) { // aa | ab
			return s[l] == s[r] ? 2 : 1;
		}
		if (s[l] == s[r]) { // 头尾相等
			return 2 + f1(s, l + 1, r - 1);
		} else { // 头尾不等
			return Math.max(f1(s, l + 1, r), f1(s, l, r - 1));
		}
	}
	//--------------------------------------------------
	
	// 记忆化搜索，因为 l <= r， 只需要填一半的表（左上）
	public static int longestPalindromeSubseq2(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		int[][] dp = new int[n][n];
		return f2(s, 0, n - 1, dp);
	}

	public static int f2(char[] s, int l, int r, int[][] dp) {
		if (l == r) {
			return 1;
		}
		if (l + 1 == r) {
			return s[l] == s[r] ? 2 : 1;
		}
		if (dp[l][r] != 0) {
			return dp[l][r];
		}
		int ans;
		if (s[l] == s[r]) {
			ans = 2 + f2(s, l + 1, r - 1, dp);
		} else {
			ans = Math.max(f2(s, l + 1, r, dp), f2(s, l, r - 1, dp));
		}
		dp[l][r] = ans;
		return ans;
	}
	
	// -------------------------------------------------------
	
	public static int longestPalindromeSubseq3(String str) { // 严格位置依赖，
		char[] s = str.toCharArray();
		int n = s.length;
		int[][] dp = new int[n][n];
		for (int l = n - 1; l >= 0; l--) {
			dp[l][l] = 1;
			if (l + 1 < n) {
				dp[l][l + 1] = s[l] == s[l + 1] ? 2 : 1;
			}
			for (int r = l + 2; r < n; r++) {
				if (s[l] == s[r]) {
					dp[l][r] = 2 + dp[l + 1][r - 1];
				} else {
					dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
				}
			}
		}
		return dp[0][n - 1];
	}
	
	//------------------------------------------------------
	
	public static int longestPalindromeSubseq4(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		int[] dp = new int[n];
		for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
			// dp[l] : 想象中的dp[l][l]
			dp[l] = 1;
			if (l + 1 < n) {
				leftDown = dp[l + 1];
				// dp[l+1] : 想象中的dp[l][l+1]
				dp[l + 1] = s[l] == s[l + 1] ? 2 : 1;
			}
			for (int r = l + 2; r < n; r++) {
				backup = dp[r];
				if (s[l] == s[r]) {
					dp[r] = 2 + leftDown;
				} else {
					dp[r] = Math.max(dp[r], dp[r - 1]);
				}
				leftDown = backup;
			}
		}
		return dp[n - 1];
	}
	
}
