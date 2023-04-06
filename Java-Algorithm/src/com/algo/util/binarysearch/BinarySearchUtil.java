package com.algo.util.binarysearch;

import com.algo.util.common.CommonArrayUtil;

public class BinarySearchUtil {

	/**
	 * ���ֲ��� O(Log(N))
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
	 * ���ֲ��� >= num ����λ��. O(Log(N))
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
	 * ���ֲ��� <= num ����λ��. O(Log(N))
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
	 * ���ֲ��ң� ������������һ���ֲ���С e.g ��19,1,4�� -> 1. O(Log(N))<br>
	 * - ��ȥ2ͷ�Ҿֲ���С�� 2ͷû����ȥ�м��� <br>
	 */

	public static int localMinimum(int[] unsortArr) {
		if (CommonArrayUtil.isEmpty(unsortArr)) {
			return -1;
		}
		// ȥͷ��
		if (CommonArrayUtil.hasOne(unsortArr) || unsortArr[0] < unsortArr[1]) {
			return 0;
		}
		// ȥβ��
		if (unsortArr[unsortArr.length - 1] < unsortArr[unsortArr.length - 2]) {
			return unsortArr.length - 1;
		}

		// ȥ�м���
		int l = 1;
		int r = unsortArr.length - 2;
		int mid = 0;

		while (l < r) {
			mid = (l + r) / 2;
			if (unsortArr[mid] > unsortArr[mid - 1]) { // 1 < 9��mid��
				r = mid - 1;
			} else if (unsortArr[mid] > unsortArr[mid + 1]) { // 11��mid�� > 2
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return l;
	}

	/**
	 * ��������Ϸ�����ˣ�С�� ���ˣ�
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
