package com.algo.util.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * �����������Լ���1�޷����������ֽ�
 * 
 * 
 * ����Ѱ��
 * �����ж�
 * �����ӷֽ�
 *
 */
public class PrimeUtil {
	
	/**
	 * �жϽ�С�����Ƿ������� O(��n)
	 */
	public static boolean isPrime(long n) {
		if (n <= 1) {
			return false;
		}
		// 2 ... ����n
		for (long i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �жϽϴ�����Ƿ������� O(s��n^3) - Miller Rabin Test
	 * 
	 * e.g: 10^9
	 */
	public static boolean isPrime2(long n) {
		BigInteger c = BigInteger.valueOf(n);
		// ���Դ���������Խ��ʧ����Խ�ͣ����ٶ�ҲԽ��
		int s = 10;
		return c.isProbablePrime(s);
	}
	
	
	/**
	 * �����ӷֽ⣺ 10 -> [2 , 5]  O(��n)
	 *  �����һ��������ӡ������������
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
