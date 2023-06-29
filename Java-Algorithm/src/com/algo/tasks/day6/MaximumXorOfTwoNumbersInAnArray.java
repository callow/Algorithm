package com.algo.tasks.day6;
/**
 * 数组中随意找2个数异或，求最大的异或值? 
 * 
 * 异或和题目的小弟弟.
 * 
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 *
 */
public class MaximumXorOfTwoNumbersInAnArray {

	public static int findMaximumXOR(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		MaxXOR.NumTrie numTrie = new MaxXOR.NumTrie(); // 可以和上一题公用一个前缀树
		numTrie.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, numTrie.maxXor(arr[i])); // 这题就不需要异或和了，直接在树上找带入即可
			numTrie.add(arr[i]);
		}
		return max;
	}
}
