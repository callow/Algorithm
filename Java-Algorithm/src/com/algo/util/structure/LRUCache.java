package com.algo.util.structure;

import java.util.HashMap;

// ʹ��˫������ ͷ= ����/��Ԫ�أ� β = ����Ԫ��
// �������� : https://leetcode.cn/problems/lru-cache/
class LRUCache {

	class DoubleNode {
		public int key;
		public int val;
		public DoubleNode last;
		public DoubleNode next;

		public DoubleNode(int k, int v) {
			key = k;
			val = v;
		}
	}

	// ˫������
	class DoubleList {
		private DoubleNode head;
		private DoubleNode tail;

		public DoubleList() {
			head = null;
			tail = null;
		}

		/**
		 * 
		 * ��Ԫ��׷�ӵ�β����
		 */
		public void addNode(DoubleNode newNode) {
			if (newNode == null) {
				return;
			}
			if (head == null) { // ��һ��
				head = newNode;
				tail = newNode;
			} else {
				tail.next = newNode;
				newNode.last = tail;
				tail = newNode;
			}
		}

		/**
		 * 
		 * ���ڵ���м��ƶ���β����
		 */
		public void moveNodeToTail(DoubleNode node) {
			if (tail == node) {
				return;
			}
			if (head == node) {
				head = node.next;
				head.last = null;
			} else {
				node.last.next = node.next; // 1 -> 2 -> 3 => 1 -> 3
				node.next.last = node.last; // 1 <- 2 <- 3 => 1 <- 3
			}
			node.last = tail;
			node.next = null;
			tail.next = node;
			tail = node;
		}

		/**
		 * 
		 * LRU�ռ䲻���ˣ��Ƴ�ͷ = ��ɵĽڵ�
		 */
		public DoubleNode removeHead() {
			if (head == null) {
				return null;
			}
			DoubleNode ans = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = ans.next;
				ans.next = null;
				head.last = null;
			}
			return ans;
		}

	}

	// key : node�ڴ��ַ
	private HashMap<Integer, DoubleNode> keyNodeMap;

	private DoubleList nodeList;

	private final int capacity;

	public LRUCache(int cap) {
		keyNodeMap = new HashMap<>();
		nodeList = new DoubleList();
		capacity = cap;
	}

	public int get(int key) {
		if (keyNodeMap.containsKey(key)) {
			DoubleNode ans = keyNodeMap.get(key);
			nodeList.moveNodeToTail(ans);
			return ans.val;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (keyNodeMap.containsKey(key)) {
			DoubleNode node = keyNodeMap.get(key);
			node.val = value;
			nodeList.moveNodeToTail(node);
		} else {
			if (keyNodeMap.size() == capacity) {
				keyNodeMap.remove(nodeList.removeHead().key);
			}
			DoubleNode newNode = new DoubleNode(key, value);
			keyNodeMap.put(key, newNode);
			nodeList.addNode(newNode);
		}
	}

}
