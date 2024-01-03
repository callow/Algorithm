package com.algo.util.trie.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 前缀树的单个节点
 *
 */
public class MapNode {

	public int pass; // 被经过了多少次
	public int end; // 以我结尾的元素有多少个
	public Map<Integer, MapNode> paths; // 往下的路有多少条，当然可以使用数组 Node[] paths

	public MapNode() {
		pass = 0;
		end = 0;
		paths = new HashMap<>();
	}
}
