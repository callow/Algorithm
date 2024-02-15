package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * �鲢���Σ� ��Χ�ϵ������Ƿ���� �������Ĵ� + �Ҳ��Ʒ�Ĵ� + �ϲ����Ҳ����Ĵ𰸣������Ƿ���������� ���붼д��Merge����
 * 
 * MergeSort �Ƿֶ���֮˼·�� ��һ�룬 ������ܸ��� + �ұ��ܸ��� + Merge�����е��ܸ����� O(NLogn)
 * 
 * �ؼ����붼����Merge�����Ѻܶණ���������ģ���ӽ���ܶ���Ŀ,����Merge�Ĺ���ҲҪ�������ߵ�������һ�飬������2�߱����������������һ�� <br>
 * 
 * - ������ �����������ж��ٸ����� <br>
 * - ������ �����������ж��ٸ����� <br>
 *
 */

public class MergeSortUtil {

	/**
	 * ����ÿ��λ�����ֻҪ����С�����ۼӣ���ͣ�ϲ��������γɵ�Sum O��nLog(n)��<br>
	 *  - ÿһ��������ж��ٸ�������С = ÿһ�����ұ߶��ٸ���������
	 *  - ��������Merge
	 *  
	 *  https://www.nowcoder.com/practice/edfe05a1d45c4ea89101d936cac32469
	 *  
	 *  ˼·�� 
	 *  	1. 0~4��Χ��С�� = 0~1�ϵ�С�� + 3~4��С�� + �����ҵ�С��
	 *  	2. ��������˱��� 
	 */
	
	public static int smallSum(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		return processSmallSum(arr, 0, arr.length - 1);
	}
	
	// arr[L..R]��Ҫ�ź���ҲҪ��С�ͷ���
	// ����mergeʱ��������С�ͣ��ۼ�
	// �������   merge������С��
	// �ұ�����  merge������С��
	private static int processSmallSum(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) / 2);
		// ���յ��� = �������ʱ������С������ + �ұ�����ʱ������С������ + Mergeʱ������С������
		return processSmallSum(arr, l, mid) 
			 + processSmallSum(arr, mid, r) 
			 + mergeSmallSum(arr, l, mid, r);
	}
	private static int mergeSmallSum(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		int smallSum = 0;
		while (p1 <= m && p2 <= r) {
			// ��� < �ұ� ʱ�� ����С�� (r - p2 + 1) * ����Ǹ���
			// (r - p2 + 1) �������м������ȵ�ǰ����
			smallSum += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return smallSum;
	}
	
	/**
	 * ����ԣ� ��>�ң�����Ҫ������	�����м�������ԣ�  O��nLog(n)�� <br>
	 *  - ÿһ�����ұ��ж��ٸ�������С�� = ��С������
	 *  - ��������Merge!
	 */
	
	public static int countReversePair(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		return processCountRevPair(arr, 0, arr.length - 1);
	}
	
	// arr[L..R]��Ҫ�ź���ҲҪ���������������
	// ����mergeʱ��������������������ۼӣ�����
	// �� ���� merge���������������
	// �� ���� merge���������������
	private static int processCountRevPair(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) / 2);
		// �����������count + �ұ���������count + �ϲ���������һ��count
		return processCountRevPair(arr, l, mid) 
			 + processCountRevPair(arr, mid, r) 
			 + mergeCountRevPair(arr, l, mid, r);
	}
	
	private static int mergeCountRevPair(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = help.length - 1;
		int p1 = m; // mid
		int p2 = r; // rightest
		int counter = 0;
		while (p1 >= l && p2 > m) {
			counter += arr[p1] > arr[p2] ? (p2 - m) : 0;
			help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
		}
		while (p1 >= l) {
			help[i--] = arr[p1--];
		}
		while (p2 > m) {
			help[i--] = arr[p2--];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return counter;
	}
	
	/**
	 * Great num => �� �ұ��м����� * 2 ��û������ ��һ�������� <br>  
	 *  - ������Ҷ�������ģ���ָ����Բ�����
	 *  - https://leetcode.com/problems/reverse-pairs/
	 *  
	 *  
	 *  ˼·�� 0~4��Χ�Ϸ�ת�Ե����� = 0~1�ϵ����� + 3~4������ + �����ҵĴ�  2. ��������˱��� 
	 */
	public static int countGreatNum (int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return 0;
		}
		return processCountGreatNum(arr, 0, arr.length - 1);
	}
	
	private static int processCountGreatNum(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) / 2);
		return processCountGreatNum(arr, l, mid) 
		     + processCountGreatNum(arr, mid, r) 
		     + mergeCountGreatNum(arr, l, mid, r);
	}
	
	private static int mergeCountGreatNum(int[] arr, int L, int m, int r) {
		// ���飺 [L....M] ���飺 [M+1....R]
		int ans = 0;
		// Ŀǰ���������������Ǵ�[M+1, windowR)
		int windowR = m + 1;
		for (int i = L; i <= m; i++) {
			while (windowR <= r && arr[i] > (long) arr[windowR] * 2) {
				windowR++;
			}
			ans += windowR - m - 1;
		}
		int[] help = new int[r - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
		return ans;
	}
	
	/**
	 * �������ۼӺͣ� һ������arr��2��λ�� lower�� upper, �ж��ٸ���������ۼӺ��ڡ�lower��upper����
	 *  https://leetcode.com/problems/count-of-range-sum/
	 */
	
	public static int countSubArray(int[] nums, int lower, int upper) {
		if (CommonArrayUtil.isEmpty(nums)) {
			return 0;
		}
		long[] preSum = CommonArrayUtil.prefixSumArr(nums);
		return processSubArray(preSum, 0, preSum.length - 1, lower, upper);
	}
	
	private static int processSubArray(long[] sum, int l, int r, int lower, int upper) {
		if (l == r) {
			return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
		}
		int m = l + ((r - l) / 2);
		return processSubArray(sum, l, m, lower, upper) 
			 + processSubArray(sum, m + 1, r, lower, upper) 
			 + mergeSubArray(sum, l, m, r, lower, upper);
	}
	
	private static int mergeSubArray(long[] arr, int l, int m, int r, int lower, int upper) {
		int ans = 0;
		int windowL = l;
		int windowR = l;
		// [windowL, windowR)
		for (int i = m + 1; i <= r; i++) {
			long min = arr[i] - upper;
			long max = arr[i] - lower;
			while (windowR <= m && arr[windowR] <= max) {
				windowR++;
			}
			while (windowL <= m && arr[windowL] < min) {
				windowL++;
			}
			// ��Ϊ�����ǡ�L,R), ����R - L ���Ǹ���
			ans += windowR - windowL;
		}
		long[] help = new long[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return ans;
	}
	
	
}
