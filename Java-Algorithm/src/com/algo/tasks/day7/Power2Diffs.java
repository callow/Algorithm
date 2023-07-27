package com.algo.tasks.day7;

/**
 * ����һ����������arr������ֵ����Ϊ��������0�� ����arr��ÿ������ƽ��֮��ͬ�Ľ���ж����֣�
 *
 * ˼·��˫ָ�����м们������abs()
 */
public class Power2Diffs {

	// ʱ�临�Ӷ�O(N)������ռ临�Ӷ�O(1)
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
