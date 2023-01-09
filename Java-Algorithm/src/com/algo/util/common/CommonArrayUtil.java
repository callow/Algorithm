package com.algo.util.common;

import java.lang.reflect.Array;

import com.algo.util.common.model.Node;
@SuppressWarnings("unchecked")
public class CommonArrayUtil {

	/**
	 * 交换数组中2个元素位置
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
	 * 对数组进行partiton, pivot放中间， < pivot放左边， > pivot 放右边
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
	 * 拷贝，产生一个新数组
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
	 * 打印数组
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
	 * 随机生成数组
	 */
	
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}
	
	/**
	 * 比较2个数组是否相等
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
	 * 是否是空
	 */
	
	public static boolean isEmpty(int[] arr) {
		return arr == null || arr.length == 0;
	}
	
	/**
	 * 是否只有1个元素
	 */
	
	public static boolean hasOne(int[] arr) {
		return arr != null && arr.length == 1;
	}
	
	public static <T>  T[] getArray(Class<T> componentType,int length) {
		return (T[]) Array.newInstance(componentType, length);
	}
	
	/**
	 * 生成前缀和数组,加快计算区间累加问题 preSum[i] = arr[0...i]的合
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
	 * 求以i位置结尾的数组有多少子数组累加和落在【lower, uppper】上 ： <br>
	 * 	 - 整体累加和 preSum(i)  <br>
	 *   - 思维转换： 有多少以i结尾的 = （preSum(i) - 每个开头的前缀和）∈ [preSum(i) - uppper， preSum(i) - lower]
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
	 * 生成前缀和数组,方便求范围的和 ： <br>
	 * 
	 * 求 i ~ j的累加和 = sum[j] - sum[i-1]
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
