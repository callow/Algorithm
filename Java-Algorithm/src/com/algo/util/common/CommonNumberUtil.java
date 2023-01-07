package com.algo.util.common;

public class CommonNumberUtil {

	/**
	 * ��ȡĳ������x��dλ�ϵ�����e.g 199 ��ȡ��λ(2ed)�ϵ���Ϊ9
	 * @param x
	 * @param d
	 * @return
	 */
	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}
	
	/**
	 * ������������ж���λ 
	 */
	
	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10; // ��10�����ο��Գ���0
		}
		return res;
	}
	
	/**
	 * 2ά�������
	 */
	public static int[][] product2D(int[][] a, int[][] b) {
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
}
