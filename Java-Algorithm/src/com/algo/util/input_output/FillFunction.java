package com.algo.util.input_output;

/**
 * 填函数风格：子矩阵的最大累加和问题
 * 
 * 给定一个NxN矩阵mat和矩阵的阶数n，已知矩阵由正整数和负整数组成，请返回元素总和最大的子矩阵的元素之和。要求元素绝对值小于等于100000，尽量高效且矩阵阶数小于等于200。 
 * [[1,2,-3],[3,4,-5],[-5,-6,-7]],3
 * 
 *  https://www.nowcoder.com/practice/840eee05dccd4ffd8f9433ce8085946b
 */
public class FillFunction {

	public int sumOfSubMatrix(int[][] mat, int n) {
		return maxSumSubmatrix(mat, n, n);
	}

	// 求子矩阵的最大累加和
	public static int maxSumSubmatrix(int[][] mat, int n, int m) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			// 需要的辅助数组，临时动态生成就可以
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
	 * 每个矩阵全看一遍
	 * for(0 ~ n) {
	 * 	for(0 ~ m) {
	 * 		// 这块子矩阵按行累加压缩成一维，然后处理简单的一维（横向选定）
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
