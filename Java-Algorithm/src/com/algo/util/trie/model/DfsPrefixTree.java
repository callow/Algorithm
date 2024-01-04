package com.algo.util.trie.model;

import java.util.Arrays;
import java.util.List;

public class DfsPrefixTree {
	
		// board : 二维网格
		// i,j : 此时来到的格子位置，i行、j列
		// t : 前缀树的编号
		// List<String> ans : 收集到了哪些字符串，都放入ans
		// 返回值 : 收集到了几个字符串
		public static int dfs(char[][] board, int i, int j, int t, List<String> ans) {
			// 越界 或者 走了回头路，直接返回0
			if (i < 0 || i == board.length || j < 0 || j == board[0].length // 越界
					|| board[i][j] == 0) { // 回头路
				return 0;
			}
			// 不越界 且 不是回头路
			// 用tmp记录当前字符
			char tmp = board[i][j];
			// 路的编号
			// a -> 0
			// b -> 1
			// ...
			// z -> 25
			int road = path(tmp);
			t = tree[t][road]; //  t = 前缀树编号，建树的时候时候 n * 26
			if (pass[t] == 0) { // t = 0 // 树上没有路 || pass[t] == 0 // 树上有路，但是结果已经收集全了
				return 0;
			}
			// i，j位置有必要来
			// fix ：从当前i，j位置出发，一共收集到了几个字符串
			int fix = 0;
			if (end[t] != null) { // 收集到字符串了
				fix++; // 我自己
				ans.add(end[t]);
				end[t] = null; // 收集过了，你再也没用了
			}
			// 把i，j位置的字符，改成0，因为后续的过程，是不可以再来到i，j位置的！
			board[i][j] = 0; // 　dfs一进来有判断
			fix += dfs(board, i - 1, j, t, ans); // 我左边
			fix += dfs(board, i + 1, j, t, ans); // 我右边
			fix += dfs(board, i, j - 1, t, ans); // 我下边
			fix += dfs(board, i, j + 1, t, ans); // 我上边
			pass[t] -= fix; // 剪枝3，29行有判断
			board[i][j] = tmp; // 恢复现场
			return fix;
		}

		public static int MAXN = 10001;

		public static int[][] tree = new int[MAXN][26];

		public static int[] pass = new int[MAXN];

		public static String[] end = new String[MAXN];

		public static int cnt;
		
		public static int path(char tmp) {
			return tmp - 'a';
		}
		
		/**
		 * 
		 * 建树
		 */
		public static void build(String[] words) {
			cnt = 1;
			for (String word : words) {
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
				end[cur] = word;
			}
		}

		public static void clear() {
			for (int i = 1; i <= cnt; i++) {
				Arrays.fill(tree[i], 0);
				pass[i] = 0;
				end[i] = null;
			}
		}
}
