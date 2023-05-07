package com.algo.util.datascale_guess;

import com.algo.util.bit.BitUtil;

/**
 * ��ӡһЩ������ҹ���������O(1)
 *
 */
public class DPDataScaleUtil {

	/**
	 * ���ֺͺ��֣��Բ���������4�Ĵη�(1,4,16,64...),��������n�ݣ�˭��Ӯ��<br>
	 * �⣺���ֹ����ǣ����Ⱥ�����
	 */

	public static String whoWin(int n) {
		if (n % 5 == 0 || n % 5 == 2) {
			return "����";
		} else {
			return "����";
		}
	}

	/**
	 * num�Ƿ���Ա�ʾһЩ���������Ħ���<br>
	 * 
	 * �⣺���ֹ����ǣ�2������false,������true<br>
	 * 
	 * ������ֻ��һ��1����2��ĳ�η�-> ���Ҳ�1���Լ���Ⱦ�֪�������
	 */
	public static boolean isContinuousSum(int num) {
		return BitUtil.rightMostOne(num) == num;
	}

}
