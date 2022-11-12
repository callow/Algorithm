package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

public class SortUtil {
	
	/**
	 * ѡ������ O��N^2����ÿ���ҵ���С�Ǹ��ŵ�ǰ��ȥ <br>
	 *   - 0 ~ N-1  ѡ����Сֵ�����ģ�swap�ŵ�0λ����  <br>
	 *   - 1 ~ n-1  �ҵ���Сֵ�����ģ�swap�ŵ�1 λ����  <br>
	 *   - 2 ~ n-1  �ҵ���Сֵ�����ģ�swap�ŵ�2 λ����  <br>
	 */
	public static void selectionSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		for (int i =0; i < arr.length -1; i++) {
			int min = i;
			for (int j = i+1; j <arr.length; j++) {
				min = arr[j] < arr[min] ? j : min;
			}
			CommonArrayUtil.swap(arr, i, min);
		}
	}
	
	/**
	 * ð������ O��N^2��
	 */
	
	public static void bubbleSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		
		for (int e = arr.length -1; e > 0; e--) {
			for (int i = 0; i < e; i++) {
				if (arr[i] > arr[i+1]) {
					CommonArrayUtil.swap(arr, i, i+1);
				}
			}
		}
	}
	/**
	 * �������� O��N^2��
	 */
	public static void insertSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i-1; j>=0 && arr[j] >arr[j+1]; j--) {
				CommonArrayUtil.swap(arr, i, j+1);
			}
		}
	}
	
	/**
	 * �鲢���� O(N * logN) <br>
	 *  - ׼��һ��help[], ����˭С����˭�����������ƶ�.help[]�����󣬿�����ԭ����������� <br>
	 *  - ָ�벻���� => O(N) <br>
	 *  - ��ͣ�طֽ⣬merge,�ֽ⣬merge
	 */
	
	public static void mergeSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}
	
	private static void process(int[] arr, int l, int r) {
		if (l == r) { // base case , ������
			return;
		}
		int mid = l + ((r - l) / 2);
		process(arr, l, mid);
		process(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}
	
	// merge = ˭С����˭
	private static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r-l +1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		
		while(p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		// Ҫôp1Խ���ˣ�Ҫôp2Խ����
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}
	
}
