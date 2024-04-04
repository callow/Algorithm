package com.algo.util.bit.collection;

public class SuperBitset {

	private int[] set;
	private final int size;
	private int zeros;
	private int ones;
	private boolean reverse;

	public SuperBitset(int n) {
		set = new int[(n + 31) / 32];
		size = n;
		zeros = n;
		ones = 0;
		reverse = false;
	}

	// 把i这个数字加入到位图 = bitset.add
	public void fix(int i) {
		int index = i / 32;
		int bit = i % 32;
		if (!reverse) {
			// 位图所有位的状态，维持原始含义
			// 0 : 不存在
			// 1 : 存在
			if ((set[index] & (1 << bit)) == 0) {
				zeros--;
				ones++;
				set[index] |= (1 << bit);
			}
		} else {
			// 位图所有位的状态，翻转了
			// 0 : 存在
			// 1 : 不存在
			if ((set[index] & (1 << bit)) != 0) {
				zeros--;
				ones++;
				set[index] ^= (1 << bit);
			}
		}
	}

	// 把i这个数字从位图中移除 = bitset.remove
	public void unfix(int i) {
		int index = i / 32;
		int bit = i % 32;
		if (!reverse) {
			if ((set[index] & (1 << bit)) != 0) {
				ones--;
				zeros++;
				set[index] ^= (1 << bit);
			}
		} else {
			if ((set[index] & (1 << bit)) == 0) {
				ones--;
				zeros++;
				set[index] |= (1 << bit);
			}
		}
	}

	/**
	 *  0 -> 1, 1 -> 0
	 */
	public void flip() {
		reverse = !reverse;
		int tmp = zeros;
		zeros = ones;
		ones = tmp;
	}


	// 是否全是1
	public boolean all() {
		return ones == size;
	}

	//是否有1
	public boolean one() {
		return ones > 0;
	}

	// 1的个数
	public int count() {
		return ones;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0, k = 0, number, status; i < size; k++) {
			number = set[k];
			for (int j = 0; j < 32 && i < size; j++, i++) {
				status = (number >> j) & 1;
				status ^= reverse ? 1 : 0;
				builder.append(status);
			}
		}
		return builder.toString();
	}

}
