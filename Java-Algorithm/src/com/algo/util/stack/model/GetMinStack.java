package com.algo.util.stack.model;

import java.util.Stack;

/**
 * ʵ��һ�������ջ��ʵ��pop,push,getMin�Ĺ��ܣ��Ҷ���O(1)
 * 
 * ˼·�� ʹ��2��ջ������ǰ��<��Сջ����ѹ����Сջ(minStack)������ǰ�� > ��Сջ�����ظ�ѹ����Сջ��
 * 7            2  (7 > 2 -> �ظ�ѹ��2)
 * 2            2  (2 < 3 -> ѹ�� 2)
 * 5            3  (5 > 3 -> �ظ�ѹ��3)
 * 3            3  
 * stackData    stackMin
 * 
 * ������ʱ��2��ջͬ������
 */
public class GetMinStack {

	public static class MyStack {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();
				this.stackMin.push(newMin);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}
	
}
