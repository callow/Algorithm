package com.algo.util.structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ���Ƶ��ջ: https://leetcode.cn/problems/maximum-frequency-stack/
 * 
 * hashmap: Ƶ�α�a:3,b:1
 * hashmap: ÿ��ڵ㣺key = �㣬 val = ����
 * 	
 *  3:{a}
 *  2:{a}
 * 	1:{a,b}
 * int: �嵽������
 * 
 * O(1)
 */
public class MaxFrequencyStack {

	// ���ֵ�������
	private int topTimes;
	// ÿ��ڵ�
	private HashMap<Integer, ArrayList<Integer>> cntValues = new HashMap<>();
	// ÿһ���������˼���
	private HashMap<Integer, Integer> valueTimes = new HashMap<>();

	public void push(int val) {
		valueTimes.put(val, valueTimes.getOrDefault(val, 0) + 1);
		int curTopTimes = valueTimes.get(val);
		if (!cntValues.containsKey(curTopTimes)) {
			cntValues.put(curTopTimes, new ArrayList<>());
		}
		ArrayList<Integer> curTimeValues = cntValues.get(curTopTimes);
		curTimeValues.add(val);
		topTimes = Math.max(topTimes, curTopTimes);
	}

	public int pop() {
		ArrayList<Integer> topTimeValues = cntValues.get(topTimes);
		int ans = topTimeValues.remove(topTimeValues.size() - 1);
		if (topTimeValues.size() == 0) {
			cntValues.remove(topTimes--);
		}
		int times = valueTimes.get(ans);
		if (times == 1) {
			valueTimes.remove(ans);
		} else {
			valueTimes.put(ans, times - 1);
		}
		return ans;
	}
}
