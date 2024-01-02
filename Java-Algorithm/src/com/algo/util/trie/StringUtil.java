package com.algo.util.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.algo.util.trie.model.PrefixTree;
import com.algo.util.trie.model.PrefixTree3;

public class StringUtil {

	public static final String EMPTY = "";

	/**
	 * 数一下Prefix开头字符串个数
	 */
	public static int countPrefixStr(List<String> strs, String prefix) {
		PrefixTree tree = new PrefixTree();
		for (String s : strs) {
			tree.insert(s);
		}
		return tree.countPrefix(prefix);
	}

	/**
	 * 
	 * 统计String中每种字符出现的频率,字符必修是小写
	 */
	public static int[] getLetterFrequency(String str) {
		char[] s1 = str.toCharArray();
		int[] count = new int[26];
		for (char cha : s1) {
			count[cha - 'a']++;
		}
		return count;
	}

	/**
	 * 寻找最长公共前缀 = 字典树最深处
	 */

	public static String getLongestCommontPrefix(String[] strs) {
		if (strs.length == 0) {
			return EMPTY;
		}
		String prefix = strs[0]; // first element
		for (int i = 1; i < strs.length; i++) {

			while (strs[i].indexOf(prefix) != 0) { // 当符合时，继续判断prefix, prefix不合格时退出
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return EMPTY;
				}
			}
		}
		return prefix;

	}
	
	/**
	 * 流式识别前缀树
	 * https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
	 * 输入：
			7
			1 qwer
			1 qwe
			3 qwer
			4 q
			2 qwer
			3 qwer
			4 q
		输出：
			YES
			2
			NO
			1
	 */
	public static int m, op;
	public static String[] splits;
	public static void streamTrie() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		String line = null;
		while ((line = in.readLine()) != null) {
			PrefixTree3.build();
			m = Integer.valueOf(line);
			for (int i = 1; i <= m; i++) {
				splits = in.readLine().split(" ");
				op = Integer.valueOf(splits[0]);
				if (op == 1) {
					PrefixTree3.insert(splits[1]);
				} else if (op == 2) {
					PrefixTree3.delete(splits[1]);
				} else if (op == 3) {
					out.println(PrefixTree3.search(splits[1]) > 0 ? "YES" : "NO");
				} else if (op == 4) {
					out.println(PrefixTree3.prefixNumber(splits[1]));
				}
			}
			PrefixTree3.clear(); // 一个test case完成，全部清空
		}
		out.flush();
		in.close();
		out.close();
	}
}
