package com.algo.tasks.day7;

/**
 * 给定一个有序数组arr，其中值可能为正、负、0。 返回arr中每个数都平方之后不同的结果有多少种？
 *
 * 思路：双指针向中间滑东，求abs()
 */
public class Power2Diffs {

	// 时间复杂度O(N)，额外空间复杂度O(1)
	public static int diff(int[] arr) {
		int N = arr.length;
		int L = 0;
		int R = N - 1;
		int count = 0;
		int leftAbs = 0;
		int rightAbs = 0;
		while (L <= R) {
			count++;
			leftAbs = Math.abs(arr[L]);
			rightAbs = Math.abs(arr[R]);
			if (leftAbs < rightAbs) {
				while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
					R--;
				}
			} else if (leftAbs > rightAbs) {
				while (L < N && Math.abs(arr[L]) == leftAbs) {
					L++;
				}
			} else {
				while (L < N && Math.abs(arr[L]) == leftAbs) {
					L++;
				}
				while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
					R--;
				}
			}
		}
		return count;
	}
}
