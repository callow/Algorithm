package com.algo.util.stack.model;

import java.util.Stack;
/**
 * 
 * 使用Stack实现Queue O(1)
 * 
 * (2个栈互相倒) out空了才能往里倒，如果倒 in必须都倒完
 * 
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class Queue<T> {

	public Stack<T> inStack;
	public Stack<T> outStack;

	public Queue() {
		inStack = new Stack<T>();
		outStack = new Stack<T>();
	}

	// push栈向pop栈倒入数据 / 从in栈倒如out栈
	// out空了才能往里倒数据
	// in每次倒必须倒空
	private void inToOut() {
		if (outStack.empty()) {
			while (!inStack.empty()) {
				outStack.push(inStack.pop());
			}
		}
	}

	public void add(T pushInt) {
		inStack.push(pushInt);
		inToOut();
	}

	public T poll() {
		if (outStack.empty() && inStack.empty()) {
			throw new RuntimeException("Queue is empty!");
		}
		inToOut();
		return outStack.pop();
	}

	public T peek() {
		if (outStack.empty() && inStack.empty()) {
			throw new RuntimeException("Queue is empty!");
		}
		inToOut();
		return outStack.peek();
	}
}
