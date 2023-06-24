package com.algo.tasks.day4;

import com.algo.util.common.CommonArrayUtil;

/**
 * ����һ������arr���ڲ���ȡ������������£�������������е�����ۼӺ�?
 * 
 * https://leetcode.com/problems/house-robber/
 *
 */
public class SubArrayMaxSumFollowUp {

	// ˼·��
	// ����dp[i] : ��ʾarr[0...i]��Χ�ϣ��ڲ���ȡ������������£�������������е�����ۼӺ�
	// ��arr[0...i]��Χ�ϣ��ڲ���ȡ������������£��õ�������ۼӺͣ������Է��ࣺ
	// ������ 1) ѡ������ϣ�������arr[i]����ôdp[i] = dp[i-1]
	// ���磬arr[0...i] = {3,4,-4}������ۼӺ��ǲ�����iλ������ʱ��
	//
	// ������ 2) ѡ������ϣ�ֻ����arr[i]����ôdp[i] = arr[i]
	// ���磬arr[0...i] = {-3,-4,4}������ۼӺ���ֻ����iλ������ʱ��
	//
	// ������ 3) ѡ������ϣ�����arr[i], �Ұ���arr[0...i-2]��Χ�ϵ��ۼӺ͡���ôdp[i] = arr[i] + dp[i-2]
	// ���磬arr[0...i] = {3,1,4}������ۼӺ���3��4��ɵ�7����Ϊ���ڲ���ѡ������i-1λ�õ���Ҫ����
	//
	// ����������dp[i] = Max { dp[i-1], arr[i] , arr[i] + dp[i-2] }
	public static int rob(int[] arr) { 
		if (CommonArrayUtil.isEmpty(arr)) {
			return 0;
		}
		int N = arr.length;
		if (N == 1) {
			return arr[0];
		}
		if (N == 2) {
			return Math.max(arr[0], arr[1]);
		}
		int[] dp = new int[N];
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0], arr[1]);
		for (int i = 2; i < N; i++) {
			int p1 = dp[i - 1];
			int p2 = arr[i] + dp[i - 2];
			int p3 = arr[i];
			dp[i] = Math.max(Math.max(p1, p2), p3);
		}
		return dp[N - 1];
	}
}
