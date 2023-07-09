package com.algo.tasks.day8;

import java.util.Arrays;

import com.algo.util.common.CommonArrayUtil;

public class SnakeGame {

	public static class Info {
		public int no; // 不用能获取最大增长值
		public int yes; // 用能获取最大增长值

		public Info(int n, int y) {
			no = n;
			yes = y;
		}
	}
	
	public static int snakeWalk(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Info cur = go(matrix, i, j);
				ans = Math.max(ans, Math.max(cur.no, cur.yes));
			}
		}
		return ans;
	}
	
	
	/**
	 * 蛇从某一个最左列，且最优的空降点降落 沿途走到(i,j)必须停！
	 *  1. 返回，一次能力也不用，获得的最大成长值
	    2.  返回，用了一次能力，获得的最大成长值
	   如果蛇从某一个最左列，且最优的空降点降落，不用能力，怎么都到不了(i,j)，那么no = -1
	   如果蛇从某一个最左列，且最优的空降点降落，用了一次能力，怎么都到不了(i,j)，那么yes = -1
	 */
	public static Info go(int[][] matrix, int i, int j) {
		
		/**
		 * 刚开始，在最左列 (j = 0)
		 */
		if (j == 0) {
			int no = Math.max(matrix[i][0], -1);
			int yes = Math.max(-matrix[i][0], -1);
			return new Info(no,yes);
		}
		
		int leftNo = -1;
		int leftYes = -1;
		
		/**
		 * 不在最左列，走在中间 (j > 0)
		 * 有可能是从左上，左中，左下，来到当前位置(i,j)
		 */
		
		// 一定有左边
		Info left = go(matrix, i, j -1);
		leftNo = Math.max(leftNo, left.no);
		leftYes = Math.max(leftYes, left.yes);
		
		// 一定有左上
		if (i > 0) { 
			left = go(matrix, i -1, j - 1);
			leftNo = Math.max(leftNo, left.no);
			leftYes = Math.max(leftYes, left.yes);
		}
		
		 // 定有有左下
		if (i < matrix.length - 1) {
			left = go(matrix, i + 1, j - 1);
			leftNo = Math.max(leftNo, left.no);
			leftYes = Math.max(leftYes, left.yes);
		}
		
		/**
		 * 不用能力的成长值： 
		 * 	p1 : 若之前不用能力到不了(<0)，现在能力值也是-1.
		 *  p2 : 若之前不用能可以到(>=0)，则现在的能力值 Max(之前的能力值+现在的格子, -1), 因为加起来也有可能是负数要和-1比一下
		 */
		int no = leftNo == -1 ? -1 : (Math.max(-1, leftNo + matrix[i][j]));
		
		/**
		 * 使用能力后成长值：可能之前用了/现在要用
		 *  p1 : 之前用了能力可以到(>=0)，则现在不能用了
		 *  p2 : 之前没用能力可以到(>=0)，现在就要用(取相反数)
		 */
		int p1 = leftYes == -1 ? -1 : (Math.max(-1, leftYes + matrix[i][j]));
		int p2 = leftNo == -1 ? -1 : (Math.max(-1, leftNo - matrix[i][j]));
		int yes = Math.max(Math.max(p1, p2), -1);
		
		return new Info(no, yes);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 记忆化搜索
	 */
	
	public static int snakeWalk2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[][][] dp = new int[matrix.length][matrix[0].length][2];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0][0] = matrix[i][0];
			dp[i][0][1] = -matrix[i][0];
			max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
		}
		for (int j = 1; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				int preUnuse = dp[i][j - 1][0];
				int preUse = dp[i][j - 1][1];
				if (i - 1 >= 0) {
					preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
					preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
				}
				if (i + 1 < matrix.length) {
					preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
					preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
				}
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
				if (preUnuse >= 0) {
					dp[i][j][0] = matrix[i][j] + preUnuse;
					dp[i][j][1] = -matrix[i][j] + preUnuse;
				}
				if (preUse >= 0) {
					dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
				}
				max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		int N = 7;
		int M = 7;
		int V = 10;
		int times = 1000000;
		for (int i = 0; i < times; i++) {
			int r = (int) (Math.random() * (N + 1));
			int c = (int) (Math.random() * (M + 1));
			int[][] matrix = CommonArrayUtil.generateRandomArray(r, c, V);
			int ans1 = snakeWalk(matrix);
			int ans2 = snakeWalk2(matrix);
			if (ans1 != ans2) {
				for (int j = 0; j < matrix.length; j++) {
					System.out.println(Arrays.toString(matrix[j]));
				}
				System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
				break;
			}
		}
		System.out.println("finish");
	}
}