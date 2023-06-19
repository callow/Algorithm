package com.algo.tasks.day3;

import java.util.Arrays;

import com.algo.util.common.CommonArrayUtil;

/** 
 * ����һ������arr������ÿ���˵�����ֵ���ٸ���һ���Ǹ���k�� ���������������ֵ����Ϊk����ô���Դ���һ�������һ�ֱ���ֻ�������� ����������ͬʱ�ж��ٳ�����.
 * 
 * ʹ�õ���̰�ģ�Ȼ����ȫ��������֤.
 * ̰�Ĳ��ԣ�������С��ֵ���ձ���
 * 
 * 1. �����ʹ�ô��������ָ��ͬʱ�ƶ�����Ա���
**/
public class MaxPairNumber {
	
	public static int maxPairNum(int[] arr, int k) {
		if (k < 0 || arr == null || arr.length < 2) {
			return 0;
		}
		Arrays.sort(arr);
		int ans = 0;
		int N = arr.length;
		int L = 0;
		int R = 0;
		boolean[] usedR = new boolean[N];
		while (L < N && R < N) {
			if (usedR[L]) {
				L++;
			} else if (L >= R) {
				R++;
			} else { // ��ֹһ���������Ҷ�û�ù���
				int distance = arr[R] - arr[L];
				if (distance == k) {
					ans++;
					usedR[R++] = true; // ֻ���Rλ���Ƿ�ʹ�ù���28��L�������λ�þͿ���ֱ������
					L++;
				} else if (distance < k) {
					R++;
				} else {
					L++;
				}
			}
		}
		return ans;
	}
	
	/**
	 * ȫ����������֤̰�Ĳ��ԵĶ����� 
	 */
	public static int maxPairNumber(int[] arr, int k) {
		if (k < 0) {
			return -1;
		}
		return process(arr, 0, k);
	}

	public static int process(int[] arr, int index, int k) {
		int ans = 0;
		if (index == arr.length) {
			for (int i = 1; i < arr.length; i += 2) {
				if (arr[i] - arr[i - 1] == k) {
					ans++;
				}
			}
		} else {
			for (int r = index; r < arr.length; r++) {
				CommonArrayUtil.swap(arr, index, r);
				ans = Math.max(ans, process(arr, index + 1, k));
				CommonArrayUtil.swap(arr, index, r);
			}
		}
		return ans;
	}
}
