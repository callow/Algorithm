package com.algo;

public class Test {

	public static void main(String[] args) {
		int[] nums = { 1, 0, 1, 1, 0, 1 };
		System.out.println(findMaxConsecutiveOnesAllowFlip1Zero(nums));
	}

	public static int findMaxConsecutiveOnesAllowFlip1Zero(int[] nums) {
		int left = 0, right = 0;
		int numOfZero = 0;
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				numOfZero++;
			}
			if (nums[i] == 1 && numOfZero == 1) { // valid
				right++;
			} else { // invalid
				max = Math.max(max, right - left);
				while (left < right) {
					left++;
				}
				numOfZero = 0;
			}
		}
		return max;
	}

}
