package com.algo.util.heap;

import com.algo.util.common.CommonArrayUtil;

/**
 * ��Array���Ա�ʾ��ȫ�����������ڵ�Ҫ����ȡ��
 *
 */
public class HeapUtil {

	/**
	 * ���ƹ��� - O��Logn��: <br>
	 * 	��ͣ����ڵ�ʱ����Ȼά�ִ���ѣ�ÿ���븸�ڵ�ȣ�Ȼ��swap(����)  <br>
	 *  ���ڵ�λ�ã� (index - 1) / 2
	 */
	public static void heapInsert(int[] arr, int index) {
		int parent = (index - 1) / 2;
		while (arr[index] > arr[parent]) {
			CommonArrayUtil.swap(arr, index, parent);
			index = parent;
		}
	}
	
	/**
	 * �³����� - O��Logn��: <br>
	 * ����ͷ�׽�����Ȼ��ͷ��ͣ��ϴ��ӱȣ�Ȼ��swap���³��� 
	 */
	public static void heapify(int[] arr, int index, int heapSize) {
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
