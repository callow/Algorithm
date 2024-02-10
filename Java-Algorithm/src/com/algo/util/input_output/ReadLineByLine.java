package com.algo.util.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * ACM-Scanner���: û�и������ݸ�ʽ/��ģ��û�취ֻ��һ��һ�ж���������Ȼ����ʹ��buffer + static
 * 
 * �����ⳤ�������ۼӺͣ�
 * 
 * û�취ʹ��StringTokenizer,��Ϊ������ȥ���� �ո�/�س�
 */

public class ReadLineByLine {

	public static String line;

	public static String[] parts;

	public static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while ((line = in.readLine()) != null) {
			parts = line.split(" ");
			sum = 0;
			for (String num : parts) {
				sum += Integer.valueOf(num);
			}
			out.println(sum); // �ŵ��ڴ����й��� ��ʱ���ύ
		}
		out.flush(); // ���ˢһ�� �ύһ��
		in.close();
		out.close();
	}
}
