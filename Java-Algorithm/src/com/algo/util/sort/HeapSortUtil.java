package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.heap.HeapUtil;

/**
 * SPACE : O(1) <br>
 * Time: O(N*logN)
 *
 */
public class HeapSortUtil {

	// ∂—≈≈–Ú∂ÓÕ‚ø’º‰∏¥‘”∂»O(1)
	public static void heapSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		
		// O(N*logN)
//		for (int i = 0; i < arr.length; i++) { // O(N)
//			heapInsert(arr, i); // O(logN)
//		}
		
		// O(N)
		for (int i = arr.length - 1; i >= 0; i--) { // O£®N£©
			HeapUtil.heapify(arr, i, arr.length); // O£®logn£©
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
