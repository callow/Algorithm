package dynamic_program.DD;
/**
 * �༭�������⣺������������ word1 �� word2�� �뷵�ؽ� word1 ת���� word2 ��ʹ�õ����ٴ���
 			���룬�滻��ɾ�� ���۶���ͬ
   
   https://leetcode.cn/problems/edit-distance/
 *
 */
public class EditDistance7 {
	
	
	public int minDistance(String word1, String word2) {
		return editDistance1(word1, word2, 1, 1, 1);
	}
	
	// dp
	/**
	 * dp[i][j] : s1ȡǰi��(s1[0..i-1])�����ױ��s2ȡǰj��(s2[0..i-1])����С����
	 * dp[n][m] �������ս�
	 * Χ�����һ���ַ����ۣ�
	 * 	1. s1[i-1]���룺��β���?
	 * 	  a. �������=> s[i-1]���s2[j-1]
	 *       �� s[i-1] = s2[j-1], ��s1[i-2] -> s2[i-2]... ������ϵֻ��dp[i-1][j-1]
	 * 		 �� s[i-1] != s2[j-1], ��������ϵdp[i-1][j-1]+�滻����
	 *    
	 *    b. �㶨֮ǰ�����һ���ַ�����
	 *       s1[0...i-1] �㶨 s2[0..i-2] + ����������i-1��
	 *        ������ϵdp[i][j-1] + �������
	 *       
	 *  2. s1[i-1]�����룺
	 *  	dp[i-1][j] + ɾ������
	 */
	public static int editDistance1(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[i][j] :
		// s1[ǰ׺����Ϊi]����s2[ǰ׺����Ϊj]�����ٸ������ٴ���
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i * b;
		}
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j * a;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				int p1 = Integer.MAX_VALUE;
				if (s1[i - 1] == s2[j - 1]) {
					p1 = dp[i - 1][j - 1];
				}
				int p2 = Integer.MAX_VALUE;
				if (s1[i - 1] != s2[j - 1]) {
					p2 = dp[i - 1][j - 1] + c;
				}
				int p3 = dp[i][j - 1] + a;
				int p4 = dp[i - 1][j] + b;
				dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
			}
		}
		return dp[n][m];
	}
	
	// ö��С�Ż���
	public static int editDistance2(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[i][j] :
		// s1[ǰ׺����Ϊi]����s2[ǰ׺����Ϊj]�����ٸ������ٴ���
		int[][] dp = new int[n + 1][m + 1];
		
		// ��1�У�s1��ͬ�����ַ���ô���s2һ���ַ�Ҳû��.ֻ��ɾ���Ϳ���
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i * b;
		}
		// ��0�У�s1һ���ַ�Ҳû����ô���s2��ͬ�����ַ���ֻ�����Ϳ���
		   // ����1 ���� ����2�������� 3��..., n���Ĳ������
		for (int j = 1; j <= m; j++) {
			dp[0][j] = j * a;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + b, dp[i][j - 1] + a), dp[i - 1][j - 1] + c);
				}
			}
		}
		return dp[n][m];
	}

	// �ռ�ѹ��
	public static int editDistance3(String str1, String str2, int a, int b, int c) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		int[] dp = new int[m + 1];
		for (int j = 1; j <= m; j++) {
			dp[j] = j * a;
		}
		for (int i = 1, leftUp, backUp; i <= n; i++) {
			leftUp = (i - 1) * b;
			dp[0] = i * b;
			for (int j = 1; j <= m; j++) {
				backUp = dp[j];
				if (s1[i - 1] == s2[j - 1]) {
					dp[j] = leftUp;
				} else {
					dp[j] = Math.min(Math.min(dp[j] + b, dp[j - 1] + a), leftUp + c);
				}
				leftUp = backUp;
			}
		}
		return dp[m];
	}
	
	
}
