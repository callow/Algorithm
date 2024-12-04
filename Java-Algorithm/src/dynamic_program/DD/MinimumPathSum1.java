package dynamic_program.DD;
/**
 * ��С·��������
 * 
 * https://leetcode.cn/problems/minimum-path-sum/
 * 
 * ��������ʱ��dp����ô�� �������ң��������ºܹؼ�
 * dp�� = ��̬�滮��
 */
public class MinimumPathSum1 {

	// �����ݹ�
	public static int minPathSum1(int[][] grid) {
		return f1(grid, grid.length - 1, grid[0].length - 1);
	}
	// f1: ��(0,0)��(i,j)��С·���� һ��ÿ��ֻ�����һ�������
	static int f1(int[][] grid, int i, int j) {
		if (i == 0 && j == 0) {
			return grid[0][0];
		}
		int up = Integer.MAX_VALUE;
		int left = Integer.MAX_VALUE;
		
		if (i - 1 >= 0) { // ���ϱ�
			up = f1(grid, i - 1, j);
		}
		if (j - 1 >= 0) { // �����
			left = f1(grid, i, j - 1);
		}
		return grid[i][j] + Math.min(up, left);
		
	}
	
	//--------------------------------------------------
	// ���仯����
	public static int minPathSum2(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = -1;
			}
		}
		return f2(grid, grid.length - 1, grid[0].length - 1, dp);
	}
	static int f2(int[][] grid, int i, int j, int[][] dp) {
		if(dp[i][j] != -1) { // ֮ǰ�߹�
			return dp[i][j];
		}
		int ans;
		if(i == 0 && j == 0) {
			ans = grid[0][0];
		} else {
			int up = Integer.MAX_VALUE;
			int left = Integer.MAX_VALUE;
			if (i - 1 >= 0) {
				up = f2(grid, i - 1, j, dp);
			}
			if (j - 1 >= 0) {
				left = f2(grid, i, j - 1, dp);
			}
			ans = grid[i][j] + Math.min(up, left);
		}
		dp[i][j] = ans;
		return ans;
	}
	
	// ----------------------------------------------------------
	
	// �ϸ�λ�������Ķ�̬�滮
	public static int minPathSum3(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < n; i++) {  // ��dp�������� 
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < m; j++) { // ��dp�����
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < n; i++) { // ��dp���м�
			for (int j = 1; j < m; j++) {
				int up = dp[i - 1][j];
				int left = dp[i][j - 1];
				int smallest = Math.min(up, left);
				dp[i][j] =  smallest + grid[i][j];
			}
		}
		return dp[n - 1][m - 1];
	}
	
	// ------------------------------------------------------------
	
	// �ϸ�λ�������Ķ�̬�滮 + �ռ�ѹ��
	public static int minPathSum4(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		// ����dp����������еı�ĵ�0�е�����
		int[] dp = new int[m];
		dp[0] = grid[0][0];
		for (int j = 1; j < m; j++) {
			dp[j] = dp[j - 1] + grid[0][j];
		}
		for (int i = 1; i < n; i++) {
			// i = 1��dp���������ж�ά��ĵ�1�е�����
			// i = 2��dp���������ж�ά��ĵ�2�е�����
			// i = 3��dp���������ж�ά��ĵ�3�е�����
			// ...
			// i = n-1��dp���������ж�ά��ĵ�n-1�е�����
			dp[0] += grid[i][0];
			for (int j = 1; j < m; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
			}
		}
		return dp[m - 1];
	}
	
	
	
	
	
}
