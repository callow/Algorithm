package com.algo.util.recursive.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/
 */
public class Combinations {

	// 先排个序。 e.g 11122233344
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
	 * @param i - 当前来到i位置
	 * @param path
	 * @param size - 之前做了哪些决定由Size管
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
			// 下一组的第一个数的位置
			int j = i + 1;
			while (j < nums.length && nums[i] == nums[j]) {
				j++;
			}
			// 当前数x，要0个
			f(nums, j, path, size, ans);
			// 当前数x，要1个、要2个、要3个...都尝试
			for (; i < j; i++) {
				path[size++] = nums[i];
				f(nums, j, path, size, ans);
			}
		}
	}
}
