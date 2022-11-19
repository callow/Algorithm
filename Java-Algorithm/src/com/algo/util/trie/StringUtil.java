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
}
