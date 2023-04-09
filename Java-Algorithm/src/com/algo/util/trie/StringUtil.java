package com.algo.util.trie;

import java.util.List;

import com.algo.util.trie.model.PrefixTree;

public class StringUtil {

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
}
