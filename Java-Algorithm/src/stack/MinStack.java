package stack;

import java.util.Stack;

public class MinStack {

	private Stack<Integer> dataStack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public void push(int num) {
		dataStack.push(num);
		if (minStack.peek() == null) {
			minStack.push(num);
		} else if (num <= minStack.peek()) { // if =, then not put
			minStack.push(num);
		}
	}

	public Integer pop() {
		Integer popValue = dataStack.pop();
		if (popValue.compareTo(minStack.peek()) == 0) {
			minStack.pop();
		}
		return popValue;
	}

	public Integer getMin() {
		return minStack.peek();
	}
}
