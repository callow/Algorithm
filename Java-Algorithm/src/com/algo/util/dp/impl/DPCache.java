package com.algo.util.dp.impl;

import java.util.List;

import com.algo.util.dp.DPService;

/**
 * ɵ���� = ���仯���� = �Ӷ����µĶ�̬�滮
 */

public class DPCache implements DPService {

	@Override
	public void hanoi(int n) {}
	@Override
	public List<String> subsequence(String n) {return null;}
	@Override
	public List<String> permutation(String n) {return null;}
	@Override
	public List<String> getAllBrackets(int n) {return null;}
	@Override
	public Integer uniquePaths(int m, int n) {return null;}
	
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
}
