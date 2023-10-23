package com.algo.tasks.day14;

import java.util.TreeSet;

/**
 * 	 �뷵��arr�У������������ۼӺͣ���<=K�ģ����������ġ�
	 ������������ۼӺ�
 */
public class MaxSubArraySumLessOrEqualK {


	public static int getMaxLessOrEqualK(int[] arr, int K) {
		// ��¼i֮ǰ�ģ�ǰ׺�ͣ������������֯
		TreeSet<Integer> set = new TreeSet<Integer>();
		// һ����Ҳû�е�ʱ�򣬾��Ѿ���һ��ǰ׺����0��
		set.add(0);
		int max = Integer.MIN_VALUE;
		int sum = 0;
		// ÿһ����i�����������������i��β������£������������ۼӺͣ���<=K�ģ�����������
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i]; // sum -> arr[0..i];
			if (set.ceiling(sum - K) != null) {
				max = Math.max(max, sum - set.ceiling(sum - K));
			}
			set.add(sum); // ��ǰ��ǰ׺�ͼ��뵽set��ȥ
		}
		return max;

	}
}
