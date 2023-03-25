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
	
	public static List<String> getAllSubsequence(String s) {
		return new DPRecursive().subsequence(s);
	}
	
	/**
	 *  打印String的全排列 = 排列组合 = 顺序不同算一种
	 */
	
	public static List<String> getAllPermutation(String s) {
		return new DPRecursive().permutation(s);
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
	
	/**
	 * 打印n对所有有效括号组合,lc22
	 */
	public static List<String> getAllBrackets(int n) {
		return new DPRecursive().getAllBrackets(n); 
	}
	

	
	
}
