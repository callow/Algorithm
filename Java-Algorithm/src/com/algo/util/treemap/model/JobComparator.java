package com.algo.util.treemap.model;

import java.util.Comparator;

public class JobComparator implements Comparator<Job> {
	
	/**
	 * o1.hard - o2.hard : 难度不同时，按照难度由小到大
	 * o2.money - o1.money： 难度相同时，按照money由大到小
	 */
	
	@Override
	public int compare(Job o1, Job o2) {
		return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
	}
}
