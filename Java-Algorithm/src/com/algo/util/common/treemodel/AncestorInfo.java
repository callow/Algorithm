package com.algo.util.common.treemodel;

import com.algo.util.common.model.BTNode;

public class AncestorInfo {
	public boolean findA;
	public boolean findB;
	public BTNode ans; // 整棵树上找到的A与B的汇聚点

	public AncestorInfo(boolean fA, boolean fB, BTNode an) {
		findA = fA;
		findB = fB;
		ans = an;
	}
}
