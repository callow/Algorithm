package com.algo.tasks.day8;

import java.util.LinkedList;

/**
 * 一个带括号的表达式，算出正确的结果， e.g: 48 * ((70-65)-43) + 8 * 1 => -1816
 * 
 * https://leetcode.com/problems/basic-calculator-iii/
 */
public class ExpressionCompute {


	public static int calculate(String str) {
		return process(str.toCharArray(), 0)[0];
	}
	
	
	/**
	 * 
	 *	请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止. 返回两个值，长度为2的数组
	 		- a 负责的这一段的结果是多少
	 		- b 负责的这一段计算到了哪个位置
	 */
	public static int[] process(char[] str, int i) {
		// 双端队列/栈都可以
		LinkedList<String> queue = new LinkedList<>();
		int cur = 0;
		int[] answer = null;
		
		// 从i开始撸串，不能是字符串结尾，也不能是右括号
		while(i < str.length && str[i] != ')') {
			
			if(str[i] >= '0' && str[i] <= '9') { // 如果遇到的是 数字 ： cur = (cur * 10 + 当前数)
				cur = cur * 10 + str[i++] - '0';
				
				
				
			} else if (str[i] != '(') { // 如果遇到的是运算符号 +-*/ ： 准备要做压栈3部曲
				addNum(queue, cur, str[i++]); // 1. 把当前数字压栈
				queue.addLast(String.valueOf(str[i++])); // 2. 把此时的符号压栈
				cur = 0; // 3. cur清0
				
				
				
			} else {  // 如果遇到的是 '(' ： 我不想算了，仍给递归，然后抓住2个返回值a,b
				answer = process(str, i + 1);
				cur = answer[0];
				i = answer[1] + 1; // 递归结束的位置+1继续运算
			}
		}
		addNum(queue, cur, '+'); // 跳出循环时，最后一个数字还没有放入，不要忘记放进去
		return new int[] { getAnswer(queue), i };
	}
	
	/**
		加入双端队列尾端，和压栈是一个性质，压栈的逻辑，需要乘开始结合
	 */
	private static void addNum(LinkedList<String> queue, int num, char op) {
		if (!queue.isEmpty() && (queue.peekLast().equals("*") || queue.peekLast().equals("/"))) {
			String top = queue.pollLast();
			int pre = Integer.valueOf(queue.pollLast());
			num = top.equals("*") ? (pre * num) : (pre / num);
		}
		queue.addLast(String.valueOf(num));
		queue.addLast(String.valueOf(op));
	}
	
	/**
	 * 依次弹出 计算答案
	 */
	public static int getAnswer(LinkedList<String> queue) {
		int ans = Integer.valueOf(queue.pollFirst());
		while (queue.size() > 1) {
			String op = queue.pollFirst();
			int num = Integer.valueOf(queue.pollFirst());
			ans += op.equals("+") ? num : -num;
		}
		return ans;
	}
}
