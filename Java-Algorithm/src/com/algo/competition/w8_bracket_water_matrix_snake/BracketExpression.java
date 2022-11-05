package com.algo.competition.w8_bracket_water_matrix_snake;

import java.util.LinkedList;

/**
 * 
 * ��������������⣬���绯ѧ����ʽ���Ӽ����ȡ�
 *
 */
public class BracketExpression {

	/**
	 * ʵ��һ�������ŵļ����������Լ���+-* / 
		https://leetcode.com/problems/basic-calculator-iii/
	 */
	
	public static int calculate(String str) {
		// go�ݹ鷵��2��info�� ��� �� �㵽����һ��λ����
		return go(str.toCharArray(), 0)[0];
	}
	
	// ���str[i...]�����㣬�����ַ�����ֹλ�û��������ţ���ֹͣ
	// ��������ֵ������Ϊ2������
	// 0) �������һ�εĽ���Ƕ���
	// 1) �������һ�μ��㵽���ĸ�λ��
	public static int[] go(char[] str, int i) {
		LinkedList<String> que = new LinkedList<>();
		int cur = 0;
		int[] info = null;
		// ��i��������ʼߣ��, �������ĩβ / �����ž�ͣ��Ȼ���ռ���
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') { 
				cur = cur * 10 + str[i++] - '0'; // cur * 10 + ��ʱ��ֵ�� 1 * 10 + 5 = 15
			} else if (str[i] != '(') { // ��������������ţ��������֣�Ҳ���ǣ�
				addNum(que, cur); // �����ּӽ�����
				que.addLast(String.valueOf(str[i++])); // �ѷ���Ҳ�ӽ�ȥ
				cur = 0; // cur ��0
			} else { // ������������
				info = go(str, i + 1); // �ݹ��ռ������Ŵ�
				cur = info[0]; // ��һ������ֵ�����ҵ�cur
				i = info[1] + 1; // �ҽ�����һλ����
			}
		}
		addNum(que, cur);
		return new int[] { getNum(que), i };
	}
	
	public static void addNum(LinkedList<String> que, int num) {
		if (!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();
			if (top.equals("+") || top.equals("-")) {
				que.addLast(top);
			} else {
				cur = Integer.valueOf(que.pollLast());
				num = top.equals("*") ? (cur * num) : (cur / num);
			}
		}
		que.addLast(String.valueOf(num));
	}
	public static int getNum(LinkedList<String> que) {
		int res = 0;
		boolean add = true;
		String cur = null;
		int num = 0;
		while (!que.isEmpty()) {
			cur = que.pollFirst();
			if (cur.equals("+")) {
				add = true;
			} else if (cur.equals("-")) {
				add = false;
			} else {
				num = Integer.valueOf(cur);
				res += add ? num : (-num);
			}
		}
		return res;
	}
	
	
}
