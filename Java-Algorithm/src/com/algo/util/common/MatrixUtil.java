package com.algo.util.common;

public class MatrixUtil {
	
	
	
	
	
	

	/**
	 * 2维矩阵相乘
	 */
	public static int[][] multiple(int[][] a, int[][] b) {
		int n = a.length;
		int m = b[0].length;
		int k = a[0].length; // a的列数同时也是b的行数
		int[][] ans = new int[n][m];
		for(int i = 0 ; i < n; i++) {
			for(int j = 0 ; j < m;j++) {
				for(int c = 0; c < k; c++) {
					ans[i][j] += a[i][c] * b[c][j];
				}
			}
		}
		return ans;
	}
}
