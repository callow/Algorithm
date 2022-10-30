package com.algo.util.sort;

import com.algo.util.CommonArrayUtil;

public class SortUtil {
	
	/**
	 * ѡ������ O��N^2����ÿ���ҵ���С�Ǹ��ŵ�ǰ��ȥ <br>
	 *   - 0 ~ N-1  ѡ����Сֵ�����ģ�swap�ŵ�0λ����  <br>
	 *   - 1 ~ n-1  �ҵ���Сֵ�����ģ�swap�ŵ�1 λ����  <br>
	 *   - 2 ~ n-1  �ҵ���Сֵ�����ģ�swap�ŵ�2 λ����  <br>
	 */
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i =0; i < arr.length -1; i++) {
			int min = i;
			for (int j = i+1; j <arr.length; j++) {
				min = arr[j] < arr[min] ? j : min;
			}
			CommonArrayUtil.swap(arr, i, min);
		}
	}
	
	/**
	 * ð������ O��N^2��
	 */
	
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		
		for (int e = arr.length -1; e > 0; e--) {
			for (int i = 0; i < e; i++) {
				if (arr[i] > arr[i+1]) {
					CommonArrayUtil.swap(arr, i, i+1);
				}
			}
		}
	}
	/**
	 * �������� O��N^2��
	 */
	public static void insertSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i-1; j>=0 && arr[j] >arr[j+1]; j--) {
				CommonArrayUtil.swap(arr, i, j+1);
			}
		}
	}
	
}
