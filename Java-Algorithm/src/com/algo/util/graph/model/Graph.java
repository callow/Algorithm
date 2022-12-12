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
	public Map<Integer, Node> nodes; 
	public Set<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
