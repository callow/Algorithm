package com.algo.util.treemap;

import java.util.TreeMap;

/**
 * 
 * TreeMap: O(logN),�����
 */
public class TreemapUtil {

	public static void demo() {
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		System.out.println(treeMap.firstKey()); // ����key����С��key
		System.out.println(treeMap.lastKey()); // ����key������key
		System.out.println(treeMap.floorKey(4)); // <= 4,��4�����key
		System.out.println(treeMap.ceilingKey(4)); // >= 4,��4�����key
	}
}
