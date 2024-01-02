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
	
	/**
	 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。给定三个整数 n , a , b ，返回第 n 个神奇的数字。
		https://leetcode.cn/problems/nth-magical-number/
	 */
	public static int nthMagicalNumber(int n, int a, int b) {
		return GCDUtil.nthMagicalNumber(n, a, b);
	}
	
	
	
}
