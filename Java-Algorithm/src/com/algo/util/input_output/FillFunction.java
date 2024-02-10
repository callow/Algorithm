package com.algo.util.input_output;

/**
 * �������Ӿ��������ۼӺ�����
 * 
 * ����һ��NxN����mat�;���Ľ���n����֪�������������͸�������ɣ��뷵��Ԫ���ܺ������Ӿ����Ԫ��֮�͡�Ҫ��Ԫ�ؾ���ֵС�ڵ���100000��������Ч�Ҿ������С�ڵ���200�� 
 * [[1,2,-3],[3,4,-5],[-5,-6,-7]],3
 * 
 *  https://www.nowcoder.com/practice/840eee05dccd4ffd8f9433ce8085946b
 */
public class FillFunction {

	public int sumOfSubMatrix(int[][] mat, int n) {
		return maxSumSubmatrix(mat, n, n);
	}

	// ���Ӿ��������ۼӺ�
	public static int maxSumSubmatrix(int[][] mat, int n, int m) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			// ��Ҫ�ĸ������飬��ʱ��̬���ɾͿ���
			int[] arr = new int[m];
			for (int j = i; j < n; j++) {
				for (int k = 0; k < m; k++) {
					arr[k] += mat[j][k];
				}
				max = Math.max(max, maxSumSubarray(arr, m));
			}
		}
		return max;
	}

	/**
	 * ÿ������ȫ��һ��
	 * for(0 ~ n) {
	 * 	for(0 ~ m) {
	 * 		// ����Ӿ������ۼ�ѹ����һά��Ȼ����򵥵�һά������ѡ����
	 * 	}
	 * }
	 */
	public static int maxSumSubarray(int[] arr, int m) {
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < m; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}
}
