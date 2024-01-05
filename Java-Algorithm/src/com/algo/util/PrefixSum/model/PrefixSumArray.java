package com.algo.util.PrefixSum.model;

public class PrefixSumArray {

	public int[] sum;

	public PrefixSumArray(int[] nums) {
		sum = new int[nums.length + 1];
		for (int i = 1; i <= nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
		}
	}
}
