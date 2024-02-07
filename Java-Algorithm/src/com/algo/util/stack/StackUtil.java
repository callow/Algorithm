package com.algo.util.stack;

import java.util.Stack;

import com.algo.util.stack.model.Queue;

public class StackUtil {

	/**
	 * 生成一个Stack实现的Queue 
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<>();
	}
	

	/**
	 * 栈底元素移除掉, 上面的元素盖下来 返回移除掉的栈底元素
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
