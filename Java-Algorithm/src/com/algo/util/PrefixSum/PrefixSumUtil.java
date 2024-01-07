package com.algo.util.PrefixSum;

import java.util.HashMap;

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
	
	
	/**
	 * ���������У��ۼӺ�Ϊk���������. https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5
	 * 
	 * ��0 ��i = 1000 k=100����õ���i��β�������飨�� = 100�� = ��ǰ׺��=900�������������
	 */
	
	public static int longestSubArraySumK(int[] arr, int k) {
		
		// key : ĳ��ǰ׺��  value : ���ǰ׺��������ֵ�λ��
		HashMap<Integer, Integer> map = new HashMap<>();
		map.clear();
		map.put(0, -1); // ��Ҫ : 0���ǰ׺�ͣ�һ������Ҳû�е�ʱ�򣬾ʹ�����
		int longestLength = 0;
		
		for (int i = 0, sum = 0; i < arr.length; i++) {
			sum += arr[i]; // ǰ׺��
			if (map.containsKey(sum - k)) { // �ҵ�ǰ׺��=900�������λ��
				longestLength = Math.max(longestLength, i - map.get(sum - k));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return longestLength;
		
		
	}
}
