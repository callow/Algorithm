package com.algo.util.trie.model;

import java.util.Arrays;

/**
 * 使用静态数组实现：推荐，根据数据量估计出空间，因为动态创建节点的方式在每个测试用例是重复计算的。浪费大量空间的方式，
 * 
 * 26条路
 * 
 * 此前缀树支持的path = a - z
 * 	
 * 	tree[n][m]: 
 *    n 随便估一个量,try and error
 * 	  m 字符种类
 */
public class StaticLetterPrefixTree {

	// 如果将来增加了数据量，就改大这个值
	public static int MAXN = 150001;

	public static int[][] tree = new int[MAXN][26];

	public static int[] end = new int[MAXN];

	public static int[] pass = new int[MAXN];

	public static int cnt;

	public static void build() {
		cnt = 1;
	}
	
	/**
	 *  支持路的种类 / 找路的方式, 路只支持 a ~ z
	 */
	public static int path(char cha) {
		return cha - 'a';
	}

	public static void insert(String word) {
		int cur = 1;
		pass[cur]++;
		for (int i = 0, path; i < word.length(); i++) {
			path = path(word.charAt(i));
			if (tree[cur][path] == 0) {
				tree[cur][path] = ++cnt;
			}
			cur = tree[cur][path];
			pass[cur]++;
		}
		end[cur]++;
	}

	public static int search(String word) {
		int cur = 1;
		for (int i = 0, path; i < word.length(); i++) {
			path = path(word.charAt(i));
			if (tree[cur][path] == 0) {
				return 0;
			}
			cur = tree[cur][path];
		}
		return end[cur];
	}

	public static int prefixNumber(String pre) {
		int cur = 1;
		for (int i = 0, path; i < pre.length(); i++) {
			path = path(pre.charAt(i));
			if (tree[cur][path] == 0) {
				return 0;
			}
			cur = tree[cur][path];
		}
		return pass[cur];
	}

	public static void delete(String word) {
		if (search(word) > 0) {
			int cur = 1;
			for (int i = 0, path; i < word.length(); i++) {
				path = path(word.charAt(i));
				if (--pass[tree[cur][path]] == 0) {
					tree[cur][path] = 0;
					return;
				}
				cur = tree[cur][path];
			}
			end[cur]--;
		}
	}

	public static void clear() {
		for (int i = 1; i <= cnt; i++) {
			Arrays.fill(tree[i], 0);
			end[i] = 0;
			pass[i] = 0;
		}
	}
	
	
	
}
