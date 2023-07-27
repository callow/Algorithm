package com.algo.tasks.day7;

import java.util.HashMap;
import java.util.Map;
/**
 * 和上一题WordBreak一样，只是单词表每个单词都有一个分数，求拼出target串，怎么拼让总得分最大？
 * 
 * 思路： 和WordBreak一样，也是前缀树，只是以前是方法数的累加和，现在是所有的得分求一个max
 *
 */
public class SplitStringMaxValue {

	// 暴力解
	public static int maxRecord1(String str, int K, String[] parts, int[] record) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		Map<String, Integer> records = new HashMap<>();
		for (int i = 0; i < parts.length; i++) {
			records.put(parts[i], record[i]); // 单词：分数
		}
		return process(str, 0, K, records);
	}

	public static int process(String str, int index, int rest, Map<String, Integer> records) {
		if (rest < 0) {
			return -1;
		}
		if (index == str.length()) {
			return rest == 0 ? 0 : -1;
		}
		int ans = -1;
		for (int end = index; end < str.length(); end++) {
			String first = str.substring(index, end + 1);
			int next = records.containsKey(first) ? process(str, end + 1, rest - 1, records) : -1;
			if (next != -1) {
				ans = Math.max(ans, records.get(first) + next);
			}
		}
		return ans;
	}

		// 动态规划解
		public static int maxRecord2(String str, int K, String[] parts, int[] record) {
			if (str == null || str.length() == 0) {
				return 0;
			}
			HashMap<String, Integer> records = new HashMap<>();
			for (int i = 0; i < parts.length; i++) {
				records.put(parts[i], record[i]);
			}
			int N = str.length();
			int[][] dp = new int[N + 1][K + 1];
			for (int rest = 1; rest <= K; rest++) {
				dp[N][rest] = -1;
			}
			for (int index = N - 1; index >= 0; index--) {
				for (int rest = 0; rest <= K; rest++) {
					int ans = -1;
					for (int end = index; end < N; end++) {
						String first = str.substring(index, end + 1);
						int next = rest > 0 && records.containsKey(first) ? dp[end + 1][rest - 1] : -1;
						if (next != -1) {
							ans = Math.max(ans, records.get(first) + next);
						}
					}
					dp[index][rest] = ans;
				}
			}
			return dp[0][K];
		}

		// 动态规划解 + 前缀树优化
		public static int maxRecord3(String s, int K, String[] parts, int[] record) {
			if (s == null || s.length() == 0) {
				return 0;
			}
			TrieNode root = rootNode(parts, record);
			char[] str = s.toCharArray();
			int N = str.length;
			int[][] dp = new int[N + 1][K + 1];
			for (int rest = 1; rest <= K; rest++) {
				dp[N][rest] = -1;
			}
			for (int index = N - 1; index >= 0; index--) {
				for (int rest = 0; rest <= K; rest++) {
					int ans = -1;
					TrieNode cur = root;
					for (int end = index; end < N; end++) {
						int path = str[end] - 'a';
						if (cur.nexts[path] == null) {
							break;
						}
						cur = cur.nexts[path];
						int next = rest > 0 && cur.value != -1 ? dp[end + 1][rest - 1] : -1;
						if (next != -1) {
							ans = Math.max(ans, cur.value + next);
						}
					}
					dp[index][rest] = ans;
				}
			}
			return dp[0][K];
		}

		public static class TrieNode {
			public TrieNode[] nexts;
			public int value;

			public TrieNode() {
				nexts = new TrieNode[26];
				value = -1;
			}
		}

		public static TrieNode rootNode(String[] parts, int[] record) {
			TrieNode root = new TrieNode();
			for (int i = 0; i < parts.length; i++) {
				char[] str = parts[i].toCharArray();
				TrieNode cur = root;
				for (int j = 0; j < str.length; j++) {
					int path = str[j] - 'a';
					if (cur.nexts[path] == null) {
						cur.nexts[path] = new TrieNode();
					}
					cur = cur.nexts[path];
				}
				cur.value = record[i];
			}
			return root;
		}
	
}
