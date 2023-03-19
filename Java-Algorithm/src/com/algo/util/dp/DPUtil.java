package com.algo.util.dp;

import java.util.List;
import java.util.Stack;

import com.algo.util.dp.impl.DPRecursive;
import com.algo.util.stack.StackUtil;

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
	
	/**
	 * 逆序栈，但是不能用额外的数据结构
	 */
	
	public static void reverseStack(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = StackUtil.popBottom(stack);
		reverseStack(stack);
		stack.push(i);
		StackUtil.popBottom(stack);
	}
	
	
}
