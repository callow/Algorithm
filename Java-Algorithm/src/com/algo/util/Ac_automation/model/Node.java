package com.algo.util.Ac_automation.model;

// ǰ׺���Ľڵ�
public class Node {
	// ���һ��node��endΪ�գ����ǽ�β
	// ���end��Ϊ�գ���ʾ�������ĳ���ַ����Ľ�β��end��ֵ��������ַ���
	public String end;
	// ֻ���������end������Ϊ�յ�ʱ��endUse��������
	// ��ʾ������ַ���֮ǰ��û�м������
	public boolean endUse;
	public Node fail;
	public Node[] nexts;

	public Node() {
		endUse = false;
		end = null;
		fail = null;
		nexts = new Node[26];
	}
}
