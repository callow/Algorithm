package com.algo.tasks.day3;

import java.util.Arrays;

import com.algo.util.common.CommonArrayUtil;

/** 
 * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人 返回最多可以同时有多少场比赛.
 * 
 * 使用的是贪心，然后用全排列来验证.
 * 贪心策略：先满足小的值来凑比赛
 * 
 * 1. 排序后，使用床后的左右指针同时移动来配对比赛
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
			} else { // 不止一个数，而且都没用过！
				int distance = arr[R] - arr[L];
				if (distance == k) {
					ans++;
					usedR[R++] = true; // 只标记R位置是否使用过，28行L来到这个位置就可以直接跳过
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
	 * 全排列用于验证贪心策略的对数器 
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
