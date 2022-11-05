package com.algo.competition.w8_bracket_water_matrix_snake;

/**
 * 盛水最多的容器： https://leetcode.com/problems/container-with-most-water/
 *
 * 思想： 只是去关心是否有推高最大值（答案）的可能性，而不是去严格纠结求出具体的答案是多少 O（N）
 * 
 * 谁小结算谁！ 指针向中间移动
 */

public class WaterContainer {

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
