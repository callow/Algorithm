package com.algo.util.bit.collection;

/**
 * 1/0 存在/不存在， 且区间必须是连续的，支持0 - n-1
 */

public class Bitset {

	public int[] set;

	// n个数字 : 0~n-1
	public Bitset(int n) {
		// a/b如果结果想向上取整，可以写成 : (a+b-1)/b
		// 前提是a和b都是非负数
		set = new int[(n + 31) / 32];
	}

	public void add(int num) {
		set[num / 32] |= 1 << (num % 32);
	}

	public void remove(int num) {
		set[num / 32] &= ~(1 << (num % 32));
	}

	/**
	 * 
	 * 反转/改变：0 -> 1, 1 -> 0
	 * 
	 * num 如果存在就remove,  不存在就add
	 */
	public void reverse(int num) {
		set[num / 32] ^= 1 << (num % 32);
	}

	public boolean contains(int num) {
		return ((set[num / 32] >> (num % 32)) & 1) == 1;
	}
}
