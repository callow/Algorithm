package com.algo.util.linklist;

import com.algo.util.common.DoubleNode;

/**
 * 
 * 使用双链表实现双端队列，前后都可以进出
 *
 */
public class DoubleEndsQueue<T> {
	public DoubleNode<T> head;
	public DoubleNode<T> tail;

	public void addFromHead(T value) {
		DoubleNode<T> cur = new DoubleNode<T>(value);
		if (head == null) {
			head = cur;
			tail = cur;
		} else {
			cur.next = head;
			head.last = cur;
			head = cur;
		}
	}

	public void addFromBottom(T value) {
		DoubleNode<T> cur = new DoubleNode<T>(value);
		if (head == null) {
			head = cur;
			tail = cur;
		} else {
			cur.last = tail;
			tail.next = cur;
			tail = cur;
		}
	}

	public T popFromHead() {
		if (head == null) {
			return null;
		}
		DoubleNode<T> cur = head;
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			cur.next = null;
			head.last = null;
		}
		return cur.value;
	}

	public T popFromBottom() {
		if (head == null) {
			return null;
		}
		DoubleNode<T> cur = tail;
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			tail = tail.last;
			tail.next = null;
			cur.last = null;
		}
		return cur.value;
	}

	public boolean isEmpty() {
		return head == null;
	}
}
