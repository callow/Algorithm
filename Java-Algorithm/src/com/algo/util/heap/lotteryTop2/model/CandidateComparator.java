package com.algo.util.heap.lotteryTop2.model;

import java.util.Comparator;

/**
 * 
 * ��Ķ�ķ�ǰ�棬ʱ����ķ�ǰ��<br><br>
 * 
 * ���ڱȽ�����˵��������ظ�������ô��һ������Ҫ����ǰ�棻���������������ô�ڶ�������Ҫ����ǰ��
 *
 */
public class CandidateComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
	}
}
