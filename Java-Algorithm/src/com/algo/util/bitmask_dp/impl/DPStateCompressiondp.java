package com.algo.util.bitmask_dp.impl;

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
		int[] dp = new int[1 << (choose + 1)]; // dp��׼����ô��e.g: 11111 ȫ1��״̬����� ���Ծ���׼���Ĵ�С100000
		// dp[status] == 1 true
		// dp[status] == -1 false
		// dp[status] == 0 process(status) û�����ȥ�㣡
		return pick(choose, 0, total, dp);
	}
	
	// Ϊʲô����status��rest�������ɱ������ȴֻ��status������״̬(Ҳ����dp)
	// ��Ϊѡ��һ������֮�󣬵õ��ĺ�һ����һ���ģ�����rest����status�����ģ�����rest����Ҫ������仯����
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

}
