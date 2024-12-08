package dynamic_program.DD;
/**
 * 交错字符串：请帮忙验证s3是否由s1和s2交错组成
 * 
 * https://leetcode.cn/problems/interleaving-string/
 */
public class InterleavingString8 {

	/**
	 * 思路：
	 *  1. s1长度 + s2长度 = s3长度
	 *  2. dp[i][j] => s1取前i个(s1[0..i-1])，s2取前j个(s2[0..j-1]) 能不能交错出s3前i+j个 => 最终结果dp[n][m]
	 *  
	 *  看s3最后一个字符是谁搞定的：||
	 *  	1. 由s1搞定的，即s1[i-1] = s3[i+j-1] => 则看前面能不交错出来，即s1[i-1个] s2[j个], s3[i+j-1个] => dp[i-1][j]
	 * 		2. 由s1搞定的，即s2[j-1] = s3[i+j-1] => 则看前面能不交错出来，即s1[i个] s2[j-1个], s3[i+j-1个] => dp[i][j-1]
	 */
	
	public static boolean isInterleave1(String str1, String str2, String str3) {
		if (str1.length() + str2.length() != str3.length()) {
			return false;
		}
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		char[] s3 = str3.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[i][j]:
		// s1[前缀长度为i]和s2[前缀长度为j]，能否交错组成出s3[前缀长度为i+j]
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[0][0] = true;
		
		// 第0列，s2不参与，是否s1能自己搞出s3
		for (int i = 1; i <= n; i++) {
			if (s1[i - 1] != s3[i - 1]) {
				break;
			}
			dp[i][0] = true;
		}
		// 第0行，s1不参与，是否s2能自己搞出s3，一个一个对比即可
		for (int j = 1; j <= m; j++) {
			if (s2[j - 1] != s3[j - 1]) {
				break;
			}
			dp[0][j] = true;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = (s1[i - 1] == s3[i + j - 1] && dp[i - 1][j]) || (s2[j - 1] == s3[i + j - 1] && dp[i][j - 1]);
			}
		}
		return dp[n][m];
	}
	
	
	// 空间压缩
	public static boolean isInterleave2(String str1, String str2, String str3) {
		if (str1.length() + str2.length() != str3.length()) {
			return false;
		}
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		char[] s3 = str3.toCharArray();
		int n = s1.length;
		int m = s2.length;
		boolean[] dp = new boolean[m + 1];
		dp[0] = true;
		for (int j = 1; j <= m; j++) {
			if (s2[j - 1] != s3[j - 1]) {
				break;
			}
			dp[j] = true;
		}
		for (int i = 1; i <= n; i++) {
			dp[0] = s1[i - 1] == s3[i - 1] && dp[0];
			for (int j = 1; j <= m; j++) {
				dp[j] = (s1[i - 1] == s3[i + j - 1] && dp[j]) || (s2[j - 1] == s3[i + j - 1] && dp[j - 1]);
			}
		}
		return dp[m];
	}
	
	
	
	
}
