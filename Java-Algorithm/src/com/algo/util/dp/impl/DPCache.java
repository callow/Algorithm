package com.algo.util.dp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.util.dp.DPService;

/**
 * ɵ���� = ���仯���� = �Ӷ����µĶ�̬�滮
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
		// dp���ǻ����
		// dp[cur][rest] == -1 -> process1(cur, rest)֮ǰû�����
		// dp[cur][rest] != -1 -> process1(cur, rest)֮ǰ���������ֵ��dp[cur][rest]
		// N+1 * K+1
		return go(start, K, aim, N, dp);
	}

	// cur ��: 1 ~ N
	// rest ����0 ~ K
	public static int go(int cur, int rest, int aim, int N, int[][] dp) {
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		// ֮ǰû�����
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
		dp[cur][rest] = ans; // �����ڷ���֮ǰ�ŵ�������
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

	// arr[L..R]�����ֻ�õ���÷�������
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

	// // arr[L..R]�����ֻ�õ���÷�������
	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int p1 = f2(arr, L + 1, R, fmap, gmap); // ����������Lλ�õ���
			int p2 = f2(arr, L, R - 1, fmap, gmap); // ����������Rλ�õ���
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
		int N = stickers.length;
		// �ؼ��Ż�(�ô�Ƶ2ά����������ֽ����)
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		Map<String, Integer> cache = new HashMap<>();
		cache.put("", 0);
		int ans = process(counts, target, cache);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// stickers[i] ���飬����i����ֽ���ַ�ͳ�� int[][] stickers -> ���е���ֽ
	// ÿһ����ֽ����������
	// ���ظ㶨target����������
	// ��������
	private int process(int[][] stickers, String t, Map<String, Integer> cache) {
		if (cache.containsKey(t)) {
			return cache.get(t);
		}
		// target������Ƶͳ��
		// target aabbc 2 2 1..
		// 0 1 2..
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// ���Ե�һ����ֽ��˭
			int[] sticker = stickers[i];

			// ��ؼ����Ż�(��Ҫ�ļ�֦!��һ��Ҳ��̰��!)
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process(stickers, rest, cache));
			}
		}
		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
		cache.put(t, ans);
		return ans;
	}

	@Override
	public int longestCommonSubsequence(String str1, String str2) {
		return 0;
	}
}
