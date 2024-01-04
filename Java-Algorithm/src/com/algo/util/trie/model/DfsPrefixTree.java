package com.algo.util.trie.model;

import java.util.Arrays;
import java.util.List;

public class DfsPrefixTree {
	
		// board : ��ά����
		// i,j : ��ʱ�����ĸ���λ�ã�i�С�j��
		// t : ǰ׺���ı��
		// List<String> ans : �ռ�������Щ�ַ�����������ans
		// ����ֵ : �ռ����˼����ַ���
		public static int dfs(char[][] board, int i, int j, int t, List<String> ans) {
			// Խ�� ���� ���˻�ͷ·��ֱ�ӷ���0
			if (i < 0 || i == board.length || j < 0 || j == board[0].length // Խ��
					|| board[i][j] == 0) { // ��ͷ·
				return 0;
			}
			// ��Խ�� �� ���ǻ�ͷ·
			// ��tmp��¼��ǰ�ַ�
			char tmp = board[i][j];
			// ·�ı��
			// a -> 0
			// b -> 1
			// ...
			// z -> 25
			int road = path(tmp);
			t = tree[t][road]; //  t = ǰ׺����ţ�������ʱ��ʱ�� n * 26
			if (pass[t] == 0) { // t = 0 // ����û��· || pass[t] == 0 // ������·�����ǽ���Ѿ��ռ�ȫ��
				return 0;
			}
			// i��jλ���б�Ҫ��
			// fix ���ӵ�ǰi��jλ�ó�����һ���ռ����˼����ַ���
			int fix = 0;
			if (end[t] != null) { // �ռ����ַ�����
				fix++; // ���Լ�
				ans.add(end[t]);
				end[t] = null; // �ռ����ˣ�����Ҳû����
			}
			// ��i��jλ�õ��ַ����ĳ�0����Ϊ�����Ĺ��̣��ǲ�����������i��jλ�õģ�
			board[i][j] = 0; // ��dfsһ�������ж�
			fix += dfs(board, i - 1, j, t, ans); // �����
			fix += dfs(board, i + 1, j, t, ans); // ���ұ�
			fix += dfs(board, i, j - 1, t, ans); // ���±�
			fix += dfs(board, i, j + 1, t, ans); // ���ϱ�
			pass[t] -= fix; // ��֦3��29�����ж�
			board[i][j] = tmp; // �ָ��ֳ�
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
		 * ����
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
