package com.algo.tasks.day9;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一个无效括号串，删掉最少的字符让其变有效，如果有多种选择结果，所有不同的结果要都返回
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
	// 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
	// 但是之前的部分是怎么调整对的，调整到了哪？就是modifyIndex
	// 比如：
	// ( ( ) ( ) ) ) ...
	// 0 1 2 3 4 5 6
	// 一开始当然checkIndex = 0，modifyIndex = 0
	// 当查到6的时候，发现不对了，
	// 然后可以去掉2位置、4位置的 )，都可以
	// 如果去掉2位置的 ), 那么下一步就是
	// ( ( ( ) ) ) ...
	// 0 1 2 3 4 5 6
	// checkIndex = 6 ，modifyIndex = 2
	// 如果去掉4位置的 ), 那么下一步就是
	// ( ( ) ( ) ) ...
	// 0 1 2 3 4 5 6
	// checkIndex = 6 ，modifyIndex = 4
	// 也就是说，
	// checkIndex（i）和modifyIndex(j)，分别表示查的开始 和 调的开始，之前的都不用管了  par  (  )
	public static void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
		for (int count = 0, i = checkIndex; i < s.length(); i++) {
			
			/**
			 * 删除右括号过程
			 */
			if (s.charAt(i) == par[0]) {
				count++;
			}
			if (s.charAt(i) == par[1]) {
				count--;
			}
			// i check计数<0的第一个位置, 开始调整j
			if (count < 0) {
				for (int j = deleteIndex; j <= i; ++j) {
					// 比如
					if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) { // 当前j位置是） && (j是deleteIndex or 和我左边不一样) - 可以删
						// 怎么删：
						remove(
								s.substring(0, j) + s.substring(j + 1, s.length()), // 除j以外的字符串：即删掉j
								ans, i, j, par);
					}
				}
				return; // 只要中一回 就 return
			}
		}
		/**
		 * 也有可能左括号比右括号多：逆序一下删除左括号
		 */
		String reversed = new StringBuilder(s).reverse().toString(); 
		if (par[0] == '(') {
			remove(reversed, ans, 0, 0, new char[] { ')', '(' });
		} else {
			ans.add(reversed);
		}
	}
}
