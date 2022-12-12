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
 * 迪瑞克斯拉(有向无负权重图)<br><br>
 * 1. 指定一个出发点<br>
 * 2. 生成出发点到所有点的最短距离Map,到不了的点距离∞
 */
public class DijkstraAdaptor {
	
	/**
	 * 加强堆的解，最优解
	 */
	public static Map<Node, Integer> dijkstra(Node head, int size) {
		SuperHeadForDijkstra nodeHeap = new SuperHeadForDijkstra(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0); // 堆上调整： O（logn）
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
	 * 普通解，没有优化
	 */
	public static Map<Node, Integer> dijkstraNative(Node from) {
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(from, 0);
		// 打过对号的点
		HashSet<Node> selectedNodes = new HashSet<>();
		Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		while (minNode != null) {
			//  原始点  ->  minNode(跳转点)   最小距离distance
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
