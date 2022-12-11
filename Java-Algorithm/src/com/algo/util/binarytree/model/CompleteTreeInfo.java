package com.algo.util.binarytree.model;

public class CompleteTreeInfo {
	public boolean isFull; // 是否是满2叉树
	public boolean isCBT; // 是否是完全2叉树
	public int height; // 树的高度

	public CompleteTreeInfo(boolean full, boolean cbt, int h) {
		isFull = full;
		isCBT = cbt;
		height = h;
	}
}
