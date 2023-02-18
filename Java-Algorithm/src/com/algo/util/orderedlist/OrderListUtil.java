package com.algo.util.orderedlist;

import com.algo.util.orderedlist.sbtree.SBTreeSet;

public class OrderListUtil {
	
	/**
	 *  子数组累加和是否达标。 达标即在[lower,upper] 内
	 *  
	 *  思路： 子数组必须以1结尾，落在这个范围上有几个， = 必须以0开头有多少个前缀和落在这个范围上
	 *  
	 *  0~i 的累加和S， [a,b], => 有多少个前缀和落在[s-b,s-a]上
	 */
	public static final int countRangeSum(int[] nums, int lower, int upper) {
		// 黑盒，加入数字（前缀和），不去重，可以接受重复数字
		// < num , 有几个数？
		SBTreeSet treeSet = new SBTreeSet();
		long sum = 0;
		int ans = 0;
		treeSet.add(0);// 一个数都没有的时候，就已经有一个前缀和累加和为0，
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// [sum - upper, sum - lower]
			// [10, 20] ?
			// < 10 ?  < 21 ?   
			long a = treeSet.lessKeySize(sum - lower + 1);
			long b = treeSet.lessKeySize(sum - upper);
			ans += a - b;
			treeSet.add(sum);
		}
		return ans;
		
	}

}
