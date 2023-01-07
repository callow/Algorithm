package com.algo.util.bit;

import java.util.Arrays;

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
		while(num != 0) {
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
		for (int i =0; i < arr.length; i++) {
			if ((arr[i] & rightMostOne) != 0) { // 1 & 1 = 1
				onlyOne ^= arr[i];
			}
		}
		return new int[] {onlyOne, (eorMix ^ onlyOne)};
	}
	
	/**
	 * ��һ��������K�Σ���������M�Σ��ҵ�K�ε�������� <br>
	 *  - ��ĳһλֻ���г���M�ε������� sum��bit�� % m ��Ȼ���� 0 
	 */
	
	public static int findKM(int[] arr, int k, int m) {
		int[] sumHelper = new int[32];
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
				sumHelper[i] += (num >> i) & 1; // Retrieve one
			}
		}
		
		// ��23�����е�32λͳ�ƺ� [5, 12, 17, 23, 12, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17]
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

}
