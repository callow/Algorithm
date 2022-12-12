package com.algo.util.graph.model;

import java.util.ArrayList;
import java.util.List;
/**
 *  
 * 点
 */
public class Node {
	public int value;
	public int in; // 入度 = 几个边指向自己
	public int out; // 出度 = 几个边从自己出去
	public List<Node> nexts; // 从自己出发，有哪些邻居点
	public List<Edge> edges; // 从自己出发，有哪些邻居边

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
