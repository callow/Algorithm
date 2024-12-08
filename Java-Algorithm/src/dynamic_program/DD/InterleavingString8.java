package dynamic_program.DD;
/**
 * �����ַ��������æ��֤s3�Ƿ���s1��s2�������
 * 
 * https://leetcode.cn/problems/interleaving-string/
 */
public class InterleavingString8 {

	/**
	 * ˼·��
	 *  1. s1���� + s2���� = s3����
	 *  2. dp[i][j] => s1ȡǰi��(s1[0..i-1])��s2ȡǰj��(s2[0..j-1]) �ܲ��ܽ����s3ǰi+j�� => ���ս��dp[n][m]
	 *  
	 *  ��s3���һ���ַ���˭�㶨�ģ�||
	 *  	1. ��s1�㶨�ģ���s1[i-1] = s3[i+j-1] => ��ǰ���ܲ������������s1[i-1��] s2[j��], s3[i+j-1��] => dp[i-1][j]
	 * 		2. ��s1�㶨�ģ���s2[j-1] = s3[i+j-1] => ��ǰ���ܲ������������s1[i��] s2[j-1��], s3[i+j-1��] => dp[i][j-1]
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
		// s1[ǰ׺����Ϊi]��s2[ǰ׺����Ϊj]���ܷ񽻴���ɳ�s3[ǰ׺����Ϊi+j]
		boolean[][] dp = new boolean[n + 1][m + 1];
		dp[0][0] = true;
		
		// ��0�У�s2�����룬�Ƿ�s1���Լ����s3
		for (int i = 1; i <= n; i++) {
			if (s1[i - 1] != s3[i - 1]) {
				break;
			}
			dp[i][0] = true;
		}
		// ��0�У�s1�����룬�Ƿ�s2���Լ����s3��һ��һ���Աȼ���
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
	
	
	// �ռ�ѹ��
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
