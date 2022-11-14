package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.CommonNumberUtil;

public class SortUtil {
	
	/**
	 * 选择排序 O（N^2），每次找到最小那个放到前面去 <br>
	 *   - 0 ~ N-1  选择到最小值，在哪，swap放到0位置上  <br>
	 *   - 1 ~ n-1  找到最小值，在哪，swap放到1 位置上  <br>
	 *   - 2 ~ n-1  找到最小值，在哪，swap放到2 位置上  <br>
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
	 * 冒泡排序 O（N^2）
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
	 * 插入排序 O（N^2）
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
	 * 归并排序 O(N * logN) <br>
	 *  - 准备一个help[], 左右谁小拷贝谁拷贝完往下移动.help[]填满后，拷贝会原数组完成排序。 <br>
	 *  - 指针不回退 => O(N) <br>
	 *  - 不停地分解，merge,分解，merge
	 */
	
	public static void mergeSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}
	
	private static void process(int[] arr, int l, int r) {
		if (l == r) { // base case , 相遇了
			return;
		}
		int mid = l + ((r - l) / 2);
		process(arr, l, mid);
		process(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}
	
	// merge = 谁小拷贝谁
	private static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r-l +1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		
		while(p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		
		// 要么p1越界了，要么p2越界了
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
	
	/**
	 * 计数排序 ∈ 不基于比较的排序 / 桶排序 O(N)<br><br>
	 * 用于数据范围小且固定，
	 * arr 只是数据范围小， e.g: 人的年龄
	 * 
	 */
	public static void countSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) { // O(n)
			max = Math.max(max, arr[i]);
		}
		int[] bucket = new int[max + 1]; // 准备max+1个桶
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
	}
	
	
	/**
	 * 基数排序 ∈ 不基于比较的排序 / 桶排序 O(N)<br><br>
	 * 先用个位为准入桶，再用百位为准入桶，再用千位为准入桶.....<br> <br>
	 * 本方法只适用于非负数，如果有负数，统一加成正数后再排序
	 */
	
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radix(arr, 0, arr.length - 1, CommonNumberUtil.maxbits(arr));
	}
	
	
	/**
	 * 在L ~ R 范围上排序
	 * @param digit 最大值的位数，e.g 3位 ，个位 十位 百位
	 */
	private static void radix(int[] arr, int L, int R, int digit) {
		final int radix = 10;
		int i = 0, j = 0;
		// 有多少个数准备多少个辅助空间
		int[] help = new int[R - L + 1];
		for (int d = 1; d <= digit; d++) { // 有多少位就进出桶几次
			// 10个空间
		    // count[0] 当前位(d位)是0的数字有多少个
			// count[1] 当前位(d位)是(0和1)的数字有多少个
			// count[2] 当前位(d位)是(0、1和2)的数字有多少个
			// count[i] 当前位(d位)是(0~i)的数字有多少个
			int[] count = new int[radix]; // count[0..9]
			for (i = L; i <= R; i++) {
				// 103  1   3
				// 209  1   9  
				j = CommonNumberUtil.getDigit(arr[i], d);
				
				count[j]++;
			}
			for (i = 1; i < radix; i++) {
				count[i] = count[i] + count[i - 1];
			}
			// 从右往左遍历， = 出桶
			for (i = R; i >= L; i--) {
				j = CommonNumberUtil.getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			for (i = L, j = 0; i <= R; i++, j++) {
				arr[i] = help[j];
			}
		}
	}
	
	/**
	 * 希尔排序： Time O(n^(1.3-2)) Space: O(1) <br><br>
	 * 
	 * 没有 O(n(logn))快，但是比O(n^2 ) 好太多
	 * 
	 */
	
	public static void shellSort(int[] arrays) {
        for (int step = arrays.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arrays.length; i++) {
                int j = i;
                int temp = arrays[j];
                while (j - step >= 0 && arrays[j - step] > temp) {
                    arrays[j] = arrays[j - step];
                    j = j - step;
                }
                arrays[j] = temp;
            }
        }
    }
}
