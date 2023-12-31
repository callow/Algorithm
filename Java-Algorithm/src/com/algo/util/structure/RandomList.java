package com.algo.util.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * ���롢ɾ���ͻ�ȡ���Ԫ��O(1)ʱ�����������ظ����ֵĽṹ.
 * 
 * https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/
 */
public class RandomList {

	
	// {a:2,3,6}, ֵ��ֵ��Ӧ��λ��
	public HashMap<Integer, HashSet<Integer>> map;

	// ��̬����
	public ArrayList<Integer> arr;

	public RandomList() {
		map = new HashMap<>();
		arr = new ArrayList<>();
	}

	public boolean insert(int val) {
		arr.add(val);
		HashSet<Integer> set = map.getOrDefault(val, new HashSet<Integer>());
		set.add(arr.size() - 1);
		map.put(val, set);
		return set.size() == 1;
	}

	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		HashSet<Integer> valSet = map.get(val);
		int valAnyIndex = valSet.iterator().next();
		int endValue = arr.get(arr.size() - 1);
		if (val == endValue) {
			valSet.remove(arr.size() - 1);
		} else {
			HashSet<Integer> endValueSet = map.get(endValue);
			endValueSet.add(valAnyIndex);
			arr.set(valAnyIndex, endValue);
			endValueSet.remove(arr.size() - 1);
			valSet.remove(valAnyIndex);
		}
		arr.remove(arr.size() - 1);
		if (valSet.isEmpty()) {
			map.remove(val);
		}
		return true;
	}

	public int getRandom() {
		return arr.get((int) (Math.random() * arr.size()));
	}
}
