package com.algo.util.trie.model;

// ·������ʵ�� �������౻�����ˣ�26�� 
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
