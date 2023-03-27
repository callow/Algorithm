package com.algo.util.dp;

import java.util.List;
import java.util.Stack;

import com.algo.util.dp.impl.DPGrid;
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
	
	/**
	 * 
	 * m * n ���̣������Ͻ�ȥ���½��ж�����·
	 * 
	 */
	public static int uniquePaths(int m, int n) {
		return new DPGrid().uniquePaths(m, n);
	}
	
	/**
	 * һάһ����n��λ�� �����˴�start*ֻ��k����aim~�ж��ٷ���
	 * 1 2 3 4 5 6 7 = N
	 *   *     ~
	 */
	public static int uniqueWays(int n, int start, int aim, int k) {
		return new DPGrid().uniqueWays(n, start, aim, k);
	}
	
	/**
	 * һ���˿ˣ�N��λ�ã�2���Ⱥ��������� �������Ӯ�����ػ�ʤ�ߵķ����� 
	 */
	
	public static int drawCardGame(int[] arr) {
		return new DPGrid().drawCardGame(arr);
	}
	
	/**
	 * �������⣺ w : ���������� v : �����ֵ�� bag: �������� 
	 */
	
	public static int knapsackMaxValue(int[] w, int[] v, int bag) {
		return new DPGrid().knapsackMaxValue(w, v, bag);
	}
}