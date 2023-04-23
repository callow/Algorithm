package com.algo.util.dp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Info {

	public int[] coins;
	public int[] zhangs;

	public Info(int[] c, int[] z) {
		coins = c;
		zhangs = z;
	}

	public static Info getInfo(int[] arr) {
		Map<Integer, Integer> counts = new HashMap<>();
		for (int value : arr) {
			if (!counts.containsKey(value)) {
				counts.put(value, 1);
			} else {
				counts.put(value, counts.get(value) + 1);
			}
		}
		int N = counts.size();
		int[] coins = new int[N];
		int[] zhangs = new int[N];
		int index = 0;
		for (Entry<Integer, Integer> entry : counts.entrySet()) {
			coins[index] = entry.getKey();
			zhangs[index++] = entry.getValue();
		}
		return new Info(coins, zhangs);
	}
}
