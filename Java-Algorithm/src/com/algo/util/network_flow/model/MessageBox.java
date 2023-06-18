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

	// ��Ϣ�ı�ţ�info��Ϣ������, ��Ϣһ����1��ʼ
	public void receive(int num, String info) {
		if (num < 1) {
			return;
		}
		Node cur = new Node(info);
		// num~num
		headMap.put(num, cur);
		tailMap.put(num, cur);
		// ������num~num������������ͷ��β
		// ��ѯ��û��ĳ������������num-1��β
		if (tailMap.containsKey(num - 1)) {
			tailMap.get(num - 1).next = cur;
			tailMap.remove(num - 1);
			headMap.remove(num);
		}
		// ��ѯ��û��ĳ������������num+1��ͷ��
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