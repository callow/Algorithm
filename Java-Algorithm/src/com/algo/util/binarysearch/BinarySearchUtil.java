package com.algo.util.binarysearch;

import com.algo.util.common.CommonArrayUtil;

public class BinarySearchUtil {

	/**
	 * 二分查找 O(Log(N))
	 */

	public static boolean exist(int[] sortArr, int num) {
		if (CommonArrayUtil.isEmpty(sortArr)) {
			return false;
		}
		int l = 0;
		int r = sortArr.length - 1;
		int mid = 0;

		while (l < r) {
			mid = l + ((r - l) / 2);
			if (sortArr[mid] == num) {
				return true;
			} else if (sortArr[mid] > num) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return sortArr[l] == num;
	}

	/**
	 * 二分查找 >= num 最左位置. O(Log(N))
	 */

	public static int nearestLeftIndex(int[] sortArr, int num) {
		int l = 0;
		int r = sortArr.length - 1;
		int mid = 0;

		int answer = -1;
		while (l <= r) {
			mid = l + ((r - l) / 2);
			if (sortArr[mid] >= num) {
				answer = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return answer;
	}

	/**
	 * 二分查找 <= num 最右位置. O(Log(N))
	 */

	public static int nearestRightIndex(int[] sortArr, int num) {
		int l = 0;
		int r = sortArr.length - 1;
		int mid = 0;

		int answer = -1;
		while (l <= r) {
			mid = l + ((r - l) / 2);
			if (sortArr[mid] <= num) {
				answer = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return answer;
	}

	/**
	 * 二分查找： 无序数组中找一个局部最小 e.g （19,1,4） -> 1. O(Log(N))<br>
	 * - 先去2头找局部最小， 2头没有再去中间找 <br>
	 */

	public static int localMinimum(int[] unsortArr) {
		if (CommonArrayUtil.isEmpty(unsortArr)) {
			return -1;
		}
		// 去头找
		if (CommonArrayUtil.hasOne(unsortArr) || unsortArr[0] < unsortArr[1]) {
			return 0;
		}
		// 去尾找
		if (unsortArr[unsortArr.length - 1] < unsortArr[unsortArr.length - 2]) {
			return unsortArr.length - 1;
		}

		// 去中间找
		int l = 1;
		int r = unsortArr.length - 2;
		int mid = 0;

		while (l < r) {
			mid = (l + r) / 2;
			if (unsortArr[mid] > unsortArr[mid - 1]) { // 1 < 9（mid）
				r = mid - 1;
			} else if (unsortArr[mid] > unsortArr[mid + 1]) { // 11（mid） > 2
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return l;
	}

	/**
	 * 猜数字游戏：大了，小了 对了！
	 */
	public static int guessNumber(int num) {
		int l = 1;
		int r = Integer.MAX_VALUE;
		int mid = 0;

		while (l < r) {
			mid = l + ((r - l) / 2);
			if (guess(mid) == 0) {
				return mid;
			} else if (guess(mid) < 0) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}

	private static int guess(int myguess) {
		int answer = 6;
		if (myguess == answer) {
			return 0;
		} else if (myguess > answer) {
			return -1;
		} else {
			return 1;
		}
	}
}
