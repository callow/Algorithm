package com.algo.util.common;

import com.algo.util.Monostack.MonotonicStackUtil;

public class MatrixUtil {
	
	/**
	 * ������ѹ����ֱ��ͼ����,����ѹ�������У���������������ľ���<br>
	 * ʹ�õ���ջ����maxHistogram
	 */
	
	public static int condenseToHistogram(int[][] matrix, int maxArea) {
		int[] height = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
			}
			maxArea = Math.max(MonotonicStackUtil.maxHistogram(height), maxArea);
		}
		return maxArea;
	}
	
	/**
	 * 2ά�������
	 */
	public static int[][] multiple(int[][] a, int[][] b) {
		int n = a.length;
		int m = b[0].length;
		int k = a[0].length; // a������ͬʱҲ��b������
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
	
	public static boolean isEmpty(int[][] matrix) {
		return matrix == null || matrix.length == 0 || matrix[0].length == 0;
	}
}
