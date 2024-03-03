package com.algo.util.common;

public class CommonNumberUtil {

	/**
	 * 提取某个数字x第d位上的数，e.g 199 提取百位(2ed)上的数为9
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
	 * 反转数字
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
