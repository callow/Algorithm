package com.algo.tasks.day9;

import java.util.ArrayList;
import java.util.List;

/**
 * ��һ����Ч���Ŵ���ɾ�����ٵ��ַ��������Ч������ж���ѡ���������в�ͬ�Ľ��Ҫ������
 * 
 * https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {
	
	public static List<String> removeInvalidParentheses(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	// modifyIndex <= checkIndex
	// ֻ��s[checkIndex....]�Ĳ��֣���Ϊ֮ǰ��һ���Ѿ���������
	// ����֮ǰ�Ĳ�������ô�����Եģ����������ģ�����modifyIndex
	// ���磺
	// ( ( ) ( ) ) ) ...
	// 0 1 2 3 4 5 6
	// һ��ʼ��ȻcheckIndex = 0��modifyIndex = 0
	// ���鵽6��ʱ�򣬷��ֲ����ˣ�
	// Ȼ�����ȥ��2λ�á�4λ�õ� )��������
	// ���ȥ��2λ�õ� ), ��ô��һ������
	// ( ( ( ) ) ) ...
	// 0 1 2 3 4 5 6
	// checkIndex = 6 ��modifyIndex = 2
	// ���ȥ��4λ�õ� ), ��ô��һ������
	// ( ( ) ( ) ) ...
	// 0 1 2 3 4 5 6
	// checkIndex = 6 ��modifyIndex = 4
	// Ҳ����˵��
	// checkIndex��i����modifyIndex(j)���ֱ��ʾ��Ŀ�ʼ �� ���Ŀ�ʼ��֮ǰ�Ķ����ù���  par  (  )
	public static void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
		for (int count = 0, i = checkIndex; i < s.length(); i++) {
			
			/**
			 * ɾ�������Ź���
			 */
			if (s.charAt(i) == par[0]) {
				count++;
			}
			if (s.charAt(i) == par[1]) {
				count--;
			}
			// i check����<0�ĵ�һ��λ��, ��ʼ����j
			if (count < 0) {
				for (int j = deleteIndex; j <= i; ++j) {
					// ����
					if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) { // ��ǰjλ���ǣ� && (j��deleteIndex or ������߲�һ��) - ����ɾ
						// ��ôɾ��
						remove(
								s.substring(0, j) + s.substring(j + 1, s.length()), // ��j������ַ�������ɾ��j
								ans, i, j, par);
					}
				}
				return; // ֻҪ��һ�� �� return
			}
		}
		/**
		 * Ҳ�п��������ű������Ŷࣺ����һ��ɾ��������
		 */
		String reversed = new StringBuilder(s).reverse().toString(); 
		if (par[0] == '(') {
			remove(reversed, ans, 0, 0, new char[] { ')', '(' });
		} else {
			ans.add(reversed);
		}
	}
}
