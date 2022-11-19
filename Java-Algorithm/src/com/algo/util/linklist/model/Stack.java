package com.algo.util.linklist.model;

/**
 * 
 * ʹ��˫����ʵ��Stack
 *
 */
public class Stack<T> {
	private DoubleEndsQueue<T> queue;

	public Stack() {
		queue = new DoubleEndsQueue<T>();
	}

	public void push(T value) {
		queue.addFromHead(value);
	}

	public T pop() {
		return queue.popFromHead();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
