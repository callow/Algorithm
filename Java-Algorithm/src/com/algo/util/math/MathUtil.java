package com.algo.util.math;

import com.algo.util.math.impl.GCDUtil;

public class MathUtil {

	/**
	 * 最大公约数 O(log(a)^3) ：50，30 -> 10
	 */
	public static long gcd(long a, long b) {
		return GCDUtil.gcd(a, b);
	}
	
	/**
	 * 最小公倍数: 50,30 -> 150
	 */
	public static long lcm(long a, long b) {
		return GCDUtil.lcm(a, b);
	}
	
}
