package com.algo.util.PrefixSum;
/**
 * 和数组乘积相关的题目
 * 
 * 
 */
public class ProductSumUtil {
	
	/**
	 * 不使用除法，获得除自己以外的乘积和数组： https://leetcode.com/problems/product-of-array-except-self
	 * 
	 * 思路： 自己左 * 自己右
	 */
    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        int[] answer = new int[n];
        
        // product of all elements to the left of index 'i'
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        // product of all elements to the right of index 'i'
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }
        for (int i = 0; i < n; i++) {
            answer[i] = left[i] * right[i];
        }
        return answer;
    }
}
