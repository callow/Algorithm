package com.algo.tasks.day13;
/**
 * 
 * �����ַ����� https://leetcode.com/problems/scramble-string/
 *
 */
public class ScrambleString {


	public static boolean isScramble0(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		return process0(str1, 0, str1.length - 1, str2, 0, str2.length - 1);
	}

	// str1[L1...R1] str2[L2...R2] �Ƿ�Ϊ���䴮
	// һ����֤�������ǵȳ��ģ�
	public static boolean process0(char[] str1, int L1, int R1, char[] str2, int L2, int R2) {
		if (L1 == R1) { // base case
			return str1[L1] == str2[L2];
		}
		for (int leftEnd = L1; leftEnd < R1; leftEnd++) {
			boolean p1 = process0(str1, L1, leftEnd, str2, L2, L2 + leftEnd - L1)
					&& process0(str1, leftEnd + 1, R1, str2, L2 + leftEnd - L1 + 1, R2);
			boolean p2 = process0(str1, L1, leftEnd, str2, R2 - (leftEnd - L1), R2)
					&& process0(str1, leftEnd + 1, R1, str2, L2, R2 - (leftEnd - L1) - 1);
			if (p1 || p2) {
				return true;
			}
		}
		return false;
	}

	
	public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
		if (str1.length != str2.length) {
			return false;
		}
		int[] map = new int[256];
		for (int i = 0; i < str1.length; i++) {
			map[str1[i]]++;
		}
		for (int i = 0; i < str2.length; i++) {
			if (--map[str2[i]] < 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isScramble1(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		return process1(str1, str2, 0, 0, N);
	}

	// ����str1[��L1��ʼ���ҳ���Ϊsize���Ӵ�]��str2[��L2��ʼ���ҳ���Ϊsize���Ӵ�]�Ƿ�Ϊ�����ַ���
	// ��str1�е���һ�κ�str2�е���һ��һ���ǵȳ��ģ�����ֻ��һ������size
	public static boolean process1(char[] str1, char[] str2, int L1, int L2, int size) {
		if (size == 1) {
			return str1[L1] == str2[L2];
		}
		// ö��ÿһ���������һ���������Ϊ����ͷ���true�����㲻������󷵻�false
		for (int leftPart = 1; leftPart < size; leftPart++) {
			if (
			// ���1���2�󣬲���1�Ҷ�2��
			(process1(str1, str2, L1, L2, leftPart)
					&& process1(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart)) ||
			// ���1���2�ң�����1�Ҷ�2��
					(process1(str1, str2, L1, L2 + size - leftPart, leftPart)
							&& process1(str1, str2, L1 + leftPart, L2, size - leftPart))) {
				return true;
			}
		}
		return false;
	}

	

	public static boolean isScramble2(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		int[][][] dp = new int[N][N][N + 1];
		// dp[i][j][k] = 0 processDP(i,j,k)״̬֮ǰû�������
		// dp[i][j][k] = -1 processDP(i,j,k)״̬֮ǰ�����,����ֵ��false
		// dp[i][j][k] = 1 processDP(i,j,k)״̬֮ǰ�����,����ֵ��true
		return process2(str1, str2, 0, 0, N, dp);
	}

	/**
	 * 
		�ĸ��ɱ����(L1,R1, L2,R2) ���Ըļ��仯���������ǿ����Ż���һ������, ��ΪSize����һ��������R1 ��R2�����Ƶ�����
	 */
	public static boolean process2(char[] str1, char[] str2, int L1, int L2, int size, int[][][] dp) {
		if (dp[L1][L2][size] != 0) {
			return dp[L1][L2][size] == 1;
		}
		boolean ans = false;
		if (size == 1) {
			ans = str1[L1] == str2[L2];
		} else {
			for (int leftPart = 1; leftPart < size; leftPart++) {
				if ((process2(str1, str2, L1, L2, leftPart, dp)
						&& process2(str1, str2, L1 + leftPart, L2 + leftPart, size - leftPart, dp))
						|| (process2(str1, str2, L1, L2 + size - leftPart, leftPart, dp)
								&& process2(str1, str2, L1 + leftPart, L2, size - leftPart, dp))) {
					ans = true;
					break;
				}
			}
		}
		dp[L1][L2][size] = ans ? 1 : -1;
		return ans;
	}

	public static boolean isScramble3(String s1, String s2) {
		if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
			return false;
		}
		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if (!sameTypeSameNumber(str1, str2)) {
			return false;
		}
		int N = s1.length();
		boolean[][][] dp = new boolean[N][N][N + 1];
		for (int L1 = 0; L1 < N; L1++) {
			for (int L2 = 0; L2 < N; L2++) {
				dp[L1][L2][1] = str1[L1] == str2[L2];
			}
		}
		// ��һ��forѭ�������ǣ�������size=2�㡢size=3��..size=N�㣬ÿһ�㶼��һ����άƽ��
		// �ڶ�������forѭ�������ǣ��ھ����һ�㣬�����涼Ҫ��д������������forѭ��ȥ��һ����ά��
		// L1��ȡֵ��Χ��[0,N-size]����Ϊ��L1�������ҳ���Ϊsize���Ӵ���L1�ǲ��ܴ�N-size+1�����ģ��������ҾͲ���size���ַ���
		// L2��ȡֵ��Χͬ��
		// ��4��forѭ����ȫ�ǵݹ麯����ôд���������ô�ĵ�
		for (int size = 2; size <= N; size++) {
			for (int L1 = 0; L1 <= N - size; L1++) {
				for (int L2 = 0; L2 <= N - size; L2++) {
					for (int leftPart = 1; leftPart < size; leftPart++) {
						if ((dp[L1][L2][leftPart] && dp[L1 + leftPart][L2 + leftPart][size - leftPart])
								|| (dp[L1][L2 + size - leftPart][leftPart] && dp[L1 + leftPart][L2][size - leftPart])) {
							dp[L1][L2][size] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][0][N];
	}
}
