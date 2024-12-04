package dynamic_program.DD;
/**
 * 最小路径和问题
 * 
 * https://leetcode.cn/problems/minimum-path-sum/
 * 
 * 分析问题时候，dp表怎么填 从左往右，从上往下很关键
 * dp表 = 动态规划表
 */
public class MinimumPathSum1 {

	// 暴力递归
	public static int minPathSum1(int[][] grid) {
		return f1(grid, grid.length - 1, grid[0].length - 1);
	}
	// f1: 从(0,0)到(i,j)最小路径和 一定每次只能向右或者向下
	static int f1(int[][] grid, int i, int j) {
		if (i == 0 && j == 0) {
			return grid[0][0];
		}
		int up = Integer.MAX_VALUE;
		int left = Integer.MAX_VALUE;
		
		if (i - 1 >= 0) { // 有上边
			up = f1(grid, i - 1, j);
		}
		if (j - 1 >= 0) { // 有左边
			left = f1(grid, i, j - 1);
		}
		return grid[i][j] + Math.min(up, left);
		
	}
	
	//--------------------------------------------------
	// 记忆化搜索
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
		if(dp[i][j] != -1) { // 之前走过
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
	
	// 严格位置依赖的动态规划
	public static int minPathSum3(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		dp[0][0] = grid[0][0];
		for (int i = 1; i < n; i++) {  // 填dp表最左行 
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < m; j++) { // 填dp表最顶行
			dp[0][j] = dp[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < n; i++) { // 填dp表中间
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
	
	// 严格位置依赖的动态规划 + 空间压缩
	public static int minPathSum4(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		// 先让dp表，变成想象中的表的第0行的数据
		int[] dp = new int[m];
		dp[0] = grid[0][0];
		for (int j = 1; j < m; j++) {
			dp[j] = dp[j - 1] + grid[0][j];
		}
		for (int i = 1; i < n; i++) {
			// i = 1，dp表变成想象中二维表的第1行的数据
			// i = 2，dp表变成想象中二维表的第2行的数据
			// i = 3，dp表变成想象中二维表的第3行的数据
			// ...
			// i = n-1，dp表变成想象中二维表的第n-1行的数据
			dp[0] += grid[i][0];
			for (int j = 1; j < m; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
			}
		}
		return dp[m - 1];
	}
	
	
	
	
	
}
