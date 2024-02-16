package com.algo.util.stack;

import java.io.IOException;
import java.util.Stack;

import com.algo.util.stack.model.Queue;
import com.algo.util.stack.model.ValidParenthese;

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
	
	/**
	 * 判断括号是否有效： 
	 * 	有效： "{}()", "[{()}]", "({()})"
	 * 	无效： "{}(", "({)}", "[[", "}{"
	 * 
	 * 	解决： 遇到左括号就压栈，遇到右括号就弹出来看看是不是配对的左括号
	 * 
	 */
	
	public static boolean isValidParentheses (String line) throws IOException {
		return ValidParenthese.isValid(line);
	}
}
