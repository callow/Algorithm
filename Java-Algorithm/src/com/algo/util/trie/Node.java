package com.algo.util.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 前缀树的单个节点
 *
 */
public class Node {

	public int pass; // 被经过了多少次
	public int end; // 以我结尾的元素有多少个
	public Map<Integer, Node> paths; // 往下的路有多少条

	public Node() {
		pass = 0;
		end = 0;
		paths = new HashMap<>();
	}
}
