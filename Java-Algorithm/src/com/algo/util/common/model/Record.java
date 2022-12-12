package com.algo.util.common.model;

public class Record {

	public DirectedGraphNode node;
	public long nodes;
	public int deep;

	public Record(DirectedGraphNode n, long o) {
		node = n;
		nodes = o;
	}
	
	public Record(DirectedGraphNode n, int i) {
		node = n;
		deep = i;
	}
	
}
