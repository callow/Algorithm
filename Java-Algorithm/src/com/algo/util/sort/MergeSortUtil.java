package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

/**
 * MergeSort 是分而治之思路， 砍一半， （左边总个数 + 右边总个数 + Merge过程中的总个数）
 * 
 * 关键代码都是在Merge，它把很多东西变成有序的，间接结出很多题目,反正Merge的过程也要左右两边的数都看一遍，且左右2边必须有序，因此利用了一下 <br>
 * 
 * - 左组中 关于右组中有多少个达标的 <br>
 * - 右组中 关于左组中有多少个达标的 <br>
 *
 */

public class MergeSortUtil {

	/**
	 * 数组每个位置左边只要比它小的数累加，不停合并，最终形成的Sum O（nLog(n)）<br>
	 *  - 每一个数左边有多少个数比它小 = 每一个数右边多少个数比它大
	 *  - 从左往右Merge
	 */
	
	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return processSmallSum(arr, 0, arr.length - 1);
	}
	
	// arr[L..R]既要排好序，也要求小和返回
	// 所有merge时，产生的小和，累加
	// 左边排序   merge并产生小合
	// 右边排序  merge并产生小合
	private static int processSmallSum(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) / 2);
		// 最终的量 = 左边排序时产生的小合总量 + 右边排序时产生的小合总量 + Merge时产生的小合总量
		return processSmallSum(arr, l, mid) 
			 + processSmallSum(arr, mid, r) 
			 + mergeSmallSum(arr, l, mid, r);
	}
	private static int mergeSmallSum(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		int smallSum = 0;
		while (p1 <= m && p2 <= r) {
			// 左边 < 右边 时， 产生小合 (r - p2 + 1) * 左边那个数
			// (r - p2 + 1) 右组中有几个数比当前数大
			smallSum += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return smallSum;
	}
	
	/**
	 * 逆序对： 左>右，不需要连续，	数组有几个逆序对？  O（nLog(n)） <br>
	 *  - 每一个数右边有多少个数比它小的 = 反小合问题
	 *  - 从右往左Merge!
	 */
	
	public static int countReversePair(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return processCountRevPair(arr, 0, arr.length - 1);
	}
	
	// arr[L..R]既要排好序，也要求逆序对数量返回
	// 所有merge时，产生的逆序对数量，累加，返回
	// 左 排序 merge并产生逆序对数量
	// 右 排序 merge并产生逆序对数量
	private static int processCountRevPair(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) / 2);
		// 左边你给我你的count + 右边你给我你的count + 合并起来给我一下count
		return processCountRevPair(arr, l, mid) 
			 + processCountRevPair(arr, mid, r) 
			 + mergeCountRevPair(arr, l, mid, r);
	}
	
	private static int mergeCountRevPair(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = help.length - 1;
		int p1 = m; // mid
		int p2 = r; // rightest
		int counter = 0;
		while (p1 >= l && p2 > m) {
			counter += arr[p1] > arr[p2] ? (p2 - m) : 0;
			help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
		}
		while (p1 >= l) {
			help[i--] = arr[p1--];
		}
		while (p2 > m) {
			help[i--] = arr[p2--];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return counter;
	}
	
	/**
	 * Great num => 它 右边有几个数 * 2 都没有它大。 数一下总数？ <br>
	 *  - 如果左右都是有序的，则指针可以不回退
	 *  - https://leetcode.com/problems/reverse-pairs/
	 */
	public static int countGreatNum (int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return processCountGreatNum(arr, 0, arr.length - 1);
	}
	
	private static int processCountGreatNum(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) / 2);
		return processCountGreatNum(arr, l, mid) 
		     + processCountGreatNum(arr, mid, r) 
		     + mergeCountGreatNum(arr, l, mid, r);
	}
	
	private static int mergeCountGreatNum(int[] arr, int L, int m, int r) {
		// 左组： [L....M] 右组： [M+1....R]
		int ans = 0;
		// 目前囊括进来的数，是从[M+1, windowR)
		int windowR = m + 1;
		for (int i = L; i <= m; i++) {
			while (windowR <= r && (long) arr[i] > (long) arr[windowR] * 2) {
				windowR++;
			}
			ans += windowR - m - 1;
		}
		int[] help = new int[r - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
		return ans;
	}
	
	/**
	 * 子数组累加和： 一个数组arr，2个位置 lower， upper, 有多少个子数组的累加和在【lower，upper】上
	 *  https://leetcode.com/problems/count-of-range-sum/
	 */
	
	public static int countSubArray(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		long[] preSum = CommonArrayUtil.prefixSumArr(nums);
		return processSubArray(preSum, 0, preSum.length - 1, lower, upper);
	}
	
	private static int processSubArray(long[] sum, int l, int r, int lower, int upper) {
		if (l == r) {
			return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
		}
		int m = l + ((r - l) / 2);
		return processSubArray(sum, l, m, lower, upper) 
			 + processSubArray(sum, m + 1, r, lower, upper) 
			 + mergeSubArray(sum, l, m, r, lower, upper);
	}
	
	private static int mergeSubArray(long[] arr, int l, int m, int r, int lower, int upper) {
		int ans = 0;
		int windowL = l;
		int windowR = l;
		// [windowL, windowR)
		for (int i = m + 1; i <= r; i++) {
			long min = arr[i] - upper;
			long max = arr[i] - lower;
			while (windowR <= m && arr[windowR] <= max) {
				windowR++;
			}
			while (windowL <= m && arr[windowL] < min) {
				windowL++;
			}
			// 因为窗口是【L,R),
			ans += windowR - windowL;
		}
		long[] help = new long[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return ans;
	}
	
	
}
