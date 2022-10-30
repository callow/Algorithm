package com.algo.util.linklist;
/**
 * 
 * ʹ��˫����ʵ��Queue
 *
 */
public class Queue<T> {
	private DoubleEndsQueue<T> queue;

	public Queue() {
		queue = new DoubleEndsQueue<T>();
	}

	public void push(T value) {
		queue.addFromHead(value);
	}

	public T poll() {
		return queue.popFromBottom();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
