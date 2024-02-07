package com.algo.util.stack;

import java.util.Stack;

import com.algo.util.stack.model.Queue;

public class StackUtil {

	/**
	 * ����һ��Stackʵ�ֵ�Queue 
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<>();
	}
	

	/**
	 * ջ��Ԫ���Ƴ���, �����Ԫ�ظ����� �����Ƴ�����ջ��Ԫ��
	 */
	
	public static int popBottom(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = popBottom(stack);
			stack.push(result);
			return last;
		}
	}
}
