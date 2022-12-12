package com.algo.util.common.comparators;

import java.util.Comparator;

import com.algo.util.graph.model.Edge;

public class EdgeComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge o1, Edge o2) {
		return o1.weight - o2.weight;
	}

}
