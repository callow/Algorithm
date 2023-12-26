package com.algo.util.graph.bfs_dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 
	 ���ʽ��� II
	 ���ֵ� wordList ��ɴӵ��� beginWord ������ endWord ת��
	 һ����ʾ�˹��̵� ת������ ����ʽ���� 
	 beginWord -> s1 -> s2 -> ... -> sk �����ĵ������У������㣺
	 ÿ�����ڵĵ���֮����е�����ĸ��ͬ
	 ת�������е�ÿ������ si��1 <= i <= k���������ֵ� wordList �еĵ���
	 ע�⣬beginWord �������ֵ� wordList �еĵ���
	 sk == endWord
	 ������������ beginWord �� endWord ���Լ�һ���ֵ� wordList
	 �����ҳ����������д� beginWord �� endWord �� ���ת������
	 ���������������ת�����У�����һ�����б�
	 ÿ�����ж�Ӧ���Ե����б� [beginWord, s1, s2, ..., sk] ����ʽ����
	 �������� : https://leetcode.cn/problems/word-ladder-ii/
 */
public class DFSBFS_GeneratePath {

	// ���ʱ� �� list -> hashSet
	public static HashSet<String> dict;

	public static HashSet<String> curLevel = new HashSet<>();

	public static HashSet<String> nextLevel = new HashSet<>();

	// ����ͼ
	public static HashMap<String, ArrayList<String>> graph = new HashMap<>();

	// ��¼·����������һ����Ч·��ʱ�򣬿�����ans��
	public static LinkedList<String> path = new LinkedList<>();

	public static List<List<String>> ans = new ArrayList<>();

	public static void build(List<String> wordList) {
		dict = new HashSet<>(wordList);
		graph.clear();
		ans.clear();
		curLevel.clear();
		nextLevel.clear();
	}

	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		build(wordList);
		if (!dict.contains(endWord)) {
			return ans;
		}
		if (bfs(beginWord, endWord)) {
			dfs(endWord, beginWord);
		}
		return ans;
	}

	// begin -> end ��һ���bfsȥ����ͼ
	// ����ֵ��������ҵ�end������true��false
	public static boolean bfs(String begin, String end) {
		boolean find = false;
		curLevel.add(begin);
		while (!curLevel.isEmpty()) {
			dict.removeAll(curLevel);
			for (String word : curLevel) {
				// word : ȥ��
				// ÿ��λ�ã��ַ�a~z����һ�飡����ڴʱ����Ƿ����
				// ���⣬�ӹ����Լ�
				char[] w = word.toCharArray();
				for (int i = 0; i < w.length; i++) {
					char old = w[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						w[i] = ch;
						String str = String.valueOf(w);
						if (dict.contains(str) && !str.equals(word)) {
							if (str.equals(end)) {
								find = true;
							}
							graph.putIfAbsent(str, new ArrayList<>());
							graph.get(str).add(word);
							nextLevel.add(str);
						}
					}
					w[i] = old;
				}
			}
			if (find) {
				return true;
			} else {
				HashSet<String> tmp = curLevel;
				curLevel = nextLevel;
				nextLevel = tmp;
				nextLevel.clear();
			}
		}
		return false;
	}

	/**
	 * DFS = �ݹ�
	 */
	public static void dfs(String word, String aim) {
		path.addFirst(word);
		if (word.equals(aim)) {
			ans.add(new ArrayList<>(path));
		} else if (graph.containsKey(word)) {
			for (String next : graph.get(word)) {
				dfs(next, aim);
			}
		}
		path.removeFirst();
	}
}
