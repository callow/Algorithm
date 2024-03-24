package com.algo.util.floodfill;

public class Util {
	
	/**
	 * flood fill = dfs
	 * 
	 * ��Ⱦ==��1������
	 */
	public static void floodFill(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != '1') {
			return;
		}
		board[i][j] = 'Z'; // ��Ⱦ�����ʲô�����Ե�ascci code
		floodFill(board, i - 1, j); // ��
		floodFill(board, i + 1, j); // ��
		floodFill(board,  i, j - 1); // ��
		floodFill(board, i, j + 1); // ��
	}
	
	
	/**
	 * flood fill = dfs
	 * 
	 * ��Ⱦ==��0������
	 */
	public static void floodFill2(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != '0') {
			return;
		}
		board[i][j] = 'Z'; // ��Ⱦ�����ʲô�����Ե�ascci code
		floodFill2(board, i - 1, j); // ��
		floodFill2(board, i + 1, j); // ��
		floodFill2(board,  i, j - 1); // ��
		floodFill2(board, i, j + 1); // ��
	}
	
	public static void floodFill3(int[][] board, int i, int j, int id) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != 1) {
			return;
		}
		board[i][j] = id; // ��Ⱦ�����ʲô�����Ե�ascci code
		floodFill3(board, i - 1, j,id); // ��
		floodFill3(board, i + 1, j,id); // ��
		floodFill3(board,  i, j - 1,id); // ��
		floodFill3(board, i, j + 1,id); // ��
	}
}
