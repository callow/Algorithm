package com.algo.util.heap.model;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.graph.model.Node;

public class SuperHeadForDijkstra {
	private Node[] nodes; // ʵ�ʵĶѽṹ
	// key ĳһ��node�� value ������е�λ��
	private Map<Node, Integer> heapIndexMap;
	// key ĳһ���ڵ㣬 value ��Դ�ڵ�������ýڵ��Ŀǰ��С����
	private Map<Node, Integer> distanceMap;
	private int size; // �����ж��ٸ���

	public SuperHeadForDijkstra(int size) {
		nodes = new Node[size];
		heapIndexMap = new HashMap<>();
		distanceMap = new HashMap<>();
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// ��һ�����node�����ڷ�����һ����Դ�ڵ��������node�ľ���Ϊdistance
	// �ж�Ҫ��Ҫ���£������Ҫ�Ļ����͸���
	public void addOrUpdateOrIgnore(Node node, int distance) {
		// update
		if (inHeap(node)) { 
			distanceMap.put(node, Math.min(distanceMap.get(node), distance));
			heapInsert(heapIndexMap.get(node)); // �������ѵõ���
		}
		
		// add
		if (!isEntered(node)) { 
			nodes[size] = node;
			heapIndexMap.put(node, size);
			distanceMap.put(node, distance);
			heapInsert(size++); // �������ѵõ���
		}
		
		// ignore
	}

	public NodeRecord pop() {
		NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
		swap(0, size - 1); // ��β����
		heapIndexMap.put(nodes[size - 1], -1); // ���һ���ڵ㵯��ʱ��Ҫɾ��ֻ�Ǹĳ�-1
		distanceMap.remove(nodes[size - 1]);
		// free C++ͬѧ��Ҫ��ԭ���Ѷ��ڵ���������javaͬѧ����
		nodes[size - 1] = null;
		heapify(0, --size);
		return nodeRecord;
	}

	private void heapInsert(int index) {
		while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
			swap(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	private void heapify(int index, int size) {
		int left = index * 2 + 1;
		while (left < size) {
			int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
					? left + 1
					: left;
			smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
			if (smallest == index) {
				break;
			}
			swap(smallest, index);
			index = smallest;
			left = index * 2 + 1;
		}
	}

	private boolean isEntered(Node node) {
		return heapIndexMap.containsKey(node);
	}

	private boolean inHeap(Node node) {
		return isEntered(node) && heapIndexMap.get(node) != -1;
	}

	private void swap(int index1, int index2) {
		heapIndexMap.put(nodes[index1], index2);
		heapIndexMap.put(nodes[index2], index1);
		Node tmp = nodes[index1];
		nodes[index1] = nodes[index2];
		nodes[index2] = tmp;
	}
	
	public static class NodeRecord {
		public Node node;
		public int distance;

		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}
}


