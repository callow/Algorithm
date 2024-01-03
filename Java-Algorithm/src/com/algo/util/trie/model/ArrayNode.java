package com.algo.util.trie.model;

// 路是数组实现 但是种类被局限了，26， 
public class ArrayNode {

	public int pass;
	public int end;
	public ArrayNode[] nexts;

	public ArrayNode() {
		pass = 0;
		end = 0;
		nexts = new ArrayNode[26];
	}
}
