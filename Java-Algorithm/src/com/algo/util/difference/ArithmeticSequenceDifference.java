package com.algo.util.difference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * �Ȳ����в��ģ����
 * 
 * һ��ʼ1~n��Χ�ϵ����ֶ���0��һ����m��������ÿ�β���Ϊ(l,r,s,e,d) ��ʾ��l~r��Χ�����μ�������Ϊs��ĩ��Ϊe������Ϊd������
  m����������֮��ͳ��1~n��Χ���������ֵ����ֵ������
  �������� : https://www.luogu.com.cn/problem/P4231
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
				set(l, r, s, e, (e - s) / (r - l)); // d �Լ������ = (e - s) / (r - l)�� Ȼ�����Ȳ����в�ֹ�ʽ
			}
			build(); // ��2��ǰ׺��
			
			// ����һ������ĿҪ������� �� ���ֵ
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
	 *  1. �Ȳ����в�ֹ�ʽ
	 */
	public static void set(int l, int r, int s, int e, int d) {
		arr[l] += s;
		arr[l + 1] += d - s;
		arr[r + 1] -= d + e;
		arr[r + 2] += e;
	}

	/**
	 *  �ӹ�2��ǰ׺��
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
