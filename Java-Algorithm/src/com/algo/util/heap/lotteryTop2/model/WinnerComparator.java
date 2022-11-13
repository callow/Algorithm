package com.algo.util.heap.lotteryTop2.model;

import java.util.Comparator;
/**
 * 
 * 买的少的放前面，时间早的放前面
 *
 */
public class WinnerComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
	}

}
