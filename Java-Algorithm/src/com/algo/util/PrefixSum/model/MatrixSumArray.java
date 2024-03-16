package com.algo.util.PrefixSum.model;

public class MatrixSumArray {

	public int[][] sum;
	
	/**
	 * Matrix前缀和公式sum[][]：
	 * 顺序：从左到右一行一行处理
	 * 	当前cell的 : 左 + 上 - 左上 + 自己 => sum[i][j] += sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1]
	 * e.g:
	 * [3,1,-2]
	 * [5,-3,4]  => sum[][] =
	 * [3,6,-2]
	 * 
	 * [3,4,2]
	 * [8,6,8]     所以 6  = 3+1+5+-3
	 * [11,15,15]
	 * 
	 * 如果求子矩阵，不是以[0][0]为基点，只需要使用容斥原理求即可。 
	 * 
	 * 实际过程中，要补第0行，和 第0列 减少边界判断
	 * e.g:
	 * [0,0,0, 0]
	 * [0,3,1,-2]
	 * [0,5,-3,4]
	 * [0,3,6,-2]
	 * 
	 */
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
	
	/**
	 * 左上角点：(a,b), 到右下角点(c,d) 的累加和公式： sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1]
	 */
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
