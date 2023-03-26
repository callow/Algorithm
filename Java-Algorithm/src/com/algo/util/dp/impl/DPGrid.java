package com.algo.util.dp.impl;

import java.util.List;

import com.algo.util.dp.DPService;

/**
 * 
 * 严格表结构，dp[][] 就是 傻缓存的全部大小
 */
public class DPGrid implements DPService {

	@Override
	public void hanoi(int n) {}
	@Override
	public List<String> subsequence(String n) {return null;}
	@Override
	public List<String> permutation(String n) {return null;}
	@Override
	public List<String> getAllBrackets(int n) {return null;}
	
	@Override
	public Integer uniquePaths(int row, int col) {
        int[] dp = new int[row];

        for(int i = 0; i < row; i++){ // fill 1st col = 1
            dp[i] = 1;
        }

        for(int j = 1; j < col; j++){
            for(int i = 1; i < row; i++){
                dp[i] = dp[i] + dp[i - 1];
            }
        }

        return dp[row - 1]; // row of paths
	}
	
	// (cur,rest)
	@Override
	public Integer uniqueWays(int N, int start, int aim, int K) {
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		int[][] dp = new int[N + 1][K + 1];
		dp[aim][0] = 1;
		for (int rest = 1; rest <= K; rest++) {
			dp[1][rest] = dp[2][rest - 1]; // 填第一行
			for (int cur = 2; cur < N; cur++) {
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1]; // 填中间行
			}
			dp[N][rest] = dp[N - 1][rest - 1]; // 填最后一行
		}
		return dp[start][K];
	}

}
