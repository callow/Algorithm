package com.algo.util.bitmask_dp.impl;

import com.algo.util.bit.BitUtil;
import com.algo.util.bitmask_dp.StateCompressionDPService;

public class DPStateCompressionCache implements StateCompressionDPService {

	@Override
	public boolean canWin(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		return pick(choose, 0, total);
	}

	/**
	 * 当前轮到先手拿，先手可以拿1~choose中的任何一个数字 <br>
	 * status: i位如果为0，代表没拿，当前可以拿,i位为1，代表已经拿过了，当前不能拿<br>
	 * 还剩rest这么值<br>
	 * 返回先手会不会赢
	 */
	public static boolean pick(int choose, int status, int rest) {
		if (rest <= 0) {
			return false;
		}
		for (int i = 1; i <= choose; i++) {
			if (((1 << i) & status) == 0) { // i 这个数字，是此时先手的决定！先手选择了第i个数
				int taken = (status | (1 << i)); // 拿走 = 将第i位标1
				boolean next = pick(choose, taken, rest - i);
				if (!next) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int tsp(int[][] distances) {
		int N = distances.length; // 0...N-1
		// 7座城 1111111
		int allCity = (1 << N) - 1;
		return go(distances, allCity, 0);
		
		// return tspDpCache(distances);
	}

	public int go(int[][] matrix, int cityStatus, int start) {
		
		// 如果city中只有一个1了
		if (cityStatus == BitUtil.rightMostOne(cityStatus)) {
			return matrix[start][0];
		}

		// 把start位的1去掉， = set null
		cityStatus = BitUtil.remove1AtIndex(cityStatus, start);
		
		int min = Integer.MAX_VALUE;
		// 枚举所有的城市
		for (int move = 0; move < matrix.length; move++) {
			
			if (BitUtil.has1AtIndex(cityStatus, move)) { // 在move位置存在1
				
				int cur = matrix[start][move] + go(matrix, cityStatus, move);
				min = Math.min(min, cur);
			}
		}
		cityStatus = BitUtil.make1AtIndex(cityStatus, start);
		return min;
	}
	
	public int tspDpCache(int[][] distances) {
		int N = distances.length; // 0...N-1
		// 7座城 1111111
		int allCity = (1 << N) - 1;
		int[][] dp = new int[1 << N][N];
		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		return goDP(distances, allCity, 0, dp);
	}
	
	public int goDP(int[][] matrix, int cityStatus, int start, int[][] dp) {
		if (dp[cityStatus][start] != -1) {
			return dp[cityStatus][start];
		}
		if (cityStatus == BitUtil.rightMostOne(cityStatus)) { 
			dp[cityStatus][start] = matrix[start][0];
		} else {
			// 把start位的1去掉，
			cityStatus  = BitUtil.remove1AtIndex(cityStatus, start);;
			int min = Integer.MAX_VALUE;
			// 枚举所有的城市
			for (int move = 0; move < matrix.length; move++) {
				if (BitUtil.has1AtIndex(cityStatus, move)) {
					int cur = matrix[start][move] + goDP(matrix, cityStatus, move, dp);
					min = Math.min(min, cur);
				}
			}
			cityStatus  = BitUtil.make1AtIndex(cityStatus, start);
			dp[cityStatus][start] = min;
		}
		return dp[cityStatus][start];
	}

	@Override
	public int paveBricks(int n, int m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
