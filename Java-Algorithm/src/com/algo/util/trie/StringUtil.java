package com.algo.util.trie;

import java.util.List;

import com.algo.util.trie.model.PrefixTree;

public class StringUtil {

	/**
	 * ��һ��Prefix��ͷ�ַ�������
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
	 * ͳ��String��ÿ���ַ����ֵ�Ƶ��,�ַ�������Сд
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
