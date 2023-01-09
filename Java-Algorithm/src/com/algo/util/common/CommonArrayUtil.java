package com.algo.util.common;

import java.lang.reflect.Array;

import com.algo.util.common.model.Node;
@SuppressWarnings("unchecked")
public class CommonArrayUtil {

	/**
	 * ����������2��Ԫ��λ��
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void swap(Node[] arr, int i, int j) {
		Node tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	/**
	 * ���������partiton, pivot���м䣬 < pivot����ߣ� > pivot ���ұ�
	 * @param nodeArr
	 * @param pivot
	 */
	public static void partition(Node<Integer>[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].value < pivot) {
				CommonArrayUtil.swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				CommonArrayUtil.swap(nodeArr, --big, index);
			}
		}
	}
	
	
	/**
	 * ����������һ��������
	 */
	
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}
	
	/**
	 * ��ӡ����
	 */
	
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * �����������
	 */
	
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // �������
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	
	/**
	 * �Ƚ�2�������Ƿ����
	 */
	
	public static boolean equals(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �Ƿ��ǿ�
	 */
	
	public static boolean isEmpty(int[] arr) {
		return arr == null || arr.length == 0;
	}
	
	/**
	 * �Ƿ�ֻ��1��Ԫ��
	 */
	
	public static boolean hasOne(int[] arr) {
		return arr != null && arr.length == 1;
	}
	
	public static <T>  T[] getArray(Class<T> componentType,int length) {
		return (T[]) Array.newInstance(componentType, length);
	}
	
	/**
	 * ����ǰ׺������,�ӿ���������ۼ����� preSum[i] = arr[0...i]�ĺ�
	 */
	
	public static long[] prefixSumArr(int[] arr) {
		long[] preSum = new long[arr.length];
		preSum[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			preSum[i] = preSum[i - 1] + arr[i];
		}
		return preSum;
	}
	
	/**
	 * ����iλ�ý�β�������ж����������ۼӺ����ڡ�lower, uppper���� �� <br>
	 * 	 - �����ۼӺ� preSum(i)  <br>
	 *   - ˼άת���� �ж�����i��β�� = ��preSum(i) - ÿ����ͷ��ǰ׺�ͣ��� [preSum(i) - uppper�� preSum(i) - lower]
	 */
	
	public static int countPostfixArrRange(int[] arr, int i, int lower, int upper) {
		long [] preSum = prefixSumArr(arr);
		long sumI = preSum[i];
		int counter = 0;
		for (int x = 0; x < i; x++) {
			if (sumI - preSum[x] >= (sumI - upper) && sumI - preSum[x] <= (sumI - lower)) {
				counter++;
			}
		}
		return counter;
		
	}
	
	/**
	 * ����ǰ׺������,������Χ�ĺ� �� <br>
	 * 
	 * �� i ~ j���ۼӺ� = sum[j] - sum[i-1]
	 */
	
	public static int[] generateSumArray(int[] arr) {
		int size = arr.length;
		int[] sums = new int[size];
		sums[0] = arr[0];
		for (int i = 1; i < size; i++) {
			sums[i] = sums[i - 1] + arr[i];
		}
		return sums;
	}
		
}
