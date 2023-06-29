package com.algo.tasks.day6;

/**
 * 一个数组arr[] , 先手和后手轮流从这些堆里拿减数字，谁最先把最后一堆拿到0就赢。
 * 
 *  异或和 <> 0 => 先手赢
 *  异或和 == 0 => 后手赢
 */
public class Nim {

	// 保证arr是正数数组
	public static void win(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
			System.out.println("后手赢");
		} else {
			System.out.println("先手赢");
		}
	}
}
