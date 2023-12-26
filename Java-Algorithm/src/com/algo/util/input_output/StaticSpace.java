package com.algo.util.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * acm 计算空间是累加的，因此局部数组空间申请尽量避免
 *   - 使用public static, 全局静态空间 
 */
public class StaticSpace {

	// 题目给定的行的最大数据量
		public static int MAXN = 201;

		// 题目给定的列的最大数据量
		public static int MAXM = 201;

		// 申请这么大的矩阵空间，一定够用了
		// 静态的空间，不停复用
		public static int[][] mat = new int[MAXN][MAXM];

		// 需要的所有辅助空间也提前生成
		// 静态的空间，不停复用
		public static int[] arr = new int[MAXM];

		// 当前测试数据行的数量是n
		// 当前测试数据列的数量是m
		// 这两个变量可以把代码运行的边界规定下来
		public static int n, m;

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StreamTokenizer in = new StreamTokenizer(br);
			PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
			while (in.nextToken() != StreamTokenizer.TT_EOF) {
				n = (int) in.nval;
				in.nextToken();
				m = (int) in.nval;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						in.nextToken();
						mat[i][j] = (int) in.nval;
					}
				}
				out.println(maxSumSubmatrix());
			}
			out.flush();
			br.close();
			out.close();
		}

		// 求子矩阵的最大累加和，后面的课会讲
		public static int maxSumSubmatrix() {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				// 因为之前的过程可能用过辅助数组
				// 为了让之前结果不干扰到这次运行，需要自己清空辅助数组需要用到的部分
				Arrays.fill(arr, 0, m, 0); // 0 - (m-1) 都填 0 = [0,m)
				for (int j = i; j < n; j++) {
					for (int k = 0; k < m; k++) {
						arr[k] += mat[j][k];
					}
					max = Math.max(max, maxSumSubarray());
				}
			}
			return max;
		}

		// 求子数组的最大累加和，后面的课会讲
		public static int maxSumSubarray() {
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
