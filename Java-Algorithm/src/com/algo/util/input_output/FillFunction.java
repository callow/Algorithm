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

	/**
	 * ѹ�����鼼�ɣ�ѹ�����ݣ������ǹ�����ӡ� ���Ӿ������ۼ�ѹ����һά��Ȼ����򵥵�һά������ѡ����
	 * 
	 * 0~0�� �ĸ��Ӿ�������ۼӺ����
	 * 0~1�� �ĸ��Ӿ�������ۼӺ����
	 * 0~2�� �ĸ��Ӿ�������ۼӺ����
	 * ...
	  *1~1�� �ĸ��Ӿ�������ۼӺ����
	 * 1~2�� �ĸ��Ӿ�������ۼӺ����
	 * 1~3�� �ĸ��Ӿ�������ۼӺ���� 
	 * ...
	 * 
	 * 
	 */
	
	public static int maxSumSubmatrix(int[][] mat, int n, int m) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			// ��Ҫ�ĸ������飬��ʱ��̬���ɾͿ���,����ѹ�������飬�������е�ͬ
			int[] arr = new int[m];
			for (int j = i; j < n; j++) { // �����µ��ƣ�0~0, 0~1,0~2... 1~1, 1~2,1~3
				for (int k = 0; k < m; k++) { 
					arr[k] += mat[j][k]; // ��ƽ�ƣ����ѹ������
				}
				max = Math.max(max, maxSumSubarray(arr, m)); // 
			}
		}
		return max;
	}

	/**
	 *  0~1 �� ��ÿ��ѹ������(1ά)������ۼӺͣ���70��
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
