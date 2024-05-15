package com.algo.util.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * 埃式筛
 * 
 * - 0 ~ n范围内的质数
 * - O(n * log(logn)) , 调和级数
 * - 常数时间小
 */
public class Ehrlich {

	/**
	 * 统计个数
	 */
	public static int count(int n) {
		// visit[i] = true，代表i是合数
		// visit[i] = false，代表i是质数
		// 初始时认为0~n所有数都是质数
		boolean[] visit = new boolean[n + 1];
		for (int i = 2; i * i <= n; i++) {
			if (!visit[i]) {
				for (int j = i * i; j <= n; j += i) {
					visit[j] = true;
				}
			}
		}
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				// 此时i就是质数，可以收集，也可以计数
				cnt++;
			}
		}
		return cnt;
	}
	
	
	/**
	 * 收集结果
	 */
	public static List<Integer> collect(int n) {
		
		// visit[i] = true，代表i是合数
		// visit[i] = false，代表i是质数
		// 初始时认为0~n所有数都是质数
		boolean[] visit = new boolean[n + 1];
		for (int i = 2; i * i <= n; i++) {
			if (!visit[i]) {
				for (int j = i * i; j <= n; j += i) {
					visit[j] = true;
				}
			}
		}
		List<Integer> r = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				// 此时i就是质数，可以收集，也可以计数
				r.add(i);
			}
		}
		return r;
	}
}
