package com.algo.tasks.day4;

import com.algo.util.common.CommonArrayUtil;

/**
 * ���������У�����������ۼӺͣ�
 * 
 * https://leetcode.com/problems/maximum-subarray/
 *
 */
public class SubArrayMaxSum {

	/**
	 * ��Ϊֻ����i-1λ��,���ֻ��Ҫһ����������ȥ�Ϳ��� ʡ��dp[]
	 */
	public static int maxSubArray(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr)) {
			return 0; 
		}

		int pre = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int p1 = arr[i]; // ֻ���Լ�������
			int p2 = arr[i] + pre; // �����������������i-1һ�������+pre
			int cur = Math.max(p1, p2);
			max =  Math.max(max, cur);
			pre = cur;
		}
		return max;
	}
}
