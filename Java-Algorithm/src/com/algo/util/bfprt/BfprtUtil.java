package com.algo.util.bfprt;

import java.util.Arrays;

import com.algo.util.bfprt.model.Bfprt;
import com.algo.util.common.CommonArrayUtil;


public class BfprtUtil {

	/**
	 * �������������ҵ�KthС���� O(n)
	 */
	
	public static int findMinKth(int[] array, int k ) {
		return Bfprt.findMinKth(array, k);
	}
	
	/**
	 * ��������������ǰK������������ҷ��ص����ݻ�Ҫ����O(nLog(k))
	 */
	public static int[] findTopK(int[] arr, int k) { 
		if (CommonArrayUtil.isEmpty(arr)) {
			return new int[0];
		}
		int N = arr.length;
		k = Math.min(N, k); // ���KС����
		// O(N)����ǰ100������������10000-100С���� ��Ҳ���ǵ�100�����
		int num = Bfprt.findMinKth(arr, N - k);
		int[] ans = new int[k];
		int index = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] > num) {
				ans[index++] = arr[i];
			}
		}
		for (; index < k; index++) {
			ans[index] = num;
		}
		// O(k*logk) ���k���ȵ�С�����Ÿ���
		Arrays.sort(ans);
		for (int L = 0, R = k - 1; L < R; L++, R--) {
			CommonArrayUtil.swap(ans, L, R);
		}
		return ans;
	}
	
}
