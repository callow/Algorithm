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
	 * BFS ͼ�Ŀ�����ȱ���/��ӡ�� Queue + Set <br><br>
	 * ÿ����һ��Ԫ�أ��Ͱ���ֱ���ھӷ������
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
	 * DFS ͼ��������ȱ���/��ӡ�� Stack + Set <br><br>
	 * һ��·�ߵ���Ϊֹ(�����ߵ��˻�·/�߹��Ľڵ�)�������˾ͻ�ͷ������û�в�·���ߵ���
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
			for (Node next : cur.nexts) { // ���������ھ�
				if (!set.contains(next)) { // ���߻�·
					stack.push(cur); // ԭ�ڵ�ѹ��ȥ
					stack.push(next); // �ھӽڵ�ѹ��ȥ
					set.add(next);
					System.out.println(next.value);
					break; // ��������
				}
			}
		}
	}
	
	/**
	 * ���ַ�ʽ����ͼ��������,ͼ�����뷽ʽ���ܲ�һ�������Լ���ת��<br><br>
	 * 1. DFS: by frequency �� by depth<br>
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
	 * ��С������ => MST
	 */
	public static Set<Edge> minimumSpanningTree(Graph graph) {
		// MinimumSpanningTreeAdpator.prim(graph);
		return MinimumSpanningTreeAdpator.kruskal(graph);
	}
	
	/**
	 * ������Head������ÿ�������̾����Map
	 */
	
	public static Map<Node, Integer> getMinimuDistancesMap(Node head, int size){
		// DijkstraAdaptor.dijkstraNative(head);
		return DijkstraAdaptor.dijkstra(head, size);
		
	}
	
	/**
	 * ͳ��һ��һ���ļ���������ļ������ļ��в��㣿
	 * �⣺bfs / dfs -> ���ļ�++
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
		// ׼��һ��Map��С���ѣ�Ϊ������ӡһ�������ļ��ʹ�С
		PriorityQueue<Long> minHeap = new PriorityQueue<>();
		Map<Long,String> filename = new HashMap<>();
		
		Stack<File> stack = new Stack<>();
		stack.add(root); // ֻ���ļ��У������ļ�
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
		
		// ���ڴ�ӡ(�ļ��� �� ��С) - û�� 
		for (Long e : minHeap) {
			System.out.println(filename.get(e) + ": " + e);
		}
		
		return files;
	}
}
