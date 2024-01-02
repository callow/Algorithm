package com.algo.util.trie.model;

// 用类描述实现前缀树�?�不推荐�?

//https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
public class PrefixTree2 {

	// 路是数组实现�? 但是种类被局限了，26， 

	  class TrieNode {
			public int pass;
			public int end;
			public TrieNode[] nexts;

			public TrieNode() {
				pass = 0;
				end = 0;
				nexts = new TrieNode[26];
			}
		}

		private TrieNode root;

		public PrefixTree2() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode node = root;
			node.pass++;
			for (int i = 0, path; i < word.length(); i++) { // 从左�?右遍历字�?
				path = word.charAt(i) - 'a'; // 由字符，对应成走向哪条路
				if (node.nexts[path] == null) {
					node.nexts[path] = new TrieNode();
				}
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		// 如果之前word插入过前�?树，那么此时删掉�?�?
		// 如果之前word没有插入过前�?树，那么�?么也不做
		public void erase(String word) {
			if (countWordsEqualTo(word) > 0) {
				TrieNode node = root;
				node.pass--;
				for (int i = 0, path; i < word.length(); i++) {
					path = word.charAt(i) - 'a';
					if (--node.nexts[path].pass == 0) {
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}

		// 查询前缀树里，word单词出现了几�?
		public int countWordsEqualTo(String word) {
			TrieNode node = root;
			for (int i = 0, path; i < word.length(); i++) {
				path = word.charAt(i) - 'a';
				if (node.nexts[path] == null) {
					return 0;
				}
				node = node.nexts[path];
			}
			return node.end;
		}

		// 查询前缀树里，有多少单词以pre做前�?
		public int countWordsStartingWith(String pre) {
			TrieNode node = root;
			for (int i = 0, path; i < pre.length(); i++) {
				path = pre.charAt(i) - 'a';
				if (node.nexts[path] == null) {
					return 0;
				}
				node = node.nexts[path];
			}
			return node.pass;
		}

}