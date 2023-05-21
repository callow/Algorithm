package com.algo.util.bitmask_dp.impl;

import com.algo.util.bit.BitUtil;
import com.algo.util.bitmask_dp.StateCompressionDPService;

public class DPStateCompressiondp implements StateCompressionDPService {

	/**
	 * status - > 2^N
	 * O(N*2^N)
	 */
	@Override
	public boolean canWin(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		int[] dp = new int[1 << (choose + 1)]; // dp表准备这么大，e.g: 11111 全1的状态是最大 所以就是准备的大小100000
		// dp[status] == 1 true
		// dp[status] == -1 false
		// dp[status] == 0 process(status) 没算过！去算！
		return pick(choose, 0, total, dp);
	}
	
	// 为什么明明status和rest是两个可变参数，却只用status来代表状态(也就是dp)
	// 因为选了一批数字之后，得到的和一定是一样的，所以rest是由status决定的，所以rest不需要参与记忆化搜索
	public static boolean pick(int choose, int status, int rest, int[] dp) {
		if (dp[status] != 0) {
			return dp[status] == 1 ? true : false;
		}
		boolean ans = false;
		if (rest > 0) {
			for (int i = 1; i <= choose; i++) {
				if (((1 << i) & status) == 0) {
					if (!pick(choose, (status | (1 << i)), rest - i, dp)) {
						ans = true;
						break;
					}
				}
			}
		}
		dp[status] = ans ? 1 : -1;
		return ans;
	}

	/**
	 * 比较难，只改到cache那个解就可以了
	 */
	@Override
	public int tsp(int[][] distances) {
		int N = distances.length; // 0...N-1
		int statusNums = 1 << N;
		int[][] dp = new int[statusNums][N];

		for (int status = 0; status < statusNums; status++) {
			for (int start = 0; start < N; start++) {
				if (BitUtil.isOneAtIndex(status, start)) {
					
					if (status == BitUtil.rightMostOne(status)) {
						
						dp[status][start] = distances[start][0];
					} else {
						int min = Integer.MAX_VALUE;
						// start 城市在status里去掉之后，的状态
						int preStatus = BitUtil.remove1AtIndex(status, start);  
						
						// start -> i
						for (int i = 0; i < N; i++) {
							if (BitUtil.isOneAtIndex(preStatus, i)) {  
								int cur = distances[start][i] + dp[preStatus][i];
								min = Math.min(min, cur);
							}
						}
						dp[status][start] = min;
					}
				}
			}
		}
		return dp[statusNums - 1][0];
	}

	@Override
	public int paveBricks(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int big = N > M ? N : M;
		int small = big == N ? M : N;
		int sn = 1 << small;
		int limit = sn - 1;
		int[] dp = new int[sn];
		dp[limit] = 1;
		int[] cur = new int[sn];
		for (int level = 0; level < big; level++) {
			for (int status = 0; status < sn; status++) {
				if (dp[status] != 0) {
					int op = (~status) & limit;
					dfs(dp[status], op, 0, small - 1, cur);
				}
			}
			for (int i = 0; i < sn; i++) {
				dp[i] = 0;
			}
			int[] tmp = dp;
			dp = cur;
			cur = tmp;
		}
		return dp[limit];
	}
	
	public static void dfs(int way, int op, int index, int end, int[] cur) {
		if (index == end) {
			cur[op] += way;
		} else {
			dfs(way, op, index + 1, end, cur);
			if (((3 << index) & op) == 0) { // 11 << index 可以放砖
				dfs(way, op | (3 << index), index + 1, end, cur);
			}
		}
	}

}
