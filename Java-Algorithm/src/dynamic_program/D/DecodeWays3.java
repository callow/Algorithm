package dynamic_program.D;

import java.util.Arrays;

/**
 * 解码方法 I： https://leetcode.cn/problems/decode-ways/
 * 
 * 递归含义：从i位置出发往后有部分多少有效的转换方案
 * 来到i位置都有3个方案：
 *  1. 压根转不了
 *  2. 转1个数 + f(i+1.....)
 *  3. 转2个数 + f(i+2.....)  // 最高26（i, i+1 <= 26）
 */
public class DecodeWays3 {
	
	// 暴力尝试
	public static int numDecodings1(String s) {
		return f1(s.toCharArray(), 0); // 整体 = 0出发往后所有
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
	// 记忆化搜索
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
	// 严格位置依赖：来到i位置要么依赖i+1,要么依赖i+2 = 从右往左填dp 
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
	// 压缩空间：因为只依赖i+1 和 i+2 所以2个变量滚动即可
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
