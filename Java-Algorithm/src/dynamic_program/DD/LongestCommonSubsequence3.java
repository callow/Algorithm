package dynamic_program.DD;
/**
 * ���������������
 * 
 * https://leetcode.cn/problems/longest-common-subsequence/
 * 
 * 
 * if S1[0...6] VS S2[0...8]
 * 
 * Then 1) S1[0...5] VS S2[0...7]
 *      2) S1[0...5] VS S2[0...8]
 *      2) S1[0...6] VS S2[0...7]
 *      2) if S1[6] == S1[8] ȥ�� ��S1[0...5] VS S2[0...7]�� + 1
 * 
 * ѧϰ��
 * ֮������ĩβƥ�����ж϶������������� ����Ϊ�ø���Sʣ�µĳ����㹻��(�����������ĳ���)����Ӧ��ʣ��ıȽ����
 * ��Χ���LCSֻ���ܱ����˿����Ż�����Ľ�С��֧
 * �κεݹ鶼����ת���dp��
 * 
 * 
 * ���似�ɣ��ڱȽ�����Խ�������£��ڶ������ּ��ٱ߽��ж�
 * 
 */
public class LongestCommonSubsequence3 {

	// �����ݹ�
	public static int longestCommonSubsequence1(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		return f1(s1, s2, n - 1, m - 1);
	}

	// s1[0....i1]��s2[0....i2]����������г���
	public static int f1(char[] s1, char[] s2, int i1, int i2) {
		if (i1 < 0 || i2 < 0) { // str1��i1 < 0  => �Ѿ�û����
			return 0;
		}
		int p1 = f1(s1, s2, i1 - 1, i2 - 1); // 2���ַ��������ڶ����Ƚ�
		int p2 = f1(s1, s2, i1 - 1, i2); // 2���ַ��� �����ڶ��� vs ���һ��
		int p3 = f1(s1, s2, i1, i2 - 1);  // 2���ַ�����  ���һ�� vs �����ڶ���
		int p4 = s1[i1] == s2[i2] ? (p1 + 1) : 0; // 2���ַ�����  ������һ��ƥ�����ˣ���Ƚϵ����ڶ���
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}
	
	
	//-----------------------------------------------------------------------
	
	
	// Ϊ�˱���ܶ�߽�����
	// �ܶ�ʱ�����������±������峢�ԣ������ó��������峢��
	// ��Ϊ���������0�����±�Խ��Ļ����������ͱȽ��鷳
	public static int longestCommonSubsequence2(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		return f2(s1, s2, n, m);
	}

	// s1[ǰ׺����Ϊlen1] vs s2[ǰ׺����Ϊlen2]
	// ����������г���
	// len1 = 6 = �±�1~�±�5
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
	
	//���仯����
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
	
	//�ϸ�λ������
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
	
	// �ϸ�λ�������Ķ�̬�滮 + �ռ�ѹ��
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
		
		int[] dp = new int[m + 1]; //һ���������ȥ
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
