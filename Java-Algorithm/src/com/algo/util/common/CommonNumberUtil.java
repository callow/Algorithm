package com.algo.util.common;

public class CommonNumberUtil {

	/**
	 * ��ȡĳ������x��dλ�ϵ�����e.g 199 ��ȡ��λ(2ed)�ϵ���Ϊ9
	 * 
	 * @param x
	 * @param d
	 * @return
	 * 
	 * 17293 -> offset = 1 -> (17293 / offset) % 10 = 3
	 * 17293 -> offset = 10 -> (17293 / offset) % 10 = 9
	 * 17293 -> offset = 100 -> (17293 / offset) % 10 = 2
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
	 * 
	 * �����м�λ
	 */

	public static int countDigits(int num) {
		int count = 0;
		while (num != 0) {
			num /= 10;
			++count;
		}
		return count;
	}
	
	/**
	 * ��ת����
	 */
	
	public static int reverse(int number) {
		int reverse = 0;
		for(;number != 0;) {  
			int remainder = number % 10;  
			reverse = reverse * 10 + remainder;  
			number=number/10;  
		}
		return reverse;
	}

	/**
	 * 
	 * �������е�0�ظ�һ�Σ�����Ԫ���������ƣ��Ҳ���������
	 */
	public static void duplicateZeros(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0 && i != arr.length - 1) {
				for (int j = arr.length - 1; j > i; j--) {
					arr[j] = arr[j - 1];
				}
				i++;
			}
		}
	}

}
