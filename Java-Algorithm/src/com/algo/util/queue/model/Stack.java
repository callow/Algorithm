package com.algo.util.queue.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ʹ��Queueʵ��Stack : https://leetcode.com/problems/implement-stack-using-queues/
 */
public class Stack<T> {

	public Queue<T> queue;
	public Queue<T> help;

	public Stack() {
		queue = new LinkedList<>();
		help = new LinkedList<>();
	}

	public void push(T value) {
		queue.offer(value);
	}

	public T poll() {
		while (queue.size() > 1) {
			help.offer(queue.poll());
		}
		T ans = queue.poll();
		Queue<T> tmp = queue;
		queue = help;
		help = tmp;
		return ans;
	}

	public T peek() {
		while (queue.size() > 1) {
			help.offer(queue.poll());
		}
		T ans = queue.poll();
		help.offer(ans);
		Queue<T> tmp = queue;
		queue = help;
		help = tmp;
		return ans;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
