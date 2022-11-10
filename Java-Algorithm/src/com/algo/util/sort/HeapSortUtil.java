package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

/**
 * SPACE : O(1) <br>
 * Time: O(N*logN)
 *
 */
public class HeapSortUtil {

	// ���������ռ临�Ӷ�O(1)
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
	 * ���³�
	 */
	private static void heapify(int[] arr, int index, int heapSize) {
		int left = index * 2 + 1;
		while (left < heapSize) { // ��������ӣ���û���Һ��ӣ������п���û�У�
			// �ѽϴ��ӵ��±꣬��largest
			int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
			largest = arr[largest] > arr[index] ? largest : index;
			if (largest == index) {
				break;
			}
			// index�ͽϴ��ӣ�Ҫ����
			CommonArrayUtil.swap(arr, largest, index);
			index = largest;
			left = index * 2 + 1;
		}
	}
}
