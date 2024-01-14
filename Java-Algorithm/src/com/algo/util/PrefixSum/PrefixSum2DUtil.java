package com.algo.util.PrefixSum;

import com.algo.util.PrefixSum.model.MatrixSumArray;

/**
 * ��ά�����ǰ׺������Sum (MatrixSum)��  ����+�ϣ� - ���� + �Լ�  // �ݳ�ԭ�� = �������һ���ټ�ȥ
 * 
 * ����a,b������c,d��������ε��ۼӺ� ��ʽ���ǣ�
 * 
 * 	sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1]
 * 
 * O(1)
 */
public class PrefixSum2DUtil {
	
	/**
	 * ��2ά����ľ��κͣ�https://leetcode.cn/problems/range-sum-query-2d-immutable/
	 */
	public static int sumRegion(int[][] matrix, int a, int b, int c, int d) {
		MatrixSumArray prefixSum = new MatrixSumArray(matrix);
		return prefixSum.sumRegion(a, b, c, d);
	}
	
	
}
