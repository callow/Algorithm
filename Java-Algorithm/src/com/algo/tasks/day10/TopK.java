package com.algo.tasks.day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
/**
 * ��Ƶ��Top K���⣺ https://www.lintcode.com/problem/top-k-frequent-words-ii/
 *
 */
public class TopK {
	private Node[] heap;
	private int heapSize;
	// ��Ƶ��   key  abc   value  (abc,7)
	private HashMap<String, Node> strNodeMap;
	private HashMap<Node, Integer> nodeIndexMap;
	private NodeHeapComp comp;
	private TreeSet<Node> treeSet;
	
	public TopK(int K) {
		heap = new Node[K];
		heapSize = 0;
		strNodeMap = new HashMap<String, Node>();
		nodeIndexMap = new HashMap<Node, Integer>();
		comp = new NodeHeapComp();
		treeSet = new TreeSet<>(new NodeTreeSetComp());
	}

	public static class Node {
		public String str;
		public int times;

		public Node(String s, int t) {
			str = s;
			times = t;
		}
	}

	public static class NodeHeapComp implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.times != o2.times ? (o1.times - o2.times) : (o2.str.compareTo(o1.str));
		}

	}

	public static class NodeTreeSetComp implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.times != o2.times ? (o2.times - o1.times) : (o1.str.compareTo(o2.str));
		}

	}

	public void add(String str) {
		if (heap.length == 0) {
			return;
		}
		// str   �ҵ���Ӧ�ڵ�  curNode
		Node curNode = null;
		// ��Ӧ�ڵ�  curNode  �ڶ��ϵ�λ��
		int preIndex = -1;
		if (!strNodeMap.containsKey(str)) {
			curNode = new Node(str, 1);
			strNodeMap.put(str, curNode);
			nodeIndexMap.put(curNode, -1);
		} else {
			curNode = strNodeMap.get(str);
			// Ҫ��time++֮ǰ������treeSet��ɾ��
			// ԭ������Ϊһ��times++��curNode��treeSet�е������ʧЧ��
			// ����ʧЧ�ᵼ������treeSet��������
			if (treeSet.contains(curNode)) {
				treeSet.remove(curNode);
			}
			curNode.times++;
			preIndex = nodeIndexMap.get(curNode);
		}
		if (preIndex == -1) {
			if (heapSize == heap.length) {
				if (comp.compare(heap[0], curNode) < 0) {
					treeSet.remove(heap[0]);
					treeSet.add(curNode);
					nodeIndexMap.put(heap[0], -1);
					nodeIndexMap.put(curNode, 0);
					heap[0] = curNode;
					heapify(0, heapSize);
				}
			} else {
				treeSet.add(curNode);
				nodeIndexMap.put(curNode, heapSize);
				heap[heapSize] = curNode;
				heapInsert(heapSize++);
			}
		} else {
			treeSet.add(curNode);
			heapify(preIndex, heapSize);
		}
	}

	public List<String> topk() {
		ArrayList<String> ans = new ArrayList<>();
		for (Node node : treeSet) {
			ans.add(node.str);
		}
		return ans;
	}

	private void heapInsert(int index) {
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (comp.compare(heap[index], heap[parent]) < 0) {
				swap(parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}

	private void heapify(int index, int heapSize) {
		int l = index * 2 + 1;
		int r = index * 2 + 2;
		int smallest = index;
		while (l < heapSize) {
			if (comp.compare(heap[l], heap[index]) < 0) {
				smallest = l;
			}
			if (r < heapSize && comp.compare(heap[r], heap[smallest]) < 0) {
				smallest = r;
			}
			if (smallest != index) {
				swap(smallest, index);
			} else {
				break;
			}
			index = smallest;
			l = index * 2 + 1;
			r = index * 2 + 2;
		}
	}

	private void swap(int index1, int index2) {
		nodeIndexMap.put(heap[index1], index2);
		nodeIndexMap.put(heap[index2], index1);
		Node tmp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = tmp;
	}
}
