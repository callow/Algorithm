package com.algo.tasks.day12;

/**
 * ������ʽƥ�䣺 https://leetcode.com/problems/regular-expression-matching/
 * 
 * б���Ż�+���仰����
 * 
 */
public class RegularExpressionMatch {
	
	// �����ݹ�
	public static boolean isMatch1(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		return process1(s, p, 0, 0);
	}

	// s[i....]�ܲ��ܱ�p[j....]��ȫƥ�����
	public static boolean process1(char[] s, char[] p, int i, int j) {
		if (i == s.length) {
			// sû��
			if (j == p.length) {
				// ���pҲû�ˣ�����true
				return true;
			} else {
				// ���p[j+1]��*����ôp[j..j+1]����������Ȼ�󿴿�p[j+2....]�ǲ��Ƕ�������
				return j + 1 < p.length && p[j + 1] == '*' && process1(s, p, i, j + 2);
			}
		} else if (j == p.length) {
			// s��
			// pû��
			return false;
		} else {
			if (j + 1 == p.length || p[j + 1] != '*') {
				// s[i....]
				// p[j....]
				// ���p[j+1]����*����ô��ǰ���ַ�������ƥ�䣺(s[i] == p[j] || p[j] == '?')
				// ͬʱ������Ҳ����ƥ���ϣ�process1(s, p, i + 1, j + 1);
				return (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j + 1);
			} else {
				// s[i....]
				// p[j....]
				// ���p[j+1]��*
				// ѡ��1: ��ǰp[j..j+1]��x*�����ǲ������㶨s[i]����ô���� : process1(s, p, i, j + 2)
				boolean p1 = process1(s, p, i, j + 2);
				// ѡ��2: ��ǰp[j..j+1]��x*��������Ը㶨s[i]����ô���� : process1(s, p, i + 1, j)
				// ������Ը㶨s[i] : (s[i] == p[j] || p[j] == '.')
				// ����ƥ�� : process1(s, p, i + 1, j)
				boolean p2 = (s[i] == p[j] || p[j] == '.') && process1(s, p, i + 1, j);
				// ����ѡ����һ�����Ը㶨�ͷ���true�����޷��㶨����false
				return p1 || p2;
			}
		}
	}

	// ���仯����
	public static boolean isMatch2(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n = s.length;
		int m = p.length;
		// dp[i][j] == 0����ʾû���
		// dp[i][j] == 1����ʾû�������true
		// dp[i][j] == 2����ʾû�������false
		int[][] dp = new int[n + 1][m + 1];
		return process2(s, p, 0, 0, dp);
	}

	public static boolean process2(char[] s, char[] p, int i, int j, int[][] dp) {
		if (dp[i][j] != 0) {
			return dp[i][j] == 1;
		}
		boolean ans;
		if (i == s.length) {
			if (j == p.length) {
				ans = true;
			} else {
				ans = j + 1 < p.length && p[j + 1] == '*' && process2(s, p, i, j + 2, dp);
			}
		} else if (j == p.length) {
			ans = false;
		} else {
			if (j + 1 == p.length || p[j + 1] != '*') {
				ans = (s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j + 1, dp);
			} else {
				ans = process2(s, p, i, j + 2, dp) || ((s[i] == p[j] || p[j] == '.') && process2(s, p, i + 1, j, dp));
			}
		}
		dp[i][j] = ans ? 1 : 2;
		return ans;
	}

	// ��̬�滮:б���Ż�
	public static boolean isMatch3(String str, String pat) {
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n = s.length;
		int m = p.length;
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[n][m] = true;
		for (int j = m - 2; j >= 0; j--) {
			dp[n][j] = p[j + 1] == '*' && dp[n][j + 2];
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (j + 1 == p.length || p[j + 1] != '*') {
					dp[i][j] = (s[i] == p[j] || p[j] == '.') && dp[i + 1][j + 1];
				} else {
					dp[i][j] = dp[i][j + 2] || ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]);
				}
			}
		}
		return dp[0][0];
	}

}
