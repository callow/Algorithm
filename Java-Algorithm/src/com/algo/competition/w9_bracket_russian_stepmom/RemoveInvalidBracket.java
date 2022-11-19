package com.algo.competition.w9_bracket_russian_stepmom;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * 
 *
 */
public class RemoveInvalidBracket {
	
	public static List<String> removeInvalidBracket(String input) {
		List<String> ans = new ArrayList<>();
		remove(input, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}
	
	public static void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
		for (int count = 0, i = checkIndex; i < s.length(); i++) {
			if (s.charAt(i) == par[0]) {
				count++;
			}
			if (s.charAt(i) == par[1]) {
				count--;
			}
			// i check计数<0的第一个位置
			if (count < 0) {
				for (int j = deleteIndex; j <= i; ++j) {
					// 比如
					if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
						remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
					}
				}
				return;
			}
		}
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(') {
			remove(reversed, ans, 0, 0, new char[] { ')', '(' });
		} else {
			ans.add(reversed);
		}
	}
}
