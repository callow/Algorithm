package com.algo.tasks.day8;

import java.util.LinkedList;

/**
 * һ�������ŵı��ʽ�������ȷ�Ľ���� e.g: 48 * ((70-65)-43) + 8 * 1 => -1816
 * 
 * https://leetcode.com/problems/basic-calculator-iii/
 */
public class ExpressionCompute {


	public static int calculate(String str) {
		return process(str.toCharArray(), 0)[0];
	}
	
	
	/**
	 * 
	 *	���str[i...]�����㣬�����ַ�����ֹλ�û��������ţ���ֹͣ. ��������ֵ������Ϊ2������
	 		- a �������һ�εĽ���Ƕ���
	 		- b �������һ�μ��㵽���ĸ�λ��
	 */
	public static int[] process(char[] str, int i) {
		// ˫�˶���/ջ������
		LinkedList<String> queue = new LinkedList<>();
		int cur = 0;
		int[] answer = null;
		
		// ��i��ʼߣ�����������ַ�����β��Ҳ������������
		while(i < str.length && str[i] != ')') {
			
			if(str[i] >= '0' && str[i] <= '9') { // ����������� ���� �� cur = (cur * 10 + ��ǰ��)
				cur = cur * 10 + str[i++] - '0';
				
				
				
			} else if (str[i] != '(') { // ������������������ +-*/ �� ׼��Ҫ��ѹջ3����
				addNum(queue, cur, str[i++]); // 1. �ѵ�ǰ����ѹջ
				queue.addLast(String.valueOf(str[i++])); // 2. �Ѵ�ʱ�ķ���ѹջ
				cur = 0; // 3. cur��0
				
				
				
			} else {  // ����������� '(' �� �Ҳ������ˣ��Ը��ݹ飬Ȼ��ץס2������ֵa,b
				answer = process(str, i + 1);
				cur = answer[0];
				i = answer[1] + 1; // �ݹ������λ��+1��������
			}
		}
		addNum(queue, cur, '+'); // ����ѭ��ʱ�����һ�����ֻ�û�з��룬��Ҫ���ǷŽ�ȥ
		return new int[] { getAnswer(queue), i };
	}
	
	/**
		����˫�˶���β�ˣ���ѹջ��һ�����ʣ�ѹջ���߼�����Ҫ�˿�ʼ���
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
	 * ���ε��� �����
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
