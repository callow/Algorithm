package dynamic_program.DDD;
/**
 * 
 * ��ʿ���ĸ���: n * n�Ĺ������������ϣ�һ����ʿ�ӵ�Ԫ��(r, c)��ʼ�������Խ��� k ���ƶ�
 * 	�к��д�0��ʼ���������ϵ�Ԫ���� (0,0)�����µ�Ԫ���� (n-1, n-1). 
 *  ����K���� ��ʿ������ֹͣ�ƶ��������������ϴ��ĸ��� 
 * 
 * https://leetcode.cn/problems/knight-probability-in-chessboard/
 * 
 */
public class KnightProbabilityInChessboard2 {
	
	// ���仯����
	public static double knightProbability(int n, int k, int row, int col) {
		double[][][] dp = new double[n][n][k + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int t = 0; t <= k; t++) {
					dp[i][j][t] = -1;
				}
			}
		}
		return f(n, row, col, k, dp);
	}

	// ��(i,j)��������k��Ҫ�ߣ���������������ϵĸ���
	public static double f(int n, int i, int j, int k, double[][][] dp) {
		if (i < 0 || i >= n || j < 0 || j >= n) {
			return 0;
		}
		if (dp[i][j][k] != -1) {
			return dp[i][j][k];
		}
		double ans = 0;
		if (k == 0) {
			ans = 1;
		} else {
			ans += (f(n, i - 2, j + 1, k - 1, dp) / 8);
			ans += (f(n, i - 1, j + 2, k - 1, dp) / 8);
			ans += (f(n, i + 1, j + 2, k - 1, dp) / 8);
			ans += (f(n, i + 2, j + 1, k - 1, dp) / 8);
			ans += (f(n, i + 2, j - 1, k - 1, dp) / 8);
			ans += (f(n, i + 1, j - 2, k - 1, dp) / 8);
			ans += (f(n, i - 1, j - 2, k - 1, dp) / 8);
			ans += (f(n, i - 2, j - 1, k - 1, dp) / 8);
		}
		dp[i][j][k] = ans;
		return ans;
	}

}