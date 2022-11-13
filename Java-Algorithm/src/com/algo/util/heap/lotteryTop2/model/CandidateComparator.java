package com.algo.util.heap.lotteryTop2.model;

import java.util.Comparator;

/**
 * 
 * 买的多的放前面，时间早的放前面<br><br>
 * 
 * 对于比较器来说，如果返回负数，那么第一个参数要排在前面；如果返回正数，那么第二个参数要排在前面
 *
 */
public class CandidateComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
	}
}
