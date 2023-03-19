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
	
	public static void printSubsequence(String s) {
		List<String> answers =  new DPRecursive().subsequence(s);
		answers.stream().forEach(System.out::println);
	}
	
	/**
	 *  ��ӡString��ȫ���� = ������� = ˳��ͬ��һ��
	 */
	
	public static void printPermutation(String s) {
		List<String> answers =  new DPRecursive().permutation(s);
		answers.stream().forEach(System.out::println);
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
	
	
}
