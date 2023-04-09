package com.algo.util.dp.impl;

import java.util.List;

import com.algo.util.dp.DPService;

/**
 * 傻缓存 = 记忆化搜索 = 从顶向下的动态规划
 */

public class DPCache implements DPService {

	@Override
	public void hanoi(int n) {
	}

	@Override
	public List<String> subsequence(String n) {
		return null;
	}

	@Override
	public List<String> permutation(String n) {
		return null;
	}

	@Override
	public List<String> getAllBrackets(int n) {
		return null;
	}

	@Override
	public Integer uniquePaths(int m, int n) {
		return null;
	}

	@Override
	public Integer uniqueWays(int N, int start, int aim, int K) {

		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}

		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		// dp就是缓存表
		// dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
		// dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
		// N+1 * K+1
		return go(start, K, aim, N, dp);
	}

	// cur 范: 1 ~ N
	// rest 范：0 ~ K
	public static int go(int cur, int rest, int aim, int N, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		// 之前没算过！
		int ans = 0;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		} else if (cur == 1) {
			ans = go(2, rest - 1, aim, N, dp);
		} else if (cur == N) {
			ans = go(N - 1, rest - 1, aim, N, dp);
		} else {
			ans = go(cur - 1, rest - 1, aim, N, dp) + go(cur + 1, rest - 1, aim, N, dp);
		}
		dp[cur][rest] = ans; // 将答案在返回之前放到缓存里
		return ans;

	}

	@Override
	public Integer drawCardGame(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fmap[i][j] = -1;
				gmap[i][j] = -1;
			}
		}
		int first = f2(arr, 0, arr.length - 1, fmap, gmap);
		int second = g2(arr, 0, arr.length - 1, fmap, gmap);
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (fmap[L][R] != -1) {
			return fmap[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else {
			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
			ans = Math.max(p1, p2);
		}
		fmap[L][R] = ans;
		return ans;
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
			int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
			ans = Math.min(p1, p2);
		}
		gmap[L][R] = ans;
		return ans;
	}

	@Override
	public int knapsackMaxValue(int[] w, int[] v, int bag) {
		return 0;
	}

	@Override
	public int convertNumToLetter(String str) {
		return 0;
	}

	@Override
	public int minStickersToSpellWords(String[] stickers, String target) {
		// TODO Auto-generated method stub
		return 0;
	}
}
