package com.algo.util.dp;

import java.util.List;

import com.algo.util.dp.impl.DPRecursive;

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
	
	
}
