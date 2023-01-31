package com.algo.util.Ac_automation.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ACAutomation {
	
	private Node root;

	public ACAutomation() {
		root = new Node();
	}

	/**
	 * 
	 * ���ַ�����ǰ׺��
	 */
	public void insertSensitiveWord(String s) {
		char[] str = s.toCharArray();
		Node cur = root;
		int index = 0;
		for (int i = 0; i < str.length; i++) {
			index = str[i] - 'a';
			if (cur.nexts[index] == null) {
				cur.nexts[index] = new Node();
			}
			cur = cur.nexts[index];
		}
		cur.end = s;
	}

	/**
	 * 
	 * �����е�failָ��
	 */
	public void build() {
		Queue<Node> queue = new LinkedList<>(); // ��queue��������ȱ���
		queue.add(root);
		Node cur = null;
		Node cfail = null;
		while (!queue.isEmpty()) {
			// ĳ�����ף�cur
			cur = queue.poll();
			for (int i = 0; i < 26; i++) { // ���е�·
				// cur -> ����  i�Ŷ��ӣ������i�Ŷ��ӵ�failָ�����úã�
				if (cur.nexts[i] != null) { // ��������i�Ŷ���
					cur.nexts[i].fail = root;
					cfail = cur.fail;
					while (cfail != null) {
						if (cfail.nexts[i] != null) {
							cur.nexts[i].fail = cfail.nexts[i];
							break;
						}
						cfail = cfail.fail;
					}
					queue.add(cur.nexts[i]);
				}
			}
		}
	}

	// �����£�content
	public List<String> containWords(String content) {
		char[] str = content.toCharArray();
		Node cur = root;
		Node follow = null;
		int index = 0;
		List<String> ans = new ArrayList<>();
		for (int i = 0; i < str.length; i++) {
			index = str[i] - 'a'; // ·
			// �����ǰ�ַ�������·��û�������������fail������������·��
			while (cur.nexts[index] == null && cur != root) {
				cur = cur.fail;
			}
			// 1) ����������·�����ǿ��Լ���ƥ���
			// 2) ���������Ľڵ㣬����ǰ׺���ĸ��ڵ�
			cur = cur.nexts[index] != null ? cur.nexts[index] : root;
			follow = cur;
			while (follow != root) {
				if (follow.endUse) {
					break;
				}
				// ��ͬ����������һ��֮���޸�
				if (follow.end != null) {
					ans.add(follow.end);
					follow.endUse = true;
				}
				// ��ͬ����������һ��֮���޸�
				follow = follow.fail;
			}
		}
		return ans;
	}

}
