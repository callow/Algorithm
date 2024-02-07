package com.algo.util.stack.model;

import java.util.Stack;
/**
 * 
 * ʹ��Stackʵ��Queue O(1)
 * 
 * (2��ջ���൹) out���˲������ﵹ������� in���붼����
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

	// pushջ��popջ�������� / ��inջ����outջ
	// out���˲������ﵹ����
	// inÿ�ε����뵹��
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
