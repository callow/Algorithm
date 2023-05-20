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
	}

	// �κ���������֮��ľ��룬������matrix�����õ�
	// set�б�ʾ����Щ���еļ��ϣ�
	// start������һ����set�
	// ��start������Ҫ��set�����еĳ��й�һ�飬���ջص�0�������У���С�����Ƕ���
	public static int go(int[][] matrix, int cityStatus, int start) {
		
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

}
