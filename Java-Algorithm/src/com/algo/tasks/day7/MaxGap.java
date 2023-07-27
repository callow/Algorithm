package com.algo.tasks.day7;
/**
 * ����һ������arr[], ��������������������������ֵ ������O(N)
 * 
 * https://leetcode.com/problems/maximum-gap/
 * 
 * ˼·�� ����ԭ��Ͱ�������
 */
public class MaxGap {
	
	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		// ץȡȫ�������С/���ֵ
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) { // �����е��ҽ���ֻ��һ����
			return 0;
		}
		
		// ��ֹһ������min~maxһ����range,len�����ݣ�len+1��Ͱ
		boolean[] hasNum = new boolean[len + 1]; // i��Ͱ�Ƿ����������
		int[] maxs = new int[len + 1]; // i��Ͱ�ռ����������ֵ����ֵ
		int[] mins = new int[len + 1]; // i��Ͱ�ռ����������ֵ���Сֵ
		
		
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		
		// Ѱ�ҿ�Ͱ������ֵ
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	/**
	 * ��ǰ������num,������Χ��min ~max, �ֳ���len+1�ݡ�
	 * 
	 * ����num������Ͱ.
	 * 
	 * ��Сֵmin ~ ���ֵmax���ֳ���len+1�ݣ�����һ���ǣ�(max - min + 1) / (len + 1)
	 * 
	 * num��min~max�е��ŵڼ�������(������0��ʼ)���ǵ�num - min������
	 * 
	   ���ԣ�numӦ������һ����� (num - min) / ( (max - min + 1) / (len + 1))��һ���

	   ���ԣ�(int) ((num - min) * (len + 1) / (max - min + 1))
	 */
	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}
}
