package com.algo.util.difference;

public class DifferenceUtil {

	/**
	 *  һγ��֣�https://leetcode.cn/problems/corporate-flight-bookings/
	 *  [L] += v
	 *  [R+1] -= v
	 *  �����ǰ׺�����飡
	 */
	public static int[] corpFlightBookings(int[][] bookings, int n) {
		int[] cnt = new int[n + 2];
		// ���ò�����飬ÿһ��������Ӧ��������
		for (int[] book : bookings) {
			cnt[book[0]] += book[2];
			cnt[book[1] + 1] -= book[2];
		}
		// �ӹ�ǰ׺��
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
