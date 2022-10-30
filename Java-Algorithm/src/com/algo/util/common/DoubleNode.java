package com.algo.util.common;

public class DoubleNode<T> {

	public T value;
	public DoubleNode<T> last;
	public DoubleNode<T> next;

	public DoubleNode(T data) {
		value = data;
	}
}
