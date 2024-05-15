package com.algo.util.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 质数：除了自己和1无法被其他数分解
 * 
 * 
 * 质数寻找
 * 质数判断
 * 质因子分解
 *
 */
public class PrimeUtil {
	
	/**
	 * 判断较小的数是否是质数 O(√n)
	 */
	public static boolean isPrime(long n) {
		if (n <= 1) {
			return false;
		}
		// 2 ... 根号n
		for (long i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断较大的数是否是质数 O(s√n^3) - Miller Rabin Test
	 * 
	 * e.g: 10^9
	 */
	public static boolean isPrime2(long n) {
		BigInteger c = BigInteger.valueOf(n);
		// 测试次数，次数越多失误率越低，但速度也越慢
		int s = 10;
		return c.isProbablePrime(s);
	}
	
	
	/**
	 * 质因子分解： 10 -> [2 , 5]  O(√n)
	 *  任意给一个数，打印所有质数因子
	 */
	public static List<Integer> primeFactors(int n) {
		List<Integer> r = new ArrayList<>();
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				r.add(i);
				while (n % i == 0) {
					n /= i;
				}
			}
		}
		if (n > 1) {
			r.add(n);
		}
		return r;
	}
	
	
	
	
}
