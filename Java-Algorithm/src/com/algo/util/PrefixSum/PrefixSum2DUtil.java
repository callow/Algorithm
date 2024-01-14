package com.algo.util.PrefixSum;

import com.algo.util.PrefixSum.model.MatrixSumArray;

/**
 * 二维数组的前缀和数组Sum (MatrixSum)：  （左+上） - 左上 + 自己  // 容斥原理 = 多出来的一块再减去
 * 
 * 想求（a,b）到（c,d）这个矩形的累加和 则公式就是：
 * 
 * 	sum[c][d] - sum[c][b-1] - sum[a-1][d] + sum[a-1][b-1]
 * 
 * O(1)
 */
public class PrefixSum2DUtil {
	
	/**
	 * 求2维矩阵的矩形和：https://leetcode.cn/problems/range-sum-query-2d-immutable/
	 */
	public static int sumRegion(int[][] matrix, int a, int b, int c, int d) {
		MatrixSumArray prefixSum = new MatrixSumArray(matrix);
		return prefixSum.sumRegion(a, b, c, d);
	}
	
	
}
