package com.algo.util.prime;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 欧拉筛
 * 
 *  - 统计0 ~ n范围内的质数
 *  - O(n)
 *  - 常数时间大
 */
public class Euler {

	public static int count(int n) {
		// visit[i] = true，代表i是合数
		// visit[i] = false，代表i是质数
		// 初始时认为0~n所有数都是质数
		boolean[] visit = new boolean[n + 1];
		// prime收集所有的质数，收集的个数是cnt
		int[] prime = new int[n / 2 + 1];
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				prime[cnt++] = i;
			}
			for (int j = 0; j < cnt; j++) {
				if (i * prime[j] > n) {
					break;
				}
				visit[i * prime[j]] = true;
				if (i % prime[j] == 0) {
					break;
				}
			}
		}
		return cnt;
	}
	
	public static List<Integer> collect(int n) {
		// visit[i] = true，代表i是合数
		// visit[i] = false，代表i是质数
		// 初始时认为0~n所有数都是质数
		boolean[] visit = new boolean[n + 1];
		// prime收集所有的质数，收集的个数是cnt
		int[] prime = new int[n / 2 + 1];
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (!visit[i]) {
				prime[cnt++] = i;
			}
			for (int j = 0; j < cnt; j++) {
				if (i * prime[j] > n) {
					break;
				}
				visit[i * prime[j]] = true;
				if (i % prime[j] == 0) {
					break;
				}
			}
		}
		return Arrays.stream(prime).boxed().collect(Collectors.toList());
	}
}
