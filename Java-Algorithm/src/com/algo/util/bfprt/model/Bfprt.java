package com.algo.util.bfprt.model;

import com.algo.util.common.CommonArrayUtil;
/**
 * 
 *  荷兰国旗问题: < k | = k | >k , 其中的pivot选择是随机的。如果 =区域命中k， 直接返回。
 *  反之走左侧/右侧只走一侧<br>
 *  bfprt 是讲究的选一个pivot。其他90% 与快排的荷兰国旗一样！<br><br>
 *  1. 数组每5个数分一小组<br>
 *  2. 每一小组排序，而整体依然无序 O(N)<br>
 *  3. 拿出每个小组的中位数，组成一个新的数组m[] 。偶数则拿上中位数。大小： N/5<br>
 *  4. 求m[]中的中位数: p<br>
 *  5. 这个h就是讲究的pivot/p. 继续bfprt：< p | = p | > p, 没命中继续bfprt <br> <br>
 *  
 *  因为： <= p 有 3N/10. >p 有7N/10. 这样每次递归至少淘汰掉3N/10. 最差情况也只需要承担7N/10代价. <br>
 *  bfprt目的就是让<p的规模变小 至少有3N/10的规模>=p 则最多有7N/10 <P.
 *	
 */
public class Bfprt {
	
	
	public static int findMinKth(int[] array, int k ) {
		int[] arr = CommonArrayUtil.copyArray(array);
		return Bfprt.go(arr, 0, arr.length - 1, k - 1);
	}

	/**
	 *  O(n)
	 */
	private static int go(int[] arr, int L, int R, int index) {
		if (L == R) {
			return arr[L];
		}
		// L...R  每五个数一组
		// 每一个小组内部排好序
		// 小组的中位数组成新数组
		// 这个新数组的中位数返回
		int pivot = medianOfMedians(arr, L, R);
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return go(arr, L, range[0] - 1, index);
		} else {
			return go(arr, range[1] + 1, R, index);
		}
	}
	
	// arr[L...R]  五个数一组
	// 每个小组内部排序
	// 每个小组中位数领出来，组成marr
	// marr中的中位数，返回
	private static int medianOfMedians(int[] arr, int L, int R) {
		int size = R - L + 1;
		int offset = size % 5 == 0 ? 0 : 1;
		int[] mArr = new int[size / 5 + offset];
		for (int team = 0; team < mArr.length; team++) {
			int teamFirst = L + team * 5;
			// L ... L + 4
			// L +5 ... L +9
			// L +10....L+14
			mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
		}
		// marr中，找到中位数
		// marr(0, marr.len - 1,  mArr.length / 2 )
		return go(mArr, 0, mArr.length - 1, mArr.length / 2);
	}
	
	private static int getMedian(int[] arr, int L, int R) {
		insertionSort(arr, L, R);
		return arr[(L + R) / 2];
	}
	
	private static void insertionSort(int[] arr, int L, int R) {
		for (int i = L + 1; i <= R; i++) {
			for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
				CommonArrayUtil.swap(arr, j, j + 1);
			}
		}
	}
	
	private static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int cur = L;
		while (cur < more) {
			if (arr[cur] < pivot) {
				CommonArrayUtil.swap(arr, ++less, cur++);
			} else if (arr[cur] > pivot) {
				CommonArrayUtil.swap(arr, cur, --more);
			} else {
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}
}
