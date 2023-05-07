package com.algo.util.datascale_guess;

import com.algo.util.bit.BitUtil;

/**
 * 1. ��ӡһЩ������ҹ���������O(1)<br>
 * 
 * 2. ������ĿҪ��������͸��Ӷȼ���<br>
 * 
 * C/C++ ÿ��10^8ָ��O(1). Java - O(1)ָ������10^8��2-4s<br>
 * 
 * - ����arr��10^6,ȥ��Java 2-4s���һ������W���򲻿��Գ���O(N^2),��ΪO((10^6)^2) > 10^8<br>
 * - ����arr��10^3,ȥ��Java 2-4s���һ������W����O(N^2)�Ϳ�����,��ΪO((10^3)^2) < 10^8<br>
 * 
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
