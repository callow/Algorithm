package com.algo.tasks.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * 大楼轮廓线问题： https://leetcode.com/problems/the-skyline-problem/
 *
 * 高度的变化用于填补上个轮廓线的结尾，最新的变化值结尾未知?：
 *  1: (3,?,3)
 *  2: (3,5,3)
 *     (3,?,3)
 *  3: (3,5,3)
 *     (3,6,3)
 * 	   (3,?,3)
 * 	...
 * 
 * 使用TreeMap : key 高度， value : 次数
 */
public class SkylineProblem {
	
	public static class Node {
		public int x; // 在x州什么位置
		public boolean isAdd; // 是+高度还是-高度
		public int h; // 高度是多少

		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}

	public static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.x - o2.x; // 第1维数据 x轴的位置排序
		}
	}
	
	public static List<List<Integer>> getSkyline(int[][] matrix) {
		Node[] nodes = new Node[matrix.length * 2];
		for (int i = 0; i < matrix.length; i++) { // 遍历每个大楼
			nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]); // 在一个位置+了高度
			nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]); // 在一个位置-了高度
		}
		Arrays.sort(nodes, new NodeComparator()); // 排序
		
		// key  高度  value 次数
		TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
		// key  x位置  value 最大高度 - 每个位置的最大高度
		TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isAdd) {
				if (!mapHeightTimes.containsKey(nodes[i].h)) {
					mapHeightTimes.put(nodes[i].h, 1);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
				}
			} else {
				if (mapHeightTimes.get(nodes[i].h) == 1) { // 如果是0次了，整个记录删掉，因为要求Map最大key的 会产生干扰
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
