package com.algo.tasks.day9;
/**
 * ����������г��ȣ����Ž�O(nlogn)
 * 
 * https://leetcode.com/problems/longest-increasing-subsequence
 */
public class LIS {

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// ends����
		// ends[i]��ʾ : Ŀǰ���г���Ϊi+1�ĵ��������е���С��β
		int[] ends = new int[arr.length];
		// ���ݺ���, һ��ʼends[0] = arr[0]
		ends[0] = arr[0];
		// ends��Ч����Χ��0...right��right����Ϊ��Ч��
		// ����һ��ʼright = 0, ��ʾ��Ч��ֻ��0...0��Χ
		int right = 0;
		// ����������еĳ���
		// ȫ�ֱ�����ץȡÿһ���Ĵ𰸣�ȡ���Ľ��
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			int l = 0;
			int r = right;
			// ��ends[l...r]��Χ�϶���
			// ��� ��ǰ��(arr[i]) > ends[m]���������
			// ��� ��ǰ��(arr[i]) <= ends[m]�������Ҳ�
			// �������־�����ends��Ѱ�� >= ��ǰ��(arr[i])������λ��
			// ���Ǵ�while�������ʱ��l���ڵ�λ�á�
			// ���ends�в����� >= ��ǰ��(arr[i])���������������Ч����Խ��λ��
			// Ҳ���Ǵ�while�������ʱ��l���ڵ�λ�ã�����Ч����Խ��λ��
			// ���� : ends = { 3, 5, 9, 12, ��������Ч}
			// �����ǰ��Ϊ8, ��while�������ʱ��l������2λ��
			// ���� : ends = { 3, 5, 9, 12, ��������Ч}
			// �����ǰ��Ϊ13, ��while�������ʱ��l��������Ч����Խ��λ�ã�4λ��
			while (l <= r) {
				int m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// ��while�����������l��λ��
			// ���l��right��˵����������Ч������ôright����Ҫ��֮���
			// ���l����right��˵��lû��������Ч����Խ��λ�ã�right����
			right = Math.max(right, l);
			// l��λ�ã����ǵ�ǰ��Ӧ���ends�������λ��
			ends[l] = arr[i];
			// ����ȫ�ֱ���
			max = Math.max(max, l + 1);
		}
		return max;
	}
}
