package com.algo.tasks.day8;

public class FindWordInMatrix {
	
	public static boolean findWordcanLoop(char[][] m, String word) {
		if (word == null || word.equals("")) {
			return true;
		}
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		char[] w = word.toCharArray();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (canLoop(m, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 可以走重复路 : 从m[i][j]这个字符出发，能不能找到word[k...]这个后缀串
	 */
	public static boolean canLoop(char[][] m, int i, int j, char[] word, int k) {
		
		// 来到word结尾了， 搞定了 结束
		if (k == word.length) { 
			return true;
		}
		
		// 当前字符和k位置不一样，搞不定
		if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != word[k]) {
			return false;
		}
		// 不越界！m[i][j] == str[k] 对的上的！上下左右全尝试一遍
		// word[k+1....]
		boolean ans = false;
		if (canLoop(m, i + 1, j, word, k + 1) || canLoop(m, i - 1, j, word, k + 1) || canLoop(m, i, j + 1, word, k + 1)
				|| canLoop(m, i, j - 1, word, k + 1)) {
			ans = true;
		}
		return ans;
	}
	
	
	
	public static boolean findWordNoLoop(char[][] m, String word) {
		if (word == null || word.equals("")) {
			return true;
		}
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		char[] w = word.toCharArray();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (noLoop(m, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 不可以走重复路 : 从m[i][j]这个字符出发，能不能找到word[k...]这个后缀串
	 * 
	 * 走过移除法： 走过的位置，ascci码标0
	 */
	public static boolean noLoop(char[][] m, int i, int j, char[] word, int k) {
		if (k == word.length) {
			return true;
		}
		if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != word[k] || m[i][j] != 0) {
			return false;
		}
		// 不越界！也不是回头路！m[i][j] == str[k] 也对的上！
		char original = m[i][j];
		m[i][j] = 0; // 标0
		boolean ans = false;
		if (noLoop(m, i + 1, j, word, k + 1) || noLoop(m, i - 1, j, word, k + 1) || noLoop(m, i, j + 1, word, k + 1)
				|| noLoop(m, i, j - 1, word, k + 1)) {
			ans = true;
		}
		m[i][j] = original; // 恢复现场
		return ans;
	}

}
