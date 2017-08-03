package leetcode;

import java.util.Stack;

public class Valid_parathesis_20 {

	public static void main(String[] args) {
		String input = "{([])}[]{{{[()]()}{}}()}{}[][]()()()()()";
		System.out.println(isValid(input));
	}
	
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(')
				stack.push(')');
			else if (c == '{')
				stack.push('}');
			else if (c == '[')
				stack.push(']'); // 之前都是判断左括号，加右括号
			else if (stack.isEmpty() || stack.pop() != c) // 如果判断是右括号  ！= 从Stack头上拿出来的右括号
				return false;
		}
		return stack.isEmpty(); // 都一一对应了 进出均衡
	}

}
