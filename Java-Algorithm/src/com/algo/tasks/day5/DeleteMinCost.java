package com.algo.tasks.day5;
/**
 * 
 * 两个字符串str1和str2, str2最少删几个字符可以变成str1?
 * 
 * Note: 简易看一下视频 因为评论里面还有一个同学的最优解
 *
 */
public class DeleteMinCost {
	
	/**
	 * 	只考虑delete的简化编辑距离解，x = str2, y = str1, 返回至少删除几个字符串
	 */
	public static int onlyDelete(char[] y, char[] x) {
		if (x.length < y.length) { // str2长度 < str 1 , 永远都变不成
			return Integer.MAX_VALUE;
		}
		int N = x.length;
		int M = y.length;
		int[][] dp = new int[N + 1][M + 1];
		// 首先： 默认初始化全是无用区
		for (int i = 0; i <= N; i++) { 
			for (int j = 0; j <= M; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;
		// dp[i][j]表示前缀长度
		// 第0列先填好，全部str2全部删除变成空的str1: ""
		for (int i = 1; i <= N; i++) { 
			dp[i][0] = i;
		}
		
		// 普遍位置依赖：dp[i][j]
		for (int xlen = 1; xlen <= N; xlen++) {
			for (int ylen = 1; ylen <= Math.min(M, xlen); ylen++) {
				if (dp[xlen - 1][ylen] != Integer.MAX_VALUE) { // 如果x除去最后一个字符，前面一坨是一个有效解,即：x[0~i-1]可以通过删除拼出y
					dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
				}
				if (x[xlen - 1] == y[ylen - 1] // 最后字符相等
						&& dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE) {
					dp[xlen][ylen] = Math.min(dp[xlen][ylen], dp[xlen - 1][ylen - 1]);
				}
			}
		}
		return dp[N][M];
	}
}
