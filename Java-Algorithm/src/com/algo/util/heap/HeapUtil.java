package com.algo.util.heap;

import com.algo.util.common.CommonArrayUtil;

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
	
	
}
