package com.algo.util.bit.collection;


public class BitSetUtil {
	
	/**
	 * 
	 * 连续空间增删改查，判断是否存在
	 */
	public static boolean bitSet() {
		Bitset set = new Bitset(10000);
		set.remove(36);
		set.add(36);
		set.reverse(36);
		return set.contains(36);
	}
	
	/**
	 * 
	 *  自定义超级bitset，
	 */
	public static boolean superBitSet() {
		SuperBitset set = new SuperBitset(10000);
		return set.all();
	}
	
	

}
