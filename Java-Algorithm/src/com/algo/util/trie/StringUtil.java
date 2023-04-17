package com.algo.util.trie;

import java.util.List;

import com.algo.util.trie.model.PrefixTree;

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
}
