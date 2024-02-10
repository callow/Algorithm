package com.algo.util.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * ACM-Scanner��� �Ӿ��������ۼӺ�����(�޾�̬�ռ�)
 * 
 * ע�� ��Ҫ�κοռ䶼��̬���ɣ��ڴ󳧱��Ի��߱����У����ַ�ʽ�����Ƽ�
 * 
 *  ACM��񣺹涨��������
 * 	- ʹ��BufferReader + StreamTokenizer,��Ҫʹ��Scanner
 *  - ʹ��PrinterWriter + flush ,��Ҫʹ��system.out.println
 *  
 *  https://www.nowcoder.com/practice/cb82a97dcd0d48a7b1f4ee917e2c0409?
 * 
 */

public class SpecifyAmount {

	public static void main(String[] args) throws IOException {
		// ���ļ�������ݣ�load�������������ڴ���ܸ�Ч���ܾ��ã��йܵĺܺ�
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// һ��һ��������, ���Կո�ͻس�
		StreamTokenizer in = new StreamTokenizer(br);
		// �ύ�𰸵�ʱ���õģ�Ҳ��һ���ڴ��й���
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) { // �ļ�û�н����ͼ���
			// n����ά�������
			int n = (int) in.nval;
			in.nextToken();
			// m����ά�������
			int m = (int) in.nval;
			// װ���ֵľ�����ʱ��̬����
			int[][] mat = new int[n][m];  // ֱ��new����2ά���鶯̬���ɣ���̫�ã�������Ƽ�ʹ��ȫ�־�̬�ռ�
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					in.nextToken();
					mat[i][j] = (int) in.nval;
				}
			}
			out.println(maxSumSubmatrix(mat, n, m));
		}
		out.flush();
		br.close();
		out.close();
	}

	// ���Ӿ��������ۼӺͣ�����Ŀλὲ
	public static int maxSumSubmatrix(int[][] mat, int n, int m) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			// ��Ҫ�ĸ������飬��ʱ��̬����
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

	// �������������ۼӺͣ�����Ŀλὲ
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
