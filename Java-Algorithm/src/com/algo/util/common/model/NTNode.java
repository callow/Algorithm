package com.algo.util.common.model;

import java.util.List;
/**
 * 
 * N ²æÊ÷½Úµã
 */
public class NTNode {
	public int val;
	public List<NTNode> children;

	public NTNode() {
	}

	public NTNode(int _val) {
		val = _val;
	}

	public NTNode(int _val, List<NTNode> _children) {
		val = _val;
		children = _children;
	}
};
