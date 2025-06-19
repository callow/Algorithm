package dfs.week1;

/**
 * 类型： 连通块面积
 */
public class MaxAreaOfIsland {

	public static void main(String[] args) {
		char[][] grid = {
	            {'1', '1', '0', '0', '0'},
	            {'1', '1', '0', '0', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '0', '0', '0'}};
	            
		System.out.println(maxAreaOfIsland(grid));
	}
	
	public static int maxAreaOfIsland(char[][] grid) {
	   int n = grid.length;
	   int m = grid[0].length;
	   int area = 0;
	   for(int i = 0; i < n; i++) {
		   for(int j = 0; j < m; j++) {
			   if (grid[i][j] == '1') {
				   int beforeDFSLargestArea = area;
				   int afterDFSLargestArea = dfs(grid,n,m,i,j);
				   area = Math.max(beforeDFSLargestArea, afterDFSLargestArea);
			   }
		   }
	   }
	   return area;
	}
	
	public static int dfs(char[][] grid, int n , int m , int i, int j) {
		if (i < 0 || i == n || j < 0 || j == m || grid[i][j] != '1') {
			return 0;
		}
		int area = 1; // 当前格子算1
		grid[i][j] = '0';
		area += dfs(grid, n,m, i - 1, j); 
		area += dfs(grid, n,m, i + 1, j); 
		area += dfs(grid, n,m, i, j - 1); 
		area += dfs(grid, n,m, i, j + 1); 
		return area;
	}
}
