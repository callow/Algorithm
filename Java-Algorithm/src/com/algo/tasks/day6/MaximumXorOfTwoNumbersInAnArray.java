package com.algo.tasks.day6;
/**
 * ������������2����������������ֵ? 
 * 
 * ������Ŀ��С�ܵ�.
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
		MaxXOR.NumTrie numTrie = new MaxXOR.NumTrie(); // ���Ժ���һ�⹫��һ��ǰ׺��
		numTrie.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, numTrie.maxXor(arr[i])); // ����Ͳ���Ҫ�����ˣ�ֱ���������Ҵ��뼴��
			numTrie.add(arr[i]);
		}
		return max;
	}
}
