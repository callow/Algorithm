package com.algo.util.trie;
/**
 * 
 * 前缀树/字典树： prefix tree = trie, 字符在路上，不是在节点上，路可以复用。<br><br>
 * 
 * Usage: <br>
 *  查询一个字符串数组 某个元素出现几次 <br>
 *  有多少字符串以ab*做 前缀的<br>
 *  有多少字符串以*cd做 后缀的<br>
 *
 */
public class PrefixTree {

	private Node root;

	public PrefixTree() {
		root = new Node();
	}
	
	/**
	 * 
	 * 将一个元素加到Tree上去 <br><br>
	 * 
	 *  将word这个字符串按照char一个一个拆开，挂到树上
	 */
	
	public void insert(String word) {
		if (word == null) {
			return;
		}
		char[] chs = word.toCharArray(); // ['a','b','c']
		Node node = root;
		node.pass++;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i];
			if (!node.paths.containsKey(index)) {
				node.paths.put(index, new Node());
			}
			node = node.paths.get(index);
			node.pass++;
		}
		node.end++;
	}
	
	/**
	 * 
	 * 将一个word从Tree上删掉1次，之前可能加过多次
	 */
	
	public void delete(String word) {
		if (count(word) != 0) { // 检查一下在Tree中有没有
			char[] chs = word.toCharArray();
			Node node = root;
			node.pass--; // 沿途p--
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i];
				if (--node.paths.get(index).pass == 0) {
					node.paths.remove(index);
					return;
				}
				node = node.paths.get(index);
			}
			node.end--;
		}
	}
	
	/**
	 * word这个单词之前加入过几回
	 */
	
	public int count(String word) {
		if (word == null) {
			return 0;
		}
		char[] chs = word.toCharArray();
		Node node = root;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i];
			if (!node.paths.containsKey(index)) {
				return 0;
			}
			node = node.paths.get(index);
		}
		return node.end;
	}
	
	/**
	 * 有几个是以pre这个字符串作为前缀的.
	 */
	
	public int countPrefix(String pre) {
		if (pre == null) {
			return 0;
		}
		char[] chs = pre.toCharArray();
		Node node = root;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i];
			if (!node.paths.containsKey(index)) {
				return 0;
			}
			node = node.paths.get(index);
		}
		return node.pass;
	}
}
