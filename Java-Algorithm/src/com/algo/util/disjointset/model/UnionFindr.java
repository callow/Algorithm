package com.algo.util.disjointset.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 并查集：适用于matrix特别巨大 而有数据的点位很少<br>
 * (17,1000009) 则map的key "17_10000009"
 *
 */
public class UnionFindr {

	private Map<String, String> parent;
	private Map<String, Integer> size;
	private List<String> help;
	private int sets;

	public UnionFindr() {
		parent = new HashMap<>();
		size = new HashMap<>();
		help = new ArrayList<>();
		sets = 0;
	}

	private String findFather(String cur) {
		while (!cur.equals(parent.get(cur))) {
			help.add(cur);
			cur = parent.get(cur);
		}
		for (String str : help) {
			parent.put(str, cur);
		}
		help.clear();
		return cur;
	}

	private void union(String s1, String s2) {
		if (parent.containsKey(s1) && parent.containsKey(s2)) {
			String f1 = findFather(s1);
			String f2 = findFather(s2);
			if (!f1.equals(f2)) {
				int size1 = size.get(f1);
				int size2 = size.get(f2);
				String big = size1 >= size2 ? f1 : f2;
				String small = big == f1 ? f2 : f1;
				parent.put(small, big);
				size.put(big, size1 + size2);
				sets--;
			}
		}
	}

	public int connect(int r, int c) {
		String key = String.valueOf(r) + "_" + String.valueOf(c);
		if (!parent.containsKey(key)) {
			parent.put(key, key);
			size.put(key, 1);
			sets++;
			String up = String.valueOf(r - 1) + "_" + String.valueOf(c);
			String down = String.valueOf(r + 1) + "_" + String.valueOf(c);
			String left = String.valueOf(r) + "_" + String.valueOf(c - 1);
			String right = String.valueOf(r) + "_" + String.valueOf(c + 1);
			
			// 上下左右union
			union(up, key);
			union(down, key);
			union(left, key);
			union(right, key);
		}
		return sets;
	}
}
