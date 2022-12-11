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
}