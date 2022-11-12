package com.algo.util.heap;

import com.algo.util.common.CommonArrayUtil;

/**
 * 用Array可以表示完全二叉树，父节点要向下取整
 *
 */
public class HeapUtil {

	/**
	 * 上移过程 - O（Logn）: <br>
	 * 	不停插入节点时候，仍然维持大根堆，每次与父节点比，然后swap(上移)  <br>
	 *  父节点位置： (index - 1) / 2
	 */
	public static void heapInsert(int[] arr, int index) {
		int parent = (index - 1) / 2;
		while (arr[index] > arr[parent]) {
			CommonArrayUtil.swap(arr, index, parent);
			index = parent;
		}
	}
	
	/**
	 * 下沉过程 - O（Logn）: <br>
	 * 首先头首交换，然后头不停与较大孩子比，然后swap（下沉） 
	 */
	public static void heapify(int[] arr, int index, int heapSize) {
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
