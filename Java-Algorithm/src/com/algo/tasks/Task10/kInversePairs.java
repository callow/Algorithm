package com.algo.tasks.Task10;
/**
 * 
 * N K ��������⡣N��������������� �γ�K������Ե��м�����
 * 
 * https://leetcode.com/problems/k-inverse-pairs-array/
 *
 */
public class kInversePairs {

	/**
	 * 
		Leedcode��Ҫ�ύ�Ĵ�
	 */
	public static int kInversePairs(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
				if (j >= i) {
					dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod; // + mod ��ֹ�������ɸ���mod �𰸴�
				}
			}
		}
		return dp[n][k];
	}

	/**
	 * 
		dp + б���Ż� ���Ͻ��Ľ�
	 */
	public static int kInversePairs2(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				if (j < i) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1] ;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i-1][j-i];
				}
			}
		}
		return dp[n][k];
	}
	
}
