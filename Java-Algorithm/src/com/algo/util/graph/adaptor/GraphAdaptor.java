package com.algo.util.graph.adaptor;

import com.algo.util.graph.model.Edge;
import com.algo.util.graph.model.Graph;
import com.algo.util.graph.model.Node;

/**
 * 
 * �����ָ�����ͬ��ͼ�Ľṹ ������Լ���ͼ
 *
 */
public class GraphAdaptor {

	/**
	 * N*3 �ľ���: [weight, from�ڵ������ֵ��to�ڵ������ֵ]<br><br>
	 * [ 5 , 0 , 7]
	 * [ 3 , 0,  1]
	 */
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			 // �õ�ÿһ���ߣ� matrix[i] 
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge newEdge = new Edge(weight, fromNode, toNode);
			fromNode.nexts.add(toNode);
			fromNode.out++;
			toNode.in++;
			fromNode.edges.add(newEdge);
			graph.edges.add(newEdge);
		}
		return graph;
	}
}
