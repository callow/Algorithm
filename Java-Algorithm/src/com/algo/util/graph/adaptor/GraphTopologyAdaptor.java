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
 * ͼ��������(�����޻�ͼ)�� <br><br>
 * �����Ⱥ�˳�򣬺͸��������������鶼�����˳�� => ����ʱ���ѭ������,������Ψһ<br>
 */
public class GraphTopologyAdaptor {

	/**
	 * ������ȣ���BFS��ʽ���������� <br><br>
	 * 1. ��� ����ǰ�棬Ȼ������<br>
	 * 2. �������Ϊ 0�ţ�Ȼ�����������
	 */
	public static List<Node> topologyOrderByIn(Graph graph) {
		Map<Node, Integer> inMap = new HashMap<>();		// key ĳ���ڵ�   value ʣ������
		Queue<Node> zeroInQueue = new LinkedList<>(); // ֻ��ʣ�����Ϊ0�ĵ㣬�Ž����������
		
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
	 * ���ݵ�Σ�Frequency������DFS�ķ�ʽ���������� <br><br>
	 * https://www.lintcode.com/problem/topological-sorting <br>
	 * 1. ��x��ĺ���DFS������ = 100�� y��ĺ���DFS������ = 80<br>
	 * 2. �� x�������� <= y�������� x,y,....
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

	//  key : ĳһ����ĵ�Σ�֮ǰ����ˣ� value : ����Ƕ���
	private static Record collectFrequency(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		// cur�ĵ��֮ǰû����� ���仯����
		long nodes = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			nodes += collectFrequency(next, order).nodes;
		}
		Record ans = new Record(cur, nodes + 1);
		order.put(cur, ans);
		return ans;
	}
	
	/**
	 * ������ȣ�Depth������DFS�ķ�ʽ���������� <br><br>
	 * https://www.lintcode.com/problem/topological-sorting <br>
	 * 1. ��x���DFS�ߵ������� >= y���DFS�ߵ�������<br>
	 * 2. �� x�������� <= y�������� x,y,....
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
	 * ������ȣ���BFS��ʽ���������� <br><br>
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
