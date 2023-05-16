package com.algo.util.bitmask_dp.impl;

import com.algo.util.bitmask_dp.StateCompressionDPService;

public class DPStateCompressiondp implements StateCompressionDPService {

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

}
