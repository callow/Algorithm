package com.algo.util.trie;

import java.util.List;

import com.algo.util.trie.model.PrefixTree;

public class StringUtil {

	public static final String EMPTY = "";

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

	/**
	 * Ѱ�������ǰ׺ = �ֵ������
	 */

	public static String getLongestCommontPrefix(String[] strs) {
		if (strs.length == 0) {
			return EMPTY;
		}
		String prefix = strs[0]; // first element
		for (int i = 1; i < strs.length; i++) {

			while (strs[i].indexOf(prefix) != 0) { // ������ʱ�������ж�prefix, prefix���ϸ�ʱ�˳�
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return EMPTY;
				}
			}
		}
		return prefix;

	}
}
