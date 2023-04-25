package com.algo.util.bit;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 * Octal(8进制) 有8位: 0, 1, 2, 3, 4, 5, 6, and 7 <br>
 * - 转2进制，3个bit一组 ： 101|110|010 = 562(8)
 * 
 * Hexadecimal(16进制) 有16位: in addition to 0 to 9, there are A, B, C, D, E, F, 对应
 * 10, 11, 12, 13, 14, 15 in decimal<br>
 * 
 * - 转2进制，4个bit一组: 1|0111|0010 = 172(16)
 * 
 * original code: 原码 = 符号位(0/1) + 机器码的实际值的绝对值 = 00001010 = 10, 10001010 = -10
 * <br>
 * 
 * inverse code： 反码 = 从原码来，正数： original code = inverse code, 负数：flip
 * 除符号位的所有位10001010->11110101<br>
 * 
 * complement code： 补码 = 从反码来，正数： complement code = original code = inverse
 * code， 负数：反码+1， 10001010(原) -> 11110101(反) -> 11110110(补) <br>
 * 
 * machine number： 机器码(010010) <br>
 * truth value :实际值. 10001010(机器码) = 138 但是实际值 = -10 因为最高位1是符号位
 * 
 * 计算机不可以用原码计算，会有问题，计算机用的是补码计算的！ 1 = true, 0 = false : <br>
 * 1 & 1 = 1, 0 | 1 = 1, 0 ^ 1 = 1, 1 ^ 1 = 0
 * 
 * left shift: << = 乘法 => 29 << k = 29 * 2^k, 29 * 6 = 29 *(( a << 2 ) + ( a <<
 * 1 ))<br>
 * arithmetic right shift: >> 右移最高符号位不变 = 除法<br>
 * logical right shift: >>> 右移左侧填0
 * 
 * 
 * Idempotent law: a & a = a, a | a = a <br>
 * De Morgan's Law: ~(a & b) = (~a) | (~b), ~(a | b) = (~a) & (~b) <br>
 * Negative operation properties: -1 = ~0, -a = ~(a−1) <br>
 * AND operation properties: a & 0 = 0, a & (-1) = a, a & ( ∼a)=0 <br>
 * OR operation properties: a | 0 = a, a | (∼a) = −1<br>
 * XOR operation properties: a ^ 0 = a, a ^ a = 0 <br>
 * a & (a−1) = 将最后的1变为0 <br>
 * a & (-a) = a & (∼(a−1)) = 只保留最后一个1 <br>
 * 
 * 
 */
public class BitUtil {

	/**
	 * 交换数组中2个数， = CommonArrayUtil.swap()
	 */
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	/**
	 * 
	 * num 在 i 位置是否是 1.
	 */

	public static boolean isOneAtIndex(int num, int i) {
		return ((num >> i) & 1) != 0;
	}

	/**
	 * 提取二进制中最右侧的1： num & (-num) 或 num & (~num +1)
	 */

	public static int rightMostOne(int num) {
		return num & (-num);
	}

	/**
	 * 数 1 的数量
	 */

	public static int countOnes(int num) {
		int counter = 0;
		while (num != 0) {
			int rightOne = BitUtil.rightMostOne(num);
			counter++;
			num ^= rightOne;
		}
		return counter;
	}

	/**
	 * 打印二进制
	 */

	public static void print(int num) {
		for (int i = 31; i >= 0; i--) {
			System.out.print((num & (1 << i)) == 0 ? "0" : "1");
		}
		System.out.println();
	}

	/**
	 * 某个数出现基数次，其他都是偶数次， 找到它 : ^ 会将相同数/偶数次消掉
	 */

	public static int findOddOnce(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		return eor;
	}

	/**
	 * 某2个数出现基数次，其他都是偶数次， 找到它们.
	 */

	public static int[] findOddTwice(int[] arr) {
		int eorMix = BitUtil.findOddOnce(arr); // 2种数的混合eor,
		int rightMostOne = BitUtil.rightMostOne(eorMix);
		int onlyOne = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & rightMostOne) != 0) { // 1 & 1 = 1
				onlyOne ^= arr[i];
			}
		}
		return new int[] { onlyOne, (eorMix ^ onlyOne) };
	}

	/**
	 * 有一种数出现K次，其他都是M次，找到K次的这个数。 <br>
	 * - 若某一位只含有出现M次的数，则 sum（bit） % m 必然等于 0
	 */

	public static int findKM(int[] arr, int k, int m) {
		int[] sumHelper = new int[32];
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
				sumHelper[i] += (num >> i) & 1; // Retrieve one
			}
		}

		// 这23个数中的32位统计和 [5, 12, 17, 23, 12, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
		// 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17]
		System.out.println(Arrays.toString(sumHelper));

		int answer = 0;
		for (int i = 0; i < 32; i++) {
			sumHelper[i] %= m;
			if (sumHelper[i] != 0) { // 说明这位有K的位！
				answer |= 1 << i; // 标上1
			}
		}
		return answer;
	}

	/**
	 * 判断最末尾是否有1
	 */
	public static boolean hasOneAtEnd(int num) {
		return (num & 1) != 0;
	}

	/**
	 * 二进制字相加： "1001" + "1100"
	 */
	public static String addBinary(String a, String b) {
		BigInteger x = new BigInteger(a, 2);
		BigInteger y = new BigInteger(b, 2);
		BigInteger zero = new BigInteger("0", 2);
		BigInteger carry, answer;

		while (y.compareTo(zero) != 0) { // y = 进位，不停的进位
			answer = x.xor(y); // 无进位相加： answer = x^y
			carry = x.and(y).shiftLeft(1); // 首次进位的位置：carry = (x & y) << 1
			x = answer;
			y = carry;
		}
		return x.toString(2);
	}

	/**
	 * 2进制 -> 7进制
	 */
	public static String convertToBase7(int num) {
		return Integer.toString(num, 7);
	}

}
