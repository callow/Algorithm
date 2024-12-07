package dynamic_program.DD;
/**
 * ��ͬ�������У� ���������ַ���s��t ��ͳ�Ʋ�������s����������t���ֵĸ���
 * 
 * https://leetcode.cn/problems/distinct-subsequences/
 */
public class DistinctSubsequences6 {

	/**
	 * dp[n][m] �������Ľ�
	 * i���ַ� ��ʵ�±����һ����i-1
	 * dp[i][j] = s[ǰi��] �м��������� = t[ǰj��]�� ����ĩβ�ַ�����
		 1. ��Ҫ���һ���ַ�(s[i-1])����ô�һ���Ҫ��ʣ���ַ����ɵ������е�������t[0..j-1],
		    ����s[0..i-2]ȥ����t[0..j-1]��Ҳ����������ϵ��dp[i-1][j]
		 2. Ҫ���һ���ַ�(s[i-1]) �����һ���ַ�Ҫ��t���һ�����ϲ��� �� s[i-1]=t[j-1] ������ϵ��dp[i-1][j-1], 
		 	��ôʣ�µ��������s��ʣ�µ�ƥ��t��ʣ�µ� s[0..i-2] ƥ����� t[0..j-2]
	 */
	public static int numDistinct1(String str, String target) {
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		// dp[i][j] :
		// s[ǰ׺����Ϊi]�������������У��ж��ٸ������е���t[ǰ׺����Ϊj]
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = dp[i - 1][j];
				if (s[i - 1] == t[j - 1]) {
					dp[i][j] += dp[i - 1][j - 1];
				}
			}
		}
		return dp[n][m];
	}
	
	//-----------------------------------------
	
	// �ռ�ѹ��
	// һ�����������Ϻ�����
	// ������������Ҹ��£���ǰ�����൱���Լ��Ϸ���ֵ������൱�����Ͻǣ�Ȼ��������
	public static int numDistinct2(String str, String target) {
		char[] s = str.toCharArray();
		char[] t = target.toCharArray();
		int n = s.length;
		int m = t.length;
		int[] dp = new int[m + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = m; j >= 1; j--) { // ��������ˢ
				if (s[i - 1] == t[j - 1]) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[m];
	}
}
