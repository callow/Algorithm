package com.algo.util.floodfill;

public class Util {
	
	/**
	 * flood fill = dfs
	 * 
	 * 感染==‘1’部分
	 */
	public static void floodFill(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != '1') {
			return;
		}
		board[i][j] = 'Z'; // 感染成随便什么都可以的ascci code
		floodFill(board, i - 1, j); // 上
		floodFill(board, i + 1, j); // 下
		floodFill(board,  i, j - 1); // 左
		floodFill(board, i, j + 1); // 右
	}
	
	
	/**
	 * flood fill = dfs
	 * 
	 * 感染==‘0’部分
	 */
	public static void floodFill2(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != '0') {
			return;
		}
		board[i][j] = 'Z'; // 感染成随便什么都可以的ascci code
		floodFill2(board, i - 1, j); // 上
		floodFill2(board, i + 1, j); // 下
		floodFill2(board,  i, j - 1); // 左
		floodFill2(board, i, j + 1); // 右
	}
	
	public static void floodFill3(int[][] board, int i, int j, int id) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != 1) {
			return;
		}
		board[i][j] = id; // 感染成随便什么都可以的ascci code
		floodFill3(board, i - 1, j,id); // 上
		floodFill3(board, i + 1, j,id); // 下
		floodFill3(board,  i, j - 1,id); // 左
		floodFill3(board, i, j + 1,id); // 右
	}
}
