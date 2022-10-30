package com.algo.util.common;

public class CommonArrayUtil {

	/**
	 * ����������2��Ԫ��λ��
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	/**
	 * ����������һ��������
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
	 * ��ӡ����
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
	 * �����������
	 */
	
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // �������
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	
	/**
	 * �Ƚ�2�������Ƿ����
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
	 * �Ƿ��ǿ�
	 */
	
	public static boolean isEmpty(int[] arr) {
		return arr == null || arr.length == 0;
	}
	
	/**
	 * �Ƿ�ֻ��1��Ԫ��
	 */
	
	public static boolean hasOne(int[] arr) {
		return arr != null && arr.length == 1;
	}
	
}
