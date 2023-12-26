package com.algo.util.bit;

/**
 * xor util
 * 
 * 
 * F  = 1111 = 15
 * 0-9 = 0 - 9
 * 10 - 15 = a - f
 * 
 * 0xFFFFFFFF = 32个1
 * 
 * 1. 异或 = 无进位相加 = plus without carry
 * 2. 满足交换律，结合律 = a^b^c = (a^b)^c ,  a^b = c 则 b = a^c
 * 3. 0^n = n,  n^n = 0
 * 4. 区间异或和： 0 ~ 17  位置的异或和 = a, 且 3,5,6,9的异或和 = b , 剩下位置的异或和 x=a^b  
 * 
 * 
 */
public class XorUtil {

	/**
	 * 交换值 if a != b, a == b  会出错
	 */
	public static void swap(int[] arr, int a, int b) {
		BitUtil.swap(arr, a, b);
	}
	
	/**
	 * 
	 *  不使用if,返回2数最大值
	 */
	public static int max(int a, int b) {
		int c = a - b;
		int sa = BitUtil.sign(a);
		int sb = BitUtil.sign(b);
		int sc = BitUtil.sign(c); // 非负（a大） -> 1, 负数（b大） -> 0
		int diffAB = sa ^ sb; // A,B符号是否不一样，如果不一样 diffAB = 1, 一样 diffAB = 0
		int sameAB = BitUtil.flip(diffAB);
		
		int returnA = diffAB * sa + sameAB * sc;
		int returnB = BitUtil.flip(returnA);
		return a * returnA + b * returnB;
	}
	
	/**
	 * 
		0-10 找到缺失的那1个数字: https://leetcode.cn/problems/missing-number/
			总异或和 ^ 出现的数字异或和 = 此数字
	 */
	public static int findMissing(int[] nums) {
		int eorAll = 0, eorHas = 0;
		for (int i = 0; i < nums.length; i++) {
			eorAll ^= i; // 总异或和
			eorHas ^= nums[i]; // 出现数字异或和
		}
		eorAll ^= nums.length;
		return eorAll ^ eorHas;
	}
	
	
}
