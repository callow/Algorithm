package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.heap.HeapUtil;

/**
 * SPACE : O(1) <br>
 * Time: O(N*logN)
 *
 */
public class HeapSortUtil {

	// ¶ÑÅÅÐò¶îÍâ¿Õ¼ä¸´ÔÓ¶ÈO(1)
	public static void heapSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		// O(N)
		for (int i = arr.length - 1; i >= 0; i--) {
			HeapUtil.heapify(arr, i, arr.length);
		}
		int heapSize = arr.length;
		CommonArrayUtil.swap(arr, 0, --heapSize);
		// O(N*logN)
		while (heapSize > 0) { // O(N)
			HeapUtil.heapify(arr, 0, heapSize); // O(logN)
			CommonArrayUtil.swap(arr, 0, --heapSize); // O(1)
		}
	}
	

}
