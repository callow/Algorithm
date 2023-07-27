package com.algo.tasks.day7;

import java.util.HashSet;
import java.util.Set;

/**
 * arr[] 是一个单词表，可用任意次，使用单词表拼出target串的方法数？
 * 
 * 从左往右尝试模型
 * 
 *
 */
public class WordBreak {
	
	// 暴力递归
	public static int ways(String str, String[] arr) {
		Set<String> set = new HashSet<>();
		for (String candidate : arr) {
			set.add(candidate);
		}
		return process(str, 0, set);
	}

	// 所有的可分解字符串，都已经放在了set中
	// str[i....] 能够被set中的贴纸分解的话，返回分解的方法数
	public static int process(String str, int i, Set<String> set) {
		// 没字符串需要分解了！
		if (i == str.length()) { 
			return 1;
		}
		//  还有字符串需要分解
		int ways = 0;
		// 每一个前缀串全试一遍
		for (int end = i; end < str.length(); end++) {
			String pre = str.substring(i, end + 1);// [) 当前前缀
			if (set.contains(pre)) { // 有此前缀 -> 收集自称组成剩余部分的方法数
				ways += process(str, end + 1, set);
			}
		}
		return ways;
	}
	
	// 动态规划
	public static int ways2(String str, String[] arr) {
		if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}
		Set<String> map = new HashSet<>();
		for (String s : arr) {
			map.add(s);
		}
		int n = str.length();
		int[] dp = new int[n + 1];
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) { 
			for (int end = i; end < n; end++) { // 每一个前缀串全试一遍,双重for
				if (map.contains(str.substring(i, end + 1))) { // 若单词表中包含此前缀，累加
					dp[i] += dp[end + 1]; // 每一个i的位置依赖end+1的位置，因为递归告诉我的：ways += process(str, end + 1, set);
				}
			}
		}
		return dp[0]; // 返回dp[0], 递归告诉我的： return process(str, 0, set);
	}
	
	
	public static class Node {
		public boolean end; // 是否有单词以此节点结尾
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26]; // 只26个小写字母
		}
	}
	// 前缀树解
	public static int ways3(String str, String[] arr) {
		if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}
		/**
		 * 所有单词都去挂到树上
		 */
		Node root = new Node();
		for (String s : arr) {
			char[] chs = s.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a'; // 前缀树分路线方法 'a' - 'a' = 0, 'b' - 'a' = 1, ...... 
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node(); // node.nexts[0] = new Node();
				}
				node = node.nexts[index];
			}
			node.end = true;
		}
		return process(str.toCharArray(), root, 0);
	}

	/**
	 * str[i...] 被分解的方法数，返回
	 */
	public static int process(char[] str, Node root, int i) {
		if (i == str.length) {
			return 1;
		}
		int ways = 0;
		Node cur = root;
		// i...end
		for (int end = i; end < str.length; end++) {
			int path = str[end] - 'a';
			if (cur.nexts[path] == null) { // 如果走不通了，提前结束，不需要继续走了
				break;
			}
			cur = cur.nexts[path]; // 如果有路就往下走
			if (cur.end) { // i...end
				ways += process(str, root, end + 1);
			}
		}
		return ways;
	}
	
	
	/**
		前缀树 + dp
	 */
	public static int ways4(String s, String[] arr) {
		if (s == null || s.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}
		// 建树
		Node root = new Node();
		for (String str : arr) {
			char[] chs = str.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node();
				}
				node = node.nexts[index];
			}
			node.end = true;
		}
		
		// 开始dp
		char[] str = s.toCharArray();
		int N = str.length;
		int[] dp = new int[N + 1];
		dp[N] = 1;
		for (int i = N - 1; i >= 0; i--) {
			Node cur = root;
			for (int end = i; end < N; end++) {
				int path = str[end] - 'a';
				if (cur.nexts[path] == null) {
					break;
				}
				cur = cur.nexts[path];
				if (cur.end) {
					dp[i] += dp[end + 1];
				}
			}
		}
		return dp[0];
	}
	
	
}
