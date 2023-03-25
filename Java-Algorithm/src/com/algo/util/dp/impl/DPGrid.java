package com.algo.util.dp.impl;

import java.util.List;

import com.algo.util.dp.DPService;

/**
 * 
 * 严格表结构
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

}
