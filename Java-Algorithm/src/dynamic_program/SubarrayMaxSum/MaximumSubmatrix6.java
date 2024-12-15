package dynamic_program.SubarrayMaxSum;

import java.util.Arrays;

/**
 * 
 * 子矩阵最大累加和问题：给定一个二维数组grid，找到其中子矩阵的最大累加和 返回拥有最大累加和的子矩阵左上角和右下角坐标
 * 
 * https://leetcode.cn/problems/max-submatrix-lcci/
 * 
 * 思路： 子矩阵累加和 => 压缩数组 + 一维数组最大累加和问题
 * 		e.g: maxtrix[4][5] -> 求0~0， 0~1，0~2，0~3 1~1,1~2,1~3,2~2,2~3,3~3 的压缩 然后一维累加和，所有的里面求max即可
 */
public class MaximumSubmatrix6 {

	// 如果行和列的规模都是n，时间复杂度O(n^3)，最优解了
	public static int[] getMaxMatrix(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = Integer.MIN_VALUE;
		int a = 0, b = 0, c = 0, d = 0;
		int[] nums = new int[m];
		for (int up = 0; up < n; up++) {
			Arrays.fill(nums, 0);
			for (int down = up; down < n; down++) {
				// 如下代码就是题目1的附加问题 :
				// 子数组中找到拥有最大累加和的子数组，其实就是up ~ down都压缩到一起了
				for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < m; r++) {
					nums[r] += grid[down][r];
					if (pre >= 0) {
						pre += nums[r];
					} else {
						pre = nums[r];
						l = r;
					}
					if (pre > max) { // 只要发现更大的累加和 就直接抓出左上角 + 右下角坐标
						max = pre;
						a = up;
						b = l;
						c = down;
						d = r;
					}
				}
			}
		}
		return new int[] { a, b, c, d };
	}
}
