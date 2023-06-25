package com.algo.tasks.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * ��¥���������⣺ https://leetcode.com/problems/the-skyline-problem/
 *
 * �߶ȵı仯������ϸ������ߵĽ�β�����µı仯ֵ��βδ֪?��
 *  1: (3,?,3)
 *  2: (3,5,3)
 *     (3,?,3)
 *  3: (3,5,3)
 *     (3,6,3)
 * 	   (3,?,3)
 * 	...
 * 
 * ʹ��TreeMap : key �߶ȣ� value : ����
 */
public class SkylineProblem {
	
	public static class Node {
		public int x; // ��x��ʲôλ��
		public boolean isAdd; // ��+�߶Ȼ���-�߶�
		public int h; // �߶��Ƕ���

		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}

	public static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.x - o2.x; // ��1ά���� x���λ������
		}
	}
	
	public static List<List<Integer>> getSkyline(int[][] matrix) {
		Node[] nodes = new Node[matrix.length * 2];
		for (int i = 0; i < matrix.length; i++) { // ����ÿ����¥
			nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]); // ��һ��λ��+�˸߶�
			nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]); // ��һ��λ��-�˸߶�
		}
		Arrays.sort(nodes, new NodeComparator()); // ����
		
		// key  �߶�  value ����
		TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
		// key  xλ��  value ���߶� - ÿ��λ�õ����߶�
		TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isAdd) {
				if (!mapHeightTimes.containsKey(nodes[i].h)) {
					mapHeightTimes.put(nodes[i].h, 1);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
				}
			} else {
				if (mapHeightTimes.get(nodes[i].h) == 1) { // �����0���ˣ�������¼ɾ������ΪҪ��Map���key�� ���������
					mapHeightTimes.remove(nodes[i].h);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
				}
			}
			if (mapHeightTimes.isEmpty()) {
				mapXHeight.put(nodes[i].x, 0);
			} else {
				mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
			}
		}
		List<List<Integer>> ans = new ArrayList<>();
		for (Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
			int curX = entry.getKey();
			int curMaxHeight = entry.getValue();
			if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
				ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
			}
		}
		return ans;
	}
	
}
