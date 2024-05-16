package com.algo.util.math.impl;

/**
 * 同余： 计算过程中数位过长要抛异常，因此同余避免了此问题
 * 
 * 加法同余 : ((a+b) + (b+c)) % m = ((a+b) % m + (b+c) % m ) % m
 * 乘法同余 : ((a*b) + (b*c)) % m = ((a*b) % m + (b*c) % m ) % m
 * 减法同余 ：（a%m -b%m + m）% m
 * 除法同余 ： (a / b) % m 
 *  逆元=倒数前提：
 *  - a / b 能整除 
 *  - m的数字必须是质数
 *  - b 于 m 最大公约数为1 => b 与 m 互质
 *  过程： 
 *   - b的逆元 = b^(m-2) % m 
 *   - 10/5 % 3 => 
 *      10 % 3 = 1
 *      5^(3-2) % 3 = 2 （5的逆元）
 *      (1 * 2) % 3 = 2
 */
public class CongruenceUtil {

	/** 
	 * 任何数字a b c d, 计算 ((a + b) * (c - d) + (a * c - b * d)) % mod 的非负结果
	 */
	public static int f2(long a, long b, long c, long d, int mod) {
		int o1 = (int) (a % mod); // a
		int o2 = (int) (b % mod); // b
		int o3 = (int) (c % mod); // c
		int o4 = (int) (d % mod); // d
		int o5 = (o1 + o2) % mod; // a + b
		int o6 = (o3 - o4 + mod) % mod; // c - d
		int o7 = (int) (((long) o1 * o3) % mod); // a * c
		int o8 = (int) (((long) o2 * o4) % mod); // b * d
		int o9 = (int) (((long) o5 * o6) % mod); // (a + b) * (c - d)
		int o10 = (o7 - o8 + mod) % mod; // (a * c - b * d)
		int ans = (o9 + o10) % mod; // ((a + b) * (c - d) + (a * c - b * d)) % mod
		return ans;
	}
	
	
	
	
	
}
