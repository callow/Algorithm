package com.algo.util.bitmask_dp.impl;

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
				if (!pick(choose, taken, rest - i)) {
					return true;
				}
			}
		}
		return false;
	}

}
