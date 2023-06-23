package com.algo.tasks.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 自由之路： https://leetcode.com/problems/freedom-trail/
 *
 */
public class FreedomTrail {
	
	/**
	 * s : 电话机字符串
	 * k : 拨的目标字符串
	 * 如何拨的代价最低？
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
	 * 电话机字符串中： 指针指着的上一个按钮 preButton
	 * 拨的目标字符串中：此时要搞定哪个字符？ keyIndex
	 * map : <字符,位置们> ： f：1，3，6
	 * str: 目标串
	 */
	private static int f1(int preButton,int keyIndex, char[] str, Map<Character, List<Integer>> map, int N) {
		if (keyIndex == str.length) {
			return 0 ;
		}
		// 还有字符需要搞定 -> 继续拨号
		char cur = str[keyIndex];
		List<Integer> positions = map.get(cur);
		int result = Integer.MAX_VALUE;
		
		for (int next : positions) {

			/**
			 * dial(preButton,next,N) -> 拨号那里的代价
			   1 -> 按一下确认键
			   f1 -> 向下传递代价
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
		// dp[][] == -1 : 表示没算过！
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
			// 还有字符需要搞定呢！
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
	 * 从i1位置 拨 到 i2位置 如何最省
	 */
	public static int dial(int i1, int i2, int size) {
		return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
	}
}
