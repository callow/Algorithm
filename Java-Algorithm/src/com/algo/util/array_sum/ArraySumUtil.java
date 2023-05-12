package com.algo.util.array_sum;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * �����ۼӺ�������⡣
 *
 */
public class ArraySumUtil {

	/**
	 * һ���Ǹ����飬�ĸ�������� = target�ҳ������<br>
	 * �⣺˫ָ�룺 ������leftΪ��ͷ���������ۼӺ���target
	 */
	public static int findLongestSubArray(int[] arr, int target) {
		if (CommonArrayUtil.isEmpty(arr) || target <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == target) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < target) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}
}
