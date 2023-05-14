package com.algo.util.array_sum;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * �����ۼӺ�������⡣
 *
 */
public class ArraySumUtil {

	/**
	 * һ��+���飬�ĸ�������� = target�ҳ������<br>
	 * �⣺ÿ��λ�ÿ�ͷ������˫ָ�룺 ������leftΪ��ͷ���������ۼӺ���target
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

	/**
	 * һ��+ - 0 ���飬�ĸ�������� = target�ҳ������<br>
	 * 
	 * �⣺ ÿ��λ�ý�β��ô����<br>
	 * 
	 * sum - target = ǰ׺�����Ƶ��λ�ã�
	 * 
	 * �� ��(0~100) = 200, target = 30, then ĳһ��ǰ׺��170������6λ�� <br>
	 * �� ��7 ~ 100�������Ƶ������100��β��������Ƶ�7λ�õõ���30
	 * 
	 * ע���任��e.g: ��������-1��1������һ������������ => ������-1 1�����ֱ��0������target=0��ʱ��ı���
	 * 
	 */
	public static int findLongestSubArray2(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key:ǰ׺��
		// value : 0~value���ǰ׺�����������key���ֵ��
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important

		int maxLen = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) { // ��i��β��������
			sum += arr[i];
			if (map.containsKey(sum - target)) {
				int prefixSumIndex = map.get(sum - target);
				maxLen = Math.max(i - prefixSumIndex, maxLen);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i); // ��ǰ׺�͵�index����map
			}
		}
		return maxLen;
	}
}
