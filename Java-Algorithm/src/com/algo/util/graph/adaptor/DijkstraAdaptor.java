package com.algo.util.graph.adaptor;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.graph.model.Edge;
import com.algo.util.graph.model.Node;
import com.algo.util.heap.model.SuperHeadForDijkstra;
import com.algo.util.heap.model.SuperHeadForDijkstra.NodeRecord;

/**
 * 
 * �����˹��(�����޸�Ȩ��ͼ)<br><br>
 * 1. ָ��һ��������<br>
 * 2. ���ɳ����㵽���е����̾���Map,�����˵ĵ�����
 */
public class DijkstraAdaptor {
	
	public static Map<Node, Integer> dijkstra(Node head, int size) {
		SuperHeadForDijkstra nodeHeap = new SuperHeadForDijkstra(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0); // ���ϵ����� O��logn��
		HashMap<Node, Integer> result = new HashMap<>();
		while (!nodeHeap.isEmpty()) {
			NodeRecord record = nodeHeap.pop();
			Node cur = record.node;
			int distance = record.distance;
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
			}
			result.put(cur, distance);
		}
		return result;
	}
}
