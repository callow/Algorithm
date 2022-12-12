package com.algo.util.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  = Í¼ÖÐµÄNode
 *
 */
public class DirectedGraphNode {

	public int label;
	public List<DirectedGraphNode> neighbors;

	public DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
}
