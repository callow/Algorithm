package com.algo.util.structure;

import java.util.HashMap;
/**
 * setAll功能的哈希表
 测试链接 : https://www.nowcoder.com/practice/7c4559f138e74ceb9ba57d76fd169967 
 */

public class SetAllHashMap {

	public static HashMap<Integer, int[]> map = new HashMap<>();
	public static int setAllValue;
	public static int setAllTime;
	public static int cnt;

	public static void put(int k, int v) {
		if (map.containsKey(k)) {
			int[] value = map.get(k);
			value[0] = v;
			value[1] = cnt++;
		} else {
			map.put(k, new int[] { v, cnt++ });
		}
	}

	public static void setAll(int v) {
		setAllValue = v;
		setAllTime = cnt++;
	}

	public static int get(int k) {
		if (!map.containsKey(k)) {
			return -1;
		}
		int[] value = map.get(k);
		if (value[1] > setAllTime) {
			return value[0];
		} else {
			return setAllValue;
		}
	}
}
