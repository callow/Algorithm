package com.algo.util.structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 一个set, 插入，删除，获取 = o（1），且getRandom是等概率随机获取
 * 
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 */
public class RandomSet {
	
	// 记录值，以及在动态数组中的位置
	public HashMap<Integer, Integer> map;

	// 动态数组
	public ArrayList<Integer> arr;

	public RandomSet() {
		map = new HashMap<>();
		arr = new ArrayList<>();
	}

	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}
		map.put(val, arr.size());
		arr.add(val);
		return true;
	}

	// remove后在动态数组中留下的空洞，使用数组最后一个填充
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		int valIndex = map.get(val);
		int endValue = arr.get(arr.size() - 1);
		map.put(endValue, valIndex);
		arr.set(valIndex, endValue);
		map.remove(val);
		arr.remove(arr.size() - 1);
		return true;
	}

	public int getRandom() {
		return arr.get((int) (Math.random() * arr.size()));
	}
}
