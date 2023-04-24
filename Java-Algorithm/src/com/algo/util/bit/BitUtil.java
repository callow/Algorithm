package com.algo.util.bit;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 * Octal(8����) ��8λ: 0, 1, 2, 3, 4, 5, 6, and 7 <br>
 * - ת2���ƣ�3��bitһ�� �� 101|110|010 = 562(8)
 * 
 * Hexadecimal(16����) ��16λ: in addition to 0 to 9, there are A, B, C, D, E, F, ��Ӧ
 * 10, 11, 12, 13, 14, 15 in decimal<br>
 * 
 * - ת2���ƣ�4��bitһ��: 1|0111|0010 = 172(16)
 * 
 * original code: ԭ�� = ����λ(0/1) + �������ʵ��ֵ�ľ���ֵ = 00001010 = 10, 10001010 = -10
 * <br>
 * 
 * inverse code�� ���� = ��ԭ������������ original code = inverse code, ������flip
 * ������λ������λ10001010->11110101<br>
 * 
 * complement code�� ���� = �ӷ������������� complement code = original code = inverse
 * code�� ����������+1�� 10001010(ԭ) -> 11110101(��) -> 11110110(��) <br>
 * 
 * machine number�� ������(010010) <br>
 * truth value :ʵ��ֵ. 10001010(������) = 138 ����ʵ��ֵ = -10 ��Ϊ���λ1�Ƿ���λ
 * 
 * �������������ԭ����㣬�������⣬������õ��ǲ������ģ� 1 = true, 0 = false : <br>
 * 1 & 1 = 1, 0 | 1 = 1, 0 ^ 1 = 1, 1 ^ 1 = 0
 * 
 * left shift: << = �˷� => 29 << k = 29 * 2^k, 29 * 6 = 29 *(( a << 2 ) + ( a <<
 * 1 ))<br>
 * arithmetic right shift: >> ������߷���λ���� = ����<br>
 * logical right shift: >>> ���������0
 *
 */
public class BitUtil {

	/**
	 * ����������2������ = CommonArrayUtil.swap()
	 */
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	/**
	 * 
	 * num �� i λ���Ƿ��� 1.
	 */

	public static boolean isOneAtIndex(int num, int i) {
		return ((num >> i) & 1) != 0;
	}

	/**
	 * ��ȡ�����������Ҳ��1�� num & (-num) �� num & (~num +1)
	 */

	public static int rightMostOne(int num) {
		return num & (-num);
	}

	/**
	 * �� 1 ������
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
	 * ��ӡ������
	 */

	public static void print(int num) {
		for (int i = 31; i >= 0; i--) {
			System.out.print((num & (1 << i)) == 0 ? "0" : "1");
		}
		System.out.println();
	}

	/**
	 * ĳ�������ֻ����Σ���������ż���Σ� �ҵ��� : ^ �Ὣ��ͬ��/ż��������
	 */

	public static int findOddOnce(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		return eor;
	}

	/**
	 * ĳ2�������ֻ����Σ���������ż���Σ� �ҵ�����.
	 */

	public static int[] findOddTwice(int[] arr) {
		int eorMix = BitUtil.findOddOnce(arr); // 2�����Ļ��eor,
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
	 * ��һ��������K�Σ���������M�Σ��ҵ�K�ε�������� <br>
	 * - ��ĳһλֻ���г���M�ε������� sum��bit�� % m ��Ȼ���� 0
	 */

	public static int findKM(int[] arr, int k, int m) {
		int[] sumHelper = new int[32];
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
				sumHelper[i] += (num >> i) & 1; // Retrieve one
			}
		}

		// ��23�����е�32λͳ�ƺ� [5, 12, 17, 23, 12, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
		// 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17]
		System.out.println(Arrays.toString(sumHelper));

		int answer = 0;
		for (int i = 0; i < 32; i++) {
			sumHelper[i] %= m;
			if (sumHelper[i] != 0) { // ˵����λ��K��λ��
				answer |= 1 << i; // ����1
			}
		}
		return answer;
	}

	/**
	 * �ж���ĩβ�Ƿ���1
	 */
	public static boolean hasOneAtEnd(int num) {
		return (num & 1) != 0;
	}

	/**
	 * ����������ӣ� "1001" + "1100"
	 */
	public static String addBinary(String a, String b) {
		BigInteger x = new BigInteger(a, 2);
		BigInteger y = new BigInteger(b, 2);
		BigInteger zero = new BigInteger("0", 2);
		BigInteger carry, answer;

		while (y.compareTo(zero) != 0) { // y = ��λ����ͣ�Ľ�λ
			answer = x.xor(y); // �޽�λ��ӣ� answer = x^y
			carry = x.and(y).shiftLeft(1); // �״ν�λ��λ�ã�carry = (x & y) << 1
			x = answer;
			y = carry;
		}
		return x.toString(2);
	}

}
