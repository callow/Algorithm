package com.algo.util.math.impl;

/**
 * ͬ�ࣺ �����������λ����Ҫ���쳣�����ͬ������˴�����
 * 
 * �ӷ�ͬ�� : ((a+b) + (b+c)) % m = ((a+b) % m + (b+c) % m ) % m
 * �˷�ͬ�� : ((a*b) + (b*c)) % m = ((a*b) % m + (b*c) % m ) % m
 * ����ͬ�� ����a%m -b%m + m��% m
 * ����ͬ�� �� (a / b) % m  = ((a % m) * b����Ԫ) % m
 *  ��Ԫ=����=Inverse ǰ�᣺
 *  - a / b ������ 
 *  - m�����ֱ���������
 *  - b �� m ���Լ��Ϊ1 => b �� m ����
 *  
 *  ���̣�
 *   - b����Ԫ = b^(m-2) % m 
 *   - 10/5 % 3 => 
 *      10 % 3 = 1
 *      5^(3-2) % 3 = 2 ��5����Ԫ��
 *      (1 * 2) % 3 = 2
 *  
 *  ������Ԫ�� 1~nÿһ�����ֵ���Ԫ InverseSerial
 */
public class CongruenceUtil {

	/** 
	 * �κ�����a b c d, ���� ((a + b) * (c - d) + (a * c - b * d)) % mod �ķǸ����
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
	
	/**
	 * ����ͬ��:
	 *     1) ���뱣֤a/b��������
		   2) ���뱣֤mod������
		   3) ���뱣֤b��mod�����Լ��Ϊ1
	 *  e.g : 3671613 / 67312  % 41
	 */
	public static int divMod(long a, long b, int mod) {
		long inv = fastPow(b, mod - 2, mod);
		return (int) (((a % mod) * inv) % mod);
	}

	/**
	 * �˷������� : b��n�η��Ľ��%mod
	 */
	public static long fastPow(long b, int n, int mod) {
		long ans = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = (ans * b) % mod;
			}
			b = (b * b) % mod;
			n >>= 1;
		}
		return ans;
	}
	
	
	
}
