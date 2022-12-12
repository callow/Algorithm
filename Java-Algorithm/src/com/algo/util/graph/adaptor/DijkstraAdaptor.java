package com.algo.util.graph.adaptor;

import java.util.HashMap;
import java.util.Map;

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
	
	public static Map<Node, Integer> dijkstra(Node head, int size) {
		SuperHeadForDijkstra nodeHeap = new SuperHeadForDijkstra(size);
		nodeHeap.addOrUpdateOrIgnore(head, 0); // 堆上调整： O（logn）
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
