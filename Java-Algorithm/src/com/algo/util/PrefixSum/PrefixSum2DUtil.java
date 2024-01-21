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
	 * 求2维矩阵的某个矩形和：https://leetcode.cn/problems/range-sum-query-2d-immutable/
	 */
	public static int sumRegion(int[][] matrix, int a, int b, int c, int d) {
		MatrixSumArray prefixSum = new MatrixSumArray(matrix);
		return prefixSum.sumRegion(a, b, c, d);
	}
	
	/**
	 * 在0/1矩阵中，寻找边框为1的最大正方向。 https://leetcode.cn/problems/largest-1-bordered-square/
	 * 
	 * 验证边框全1的思路：
	 * 	先求（a,b）-> (c,d) 的累加和Sum1
	 *  再求 (e,f）-> (g,h) 内正方向的正累和Sum2
	 *  看一下是否 Sum1 - Sum2 = （a,b）-> (c,d) 的周长?
	 *  
	 *  周长算法： 若k=4，则是一个4*4的正方形，周长 = (k-1) * 4 = 12 or  (k - 1) << 2
	 *  
	 * 枚举所有正方形： O(N * M * MIN(N,M))
	 */
	public static int largest1BorderedSquare(int[][] g) {
		int n = g.length;
		int m = g[0].length;
		MatrixSumArray.build(n, m, g); // 构建前缀和矩阵
		if (MatrixSumArray.sum(g, 0, 0, n - 1, m - 1) == 0) { // 看看有没有1，没有1直接返回0
			return 0;
		}
		// 找到的最大合法正方形的边长
		int ans = 1;
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < m; b++) {
				// 枚举所有(a,b)所有左上角点
				//     枚举所有(c,d)更大边长的右下角点，k是当前尝试的边长
				// e.g： （5，7） 则2 * 2的 右下角 （6，8）
				// k 是边长
				for (int c = a + ans, d = b + ans, k = ans + 1; c < n && d < m; c++, d++, k++) {
					if (MatrixSumArray.sum(g, a, b, c, d) // 整个正方形
							- MatrixSumArray.sum(g, a + 1, b + 1, c - 1, d - 1) //内侧正方形
							== (k - 1) << 2) { // 周长
						ans = k;
					}
				}
			}
		}
		return ans * ans; // 边长 * 边长 = 面积
	}
	
	
}
