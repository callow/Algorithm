package com.algo.util.trie.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ǰ׺���ĵ����ڵ�
 *
 */
public class MapNode {

	public int pass; // �������˶��ٴ�
	public int end; // ���ҽ�β��Ԫ���ж��ٸ�
	public Map<Integer, MapNode> paths; // ���µ�·�ж���������Ȼ����ʹ������ Node[] paths

	public MapNode() {
		pass = 0;
		end = 0;
		paths = new HashMap<>();
	}
}
