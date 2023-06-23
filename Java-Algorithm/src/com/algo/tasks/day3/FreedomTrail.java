package com.algo.tasks.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * ����֮·�� https://leetcode.com/problems/freedom-trail/
 *
 */
public class FreedomTrail {
	
	/**
	 * s : �绰���ַ���
	 * k : ����Ŀ���ַ���
	 * ��β��Ĵ�����ͣ�
	 */
	public static int findRotateStepsByRecursion(String r, String k) {
		char[] ring = r.toCharArray();
		int N = ring.length;
		Map<Character, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			if (!map.containsKey(ring[i])) {
				map.put(ring[i], new ArrayList<>());
			}
			map.get(ring[i]).add(i);
		}
		return f1(0,0,k.toCharArray(),map,N);
	}
	
	/**
	 * �绰���ַ����У� ָ��ָ�ŵ���һ����ť preButton
	 * ����Ŀ���ַ����У���ʱҪ�㶨�ĸ��ַ��� keyIndex
	 * map : <�ַ�,λ����> �� f��1��3��6
	 * str: Ŀ�괮
	 */
	private static int f1(int preButton,int keyIndex, char[] str, Map<Character, List<Integer>> map, int N) {
		if (keyIndex == str.length) {
			return 0 ;
		}
		// �����ַ���Ҫ�㶨 -> ��������
		char cur = str[keyIndex];
		List<Integer> positions = map.get(cur);
		int result = Integer.MAX_VALUE;
		
		for (int next : positions) {

			/**
			 * dial(preButton,next,N) -> ��������Ĵ���
			   1 -> ��һ��ȷ�ϼ�
			   f1 -> ���´��ݴ���
			 */
			int cost = dial(preButton,next,N) + 1 + f1(next,keyIndex+1,str,map,N);
			result = Math.min(result, cost);
		}
		return result;
	}
	
	
	public static int findRotateStepsByDp(String r, String k) {
		char[] ring = r.toCharArray();
		int N = ring.length;
		Map<Character, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			if (!map.containsKey(ring[i])) {
				map.put(ring[i], new ArrayList<>());
			}
			map.get(ring[i]).add(i);
		}
		
		char[] str = k.toCharArray();
		int M = str.length;
		int[][] dp = new int[N][M + 1];
		// hashmap
		// dp[][] == -1 : ��ʾû�����
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= M; j++) {
				dp[i][j] = -1;
			}
		}
		
		return f2(0,0,k.toCharArray(),map,N,dp);
	}
	
	public static int f2(int preButton, int keyIndex, char[] str, Map<Character, List<Integer>> map, int N,int[][] dp) {
		if (dp[preButton][keyIndex] != -1) {
			return dp[preButton][keyIndex];
		}
		int ans = Integer.MAX_VALUE;
		if (keyIndex == str.length) {
			ans = 0;
		} else {
			// �����ַ���Ҫ�㶨�أ�
			char cur = str[keyIndex];
			List<Integer> positions = map.get(cur);
			for (int next : positions) {
				int cost = dial(preButton, next, N) + 1 + f2(next, keyIndex + 1, str, map, N, dp);
				ans = Math.min(ans, cost);
			}
		}
		dp[preButton][keyIndex] = ans;
		return ans;
	}
	
	
	
	
	/**
	 * ��i1λ�� �� �� i2λ�� �����ʡ
	 */
	public static int dial(int i1, int i2, int size) {
		return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
	}
}
