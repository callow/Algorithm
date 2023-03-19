package com.algo.util.dp;

import java.util.List;

import com.algo.util.dp.impl.DPRecursive;

public class DPUtil {
	
	/**
	 * 汉诺塔问题, O(2^N - 1)
	 */
	
	public static void hanoi(int n) {
		new DPRecursive().hanoi(n);
	}
	
	/**
	 *  打印String的全部子序列,子序列不需要连续
	 */
	
	public static void printSubsequence(String s) {
		List<String> answers =  new DPRecursive().subsequence(s);
		answers.stream().forEach(System.out::println);
	}
	
	/**
	 *  打印String的全排列 = 排列组合 = 顺序不同算一种
	 */
	
	public static void printPermutation(String s) {
		List<String> answers =  new DPRecursive().permutation(s);
		answers.stream().forEach(System.out::println);
	}
	
	
}
