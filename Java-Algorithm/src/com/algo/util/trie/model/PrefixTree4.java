package com.algo.util.trie.model;

import java.util.Arrays;

/**
 * ʹ�þ�̬���� +  �����·����ʡ�ռ��ǰ׺�� - 12��·
 * 
 * ��Ϊ·���ܺܶ����[988,9870]�����������鳤����9870�� �������·�ķ�ʽ ��װ��=> 988#9870#
 * 	 o
 *    \9
 *     o
 *      \8
 *       o
 *     7/ \8
 *     o   o
 *    /0    \#
 *   o       o
 *  /#  
 * o     
 */			 
public class PrefixTree4 {

	    // ����������������������͸Ĵ����ֵ
		public static int MAXN = 2000001;

		public static int[][] tree = new int[MAXN][12]; // ֻ��Ҫ12��·����0 - 9 �� -�� #

		public static int[] pass = new int[MAXN];

		public static int cnt;

		public static void build() {
			cnt = 1;
		}

		/**
		 *  ֧��·������ / ��·�ķ�ʽ, ·ֻ֧�� 
		 *  	'0' ~ '9' 10�� 0~9
		 *  	'#' 10
		 *  	'-' 11
		 */
		public static int path(char cha) {
			if (cha == '#') {
				return 10;
			} else if (cha == '-') {
				return 11;
			} else {
				return cha - '0';
			}
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
		}

		public static int count(String pre) {
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

		public static void clear() {
			for (int i = 1; i <= cnt; i++) {
				Arrays.fill(tree[i], 0);
				pass[i] = 0;
			}
		}
}
