package com.algo.util.graph.adaptor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

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
	
	/**
	 * ��ǿ�ѵĽ⣬���Ž�
	 */
	public static Map<Node, Integer> dijkstra(Node head, int size) {
		SuperHeadForDijkstra nodeHeap = new SuperHeadForDijkstra(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0); // ���ϵ����� O��logn��
		Map<Node, Integer> result = new HashMap<>();
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
	
	/**
	 * ��ͨ�⣬û���Ż�
	 */
	public static Map<Node, Integer> dijkstraNative(Node from) {
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(from, 0);
		// ����Ժŵĵ�
		HashSet<Node> selectedNodes = new HashSet<>();
		Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		while (minNode != null) {
			//  ԭʼ��  ->  minNode(��ת��)   ��С����distance
			int distance = distanceMap.get(minNode);
			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;
				if (!distanceMap.containsKey(toNode)) {
					distanceMap.put(toNode, distance + edge.weight);
				} else { // toNode 
					distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
				}
			}
			selectedNodes.add(minNode);
			minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		}
		return distanceMap;
	}

	public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
		Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
			Node node = entry.getKey();
			int distance = entry.getValue();
			if (!touchedNodes.contains(node) && distance < minDistance) {
				minNode = node;
				minDistance = distance;
			}
		}
		return minNode;
	}
	
}
