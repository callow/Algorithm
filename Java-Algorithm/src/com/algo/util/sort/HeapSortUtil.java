package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

/**
 * SPACE : O(1) <br>
 * Time: O(N*logN)
 *
 */
public class HeapSortUtil {

	// 堆排序额外空间复杂度O(1)
	public static void heapSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		// O(N)
		for (int i = arr.length - 1; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}
		int heapSize = arr.length;
		CommonArrayUtil.swap(arr, 0, --heapSize);
		// O(N*logN)
		while (heapSize > 0) { // O(N)
			heapify(arr, 0, heapSize); // O(logN)
			CommonArrayUtil.swap(arr, 0, --heapSize); // O(1)
		}
	}
	
	/**
	 * 向下沉
	 */
	private static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		while (left < heapSize) { // 如果有左孩子，有没有右孩子，可能有可能没有！
			// 把较大孩子的下标，给largest
			int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
			largest = arr[largest] > arr[index] ? largest : index;
			if (largest == index) {
				break;
			}
			// index和较大孩子，要互换
			CommonArrayUtil.swap(arr, largest, index);
			index = largest;
			left = index * 2 + 1;
		}
	}
}
