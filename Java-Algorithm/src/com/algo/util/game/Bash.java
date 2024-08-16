package com.algo.util.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * ��ʿ����
 */
public class Bash {

	
	/**
	 * ֻ��1��ʯ��n����ÿ����1~m����˭�����һ������˭Ӯ <br>
	 * true = ����Ӯ<br>
	 * �⣺����Ҫά�� m+1�ı���, ��Ϊ����ÿ�ζ�����n % (m + 1) != 0 ��������������Ӯ��
	 * 
	 * 	  �����ʼ��ʯ������!= (m+1)���� => ����Ӯ�� ��Ϊ���ֿ������Լ�ÿһ�ֶ���Բ��ǣ�m+1��������״̬�����ú���(����)ÿһ�ֶ�����ǣ�m+1��������״̬��������������һ�������ջᱻ�������ߣ���ʤ̬��
	 * 	  �����ʼ��ʯ������== (m+1)���� => ����Ӯ ���ذ�̬��
	 */
	public static String whoWin(int m, int n) {
		return n % (m + 1) != 0 ? "����" : "����";
	}
	
	
	
	
	/**
	 *  ֻ��1��ʯ��n����ÿ����p^k����˭�����һ������˭Ӯ(p��������k����Ȼ����0����Ȼ��) https://www.luogu.com.cn/problem/P4018
	 *  
	 *  �⣺�۲�õ�1~5�����ã�����6�Ͳ����ԣ��۲�õ�6�ı���һ��������p^k.
	 *  
	 *    ����6�ı��� - ��ʤ̬����Ϊ�������ǿ�����1-5�������ú��������6���������ľ���
	 *    6�ı��� - �ذ�̬
	 */
	public static int t, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		t = (int) in.nval;
		for (int i = 0; i < t; i++) {
			in.nextToken();
			n = (int) in.nval;
			out.println(whoWin(n));
		}
		out.flush();
		out.close();
		br.close();
	}
	public static String whoWin(int n) {
		return n % 6 != 0 ? "October wins!" :"Roy wins!";
	}
	
	
	
	
}
