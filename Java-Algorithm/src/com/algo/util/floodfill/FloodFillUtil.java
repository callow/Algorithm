package com.algo.util.floodfill;

/**
 * 
 * flood fill = dfs/bfs
 */
public class FloodFillUtil {
	
	/**
	 * 1的岛屿数量问题
	 *  flood fill = dfs = 感染 = 是1就上下左右感染（1-> 2）
	 *  
	 *  https://leetcode.com/problems/number-of-islands/description/
	 */
	public static int numOfislands(char[][] board) {
		int island = 0;
		int n = board.length;
		int m = board[0].length;
		
		for(int i =0; i < n; i++) { // 从左往右
			for(int j =0; j < m; j++) { // 从上往下
				if(board[i][j] == '1') {
					island++;
					Util.floodFill(board,i,j);
				}
			}
		}
		return island;
	}

	
	/**
	 * 只要被x包围的0，都必须改成0。
	 * xxx    xxx
	 * x0x -> xxx
	 * xxx	  xxx
	 * 
	 * https://leetcode.com/problems/surrounded-regions/description/
	 * 
	 * 思路：
	 * 	1. 把四个边的0联通区域洪水填充换成w （外界是通的=没围住）
	 *  2. 遍历board, 将被包围的0变成x
	 *  3. 恢复w称0
	 * 
	 */
	public static void flipSurroundedRegion(char[][] board) {
		int n = board.length; // row
		int m = board[0].length; // col 
		
		for (int j = 0; j < m; j++) {
			if (board[0][j] == 'O') { // 上: 0行1列，0行2列...
				Util.floodFill2(board, 0, j);
			}
			if (board[n - 1][j] == 'O') { // 下： n行1列，n行1列...
				Util.floodFill2(board, n - 1, j);
			}
		 }
		
		for (int i = 1; i < n - 1; i++) {
			if (board[i][0] == 'O') { // 左： 1行0列，2行0列...
				Util.floodFill2(board, i, 0);
			}
			if (board[i][m - 1] == 'O') { // 右：1行m列，2行m列...
				Util.floodFill2(board, i, m - 1);
			}
		}
		
		// 现在开始变0 --> x
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'F') {
					board[i][j] = 'O';
				}
			}
		}
	}
	
	
	/**
	 * 0 = water, 1 = island, 只允许flip一个0 让连成的岛最大
	 * 
	 * https://leetcode.com/problems/making-a-large-island/description/
	 * 
	 * 1. 使用Floodfill给每一片岛标号：1，2，3，4. 并统计个数
	 * 2. 遍历剩下的0，统计连成最大的岛
	 */
	public static int largestIslandAfterFlip(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int id = 2;
		
		// 给岛屿标号
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					Util.floodFill3(grid, i, j, id++);
				}
			}
		}
		
		// 收集每个编号岛大小
		int[] sizes = new int[id];
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] > 1) {
					ans = Math.max(ans, ++sizes[grid[i][j]]);
				}
			}
		}
		
		// 讨论所有的0，变成1，能带来的最大岛的大小
		boolean[] visited = new boolean[id];
		int up, down, left, right, merge; // 上面是哪个岛的编号，下面是哪个岛的编号
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) {
					up = i > 0 ? grid[i - 1][j] : 0;
					down = i + 1 < n ? grid[i + 1][j] : 0;
					left = j > 0 ? grid[i][j - 1] : 0;
					right = j + 1 < m ? grid[i][j + 1] : 0;
					visited[up] = true;
					merge = 1 + sizes[up];
					if (!visited[down]) {
						merge += sizes[down];
						visited[down] = true;
					}
					if (!visited[left]) {
						merge += sizes[left];
						visited[left] = true;
					}
					if (!visited[right]) {
						merge += sizes[right];
						visited[right] = true;
					}
					ans = Math.max(ans, merge); // 变后收集最大的岛屿
					visited[up] = false;
					visited[down] = false;
					visited[left] = false;
					visited[right] = false;
				}
			}
		}
		return ans;
	}
	
	/**
	 * 落砖块问题： 砖块通过1(砖)粘在天花板上，我们打，看几个砖能掉下来
	 * 
	 * https://leetcode.cn/problems/bricks-falling-when-hit/
	 * 
	 * 技巧： 时光倒流：
	 *  1. 全部炮弹位置-1
	 *  2. floodfill天花板=> 2
	 *  3. 时光倒流: 每个炮弹从后往前顺序+1回来，然后看看上下左右是否有2/是否是天花板，如果有则floodfill 看看念在天花板上的砖块数量是否有新增，比如新增4个2，则打下3个砖
	 *  	即：答案就是新增2的数量-1
	 */

	public static int[] hitBricks(int[][] grid, int[][] hits) {
		int n = grid.length; // 行
		int m = grid[0].length;
		
		int[] ans = new int[hits.length];
		if (n == 1) { // 只有1行，没回砖块掉落
			return ans;
		}
		
		// 1. 全部炮弹位置-1
		for (int[] hit : hits) {
			grid[hit[0]][hit[1]]--;
		}
		// floodfill天花板=> 2
		for (int i = 0; i < m; i++) {
			Util.floodFill4(grid,0, i);
		}
		
		// 时光倒流的顺序去处理炮弹
		for (int i = hits.length - 1, row, col; i >= 0; i--) {
			row = hits[i][0];
			col = hits[i][1];
			grid[row][col]++; // 恢复炮弹
			if (worth(grid, row, col, n, m)) { // 判断是否值得去判断
				ans[i] = Util.floodFill4(grid, row, col) - 1;
			}
		}
		return ans;
	}
	public static boolean worth(int[][] grid, int i, int j, int n, int m) {
		return grid[i][j] == 1 // 现在是1
				&&
				(i == 0 // 如果处于天花板位置
				// 如果上下左右位置存在2
				|| (i > 0 && grid[i - 1][j] == 2)
				|| (i < n - 1 && grid[i + 1][j] == 2)
				|| (j > 0 && grid[i][j - 1] == 2)
				|| (j < m - 1 && grid[i][j + 1] == 2));
	}
	
	
	
	
}
