package com.algo.util.graph.adaptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.algo.util.common.comparators.EdgeComparator;
import com.algo.util.disjointset.model.UnionFind;
import com.algo.util.graph.model.Edge;
import com.algo.util.graph.model.Graph;
import com.algo.util.graph.model.Node;

/**
 * 
 * ��С������������ͼ��<br><br>
 * ���е㶼Ҫ��ͨ����Ȩ���ۼӺ͵���Сֵ
 */
public class MinimumSpanningTreeAdpator {

	/**
	 * kruskal�㷨�� <br><br>
	 * ̰�ģ� Ȩ�ش�С�������ο�����ǰ�߲����γɻ����������������������ļ��Ͼ���MST<br>
	 * 1. һ��ʼÿ���㶼���Լ��ļ��ϣ����鼯��<br>
	 * 2. �ж��Ƿ��л��������鼯�Ƿ�2��node��һ��������
	 * 3. �ʺϱ߱Ƚ��ٵ�������Ƚ϶�Ļ��Ῠ
	 */
	public static Set<Edge> kruskal(Graph graph) {
		UnionFind<Node> disjointSet = new UnionFind<Node>(new ArrayList<>(graph.nodes.values()));
		// ��С�ıߵ���ıߣ����ε�����С���ѣ�
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		for (Edge edge : graph.edges) { // M ����
			priorityQueue.add(edge);  // O(logM)
		}
		Set<Edge> result = new HashSet<>();
		while (!priorityQueue.isEmpty()) { // M ����
			Edge edge = priorityQueue.poll(); // O(logM)
			if (!disjointSet.isSameSet(edge.from, edge.to)) { // O(1)
				result.add(edge);
				disjointSet.union(edge.from, edge.to);
			}
		}
		return result;
	}
	
	/**
	 * Prim�㷨�� <br><br>
	 * 1.��һ���������ĳ������뵽��ѡȡ�㼯�У����������еı�<br>
	 * 2. ѡ��Ȩ����С�Ǹ��ߣ������Ƿ���γɻ�
	 * 3. ����ᣬ�򿼲��С�ıߣ�������ᣬҪ������ߣ����ñ�ָ��ĵ���뵽�㼯��
	 * 4. �����е㶼��ѡȡ��MST����
	 *
	 */
	public static Set<Edge> prim(Graph graph) {
		// �����ı߽���С����
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

		// ��Щ�㱻����������
		Set<Node> nodeSet = new HashSet<>();
		
		Set<Edge> result = new HashSet<>(); // ������ѡ�ĵı���result��

		for (Node node : graph.nodes.values()) { // �������һ����
			// node �ǿ�ʼ��
			if (!nodeSet.contains(node)) {
				nodeSet.add(node);
				for (Edge edge : node.edges) { // ��һ���㣬�������������ı�
					priorityQueue.add(edge);
				}
				while (!priorityQueue.isEmpty()) {
					Edge edge = priorityQueue.poll(); // ���������ı��У���С�ı�
					Node toNode = edge.to; // ���ܵ�һ���µĵ�
					if (!nodeSet.contains(toNode)) { // �����е�ʱ�򣬾����µĵ�
						nodeSet.add(toNode);
						result.add(edge);
						for (Edge nextEdge : toNode.edges) {
							priorityQueue.add(nextEdge);
						}
					}
				}
			}
			// break;
		}
		return result;
	}
	
}
