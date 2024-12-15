package dynamic_program.SubarrayMaxSum;

import java.util.Arrays;

/**
 * 
 * �Ӿ�������ۼӺ����⣺����һ����ά����grid���ҵ������Ӿ��������ۼӺ� ����ӵ������ۼӺ͵��Ӿ������ϽǺ����½�����
 * 
 * https://leetcode.cn/problems/max-submatrix-lcci/
 * 
 * ˼·�� �Ӿ����ۼӺ� => ѹ������ + һά��������ۼӺ�����
 * 		e.g: maxtrix[4][5] -> ��0~0�� 0~1��0~2��0~3 1~1,1~2,1~3,2~2,2~3,3~3 ��ѹ�� Ȼ��һά�ۼӺͣ����е�������max����
 */
public class MaximumSubmatrix6 {

	// ����к��еĹ�ģ����n��ʱ�临�Ӷ�O(n^3)�����Ž���
	public static int[] getMaxMatrix(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int max = Integer.MIN_VALUE;
		int a = 0, b = 0, c = 0, d = 0;
		int[] nums = new int[m];
		for (int up = 0; up < n; up++) {
			Arrays.fill(nums, 0);
			for (int down = up; down < n; down++) {
				// ���´��������Ŀ1�ĸ������� :
				// ���������ҵ�ӵ������ۼӺ͵������飬��ʵ����up ~ down��ѹ����һ����
				for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < m; r++) {
					nums[r] += grid[down][r];
					if (pre >= 0) {
						pre += nums[r];
					} else {
						pre = nums[r];
						l = r;
					}
					if (pre > max) { // ֻҪ���ָ�����ۼӺ� ��ֱ��ץ�����Ͻ� + ���½�����
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
