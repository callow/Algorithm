package com.algo.util.graph;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.algo.util.common.model.DirectedGraphNode;
import com.algo.util.graph.adaptor.DijkstraAdaptor;
import com.algo.util.graph.adaptor.GraphTopologyAdaptor;
import com.algo.util.graph.adaptor.MinimumSpanningTreeAdpator;
import com.algo.util.graph.model.Edge;
import com.algo.util.graph.model.Graph;
import com.algo.util.graph.model.Node;

public class GraphUtil {

	/**
	 * BFS 图的宽度优先遍历/打印： Queue + Set <br><br>
	 * 每弹出一个元素，就把它直接邻居放入队列
	 */
	public static void bfs(Node start) {
		if (start == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		Set<Node> set = new HashSet<>();
		queue.add(start);
		set.add(start);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					set.add(next);
					queue.add(next);
				}
			}
		}
	}
	
	/**
	 * DFS 图的深度优先遍历/打印： Stack + Set <br><br>
	 * 一条路走到死为止(即，走到了环路/走过的节点)，走完了就回头看看有没有岔路再走到死
	 */
	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		Set<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) { // 遍历所有邻居
				if (!set.contains(next)) { // 不走环路
					stack.push(cur); // 原节点压回去
					stack.push(next); // 邻居节点压回去
					set.add(next);
					System.out.println(next.value);
					break; // 结束遍历
				}
			}
		}
	}
	
	/**
	 * 多种方式生成图的拓扑序,图的输入方式可能不一样，请自己做转化<br><br>
	 * 1. DFS: by frequency 和 by depth<br>
	 * 2. BFS: by in<br>
	 */
	
	public static void topologyOrder(Graph graph) {
		GraphTopologyAdaptor.topologyOrderByIn(graph);
	}
	public static void topologyOrder2(List<DirectedGraphNode> nodes) {
		GraphTopologyAdaptor.topologyOrderByInByFrequency(nodes);
		GraphTopologyAdaptor.topologyOrderByInByDepth(nodes);
		GraphTopologyAdaptor.topologyOrderByIn(nodes);
	}
	
	/**
	 * 最小生成树 => MST
	 */
	public static Set<Edge> minimumSpanningTree(Graph graph) {
		// MinimumSpanningTreeAdpator.prim(graph);
		return MinimumSpanningTreeAdpator.kruskal(graph);
	}
	
	/**
	 * 生成以Head出发到每个点的最短距离的Map
	 */
	
	public static Map<Node, Integer> getMinimuDistancesMap(Node head, int size){
		// DijkstraAdaptor.dijkstraNative(head);
		return DijkstraAdaptor.dijkstra(head, size);
		
	}
	
	/**
	 * 统计一下一个文件夹下面的文件数，文件夹不算？
	 * 解：bfs / dfs -> 找文件++
	 * 
	 */
	public static int countFiles(String path) {
		File root = new File(path);
		if (!root.isDirectory() && !root.isFile()) {
			return 0;
		}
		if (root.isFile()) {
			return 1;
		}
		// 准备一个Map和小根堆，为了最后打印一下所有文件和大小
		PriorityQueue<Long> minHeap = new PriorityQueue<>();
		Map<Long,String> filename = new HashMap<>();
		
		Stack<File> stack = new Stack<>();
		stack.add(root); // 只放文件夹，不放文件
		int files = 0;
		while (!stack.isEmpty()) {
			File folder = stack.pop();
			if (null != folder.listFiles()) {			
				for (File next : folder.listFiles()) {
					if (next.isFile()) {
						files++;
					}
					if (next.isDirectory() && next != null) {
						stack.push(next);
						minHeap.add(next.getUsableSpace());
						filename.put(next.getUsableSpace(), next.getName());
					}
				}
			}
		}
		
		// 用于打印(文件名 ： 大小) - 没用 
		for (Long e : minHeap) {
			System.out.println(filename.get(e) + ": " + e);
		}
		
		return files;
	}
}
