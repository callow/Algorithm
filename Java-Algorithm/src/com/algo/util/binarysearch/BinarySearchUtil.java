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
	 * �ֲ����
	 */
	public static int localMaximum(int[] unsortArr) { // leecode 162
		if (CommonArrayUtil.isEmpty(unsortArr)) {
			return -1;
		}
		// ȥͷ��
		if (CommonArrayUtil.hasOne(unsortArr) || unsortArr[0] > unsortArr[1]) {
			return 0;
		}
		// ȥβ��
		if (unsortArr[unsortArr.length - 1] > unsortArr[unsortArr.length - 2]) {
			return unsortArr.length - 1;
		}

		// ȥ�м���
		int l = 1;
		int r = unsortArr.length - 2;
		int mid = 0;

		while (l < r) {
			mid = (l + r) / 2;
			if (unsortArr[mid] > unsortArr[mid + 1]) { // 11��mid�� > 2
				r = mid; // go to left
			} else {
				l = mid + 1;
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

	/**
	 * ����ת�����в���ĳ��
	 */
	public static int searchInRotatedArray(int[] arr, int num) {
		if (CommonArrayUtil.isEmpty(arr)) {
			return -1;
		}
		int l = 0;
		int r = arr.length - 1;
		int mid = 0;

		while (l <= r) {
			mid = l + ((r - l) / 2);
			if (arr[mid] == num) {
				return mid;
			} else if (arr[mid] >= arr[l]) {
				if (num >= arr[l] && num < arr[mid]) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			} else {
				if (num <= arr[r] && num > arr[mid]) {
					l = mid + 1;
				} else
					r = mid - 1;
			}

		}
		return -1;
	}

	/**
	 * ����ת������Ѱ����Сֵ, mid��ͷ/β�Ƚ�
	 */
	public static int findMinInRotatedArray(int[] nums) {
		if (CommonArrayUtil.hasOne(nums) || CommonArrayUtil.hasNoRotation(nums)) { // no rotation
			return nums[0];
		}

		int l = 0;
		int r = nums.length - 1;
		int mid = 0;

		while (l < r) {
			mid = l + (r - l) / 2;
			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			} else if (nums[mid - 1] > nums[mid]) {
				return nums[mid];
			} else if (nums[mid] > nums[0]) { // mid in left side
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * �������������±߽�
	 */

	public static int[] findRange(int[] nums, int target) {
		int leftBound = findBound(nums, target, true);
		if (leftBound == -1) {
			return new int[] { -1, -1 };
		}
		int rightBound = findBound(nums, target, false);
		return new int[] { leftBound, rightBound };
	}

	private static int findBound(int[] nums, int target, boolean isLeft) {
		int l = 0;
		int r = nums.length - 1;

		while (l <= r) {

			int mid = (l + r) / 2;

			if (nums[mid] == target) {

				if (isLeft) {

					// This means we found our lower bound.
					if (mid == l || nums[mid - 1] != target) {
						return mid;
					}

					// Search on the left side for the bound.
					r = mid - 1;

				} else {

					// This means we found our upper bound.
					if (mid == r || nums[mid + 1] != target) {
						return mid;
					}

					// Search on the right side for the bound.
					l = mid + 1;
				}

			} else if (nums[mid] > target) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}

		return -1;
	}

}
