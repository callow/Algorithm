package com.algo.util.difference;

public class DifferenceUtil {

	/**
	 *  一纬差分：https://leetcode.cn/problems/corporate-flight-bookings/
	 *  [L] += v
	 *  [R+1] -= v
	 *  最后求前缀和数组！
	 */
	public static int[] corpFlightBookings(int[][] bookings, int n) {
		int[] cnt = new int[n + 2];
		// 设置差分数组，每一个操作对应两个设置
		for (int[] book : bookings) {
			cnt[book[0]] += book[2];
			cnt[book[1] + 1] -= book[2];
		}
		// 加工前缀和
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = cnt[i + 1];
		}
		return ans;
	}
}
