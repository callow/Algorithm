package com.algo.tasks.day10;
/**
 * Boolean �������https://leetcode-cn.com/problems/boolean-evaluation-lcci/
 * 
 *
 */
public class BooleanEvaluation {
	
	public static class Info {
		public int t; // ΪTrue�ķ�����
		public int f; // ΪFalse�ķ�����

		public Info(int tr, int fa) {
			t = tr;
			f = fa;
		}
	}
	
	public static int countEval(String express, int desired) {
		if (express == null || express.equals("")) {
			return 0;
		}
		char[] exp = express.toCharArray();
		int N = exp.length;
		Info[][] dp = new Info[N][N];
		Info allInfo = func(exp, 0, exp.length - 1, dp);
		return desired == 1 ? allInfo.t : allInfo.f;
	}
	
	
	// ����:
	// L...R�ϣ�һ�����������ַ�
	// Lλ�õ��ַ���Rλ�õ��ַ�����0��1���������߼����ţ�
	// ����str[L...R]��һ�Σ�Ϊtrue�ķ���������false�ķ�����
	public static Info func(char[] str, int L, int R, Info[][] dp) {
		if (dp[L][R] != null) {
			return dp[L][R];
		}
		int t = 0;
		int f = 0;
		if (L == R) { // base case
			t = str[L] == '1' ? 1 : 0;
			f = str[L] == '0' ? 1 : 0;
		} else { // L..R >=3
			// ÿһ�����߼����ţ�splitö�ٵĶ���
			// ��ȥ���������
			for (int split = L + 1; split < R; split += 2) {
				Info leftInfo = func(str, L, split - 1, dp);
				Info rightInfo = func(str, split + 1, R, dp);
				int a = leftInfo.t;
				int b = leftInfo.f;
				int c = rightInfo.t;
				int d = rightInfo.f;
				switch (str[split]) {
				case '&':
					t += a * c;
					f += b * c + b * d + a * d;
					break;
				case '|':
					t += a * c + a * d + b * c;
					f += b * d;
					break;
				case '^':
					t += a * d + b * c;
					f += a * c + b * d;
					break;
				}
			}

		}
		dp[L][R] = new Info(t, f);
		return dp[L][R];
	}
	
	
	public static int countEval2(String express, int desired) {
		if (express == null || express.equals("")) {
			return 0;
		}
		char[] exp = express.toCharArray();
		int N = exp.length;
		int[][][] dp = new int[2][N][N];
		dp[0][0][0] = exp[0] == '0' ? 1 : 0;
		dp[1][0][0] = dp[0][0][0] ^ 1;
		for (int i = 2; i < exp.length; i += 2) {
			dp[0][i][i] = exp[i] == '1' ? 0 : 1;
			dp[1][i][i] = exp[i] == '0' ? 0 : 1;
			for (int j = i - 2; j >= 0; j -= 2) {
				for (int k = j; k < i; k += 2) {
					if (exp[k + 1] == '&') {
						dp[1][j][i] += dp[1][j][k] * dp[1][k + 2][i];
						dp[0][j][i] += (dp[0][j][k] + dp[1][j][k]) * dp[0][k + 2][i] + dp[0][j][k] * dp[1][k + 2][i];
					} else if (exp[k + 1] == '|') {
						dp[1][j][i] += (dp[0][j][k] + dp[1][j][k]) * dp[1][k + 2][i] + dp[1][j][k] * dp[0][k + 2][i];
						dp[0][j][i] += dp[0][j][k] * dp[0][k + 2][i];
					} else {
						dp[1][j][i] += dp[0][j][k] * dp[1][k + 2][i] + dp[1][j][k] * dp[0][k + 2][i];
						dp[0][j][i] += dp[0][j][k] * dp[0][k + 2][i] + dp[1][j][k] * dp[1][k + 2][i];
					}
				}
			}
		}
		return dp[desired][0][N - 1];
	}


}
