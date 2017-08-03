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
				stack.push(']'); // ֮ǰ�����ж������ţ���������
			else if (stack.isEmpty() || stack.pop() != c) // ����ж���������  ��= ��Stackͷ���ó�����������
				return false;
		}
		return stack.isEmpty(); // ��һһ��Ӧ�� ��������
	}

}
