package com.algo.util.math;

import com.algo.util.math.impl.GCDUtil;

public class MathUtil {

	/**
	 * ���Լ�� O(log(a)^3) ��50��30 -> 10
	 */
	public static long gcd(long a, long b) {
		return GCDUtil.gcd(a, b);
	}
	
	/**
	 * ��С������: 50,30 -> 150
	 */
	public static long lcm(long a, long b) {
		return GCDUtil.lcm(a, b);
	}
	
	/**
	 * һ������������ܱ� a �� b ��������ô��������ġ������������� n , a , b �����ص� n ����������֡�
		https://leetcode.cn/problems/nth-magical-number/
	 */
	public static int nthMagicalNumber(int n, int a, int b) {
		return GCDUtil.nthMagicalNumber(n, a, b);
	}
	
	
	
}