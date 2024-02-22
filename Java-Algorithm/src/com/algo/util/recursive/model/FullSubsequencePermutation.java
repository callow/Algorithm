package com.algo.util.recursive.model;

import java.util.HashSet;
import java.util.Set;

/**
 * �����ַ�����ȫ�������� ��  https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
 * 
 * https://leetcode.com/problems/subsets/
 */
public class FullSubsequencePermutation {
	
	public static StringBuilder path = new StringBuilder();
	public static Set<String> result = new HashSet<>();
	
	public static String[] permutation(String str) {
		dfs(str.toCharArray(), 0);
		return result.toArray(String[]::new);
		
	}
	// ��ǰ����a[i]λ�ã�֮ǰ�ռ���·��path
	static void dfs(char[] a, int i) {
		if (i == a.length) {
			result.add(path.toString());
		} else {
			path.append(a[i]);
			dfs(a, i+1); // ȥi+1λ��
			path.deleteCharAt(path.length() - 1); // �ָ��ֳ�, ɾ��
			dfs(a, i+1);
		}
	}
}
