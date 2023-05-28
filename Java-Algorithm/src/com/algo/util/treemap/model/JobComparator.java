package com.algo.util.treemap.model;

import java.util.Comparator;

public class JobComparator implements Comparator<Job> {
	
	/**
	 * o1.hard - o2.hard : �ѶȲ�ͬʱ�������Ѷ���С����
	 * o2.money - o1.money�� �Ѷ���ͬʱ������money�ɴ�С
	 */
	
	@Override
	public int compare(Job o1, Job o2) {
		return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
	}
}
