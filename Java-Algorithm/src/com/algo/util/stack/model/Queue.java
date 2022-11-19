package com.algo.util.stack.model;

import java.util.Stack;
/**
 * 
 * 使用Stack实现Queue
 */
public class Queue<T> {

	public Stack<T> stackPush;
	public Stack<T> stackPop;

	public Queue() {
		stackPush = new Stack<T>();
		stackPop = new Stack<T>();
	}

	// push栈向pop栈倒入数据
	private void pushToPop() {
		if (stackPop.empty()) {
			while (!stackPush.empty()) {
				stackPop.push(stackPush.pop());
			}
		}
	}

	public void add(T pushInt) {
		stackPush.push(pushInt);
		pushToPop();
	}

	public T poll() {
		if (stackPop.empty() && stackPush.empty()) {
			throw new RuntimeException("Queue is empty!");
		}
		pushToPop();
		return stackPop.pop();
	}

	public T peek() {
		if (stackPop.empty() && stackPush.empty()) {
			throw new RuntimeException("Queue is empty!");
		}
		pushToPop();
		return stackPop.peek();
	}
}
