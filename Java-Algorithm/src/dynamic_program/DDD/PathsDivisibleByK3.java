package dynamic_program.DDD;
/**
 * 
 * �����к��ܱ� K ������·��: ��һ���±��0��ʼ�� n * m �������� grid ��һ������ k, �����(0,0)������ÿ��ֻ�����»������ң�����Ҫ�����յ�(m-1, n-1)
 * 	���㷵��·�����ܱ� k ������·����Ŀ
 * 
 * https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
 * 
 */
public class PathsDivisibleByK3 {

	
	public static int mod = 1000000007;

	public static int numberOfPaths1(int[][] grid, int k) {
		int n = grid.length;
		int m = grid[0].length;
		return f1(grid, n, m, k, 0, 0, 0);
	}

	// ��ǰ����(i,j)λ�ã�����һ��Ҫ�ߵ����½�(n-1,m-1)
	// ��(i,j)����������һ��Ҫ�ߵ����½�(n-1,m-1)���ж�����·�����ۼӺ�%k��������r
	public static int f1(int[][] grid, int n, int m, int k, int i, int j, int r) {
		
		if (i == n - 1 && j == m - 1) {
			return grid[i][j] % k == r ? 1 : 0;
		}
		// ������Ҫ�ճ���������need
 		int need = (k + r - (grid[i][j] % k)) % k;
		int ans = 0;
		if (i + 1 < n) { // ������
			ans = f1(grid, n, m, k, i + 1, j, need);
		}
		if (j + 1 < m) {// ������
			ans = (ans + f1(grid, n, m, k, i, j + 1, need)) % mod;
		}
		return ans;
	}
	
	//---------------------------------------------------------------
	
	// ���仯����
	public static int numberOfPaths2(int[][] grid, int k) {
		int n = grid.length;
		int m = grid[0].length;
		int[][][] dp = new int[n][m][k];
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < m; b++) {
				for (int c = 0; c < k; c++) {
					dp[a][b][c] = -1;
				}
			}
		}
		return f2(grid, n, m, k, 0, 0, 0, dp);
	}

	public static int f2(int[][] grid, int n, int m, int k, int i, int j, int r, int[][][] dp) {
		if (i == n - 1 && j == m - 1) {
			return grid[i][j] % k == r ? 1 : 0;
		}
		if (dp[i][j][r] != -1) {
			return dp[i][j][r];
		}
		int need = (k + r - grid[i][j] % k) % k;
		int ans = 0;
		if (i + 1 < n) {
			ans = f2(grid, n, m, k, i + 1, j, need, dp);
		}
		if (j + 1 < m) {
			ans = (ans + f2(grid, n, m, k, i, j + 1, need, dp)) % mod;
		}
		dp[i][j][r] = ans;
		return ans;
	}
	
	// ------------------------------------
	// �ϸ�λ������
	public static int numberOfPaths3(int[][] grid, int k) {
		int n = grid.length;
		int m = grid[0].length;
		int[][][] dp = new int[n][m][k];
		dp[n - 1][m - 1][grid[n - 1][m - 1] % k] = 1;
		for (int i = n - 2; i >= 0; i--) {
			for (int r = 0; r < k; r++) {
				dp[i][m - 1][r] = dp[i + 1][m - 1][(k + r - grid[i][m - 1] % k) % k];
			}
		}
		for (int j = m - 2; j >= 0; j--) {
			for (int r = 0; r < k; r++) {
				dp[n - 1][j][r] = dp[n - 1][j + 1][(k + r - grid[n - 1][j] % k) % k];
			}
		}
		for (int i = n - 2, need; i >= 0; i--) {
			for (int j = m - 2; j >= 0; j--) {
				for (int r = 0; r < k; r++) {
					need = (k + r - grid[i][j] % k) % k;
					dp[i][j][r] = dp[i + 1][j][need];
					dp[i][j][r] = (dp[i][j][r] + dp[i][j + 1][need]) % mod;
				}
			}
		}
		return dp[0][0][0];
	}
}
