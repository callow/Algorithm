package com.algo.util.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * acm ����ռ����ۼӵģ���˾ֲ�����ռ����뾡������
 *   - ʹ��public static, ȫ�־�̬�ռ� 
 */
public class StaticSpace {

	// ��Ŀ�������е����������
		public static int MAXN = 201;

		// ��Ŀ�������е����������
		public static int MAXM = 201;

		// ������ô��ľ���ռ䣬һ��������
		// ��̬�Ŀռ䣬��ͣ����
		public static int[][] mat = new int[MAXN][MAXM];

		// ��Ҫ�����и����ռ�Ҳ��ǰ����
		// ��̬�Ŀռ䣬��ͣ����
		public static int[] arr = new int[MAXM];

		// ��ǰ���������е�������n
		// ��ǰ���������е�������m
		// �������������԰Ѵ������еı߽�涨����
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

		// ���Ӿ��������ۼӺͣ�����Ŀλὲ
		public static int maxSumSubmatrix() {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				// ��Ϊ֮ǰ�Ĺ��̿����ù���������
				// Ϊ����֮ǰ��������ŵ�������У���Ҫ�Լ���ո���������Ҫ�õ��Ĳ���
				Arrays.fill(arr, 0, m, 0); // 0 - (m-1) ���� 0 = [0,m)
				for (int j = i; j < n; j++) {
					for (int k = 0; k < m; k++) {
						arr[k] += mat[j][k];
					}
					max = Math.max(max, maxSumSubarray());
				}
			}
			return max;
		}

		// �������������ۼӺͣ�����Ŀλὲ
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
