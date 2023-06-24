package com.algo.tasks.day4;

public class SubMatrixMaxSum {

	public static int maxSum(int[][] m) {
		if (m == null || m.length == 0 || m[0].length == 0) {
			return 0;
		}
		// O(N^2 * M)
		int N = m.length; //��
		int M = m[0].length; //��
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			// i��~j��������
			int[] s = new int[M];
			for (int j = i; j < N; j++) {
				
				// ����ѹ�����ɣ������������������ⲻ�ˣ��Ѷ�λ���黻��1άȥ��
				for (int k = 0; k < M; k++) {
					s[k] += m[j][k];
				}
				max = Math.max(max, SubArrayMaxSum.maxSubArray(s));
			}
		}
		return max;
	}
	
	// ����������� : 
	public static int[] getMaxMatrix(int[][] m) {
			int N = m.length;
			int M = m[0].length;
			int max = Integer.MIN_VALUE;
			int cur = 0;
			int a = 0;
			int b = 0;
			int c = 0;
			int d = 0;
			for (int i = 0; i < N; i++) {
				int[] s = new int[M];
				for (int j = i; j < N; j++) {
					cur = 0;
					int begin = 0;
					for (int k = 0; k < M; k++) {
						s[k] += m[j][k];
						cur += s[k];
						if (max < cur) { // max < ��ʱ�ۼӺ�ʱ��Ҫ���µ�max -> ��¼һ�´�
							max = cur;
							a = i;
							b = begin;
							c = j;
							d = k;
						}
						if (cur < 0) {
							cur = 0;
							begin = k + 1;
						}
					}
				}
			}
			return new int[] { a, b, c, d };
		}
}
