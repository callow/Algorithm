package com.algo.util.floodfill;

/**
 * 
 * flood fill = dfs/bfs
 */
public class FloodFillUtil {
	
	/**
	 * 1的岛屿数量问题
	 *  flood fill = dfs = 感染 = 是1就上线左右感染（1-> 2）
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
	
	
	
}
