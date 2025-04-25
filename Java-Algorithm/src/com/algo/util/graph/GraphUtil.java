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
	 * 
	 * �������еĵ� �ұߵ�Ȩ�غ���С��
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
	
	/**
	 * ʹ��BFS���㣬�ֲ�ͼ���·�� https://leetcode.com/problems/shortest-path-to-get-all-keys/description/
	 * 
	 * �� -> ����
	 * # -> ǽ
	 * @ -> ���
	 * aԿ�� -> A��
	 * bԿ�� -> B��
	 * 
	 *  00 01 10 11
	 *  ab ab ab ab
	 * 
	 * ���㣺 һ���㣨0��0���� ����4���� ��0��0��00��,��0��0��01��,��0��0��10��,��0��0��11��
	 * ���� = ����
	 * 
	 */
	static int N = 31, M = 31, K = 6;
	static boolean[][][] visited = new boolean[N][M][1 << K];
	static int[] move = new int[] {-1,0,1,0,-1};
	static char[][] grid = new char[N][];
	static int[][] queue = new int[N * M * (1 << K)][3]; // 0 = �У� 1 = �У� 2 =  �ռ�Կ�׵�״̬ 
	static int l, r, n, m, key; // ��l�ã���r��
	public static int shortestPathAllKeys(String[] graph) {
		build(graph);
		int level = 1;
		// ��ʼ���bfs
		while(l < r) { 
			int size = r - l; // ����Size
			for (int k = 0; k < size; k++) { // ��1��
				int x = queue[l][0];
				int y = queue[l][1];
				int s = queue[l++][2];
				for (int i = 0; i < 4; i++) { // ���������ƶ�
					int nx = x + move[i];
					int ny = y + move[i + 1];
					int ns = s;
					if (nx < 0 || nx == n || ny < 0 || ny == m || grid[nx][ny] == '#') {
						// Խ������ϰ�
						continue;
					}
					if (grid[nx][ny] >= 'A' && grid[nx][ny] <= 'F' && ((ns & (1 << (grid[nx][ny] - 'A'))) == 0)) {
						// ��������û�ж�Ӧ��Կ��
						continue;
					}
					if (grid[nx][ny] >= 'a' && grid[nx][ny] <= 'f') {
						// ��ĳһ��Կ��
						ns |= (1 << (grid[nx][ny] - 'a'));
					}
					if (grid[nx][ny] >= 'a' && grid[nx][ny] <= 'f') {
						// ��ĳһ��Կ��
						ns |= (1 << (grid[nx][ny] - 'a'));
					}
					if (ns == key) {
						// ������֦
						// �����յ�ֱ�ӷ���
						// ���õȶ�����
						return level;
					}
					if (!visited[nx][ny][ns]) {
						visited[nx][ny][ns] = true;
						queue[r][0] = nx;
						queue[r][1] = ny;
						queue[r++][2] = ns;
					}
					
				}
			}
			level++;
		}
		return -1;		
	}
	static void build(String[] g) {
		l = r = key = 0;
		n = g.length;
		m = g[0].length();
		for (int i = 0; i < n; i++) {
			grid[i] = g[i].toCharArray();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '@') { // ��������queue��׼��BFS
					queue[r][0] = i;
					queue[r][1] = j;
					// 0 : 000000
					queue[r++][2] = 0; // ɶԿ�׶�û�� һ��ʼ
				}
				if (grid[i][j] >= 'a' && grid[i][j] <= 'f') {
					key |= 1 << (grid[i][j] - 'a');
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int s = 0; s <= key; s++) {
					visited[i][j][s] = false; // {false,false,false,false}
				}
			}
		}
	}
	
}
