package com.algo.util.sort;

import com.algo.util.common.CommonArrayUtil;

/**
 * ���ѡ���㷨 �� https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * 
 * 	�����������ҵ�K��/��KС��ʱ�临�Ӷ�O��N), ���Բ�������
 * 
 * 	˼·����д������ţ�������52th���ֵ, ֱ�ӿ����Ź������Ƿ��ں�������=�������û����ȥ�����Ѱ�Ҽ���
 * 
 * 	������ڸպ�=8������ [36,60], ��52th�� = 8�� 
 *  ������ڸպ�=8������ [36,50]����ֻ��Ҫȥ�Ҳ�[51,r] ������еݹ�������죬����Ҫ�������
 */
public class RandomSelectAlgorithm {
	
	public static int first, last;

	public static int findKthLargest(int[] nums, int k) {
		return randomizedSelect(nums, nums.length - k);
	}
	
	// ���arr����Ļ�����iλ�õ�������ʲô
	public static int randomizedSelect(int[] arr, int i) {
		int ans = 0;
		for (int l = 0, r = arr.length - 1; l <= r;) {
			// �����һ�£�����ʱ��Ƚϴ�
			// ��ֻ����һ������������ڸ����ϰ�ʱ�临�Ӷ�������O(n)
			int x = arr[l + (int) (Math.random() * (r - l + 1))]; // ���ѡ���λ��
			partition(arr, l, r, x);  // [l,r]��Χ��������x
			// ��Ϊ��������ֻ��Ҫ��һ��
			// ���Բ���Ҫ��ʱ������¼ȫ�ֵ�first��last
			// ֱ���ü���
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
