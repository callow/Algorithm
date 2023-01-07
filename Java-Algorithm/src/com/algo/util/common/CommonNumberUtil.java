package com.algo.util.common;

public class CommonNumberUtil {

	/**
	 * 提取某个数字x第d位上的数，e.g 199 提取百位(2ed)上的数为9
	 * @param x
	 * @param d
	 * @return
	 */
	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}
	
	/**
	 * 数组中最大数有多少位 
	 */
	
	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10; // 除10除几次可以除成0
		}
		return res;
	}
	
	/**
	 * 2维矩阵相乘
	 */
	public static int[][] product2D(int[][] a, int[][] b) {
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
