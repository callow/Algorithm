package com.algo.util.stack.model;

import java.util.Stack;

/**
 * 实现一个特殊的栈，实现pop,push,getMin的功能，且都是O(1)
 * 
 * 思路： 使用2个栈，若当前数<最小栈顶，压入最小栈(minStack)，若当前数 > 最小栈顶，重复压入最小栈顶
 * 7            2  (7 > 2 -> 重复压入2)
 * 2            2  (2 < 3 -> 压入 2)
 * 5            3  (5 > 3 -> 重复压入3)
 * 3            3  
 * stackData    stackMin
 * 
 * 弹出的时候，2个栈同步弹出
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
