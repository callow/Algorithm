package com.algo.util.PrefixSum.model;

public class MatrixSumArray {

	public int[][] sum;

	public MatrixSumArray(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		sum = new int[n + 1][m + 1];
		for (int a = 1, c = 0; c < n; a++, c++) {
			for (int b = 1, d = 0; d < m; b++, d++) {
				sum[a][b] = matrix[c][d];
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				sum[i][j] += sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int a, int b, int c, int d) {
		c++;
		d++;
		return sum[c][d] - sum[c][b] - sum[a][d] + sum[a][b];
	}
	
	// ---------------------------------------------------------------------------------
	
	/**
	 *  构建前缀和数组，复用自己，没有申请额外空间sum
	 */
	public static void build(int n, int m, int[][] g) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				g[i][j] += get(g, i, j - 1) + get(g, i - 1, j) - get(g, i - 1, j - 1);
			}
		}
	}

	public static int sum(int[][] g, int a, int b, int c, int d) {
		return a > c ? 0 : (g[c][d] - get(g, c, b - 1) - get(g, a - 1, d) + get(g, a - 1, b - 1));
	}

	public static int get(int[][] g, int i, int j) {
		return (i < 0 || j < 0) ? 0 : g[i][j];
	}
}
