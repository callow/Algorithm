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
	 * ��ǰ�ֵ������ã����ֿ�����1~choose�е��κ�һ������ <br>
	 * status: iλ���Ϊ0������û�ã���ǰ������,iλΪ1�������Ѿ��ù��ˣ���ǰ������<br>
	 * ��ʣrest��ôֵ<br>
	 * �������ֻ᲻��Ӯ
	 */
	public static boolean pick(int choose, int status, int rest) {
		if (rest <= 0) {
			return false;
		}
		for (int i = 1; i <= choose; i++) {
			if (((1 << i) & status) == 0) { // i ������֣��Ǵ�ʱ���ֵľ���������ѡ���˵�i����
				int taken = (status | (1 << i)); // ���� = ����iλ��1
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
		// 7���� 1111111
		int allCity = (1 << N) - 1;
		return go(distances, allCity, 0);
		
		// return tspDpCache(distances);
	}

	public int go(int[][] matrix, int cityStatus, int start) {
		
		// ���city��ֻ��һ��1��
		if (cityStatus == BitUtil.rightMostOne(cityStatus)) {
			return matrix[start][0];
		}

		// ��startλ��1ȥ���� = set null
		cityStatus = BitUtil.remove1AtIndex(cityStatus, start);
		
		int min = Integer.MAX_VALUE;
		// ö�����еĳ���
		for (int move = 0; move < matrix.length; move++) {
			
			if (BitUtil.has1AtIndex(cityStatus, move)) { // ��moveλ�ô���1
				
				int cur = matrix[start][move] + go(matrix, cityStatus, move);
				min = Math.min(min, cur);
			}
		}
		cityStatus = BitUtil.make1AtIndex(cityStatus, start);
		return min;
	}
	
	public int tspDpCache(int[][] distances) {
		int N = distances.length; // 0...N-1
		// 7���� 1111111
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
			// ��startλ��1ȥ����
			cityStatus  = BitUtil.remove1AtIndex(cityStatus, start);;
			int min = Integer.MAX_VALUE;
			// ö�����еĳ���
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
	public int paveBricks(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int max = Math.max(N, M);
		int min = Math.min(N, M);
		int pre = (1 << min) - 1;
		int[][] dp = new int[1 << min][max + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		
		return process(pre, 0, max, min, dp);
	}
	
	public int process(int pre, int i, int N, int M, int[][] dp) {
		if (dp[pre][i] != -1) {
			return dp[pre][i];
		}
		int ans = 0;
		if (i == N) {
			ans = pre == ((1 << M) - 1) ? 1 : 0;
		} else {
			int op = ((~pre) & ((1 << M) - 1));
			ans = dfs(op, M - 1, i, N, M, dp);
		}
		dp[pre][i] = ans;
		return ans;
	}

	public int dfs(int op, int col, int level, int N, int M, int[][] dp) {
		if (col == -1) {
			return process(op, level + 1, N, M, dp);
		}
		int ans = 0;
		ans += dfs(op, col - 1, level, N, M, dp);
		if (col > 0 && (op & (3 << (col - 1))) == 0) {
			ans += dfs((op | (3 << (col - 1))), col - 2, level, N, M, dp);
		}
		return ans;
	}

}
