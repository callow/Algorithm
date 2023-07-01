package com.algo.tasks.day7;

import com.algo.util.bit.BitUtil;
import com.algo.util.common.CommonArrayUtil;

/**
 * ����һ��������ɵ����飬����һ������1��������������������(&)�Ľ�����.
 * 
 * 32λ��λ�����Ƿ��ܴ�1
 * 
 */
public class MaxAndValue {

	public static int maxAndValue2(int[] arr) {
		// arr[0...M-1]  arr[M....]
		int M = arr.length;
		int ans = 0;
		for (int bit = 30; bit >= 0; bit--) {
			// �������� arr[0...M-1]  �������� arr[M...]
			int i = 0;
			int tmp = M;
			
			// ֻ�� arr[0...M-1]����Ϊ�������Ĳ��ֲ��ÿ��� �Ǳ���̭��
			while (i < M) {
				if (!BitUtil.isOneAtIndex(arr[i], bit)) {
					CommonArrayUtil.swap(arr, i, --M); // ��ǰ������������ǰһ������������������
				} else {
					i++;
				}
			}
			if (M == 2) { // arr[0,1] , ֻ��0��1λ�ò���������
				return arr[0] & arr[1];
			}
			if (M < 2) { // �ڵ�bitλ�Ҳ�����M����ȥһ����Ҳ��ɾ
				M = tmp;
			} else { // > 2������bitλ����1��
				ans |= (1 << bit); // BitUtil.set1AtIndex(ans, bit)
			}
		}
		return ans;
	}
	
}
