package com.algo.util.treemap;

import java.util.TreeMap;

/**
 * 
 * TreeMap: O(logN),红黑树
 */
public class TreemapUtil {

	public static void demo() {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		System.out.println(treeMap.firstKey()); // 所有key中最小的key
		System.out.println(treeMap.lastKey()); // 所有key中最大的key
		System.out.println(treeMap.floorKey(4)); // <= 4,离4最近的key
		System.out.println(treeMap.ceilingKey(4)); // >= 4,离4最近的key
	}
}
