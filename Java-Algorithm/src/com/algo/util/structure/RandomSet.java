package com.algo.util.structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * һ��set, ���룬ɾ������ȡ = o��1������getRandom�ǵȸ��������ȡ
 * 
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 */
public class RandomSet {
	
	// ��¼ֵ���Լ��ڶ�̬�����е�λ��
	public HashMap<Integer, Integer> map;

	// ��̬����
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

	// remove���ڶ�̬���������µĿն���ʹ���������һ�����
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
