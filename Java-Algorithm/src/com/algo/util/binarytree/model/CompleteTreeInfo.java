package com.algo.util.binarytree.model;

public class CompleteTreeInfo {
	public boolean isFull; // �Ƿ�����2����
	public boolean isCBT; // �Ƿ�����ȫ2����
	public int height; // ���ĸ߶�

	public CompleteTreeInfo(boolean full, boolean cbt, int h) {
		isFull = full;
		isCBT = cbt;
		height = h;
	}
}
