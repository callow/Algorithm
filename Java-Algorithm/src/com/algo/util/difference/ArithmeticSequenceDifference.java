package com.algo.util.difference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 等差数列拆分模版题
 * 
 * 一开始1~n范围上的数字都是0，一共有m个操作，每次操作为(l,r,s,e,d) 表示在l~r范围上依次加上首项为s、末项为e、公差为d的数列
  m个操作做完之后，统计1~n范围上所有数字的最大值和异或和
  测试链接 : https://www.luogu.com.cn/problem/P4231
 * 
 */

public class ArithmeticSequenceDifference {

	public static int MAXN = 10000005;

	public static long[] arr = new long[MAXN];

	public static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			for (int i = 0, l, r, s, e; i < m; i++) {
				in.nextToken(); l = (int) in.nval;
				in.nextToken(); r = (int) in.nval;
				in.nextToken(); s = (int) in.nval;
				in.nextToken(); e = (int) in.nval;
				set(l, r, s, e, (e - s) / (r - l)); // d 自己算出来 = (e - s) / (r - l)， 然后带入等差数列差分公式
			}
			build(); // 求2遍前缀和
			
			// 遍历一下求题目要求的异或和 ， 最大值
			long max = 0, xor = 0;
			for (int i = 1; i <= n; i++) {
				max = Math.max(max, arr[i]);
				xor ^= arr[i];
			}
			out.println(xor + " " + max);
		}
		out.flush();
		out.close();
		br.close();
	}

	/**
	 *  1. 等差数列拆分公式
	 */
	public static void set(int l, int r, int s, int e, int d) {
		arr[l] += s;
		arr[l + 1] += d - s;
		arr[r + 1] -= d + e;
		arr[r + 2] += e;
	}

	/**
	 *  加工2遍前缀和
	 */
	public static void build() {
		for (int i = 1; i <= n; i++) {
			arr[i] += arr[i - 1];
		}
		for (int i = 1; i <= n; i++) {
			arr[i] += arr[i - 1];
		}
	}
}
