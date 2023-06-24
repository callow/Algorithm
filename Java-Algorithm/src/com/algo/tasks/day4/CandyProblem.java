package com.algo.tasks.day4;
/**
 * 
 * 糖果问题 ： https://leetcode.com/problems/candy/
 *
 */
public class CandyProblem {

	// 时间复杂度O(N)，额外空间复杂度O(N)
	public static int candy(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] left = new int[N];
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] < arr[i]) {
				left[i] = left[i - 1] + 1;
			}
		}
		int[] right = new int[N];
		for (int i = N - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				right[i] = right[i + 1] + 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += Math.max(left[i], right[i]);
		}
		return ans + N;
	}
	
	
}
