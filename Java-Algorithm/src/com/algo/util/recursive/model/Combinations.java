package com.algo.util.recursive.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/
 */
public class Combinations {

	// ���Ÿ��� e.g 11122233344
	// 
	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		Arrays.sort(nums);
		f(nums, 0, new int[nums.length], 0, ans);
		return ans;
	}
	
	/**
	 * 
	 * @param nums
	 * @param i - ��ǰ����iλ��
	 * @param path
	 * @param size - ֮ǰ������Щ������Size��
	 * @param ans
	 */
	public static void f(int[] nums, int i, int[] path, int size, List<List<Integer>> ans) {
		if (i == nums.length) {
			ArrayList<Integer> cur = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				cur.add(path[j]);
			}
			ans.add(cur);
		} else {
			// ��һ��ĵ�һ������λ��
			int j = i + 1;
			while (j < nums.length && nums[i] == nums[j]) {
				j++;
			}
			// ��ǰ��x��Ҫ0��
			f(nums, j, path, size, ans);
			// ��ǰ��x��Ҫ1����Ҫ2����Ҫ3��...������
			for (; i < j; i++) {
				path[size++] = nums[i];
				f(nums, j, path, size, ans);
			}
		}
	}
}
