package com.algo.tasks.day14;

public class Parenthese {

	public static boolean valid(String s) {
		char[] str = s.toCharArray();
		int count = 0;
		for (int i = 0; i < str.length; i++) {
			count += str[i] == '(' ? 1 : -1;
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

	public static int needParentheses(String s) {
		char[] str = s.toCharArray();
		int count = 0;
		int need = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				count++;
			} else { // ��������')'
				if (count == 0) {
					need++;
				} else {
					count--;
				}
			}
		}
		return count + need;
	}

	public static boolean isValid(char[] str) {
		if (str == null || str.length == 0) {
			return false;
		}
		int status = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] != ')' && str[i] != '(') {
				return false;
			}
			if (str[i] == ')' && --status < 0) {
				return false;
			}
			if (str[i] == '(') {
				status++;
			}
		}
		return status == 0;
	}

	public static int deep(String s) {
		char[] str = s.toCharArray();
		if (!isValid(str)) {
			return 0;
		}
		int count = 0;
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				max = Math.max(max, ++count);
			} else {
				count--;
			}
		}
		return max;
	}

	// sֻ��(��)���
	// �����Ч�����Ӵ�����
	// ����������� : https://leetcode.com/problems/longest-valid-parentheses/
    public static int longestValidParentheses(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		// dp[i] : �Ӵ�������iλ�ý�β������£�������Զ�������೤����Ч����
		int[] dp = new int[str.length];
		// dp[0] = 0; ��  ��
		int pre = 0;
		int ans = 0;
		for (int i = 1; i < str.length; i++) {
			if (str[i] == ')') {
				// ��ǰ˭��iλ�õ�)��ȥ�䣡
				pre = i - dp[i - 1] - 1; // ��str[i]��Ե������ŵ�λ�� pre
				if (pre >= 0 && str[pre] == '(') { // ��Խ�粢����������
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}
	
}
