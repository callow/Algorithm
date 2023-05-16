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
		// arr[i] != -1 ��ʾarr[i]������ֻ�û������
		// arr[i] == -1 ��ʾarr[i]��������Ѿ�������
		// ���ϣ�arr��1~choose
		return pick(arr, total);
	}

	/**
	 * 
	 * ��ǰ�ֵ������ã�����ֻ��ѡ����arr�л����ڵ����֣���ʣrest��ôֵ���������ֻ᲻��Ӯ
	 */
	public static boolean pick(int[] arr, int rest) {
		if (rest <= 0) {
			return false; // ��������Ѿ��������ֱ������
		}
		// ����ȥ�������е����
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				int cur = arr[i];
				arr[i] = -1; // ����
				boolean next = pick(arr, rest - cur); // ���ֵĺ������̣��㱨��˭Ӯ��
				arr[i] = cur; // ���곢�Ժ󣬰����ֻ�ԭ��ȥ���ָ��ֳ���
				if (!next) { // ���ֵĺ������̸������֣�(�Ǻ������̵ĺ���Ӯ�� = �ǵ�ǰ���̵�����Ӯ��)
					return true;
				}
			}
		}
		return false;
	}

}
