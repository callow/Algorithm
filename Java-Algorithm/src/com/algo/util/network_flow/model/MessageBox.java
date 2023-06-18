package com.algo.util.network_flow.model;

import java.util.HashMap;

import com.algo.util.common.model.Node;

@SuppressWarnings("rawtypes")
public class MessageBox {
	
	private HashMap<Integer, Node> headMap;
	private HashMap<Integer, Node> tailMap;
	private int waitPoint;

	public MessageBox() {
		headMap = new HashMap<Integer, Node>();
		tailMap = new HashMap<Integer, Node>();
		waitPoint = 1;
	}

	// 消息的编号，info消息的内容, 消息一定从1开始
	public void receive(int num, String info) {
		if (num < 1) {
			return;
		}
		Node cur = new Node(info);
		// num~num
		headMap.put(num, cur);
		tailMap.put(num, cur);
		// 建立了num~num这个连续区间的头和尾
		// 查询有没有某个连续区间以num-1结尾
		if (tailMap.containsKey(num - 1)) {
			tailMap.get(num - 1).next = cur;
			tailMap.remove(num - 1);
			headMap.remove(num);
		}
		// 查询有没有某个连续区间以num+1开头的
		if (headMap.containsKey(num + 1)) {
			cur.next = headMap.get(num + 1);
			tailMap.remove(num);
			headMap.remove(num + 1);
		}
		if (num == waitPoint) {
			print();
		}
	}

	private void print() {
		Node node = headMap.get(waitPoint);
		headMap.remove(waitPoint);
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
			waitPoint++;
		}
		tailMap.remove(waitPoint-1);
		System.out.println();
	}

}