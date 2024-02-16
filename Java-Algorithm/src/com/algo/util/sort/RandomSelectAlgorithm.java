package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

/**
 * 随机选择算法 ： https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * 
 * 	无序数组中找第K大/第K小，时间复杂度O（N), 所以不能排序
 * 
 * 	思路：改写随机快排，如果求第52th大的值, 直接看快排过程中是否在荷兰国旗=区域，如果没命中去单侧的寻找即可
 * 
 * 	如果现在刚好=8区域是 [36,60], 则52th大 = 8， 
 *  如果现在刚好=8区域是 [36,50]，则只需要去右侧[51,r] 区域进行递归荷兰国旗，不需要看左侧了
 */
public class RandomSelectAlgorithm {
	
	public static int first, last;

	public static int findKthLargest(int[] nums, int k) {
		return randomizedSelect(nums, nums.length - k);
	}
	
	// 如果arr排序的话，在i位置的数字是什么
	public static int randomizedSelect(int[] arr, int i) {
		int ans = 0;
		for (int l = 0, r = arr.length - 1; l <= r;) {
			// 随机这一下，常数时间比较大
			// 但只有这一下随机，才能在概率上把时间复杂度收敛到O(n)
			int x = arr[l + (int) (Math.random() * (r - l + 1))]; // 随机选择的位置
			partition(arr, l, r, x);  // [l,r]范围等于区域x
			// 因为左右两侧只需要走一侧
			// 所以不需要临时变量记录全局的first、last
			// 直接用即可
			if (i < first) {
				r = first - 1;
			} else if (i > last) {
				l = last + 1;
			} else {
				ans = arr[i];
				break;
			}
		}
		return ans;
	}
	
	public static void partition(int[] arr, int l, int r, int x) {
		first = l;
		last = r;
		int i = l;
		while (i <= last) {
			if (arr[i] == x) {
				i++;
			} else if (arr[i] < x) { 
				CommonArrayUtil.swap(arr, first++, i++);
			} else {
				CommonArrayUtil.swap(arr, i, last--);
			}
		}
	}
	
}
