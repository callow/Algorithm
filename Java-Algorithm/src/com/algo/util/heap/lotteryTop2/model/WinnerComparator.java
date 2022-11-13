package com.algo.util.heap.lotteryTop2.model;

import java.util.Comparator;
/**
 * 
 * ����ٵķ�ǰ�棬ʱ����ķ�ǰ��
 *
 */
public class WinnerComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
	}

}
