package com.algo.util.structure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 最大频率栈: https://leetcode.cn/problems/maximum-frequency-stack/
 * 
 * hashmap: 频次表：a:3,b:1
 * hashmap: 每层节点：key = 层， val = 链表
 * 	
 *  3:{a}
 *  2:{a}
 * 	1:{a,b}
 * int: 冲到最大次数
 * 
 * O(1)
 */
public class MaxFrequencyStack {

	// 出现的最大次数
	private int topTimes;
	// 每层节点
	private HashMap<Integer, ArrayList<Integer>> cntValues = new HashMap<>();
	// 每一个数出现了几次
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
