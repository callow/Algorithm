package com.algo.util.common.treemodel;

import com.algo.util.common.model.BTNode;

public class AncestorInfo {
	public boolean findA;
	public boolean findB;
	public BTNode ans; // ���������ҵ���A��B�Ļ�۵�

	public AncestorInfo(boolean fA, boolean fB, BTNode an) {
		findA = fA;
		findB = fB;
		ans = an;
	}
}
