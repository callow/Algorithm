package com.algo.util.heap.model;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.graph.model.Node;

public class SuperHeadForDijkstra {
	private Node[] nodes; // 实际的堆结构
	// key 某一个node， value 上面堆中的位置
	private Map<Node, Integer> heapIndexMap;
	// key 某一个节点， value 从源节点出发到该节点的目前最小距离
	private Map<Node, Integer> distanceMap;
	private int size; // 堆上有多少个点

	public SuperHeadForDijkstra(int size) {
		nodes = new Node[size];
		heapIndexMap = new HashMap<>();
		distanceMap = new HashMap<>();
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
	// 判断要不要更新，如果需要的话，就更新
	public void addOrUpdateOrIgnore(Node node, int distance) {
		// update
		if (inHeap(node)) { 
			distanceMap.put(node, Math.min(distanceMap.get(node), distance));
			heapInsert(heapIndexMap.get(node)); // 往上做堆得调整
		}
		
		// add
		if (!isEntered(node)) { 
			nodes[size] = node;
			heapIndexMap.put(node, size);
			distanceMap.put(node, distance);
			heapInsert(size++); // 往上做堆得调整
		}
		
		// ignore
	}

	public NodeRecord pop() {
		NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
		swap(0, size - 1); // 手尾交换
		heapIndexMap.put(nodes[size - 1], -1); // 最后一个节点弹出时不要删，只是改成-1
		distanceMap.remove(nodes[size - 1]);
		// free C++同学还要把原本堆顶节点析构，对java同学不必
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


