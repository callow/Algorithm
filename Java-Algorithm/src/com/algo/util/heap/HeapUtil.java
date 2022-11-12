package com.algo.util.heap;

import java.util.PriorityQueue;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.Line;

/**
 * ��Array���Ա�ʾ��ȫ�����������ڵ�Ҫ����ȡ�� �������߶�Logn: <br><br>
 * �κ�һ�� i λ�� �� <br>
 *    �����ӵ�λ�� 2i+1 <br> 
 *    ���Һ��ӵ�λ�� 2i+2 <br>
 *    �����׵�λ�� (i -1) /2
 *
 */
public class HeapUtil {

	/**
	 * ���ƹ��� - O��Logn��: <br>
	 * 	��ͣ����ڵ�ʱ����Ȼά�ִ���ѣ�ÿ���븸�ڵ�PK�����ȸ�����swap(����)  <br>
	 *  ���ڶѵ�Push������������Ԫ��
	 */
	public static void heapInsert(int[] arr, int i) {
		int parent = (i - 1) / 2;
		while (arr[i] > arr[parent]) {
			CommonArrayUtil.swap(arr, i, parent);
			i = parent;
		}
	}
	
	/**
	 * �³����� - O��Logn��: <br>
	 * ����ͷ�׽����� Ȼ��HeapSize - 1�� Ȼ��ͷ��ͣ��ϴ��ӱȣ�Ȼ��swap���³��� <br>
	 * ���ڶѵ�pop��������ɾ�����Ԫ��
	 */
	public static void heapify(int[] arr, int i, int heapSize) {
		int leftSon = i * 2 + 1;
		while (leftSon < heapSize) {
			// �ҵ��ϴ�ĺ��ӣ����Լ�PK������ϴ���PK���Լ�������������
			int rightSon = i * 2 + 2;
			int largestSon = rightSon < heapSize && arr[rightSon] > arr[leftSon] ? rightSon : leftSon;
			largestSon = arr[largestSon] > arr[i] ? largestSon : i;
			if (largestSon == i) { // �����³��ˣ�ͣ��
				break;
			}
			// index�ͽϴ���Ҫ���� = �³���
			CommonArrayUtil.swap(arr, largestSon, i);
			i = largestSon; // i�����ϴ��ӵ�λ�ã�����
			leftSon = i * 2 + 1;
		}
	}
	
	/**
	 * �������������arr, ÿ��Ԫ��i ����k��С��Χ�������򣬶�Arr����������
	 * @param arr
	 * @param k : �����С��Χ��e.g k = 5
	 */
	
	public static void sortedArrDistanceLessK(int[] arr, int k) {
		if (k == 0) {
			return;
		}
		// Ĭ��С����
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		// 0...K-1
		for (; index <= Math.min(arr.length - 1, k - 1); index++) { // С���Ѵ�СΪK�� 
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; i++, index++) { // ��һ������һ������ΪС����Ĭ������
			heap.add(arr[index]);
			arr[i] = heap.poll();
		}
		while (!heap.isEmpty()) { //���Ľ�β�߽�
			arr[i++] = heap.poll();
		}
	}
	
	/**
	 * ����߶��غ����� ��n��[start,end], �ĸ���������д������򣬷����ص��� O(nLogn)<br><br>
	 * 
	 * 
	 */
	
	public static int getMaxDuplicates(int[][] m) {
		// ��Startλ����С��������
		Line[] lines = Line.fillAndSort(m);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			Line current = lines[i];
			
			// ��С������<= start��ȫ��Remove, minHeap.peek() ����ĳ���߶ε�End
			while (!minHeap.isEmpty() && minHeap.peek() <= current.start) {
				minHeap.poll();
			}
			// Ȼ����Լ���end����С����, С�������м��������Ǵ�, ���ͣ� ����غ�������start��Ϊ��߽�Ļ����ж����߶λᴩԽ��start
			minHeap.add(current.end);
			
			// �����߶���ɸѡһ�����ľ���ans
			max = Math.max(max, minHeap.size());
		}
		return max;
	}
	
}
