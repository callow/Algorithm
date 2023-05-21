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

	/**
	 * �Ƚ��ѣ�ֻ�ĵ�cache�Ǹ���Ϳ�����
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
						// start ������status��ȥ��֮�󣬵�״̬
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
			if (((3 << index) & op) == 0) { // 11 << index ���Է�ש
				dfs(way, op | (3 << index), index + 1, end, cur);
			}
		}
	}

}
