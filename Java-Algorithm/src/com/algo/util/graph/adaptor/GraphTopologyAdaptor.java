package com.algo.util.graph.adaptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.algo.util.common.comparators.RecordDepthComparator;
import com.algo.util.common.comparators.RecordFrequencyComparator;
import com.algo.util.common.model.DirectedGraphNode;
import com.algo.util.common.model.Record;
import com.algo.util.graph.model.Graph;
import com.algo.util.graph.model.Node;


/**
 * 图的拓扑序(有向无环图)： <br><br>
 * 根据先后顺序，和各种依赖，把事情都做完的顺序 => 编译时解决循环依赖,拓扑序不唯一<br>
 */
public class GraphTopologyAdaptor {

	/**
	 * 根据入度，用BFS方式生成拓扑序 <br><br>
	 * 1. 入度 放最前面，然后消除<br>
	 * 2. 继续入度为 0排，然后继续消除。
	 */
	public static List<Node> topologyOrderByIn(Graph graph) {
		Map<Node, Integer> inMap = new HashMap<>();		// key 某个节点   value 剩余的入度
		Queue<Node> zeroInQueue = new LinkedList<>(); // 只有剩余入度为0的点，才进入这个队列
		
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		
		List<Node> result = new ArrayList<>();
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
	/**
	 * 根据点次（Frequency），用DFS的方式生成拓扑序 <br><br>
	 * https://www.lintcode.com/problem/topological-sorting <br>
	 * 1. 若x点的后面DFS点数量 = 100， y点的后面DFS点数量 = 80<br>
	 * 2. 则 x的拓扑序 <= y的拓扑序： x,y,....
	 */
	
	public static List<DirectedGraphNode> topologyOrderByInByFrequency(List<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Record> order = new HashMap<>();
		for (DirectedGraphNode cur : graph) {
			collectFrequency(cur, order);
		}
		List<Record> recordArr = new ArrayList<>();
		for (Record r : order.values()) {
			recordArr.add(r);
		}
		recordArr.sort(new RecordFrequencyComparator());
		List<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
		for (Record r : recordArr) {
			ans.add(r.node);
		}
		return ans;
	}

	//  key : 某一个点的点次，之前算过了！ value : 点次是多少
	private static Record collectFrequency(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		// cur的点次之前没算过！ 记忆化搜索
		long nodes = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			nodes += collectFrequency(next, order).nodes;
		}
		Record ans = new Record(cur, nodes + 1);
		order.put(cur, ans);
		return ans;
	}
	
	/**
	 * 根据深度（Depth），用DFS的方式生成拓扑序 <br><br>
	 * https://www.lintcode.com/problem/topological-sorting <br>
	 * 1. 若x点的DFS走的最大深度 >= y点的DFS走的最大深度<br>
	 * 2. 则 x的拓扑序 <= y的拓扑序： x,y,....
	 */
	public static List<DirectedGraphNode> topologyOrderByInByDepth(List<DirectedGraphNode> graph) {
		Map<DirectedGraphNode, Record> order = new HashMap<>();
		for (DirectedGraphNode cur : graph) {
			calculateDepth(cur, order);
		}
		ArrayList<Record> recordArr = new ArrayList<>();
		for (Record r : order.values()) {
			recordArr.add(r);
		}
		recordArr.sort(new RecordDepthComparator());
		List<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
		for (Record r : recordArr) {
			ans.add(r.node);
		}
		return ans;
	}

	public static Record calculateDepth(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		int follow = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			follow = Math.max(follow, calculateDepth(next, order).deep);
		}
		Record ans = new Record(cur, follow + 1);
		order.put(cur, ans);
		return ans;
	}
	
	/**
	 * 根据入度，用BFS方式生成拓扑序 <br><br>
	 */
	public static ArrayList<DirectedGraphNode> topologyOrderByIn(List<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
		for (DirectedGraphNode cur : graph) {
			indegreeMap.put(cur, 0);
		}
		for (DirectedGraphNode cur : graph) {
			for (DirectedGraphNode next : cur.neighbors) {
				indegreeMap.put(next, indegreeMap.get(next) + 1);
			}
		}
		Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
		for (DirectedGraphNode cur : indegreeMap.keySet()) {
			if (indegreeMap.get(cur) == 0) {
				zeroQueue.add(cur);
			}
		}
		ArrayList<DirectedGraphNode> ans = new ArrayList<>();
		while (!zeroQueue.isEmpty()) {
			DirectedGraphNode cur = zeroQueue.poll();
			ans.add(cur);
			for (DirectedGraphNode next : cur.neighbors) {
				indegreeMap.put(next, indegreeMap.get(next) - 1);
				if (indegreeMap.get(next) == 0) {
					zeroQueue.offer(next);
				}
			}
		}
		return ans;
	}
}
