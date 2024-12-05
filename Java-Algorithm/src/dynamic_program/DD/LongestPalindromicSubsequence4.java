package dynamic_program.DD;
/**
 * �����������: ����һ���ַ��� s ���ҳ�������Ļ��������У������ظ����еĳ���
 * 
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 * 
 * ѧϰ�� 
 *  1. ���� �� ԭʼ���������������
 *  2. ����DP�� dp�Ŀɱ�����漰���䣬��Ҫ��β����
 *  
 *  	�� S[L] != S[R]
 *  	  �򿴣�[L ~ R-1]
 *   		  [l+1 ~ R]
 *      �� S[L] = S[R]
 *        �򿴣� 2+[L+1 ~ R-1]
 *			 
 */
public class LongestPalindromicSubsequence4 {

	// �����ݹ�
	public static int longestPalindromeSubseq1(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		return f1(s, 0, n - 1);
	}

	// s[l...r]����������г���, ������Χ�� l <= r
	public static int f1(char[] s, int l, int r) { // l~r ����dp
		if (l == r) { // a
			return 1;
		}
		if (l + 1 == r) { // aa | ab
			return s[l] == s[r] ? 2 : 1;
		}
		if (s[l] == s[r]) { // ͷβ���
			return 2 + f1(s, l + 1, r - 1);
		} else { // ͷβ����
			return Math.max(f1(s, l + 1, r), f1(s, l, r - 1));
		}
	}
	//--------------------------------------------------
	
	// ���仯��������Ϊ l <= r�� ֻ��Ҫ��һ��ı����ϣ�
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
	
	public static int longestPalindromeSubseq3(String str) { // �ϸ�λ��������
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
			// dp[l] : �����е�dp[l][l]
			dp[l] = 1;
			if (l + 1 < n) {
				leftDown = dp[l + 1];
				// dp[l+1] : �����е�dp[l][l+1]
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
