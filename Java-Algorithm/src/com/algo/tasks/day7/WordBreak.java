package com.algo.tasks.day7;

import java.util.HashSet;
import java.util.Set;

/**
 * arr[] ��һ�����ʱ���������Σ�ʹ�õ��ʱ�ƴ��target���ķ�������
 * 
 * �������ҳ���ģ��
 * 
 *
 */
public class WordBreak {
	
	// �����ݹ�
	public static int ways(String str, String[] arr) {
		Set<String> set = new HashSet<>();
		for (String candidate : arr) {
			set.add(candidate);
		}
		return process(str, 0, set);
	}

	// ���еĿɷֽ��ַ��������Ѿ�������set��
	// str[i....] �ܹ���set�е���ֽ�ֽ�Ļ������طֽ�ķ�����
	public static int process(String str, int i, Set<String> set) {
		// û�ַ�����Ҫ�ֽ��ˣ�
		if (i == str.length()) { 
			return 1;
		}
		//  �����ַ�����Ҫ�ֽ�
		int ways = 0;
		// ÿһ��ǰ׺��ȫ��һ��
		for (int end = i; end < str.length(); end++) {
			String pre = str.substring(i, end + 1);// [) ��ǰǰ׺
			if (set.contains(pre)) { // �д�ǰ׺ -> �ռ��Գ����ʣ�ಿ�ֵķ�����
				ways += process(str, end + 1, set);
			}
		}
		return ways;
	}
	
	// ��̬�滮
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
			for (int end = i; end < n; end++) { // ÿһ��ǰ׺��ȫ��һ��,˫��for
				if (map.contains(str.substring(i, end + 1))) { // �����ʱ��а�����ǰ׺���ۼ�
					dp[i] += dp[end + 1]; // ÿһ��i��λ������end+1��λ�ã���Ϊ�ݹ�����ҵģ�ways += process(str, end + 1, set);
				}
			}
		}
		return dp[0]; // ����dp[0], �ݹ�����ҵģ� return process(str, 0, set);
	}
	
	
	public static class Node {
		public boolean end; // �Ƿ��е����Դ˽ڵ��β
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26]; // ֻ26��Сд��ĸ
		}
	}
	// ǰ׺����
	public static int ways3(String str, String[] arr) {
		if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}
		/**
		 * ���е��ʶ�ȥ�ҵ�����
		 */
		Node root = new Node();
		for (String s : arr) {
			char[] chs = s.toCharArray();
			Node node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a'; // ǰ׺����·�߷��� 'a' - 'a' = 0, 'b' - 'a' = 1, ...... 
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
	 * str[i...] ���ֽ�ķ�����������
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
			if (cur.nexts[path] == null) { // ����߲�ͨ�ˣ���ǰ����������Ҫ��������
				break;
			}
			cur = cur.nexts[path]; // �����·��������
			if (cur.end) { // i...end
				ways += process(str, root, end + 1);
			}
		}
		return ways;
	}
	
	
	/**
		ǰ׺�� + dp
	 */
	public static int ways4(String s, String[] arr) {
		if (s == null || s.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}
		// ����
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
		
		// ��ʼdp
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
