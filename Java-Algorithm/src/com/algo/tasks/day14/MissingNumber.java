package com.algo.tasks.day14;

import com.algo.util.common.CommonArrayUtil;

/**
 * Ѱ����С���������� https://leetcode.com/problems/first-missing-positive/
 */
public class MissingNumber {

	public static int firstMissingPositive(int[] arr) {
		// l�Ƕ��ŵ�λ��
		// 0 ~ L-1��Ч��
		int L = 0;
		int R = arr.length;
		while (L != R) {
			if (arr[L] == L + 1) {
				L++;
			} else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) { // ���������
				CommonArrayUtil.swap(arr, L, --R);
			} else {
				CommonArrayUtil.swap(arr, L, arr[L] - 1);
			}
		}
		return L + 1;
	}
}
