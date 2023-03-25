package com.algo.util.dp;

import java.util.List;
import java.util.Stack;

import com.algo.util.dp.impl.DPRecursive;
import com.algo.util.stack.StackUtil;

public class DPUtil {
	
	/**
	 * ��ŵ������, O(2^N - 1)
	 */
	
	public static void hanoi(int n) {
		new DPRecursive().hanoi(n);
	}
	
	/**
	 *  ��ӡString��ȫ��������,�����в���Ҫ����
	 */
	
	public static List<String> getAllSubsequence(String s) {
		return new DPRecursive().subsequence(s);
	}
	
	/**
	 *  ��ӡString��ȫ���� = ������� = ˳��ͬ��һ��
	 */
	
	public static List<String> getAllPermutation(String s) {
		return new DPRecursive().permutation(s);
	}
	
	/**
	 * ����ջ�����ǲ����ö�������ݽṹ
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
	 * ��ӡn��������Ч�������,lc22
	 */
	public static List<String> getAllBrackets(int n) {
		return new DPRecursive().getAllBrackets(n); 
	}
	

	
	
}
