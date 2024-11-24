package dynamic_program.D;

import java.util.Arrays;

/**
 * ���뷽�� I�� https://leetcode.cn/problems/decode-ways/
 * 
 * �ݹ麬�壺��iλ�ó��������в��ֶ�����Ч��ת������
 * ����iλ�ö���3��������
 *  1. ѹ��ת����
 *  2. ת1���� + f(i+1.....)
 *  3. ת2���� + f(i+2.....)  // ���26��i, i+1 <= 26��
 */
public class DecodeWays3 {
	
	// ��������
	public static int numDecodings1(String s) {
		return f1(s.toCharArray(), 0); // ���� = 0������������
	}
	public static int f1(char[] s, int i) {
		if(i == s.length) {
			return 1;
		}
		int ans = 0;
		if (s[i]  == '0') {
			ans = 0;
		} else {
			ans = 1 * f1(s, i+1);
			// '1' - '0' = 1
			if (i + 1 < s.length && ((s[i] - '0') * 10 + s[i + 1] - '0') <= 26) {
				ans += 1 * f1(s, i + 2);
			}
		}
		return ans;
	}
	
	//-------------------------------------------------------
	// ���仯����
	public static int numDecodings2(String s) {
		int[] dp = new int[s.length()];
		Arrays.fill(dp, -1);
		return f2(s.toCharArray(), 0, dp);
	}

	public static int f2(char[] s, int i, int[] dp) {
		if (i == s.length) {
			return 1;
		}
		if (dp[i] != -1) {
			return dp[i];
		}
		int ans;
		if (s[i] == '0') {
			ans = 0;
		} else {
			ans = 1 * f2(s, i + 1, dp);
			if (i + 1 < s.length && ((s[i] - '0') * 10 + s[i + 1] - '0') <= 26) {
				ans += 1 * f2(s, i + 2, dp);
			}
		}
		dp[i] = ans;
		return ans;
	}
	
	//------------------------------------------------------------------
	// �ϸ�λ������������iλ��Ҫô����i+1,Ҫô����i+2 = ����������dp 
	public static int numDecodings3(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		int[] dp = new int[n + 1];
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (s[i] == '0') {
				dp[i] = 0;
			} else {
				dp[i] = dp[i + 1];
				if (i + 1 < s.length && ((s[i] - '0') * 10 + s[i + 1] - '0') <= 26) {
					dp[i] += dp[i + 2];
				}
			}
		}
		return dp[0];
	}
	
	// ---------------------------------------------------------------
	// ѹ���ռ䣺��Ϊֻ����i+1 �� i+2 ����2��������������
	public static int numDecodings4(String s) {
		// dp[i+1]
		int next = 1;
		// dp[i+2]
		int nextNext = 0;
		for (int i = s.length() - 1, cur; i >= 0; i--) {
			if (s.charAt(i) == '0') {
				cur = 0;
			} else {
				cur = next;
				if (i + 1 < s.length() && ((s.charAt(i) - '0') * 10 + s.charAt(i + 1) - '0') <= 26) {
					cur += nextNext;
				}
			}
			nextNext = next;
			next = cur;
		}
		return next;
	}
	
}
