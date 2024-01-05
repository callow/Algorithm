package com.algo.util.trie.model;

/**
 * 26��·
 * ǰ׺��/�ֵ����� prefix tree = trie, �ַ���·�ϣ������ڽڵ��ϣ�·���Ը��á�  Time: O(m) ~m����ַ�������<br><br>
 * 
 * Usage: <br>
 *  ��ѯһ���ַ������� ĳ��Ԫ�س��ּ��� <br>
 *  �ж����ַ�����ab*�� ǰ׺��<br>
 *  �ж����ַ�����*cd�� ��׺��<br>
 *
 * ʹ��map�ķ�ʽʵ��ǰ׺��
 */
public class MapLetterPrefixTree {

	private MapNode root;

	public MapLetterPrefixTree() {
		root = new MapNode();
	}
	
	/**
	 * 
	 * ��һ��Ԫ�ؼӵ�Tree��ȥ <br><br>
	 * 
	 *  ��word����ַ�������charһ��һ���𿪣��ҵ�����
	 */
	
	public void insert(String word) {
		if (word == null) {
			return;
		}
		char[] chs = word.toCharArray(); // ['a','b','c']
		MapNode node = root;
		node.pass++;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i];
			if (!node.paths.containsKey(index)) {
				node.paths.put(index, new MapNode());
			}
			node = node.paths.get(index);
			node.pass++;
		}
		node.end++;
	}
	
	/**
	 * 
	 * ��һ��word��Tree��ɾ��1�Σ�֮ǰ���ܼӹ����
	 */
	
	public void delete(String word) {
		if (count(word) != 0) { // ���һ����Tree����û��
			char[] chs = word.toCharArray();
			MapNode node = root;
			node.pass--; // ��;p--
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
	 * word�������֮ǰ���������
	 */
	
	public int count(String word) {
		if (word == null) {
			return 0;
		}
		char[] chs = word.toCharArray();
		MapNode node = root;
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
	 * �м�������pre����ַ�����Ϊǰ׺��.
	 */
	
	public int countPrefix(String pre) {
		if (pre == null) {
			return 0;
		}
		char[] chs = pre.toCharArray();
		MapNode node = root;
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