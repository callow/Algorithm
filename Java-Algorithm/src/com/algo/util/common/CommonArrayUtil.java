package com.algo.util.common;

public class CommonArrayUtil {

	/**
	 * 交换数组中2个元素位置
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	/**
	 * 拷贝，产生一个新数组
	 */
	
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}
	
	/**
	 * 打印数组
	 */
	
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 随机生成数组
	 */
	
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	
	/**
	 * 比较2个数组是否相等
	 */
	
	public static boolean equals(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 是否是空
	 */
	
	public static boolean isEmpty(int[] arr) {
		return arr == null || arr.length == 0;
	}
	
	/**
	 * 是否只有1个元素
	 */
	
	public static boolean hasOne(int[] arr) {
		return arr != null && arr.length == 1;
	}
	
}
