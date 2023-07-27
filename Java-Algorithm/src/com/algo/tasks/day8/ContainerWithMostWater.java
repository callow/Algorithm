package com.algo.tasks.day8;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 
 * 只关注是否会推高答案
 */
public class ContainerWithMostWater {

	public static int maxArea(int[] h) {
		int max = 0;
		int l = 0;
		int r = h.length - 1;
		while (l < r) {
			max = Math.max(max, Math.min(h[l], h[r]) * (r - l));
			if (h[l] > h[r]) {
				r--;
			} else {
				l++;
			}
		}
		return max;
	}
}
