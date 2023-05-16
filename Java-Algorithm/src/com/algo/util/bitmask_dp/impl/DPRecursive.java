package com.algo.util.bitmask_dp.impl;

import com.algo.util.bitmask_dp.StateCompressionDPService;

public class DPRecursive implements StateCompressionDPService {

	@Override
	public boolean canWin(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		int[] arr = new int[choose];
		for (int i = 0; i < choose; i++) {
			arr[i] = i + 1;
		}
		// arr[i] != -1 表示arr[i]这个数字还没被拿走
		// arr[i] == -1 表示arr[i]这个数字已经被拿走
		// 集合，arr，1~choose
		return pick(arr, total);
	}

	/**
	 * 
	 * 当前轮到先手拿，先手只能选择在arr中还存在的数字，还剩rest这么值，返回先手会不会赢
	 */
	public static boolean pick(int[] arr, int rest) {
		if (rest <= 0) {
			return false; // 先手面对已经满的情况直接认输
		}
		// 先手去尝试所有的情况
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				int cur = arr[i];
				arr[i] = -1; // 拿走
				boolean next = pick(arr, rest - cur); // 先手的后续过程，汇报是谁赢了
				arr[i] = cur; // 做完尝试后，把数字还原回去（恢复现场）
				if (!next) { // 先手的后续过程告诉先手，(是后续过程的后手赢了 = 是当前过程的先手赢了)
					return true;
				}
			}
		}
		return false;
	}

}
