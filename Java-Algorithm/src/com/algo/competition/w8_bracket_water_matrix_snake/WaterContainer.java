package com.algo.competition.w8_bracket_water_matrix_snake;

/**
 * ʢˮ���������� https://leetcode.com/problems/container-with-most-water/
 *
 * ˼�룺 ֻ��ȥ�����Ƿ����Ƹ����ֵ���𰸣��Ŀ����ԣ�������ȥ�ϸ�����������Ĵ��Ƕ��� O��N��
 * 
 * ˭С����˭�� ָ�����м��ƶ�
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
