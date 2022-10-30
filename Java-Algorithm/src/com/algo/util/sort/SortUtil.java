package com.algo.util.sort;

import com.algo.util.CommonArrayUtil;

public class SortUtil {
	
	/**
	 * 选择排序 O（N^2），每次找到最小那个放到前面去 <br>
	 *   - 0 ~ N-1  选择到最小值，在哪，swap放到0位置上  <br>
	 *   - 1 ~ n-1  找到最小值，在哪，swap放到1 位置上  <br>
	 *   - 2 ~ n-1  找到最小值，在哪，swap放到2 位置上  <br>
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
	 * 冒泡排序 O（N^2）
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
	 * 插入排序 O（N^2）
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
