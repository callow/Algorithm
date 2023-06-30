package com.algo.tasks.day6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个arr[], 可以切分为若干个不相交的数组，其中有一种最优解使得切出异或和=0的子数组最多，返回最多多少个？
 * 
 *
 */
public class MostXorZero {
	/**
	 * 
	 * 暴力解
	 */
	public static int mostXorZero1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		// 异或分组技巧 ： 生成 异或和 预处理数组
		int[] eor = new int[N];
		eor[0] = arr[0];
		for (int i = 1; i < N; i++) {
			eor[i] = eor[i-1] ^ arr[i];
		}
		
		return f(eor, 1, new ArrayList<>());
	}
	
	/**
	 * 
	 * @param eor : 固定的
	 * @param index ： 当前来到Index位置
	 * @param parts ： 放着切分的每个部分结束的位置
	 * index去决定：前一坨部分是否结束，如果结束(切分)就把index放入parts中，如果不结束(不切)就不放
	 */
	private static int f(int[] eor, int index, List<Integer> parts) {
		int ans = 0;
		if (index == eor.length) { // 数组最后的时候
			parts.add(eor.length);
			ans = eorZeroParts(eor, parts); // 统计一下切了及份
			parts.remove(parts.size() - 1); // 深度优先遍历 清理现场
		} else {
			// 没有把index放入parts中， 前一部分不结束
			int p1 = f(eor, index + 1, parts);
			parts.add(index);
			int p2 = f(eor, index + 1, parts);
			parts.remove(parts.size() - 1);
			ans = Math.max(p1, p2);
		}
		return ans;
	}
	// 最后的统计：统计所有隔断
	private static int eorZeroParts(int[] eor, List<Integer> parts) {
		int L = 0;
		int ans = 0;
		for (Integer end : parts) {
			if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
				ans++;
			}
			L = end;
		}
		return ans;
	}
	
	// 时间复杂度O(N)的方法
	public static int mostXor(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		
		// key 某一个前缀异或和
		// value 这个前缀异或和上次出现的位置(最晚！)
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		// 0~i整体的异或和
		int xor = 0;
		for (int i = 0; i < N; i++) {
			xor ^= arr[i];
			if (map.containsKey(xor)) { // 可能性2
				int pre = map.get(xor);
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			if (i > 0) { // 要和dp[i-1] pk一下，因此一定要保证i>0
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}
			map.put(xor, i);
		}
		return dp[N - 1];
	}
	
	
}
