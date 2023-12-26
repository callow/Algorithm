package com.algo.util.bit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * 反: -1 = ~0, -a = ~(a−1) <br>
 * 与 : a & 0 = 0, a & (-1) = a, a & ( ∼a)=0 <br>
 * 或: a | 0 = a, a | (∼a) = −1<br>
 * 异或: a ^ 0 = a, a ^ a = 0 <br>
 * a & (a−1) = 将最后的1变为0 <br>
 * a & (-a) = a & (∼(a−1)) = 只保留最右侧的1 <br>
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
	 * 提取 num 的第 i 位. 只会返回 0/1
	 */
	
	public static int retrieveBit(int num, int i) {
		return ((num >> i) & 1);
	}

	/**
	 * 
	 * num 在 i 位置是否是 1.
	 */

	public static boolean isOneAtIndex(int num, int i) {
		return ((num >> i) & 1) != 0;
	}

	/**
	 * Brain Kernighan算法
	 * 提取二进制中最右侧的1： num & (-num) 或 num & (~num +1)
	 * 
	 * e.g: 0101000 -> 0001000
	 */

	public static int rightMostOne(int num) {
		return num & (-num);
	}

	/**
	 * 数 1 的数量(Hamming weight - 码重)(pop count)
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
	 * 不用+ 实现加法
	 */
	public static int sum(int x, int y) {
		int carry = 0;
		int answer = 0;
		while (y != 0) {
			answer = x ^ y;
			carry = (x & y) << 1;
			x = answer;
			y = carry;
		}
		return x;
	}

	/**
	 * 2进制 -> 7进制 = num 不停的 / 7 , 将余数反向组合即可
	 */
	public static String convertToBase7(int num) {
		return Integer.toString(num, 7);
	}

	/**
	 * 反转bits
	 */

	public static int reverseBits(int n) {
		int result = 0;
		int power = 31;
		while (power >= 0) {
			// 将最后一位放到result的前面
			result += (n & 1) << power;
			// n 右移一位，继续提取最后一位
			n = n >> 1;
			// 同时幂也- 1来配合n
			power--;
		}
		return result;
	}

	/**
	 * 获取N位格雷码 ： 格雷码/循环二进制单位距离码,https://leetcode.com/problems/gray-code/editorial/
	 * <br>
	 * 规律1： 竖着看，每一位组都是对称的: <br>
	 * 000 <br>
	 * 001 <br>
	 * 011 <br>
	 * 010 <br>
	 * 110 <br>
	 * 111 <br>
	 * 101 <br>
	 * 100 <br>
	 * 第0位：0110循环对称， 第1位：00111100循环对称，依次类推
	 * 
	 * 规律2: grayCode = index ^ index/2 (此题用的就是规律2做出了的)
	 */
	public static List<Integer> getGrayCode(int n) { // e.g : n = 3

		List<Integer> result = new ArrayList<>();
		// there are 2 ^ n numbers in the Gray code sequence. n = 3 时 总共List种有8个数字
		int seqLength = 1 << n;
		for (int i = 0; i < seqLength; i++) {
			int num = i ^ i >> 1; // index ^ index/2
			result.add(num);
		}
		return result;
	}

	/**
	 * 
	 * 区间与操作：Brian Kernighan Algorithm<br>
	 * 
	 * 区间与操作的和 = <b>公共前缀与和</b>
	 * 
	 * https://leetcode.com/problems/bitwise-and-of-numbers-range/editorial/
	 * 
	 * 利用BK算法，找出2数的公共前缀 : <br>
	 * 
	 * 当与操作对number 和 number-1时，最右侧一位bit会被清除reset(从1变成0) -> number呈几何速度缩小，从r -> l
	 */
	public static int rangeBitwiseAnd(int l, int r) {
		while (l < r) {
			// turn off rightmost 1-bit
			r &= (r - 1);
		}
		// 这时的r < l了，然后我们使用& 来提取公共前缀
		return l & r;

	}
	
	public static int rangeBitwiseAnd2(int l, int r) {
		while(l < r) {
			r -= rightMostOne(r);
		}
		return r;
	}

	/**
	 * 将最右侧最低位的1变成0：Last set bit
	 */
	public static int flipLastSetBit(int num) {
		return num & (num - 1);
	}

	/**
	 * 从0 ~ n 返回每个数1的数量放入int[]<br>
	 * 
	 * Most significant bit (最左侧位-最高有效位)<br>
	 * Least significant bit (最右侧位-最低有效位)<br>
	 * <b>解：每一个数1数量 都可以通过之前的某数将last set bit (最右set成1的位置-最低设置位)数量 + 1 来变得：<br>
	 * 这个数字就是： 将数字x的.LSB的1变成0后的数字： x &= x - 1 // 1 变 0</b>
	 * 
	 * P(x) = P(x & (x−1)) + 1 <br>
	 * 
	 * O(n)
	 */
	public static int[] loopCountOnes(int num) {
		int[] ans = new int[num + 1];
		for (int x = 1; x <= num; ++x) { // x from 1 ~ num
			ans[x] = ans[flipLastSetBit(x)] + 1; // 6 的1的数量 = 4的1的数量+1（很巧）
		}
		return ans;
	}
	
	/**
	 * 从num的位中，将index位置的1去掉，变成 0 
	 */
	
	public static int remove1AtIndex(int num, int index) {
		return num &= (~(1 << index));
	}
	
	/**
	 * 在num的位中，index 位置是 1 = isOneAtIndex
	 */
	public static boolean has1AtIndex(int num, int index) {
		return (num & (1 << index)) != 0;
	}
	
	/**
	 * 从num的位中，将index位置变成1
	 */
	public static int make1AtIndex(int num, int index) {
		return num |= (1 << index);
	}
	
	/**
	 * 找 >=n 的 2的某次幂的一个最近的数字
	 */
	public static int biggerNum2Power(int n) {
		n--;
		// 将最高位的1后面全变成1: e.g: 00010010 => 00011111
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16; // 整形只有32位，因此只需要做到16
		
		return (n < 0) ? 1 : n + 1;
	}
	
	/**
	 * 验证2数是否有相同奇偶性(Parity), Odd, Even
	 * 
	 * 只要看最后一位是否是2的倍数
	 */
	public static boolean isSameParity(int num1, int num2) {
		return  ((num1 & 1) ^ (num2 & 1)) != 0;
	}
	
	/**
	 * 一个数字一共32位，将数字的第Index位设置为1
	 */
	public static int set1AtIndex(int num, int index) {
		num |= (1 << index);
		return num;
	}
	
	/**
	 *  获取符号位：非负返回1， 负数返回0
	 */
	public static int sign(int n) {
		return flip(n >>> 31);
	}
	
	/**
	 * n 一定是0/1 -> 0变1， 1变0
	 */
	public static int flip(int n) {
		return n ^ 1;
	}
	
	/**
	 * n是否是2的幂?
	 */
	public static boolean isPowerOf2(int n ) {
		return n > 0 && n == rightMostOne(n);
	}
	
	/**
	 * n是否是3的幂?
	 * 
	 * 1162261467 = 3^19 , 整数内最大的3的幂
	 */
	public static boolean isPowerOf3(int n ) {
		return n > 0 && 1162261467 % n  == 0;
	}

}
