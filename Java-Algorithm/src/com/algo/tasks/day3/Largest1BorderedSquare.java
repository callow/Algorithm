package com.algo.tasks.day3;

/**
 * 一个只有0、1组成的Matrix，返回边框全是1的最大正方形面积？
 * 
 * https://leetcode.com/problems/largest-1-bordered-square/
 *
 */
public class Largest1BorderedSquare {
	
	public static int largest1BoarderedSquare(int[][] m) {
		// O(N^2): 填充右侧连续1统计信息 + 下侧连续1统计信息 
		int[][] right = new int[m.length][m[0].length];
		int[][] down = new int[m.length][m[0].length];
		setBorderMap(m, right, down);
		
		// O(N^3) : 空降遍历每个点，然后以最短边组成正方形
		for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
			if (hasSizeOfBorder(size, right, down)) {
				return size * size;
			}
		}
		return 0;
	}
	
	private static void setBorderMap(int[][] m, int[][] right, int[][] down) {
		int r = m.length;
		int c = m[0].length;
		if (m[r - 1][c - 1] == 1) {
			right[r - 1][c - 1] = 1;
			down[r - 1][c - 1] = 1;
		}
		for (int i = r - 2; i != -1; i--) {
			if (m[i][c - 1] == 1) {
				right[i][c - 1] = 1;
				down[i][c - 1] = down[i + 1][c - 1] + 1;
			}
		}
		for (int i = c - 2; i != -1; i--) {
			if (m[r - 1][i] == 1) {
				right[r - 1][i] = right[r - 1][i + 1] + 1;
				down[r - 1][i] = 1;
			}
		}
		for (int i = r - 2; i != -1; i--) {
			for (int j = c - 2; j != -1; j--) {
				if (m[i][j] == 1) {
					right[i][j] = right[i][j + 1] + 1;
					down[i][j] = down[i + 1][j] + 1;
				}
			}
		}
	}
	
	private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
		for (int i = 0; i != right.length - size + 1; i++) {
			for (int j = 0; j != right[0].length - size + 1; j++) {
				// O(1) : 判断是否是边长全是1的正方形
				if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
						&& down[i][j + size - 1] >= size) {
					return true;
				}
			}
		}
		return false;
	}
}
