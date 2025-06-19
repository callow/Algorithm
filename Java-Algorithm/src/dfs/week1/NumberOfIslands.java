package dfs.week1;

/**
 * 类型： 连通块计数
 */
public class NumberOfIslands {

	public static void main(String[] args) {
		char[][] grid = {
	            {'1', '1', '0', '0', '0'},
	            {'1', '1', '0', '0', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '0', '0', '0'}};
	            
		System.out.println(numIslands(grid));
	}
	
	public static int numIslands(char[][] grid) {
	   int n = grid.length;
	   int m = grid[0].length;
	   int islands = 0;
	   for(int i = 0; i < n; i++) {
		   for(int j = 0; j < m; j++) {
			   if (grid[i][j] == '1') {
				   islands++;
				   dfs(grid,n,m,i,j);
			   }
		   }
	   }
	   return islands;
	}
	
	public static void dfs(char[][] grid, int n , int m , int i, int j) {
		if (i < 0 || i == n || j < 0 || j == m || grid[i][j] != '1') {
			return;
		}
		grid[i][j] = '0';
		dfs(grid, n,m, i - 1, j); 
		dfs(grid, n,m, i + 1, j); 
		dfs(grid, n,m, i, j - 1); 
		dfs(grid, n,m, i, j + 1); 
	}
	
}
