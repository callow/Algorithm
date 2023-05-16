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
		int[] dp = new int[1 << (choose + 1)]; // dp��׼����ô��e.g: 11111 ȫ1��״̬����� ���Ծ���׼���Ĵ�С100000
		// dp[status] == 1 true
		// dp[status] == -1 false
		// dp[status] == 0 process(status) û�����ȥ�㣡
		return pick(choose, 0, total, dp);
	}

}
