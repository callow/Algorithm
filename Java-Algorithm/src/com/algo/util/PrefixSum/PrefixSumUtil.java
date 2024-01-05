package com.algo.util.PrefixSum;

import com.algo.util.PrefixSum.model.PrefixSumArray;

/**
 * ǰ׺�����鼼��
 */
public class PrefixSumUtil {

	/**
	 * ��o(1) ʵ�� L ~ R���ۼӺ�
	 */
	public static int sumRange(int[] arr, int left, int right) {
		PrefixSumArray prefixSum = new PrefixSumArray(arr); // ����ǰ׺������
		return prefixSum.sum[right + 1] - prefixSum.sum[left]; // ���һ��
	}
}
