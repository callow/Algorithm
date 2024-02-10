package com.algo.util.input_output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * ACM-Scanner风格: 没有给定数据格式/规模，没办法只能一行一行读，但是依然可以使用buffer + static
 * 
 * 求任意长度数组累加和？
 * 
 * 没办法使用StringTokenizer,因为它不会去区分 空格/回车
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
			out.println(sum); // 放到内存托托管区 暂时不提交
		}
		out.flush(); // 最后刷一下 提交一次
		in.close();
		out.close();
	}
}
