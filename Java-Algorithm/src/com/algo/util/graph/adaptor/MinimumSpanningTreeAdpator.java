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
 * 最小生成树（无向图）<br><br>
 * 所有点都要连通，边权重累加和的最小值
 */
public class MinimumSpanningTreeAdpator {

	/**
	 * kruskal算法： <br><br>
	 * 贪心： 权重从小到大依次看，当前边不会形成环则保留，否则舍弃，跑完后的集合就是MST<br>
	 * 1. 一开始每个点都是自己的集合（并查集）<br>
	 * 2. 判断是否有环，即并查集是否2个node在一个集合中
	 * 3. 适合边比较少的情况，比较多的话会卡
	 */
	public static Set<Edge> kruskal(Graph graph) {
		UnionFind<Node> disjointSet = new UnionFind<Node>(new ArrayList<>(graph.nodes.values()));
		// 从小的边到大的边，依次弹出，小根堆！
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		for (Edge edge : graph.edges) { // M 条边
			priorityQueue.add(edge);  // O(logM)
		}
		Set<Edge> result = new HashSet<>();
		while (!priorityQueue.isEmpty()) { // M 条边
			Edge edge = priorityQueue.poll(); // O(logM)
			if (!disjointSet.isSameSet(edge.from, edge.to)) { // O(1)
				result.add(edge);
				disjointSet.union(edge.from, edge.to);
			}
		}
		return result;
	}
	
	/**
	 * Prim算法： <br><br>
	 * 1.任一点出发，当某个点加入到被选取点集中，解锁它所有的边<br>
	 * 2. 选择权重最小那个边，看看是否回形成环
	 * 3. 如果会，则考察次小的边，如果不会，要了这个边，将该边指向的点加入到点集中
	 * 4. 当所有点都被选取，MST成型
	 *
	 */
	public static Set<Edge> prim(Graph graph) {
		// 解锁的边进入小根堆
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

		// 哪些点被解锁出来了
		Set<Node> nodeSet = new HashSet<>();
		
		Set<Edge> result = new HashSet<>(); // 依次挑选的的边在result里

		for (Node node : graph.nodes.values()) { // 随便挑了一个点
			// node 是开始点
			if (!nodeSet.contains(node)) {
				nodeSet.add(node);
				for (Edge edge : node.edges) { // 由一个点，解锁所有相连的边
					priorityQueue.add(edge);
				}
				while (!priorityQueue.isEmpty()) {
					Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
					Node toNode = edge.to; // 可能的一个新的点
					if (!nodeSet.contains(toNode)) { // 不含有的时候，就是新的点
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
