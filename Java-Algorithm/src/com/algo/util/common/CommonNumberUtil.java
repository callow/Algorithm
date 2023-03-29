package com.algo.util.common;

public class CommonNumberUtil {

	/**
	 * 提取某个数字x第d位上的数，e.g 199 提取百位(2ed)上的数为9
	 * 
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
	 * 
	 * 数字有几位
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
	 * 
	 * 将数组中的0重复一次，数组元素依次右移，且不可以扩容
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
