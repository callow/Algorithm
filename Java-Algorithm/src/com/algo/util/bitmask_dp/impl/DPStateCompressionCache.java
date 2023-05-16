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
				if (!pick(choose, taken, rest - i)) {
					return true;
				}
			}
		}
		return false;
	}

}
