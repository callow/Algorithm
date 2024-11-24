package com.algo.util.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.List;

public class Nim {

	
	/**
	 * ��N��ʯ�ӣ�˭�����һ������˭Ӯ(���ֺͺ���)<br>
	 * 
	 * true = ����Ӯ<br>
	 * 
	 * �⣺˭�����0��0��0��0... ˭�� ��������� ^�� != 0�� ����Ӯ����Ϊ�����������ƶ�ʯ���ú������ȫ0<br>
	 * 
	 * 	  ^ != 0 , ��ʤ̬�� ��Ϊ���ֿ��Զ�һ�����ú������ ^==0��״̬��ֱ����Ϊֹ
	 *    ^ == 0 , �ذ�̬
	 * 
	 * �Ƿ����ֻ�Ӯ��
	 */
	public static String whoWin(List<Integer> stones) {
		int first = stones.get(0);
		
		// ֻ��1��ʯ�ӣ�����ֱ��Ӯ
		if (stones.size() == 1) {
			return "����";
		}
		
		// ��2��ʯ�ӣ�2�Ѳ�һ��������Ӯ
		if (stones.size() == 2) {
			int second = stones.get(1);
			return first != second ? "����" : "����";
		}
		
		// ������
		if (stones.size() > 1) {
			for (int i = 2; i < stones.size(); i++) {
				first ^= stones.get(i);
			}
		}
		return first != 0 ? "����" : "����";
	}
	
	
	/**
	 * ��Nim���ģ�˭�������һ��˭�� ��������Ϸ��
	 * ���ֻ�ʤ��ӡJohn�� ���ֻ�ʤ��ӡBrother
	 * �⣺
	 */
	
	public static int MAXN = 51;
	public static int[] stones = new int[MAXN];
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
			for (int j = 0; j < n; j++) {
				in.nextToken();
				stones[j] = (int) in.nval;
			}
			out.println(compute());
		}
		out.flush();
		out.close();
		br.close();
	}
	public static String compute() {
		int eor = 0, sum = 0;
		for (int i = 0; i < n; i++) {
			eor ^= stones[i];
			sum += stones[i] == 1 ? 1 : 0;
		}
		if (sum == n) {
			return (n & 1) == 1 ? "Brother" : "John";
		} else {
			return eor != 0 ? "John" : "Brother";
		}
	}
	
	
	
	
}
