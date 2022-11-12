package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

public class SortUtil {
	
	/**
	 * 选择排序 O（N^2），每次找到最小那个放到前面去 <br>
	 *   - 0 ~ N-1  选择到最小值，在哪，swap放到0位置上  <br>
	 *   - 1 ~ n-1  找到最小值，在哪，swap放到1 位置上  <br>
	 *   - 2 ~ n-1  找到最小值，在哪，swap放到2 位置上  <br>
	 */
	public static void selectionSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
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
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
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
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i-1; j>=0 && arr[j] >arr[j+1]; j--) {
				CommonArrayUtil.swap(arr, i, j+1);
			}
		}
	}
	
	/**
	 * 归并排序 O(N * logN) <br>
	 *  - 准备一个help[], 左右谁小拷贝谁拷贝完往下移动.help[]填满后，拷贝会原数组完成排序。 <br>
	 *  - 指针不回退 => O(N) <br>
	 *  - 不停地分解，merge,分解，merge
	 */
	
	public static void mergeSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}
	
	private static void process(int[] arr, int l, int r) {
		if (l == r) { // base case , 相遇了
			return;
		}
		int mid = l + ((r - l) / 2);
		process(arr, l, mid);
		process(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}
	
	// merge = 谁小拷贝谁
	private static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r-l +1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		
		while(p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		// 要么p1越界了，要么p2越界了
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}
	
}
