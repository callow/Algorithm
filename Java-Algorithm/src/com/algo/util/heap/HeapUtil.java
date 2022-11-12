package com.algo.util.heap;

import com.algo.util.common.CommonArrayUtil;

/**
 * 用Array可以表示完全二叉树，父节点要向下取整 二叉树高度Logn: <br><br>
 * 任何一个 i 位置 ： <br>
 *    它左孩子的位置 2i+1 <br> 
 *    它右孩子的位置 2i+2 <br>
 *    它父亲的位置 (i -1) /2
 *
 */
public class HeapUtil {

	/**
	 * 上移过程 - O（Logn）: <br>
	 * 	不停插入节点时候，仍然维持大根堆，每次与父节点PK，若比父大则swap(上移)  <br>
	 *  用于堆的Push操作，即插入元素
	 */
	public static void heapInsert(int[] arr, int i) {
		int parent = (i - 1) / 2;
		while (arr[i] > arr[parent]) {
			CommonArrayUtil.swap(arr, i, parent);
			i = parent;
		}
	}
	
	/**
	 * 下沉过程 - O（Logn）: <br>
	 * 首先头首交换， 然后HeapSize - 1， 然后头不停与较大孩子比，然后swap（下沉） <br>
	 * 用于堆的pop操作，即删除最大元素
	 */
	public static void heapify(int[] arr, int i, int heapSize) {
		int leftSon = i * 2 + 1;
		while (leftSon < heapSize) {
			// 找到较大的孩子，与自己PK，如果较大孩子PK了自己，则让它上来
			int rightSon = i * 2 + 2;
			int largestSon = rightSon < heapSize && arr[rightSon] > arr[leftSon] ? rightSon : leftSon;
			largestSon = arr[largestSon] > arr[i] ? largestSon : i;
			if (largestSon == i) { // 不用下沉了，停！
				break;
			}
			// index和较大孩子要互换 = 下沉！
			CommonArrayUtil.swap(arr, largestSon, i);
			i = largestSon; // i来到较大孩子的位置，继续
			leftSon = i * 2 + 1;
		}
	}
	
	
}
