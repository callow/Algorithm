package com.algo.util.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * acm: û�и������ݸ�ʽ������һ��һ�ж���������Ȼ����ʹ��buffer + static
 */

//չʾacm���Ĳ��Է�ʽ
//�������� : https://www.nowcoder.com/exam/test/70070648/detail?pid=27976983
//���У�7.A+B(7)������һ��û�и������ݹ�ģ��ֻ�ܰ��ж����ݵ�����
//��ʱ��Ҫ�Լ��зֳ�����������
//��ͬѧ����زο����´����й������롢����Ĵ���
//���������������Ч�ʺܸߵ�д��
//�ύ���µ�code���ύʱ��������ĳ�"Main"������ֱ��ͨ��
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
			out.println(sum);
		}
		out.flush();
		in.close();
		out.close();
	}
}
