package com.algo.util.heap;

import java.util.PriorityQueue;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.Line;

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
	
	/**
	 * 几乎有序的数组arr, 每个元素i 往后k的小范围可能无序，对Arr它进行排序
	 * @param arr
	 * @param k : 无序的小范围，e.g k = 5
	 */
	
	public static void sortedArrDistanceLessK(int[] arr, int k) {
		if (k == 0) {
			return;
		}
		// 默认小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		// 0...K-1
		for (; index <= Math.min(arr.length - 1, k - 1); index++) { // 小根堆大小为K， 
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; i++, index++) { // 加一个，弹一个，因为小根堆默认有序
			heap.add(arr[index]);
			arr[i] = heap.poll();
		}
		while (!heap.isEmpty()) { //最后的结尾边界
			arr[i++] = heap.poll();
		}
	}
	
	/**
	 * 最大线段重合问题 有n个[start,end], 哪个区域是重写最多区域，返回重叠数 O(nLogn)<br><br>
	 * 
	 * 
	 */
	
	public static int getMaxDuplicates(int[][] m) {
		// 以Start位置由小到大排序
		Line[] lines = Line.fillAndSort(m);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			Line current = lines[i];
			
			// 将小根堆中<= start的全部Remove, minHeap.peek() 就是某个线段的End
			while (!minHeap.isEmpty() && minHeap.peek() <= current.start) {
				minHeap.poll();
			}
			// 然后把自己的end放入小根堆, 小根堆中有几个数就是答案, 解释： 如果重合区域以start作为左边界的话，有多少线段会穿越过start
			minHeap.add(current.end);
			
			// 所有线段中筛选一遍最大的就是ans
			max = Math.max(max, minHeap.size());
		}
		return max;
	}
	
}
