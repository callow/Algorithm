package com.algo.util.graph.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 *  
 * 图： 很多边 + 很多点
 */
public class Graph {
	public Map<Integer, Node> nodes; // 点 
	public Set<Edge> edges; // 边
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
