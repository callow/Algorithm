package com.algo.competition.w8_bracket_water_matrix_snake;

import java.util.LinkedList;

/**
 * 
 * 计算括号相关问题，比如化学方程式，加减法等。
 *
 */
public class BracketExpression {

	/**
	 * 实现一个带括号的计算器，可以计算+-* / 
		https://leetcode.com/problems/basic-calculator-iii/
	 */
	
	public static int calculate(String str) {
		// go递归返回2个info： 结果 和 算到了哪一个位置了
		return go(str.toCharArray(), 0)[0];
	}
	
	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组
	// 0) 负责的这一段的结果是多少
	// 1) 负责的这一段计算到了哪个位置
	public static int[] go(char[] str, int i) {
		LinkedList<String> que = new LinkedList<>();
		int cur = 0;
		int[] info = null;
		// 从i出发，开始撸串, 如果遇到末尾 / 右括号就停，然后收集答案
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') { 
				cur = cur * 10 + str[i++] - '0'; // cur * 10 + 此时的值， 1 * 10 + 5 = 15
			} else if (str[i] != '(') { // 遇到的是运算符号，不是数字，也不是（
				addNum(que, cur); // 把数字加进容器
				que.addLast(String.valueOf(str[i++])); // 把符号也加进去
				cur = 0; // cur 清0
			} else { // 遇到左括号了
				info = go(str, i + 1); // 递归收集子括号答案
				cur = info[0]; // 第一个返回值就是我的cur
				i = info[1] + 1; // 我接着下一位继续
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
