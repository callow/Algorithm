package com.algo.util.Ac_automation.model;

// 前缀树的节点
public class Node {
	// 如果一个node，end为空，不是结尾
	// 如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
	public String end;
	// 只有在上面的end变量不为空的时候，endUse才有意义
	// 表示，这个字符串之前有没有加入过答案
	public boolean endUse;
	public Node fail;
	public Node[] nexts;

	public Node() {
		endUse = false;
		end = null;
		fail = null;
		nexts = new Node[26];
	}
}
