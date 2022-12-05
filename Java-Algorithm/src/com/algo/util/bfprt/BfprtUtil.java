package com.algo.util.bfprt;

import java.util.Arrays;

import com.algo.util.bfprt.model.Bfprt;
import com.algo.util.common.CommonArrayUtil;


public class BfprtUtil {

	/**
	 * 在无序数组种找第Kth小的数 O(n)
	 */
	
	public static int findMinKth(int[] array, int k ) {
		return Bfprt.findMinKth(array, k);
	}
	
	/**
	 * 在无序数组种找前K个大的数。而且返回的数据还要有序。O(nLog(k))
	 */
	public static int[] findTopK(int[] arr, int k) { 
		if (CommonArrayUtil.isEmpty(arr)) {
			return new int[0];
		}
		int N = arr.length;
		k = Math.min(N, k); // 求第K小的数
		// O(N)若求前100个，则就是求第10000-100小的数 ，也就是第100大的数
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
		// O(k*logk) 针对k长度的小数组排个序
		Arrays.sort(ans);
		for (int L = 0, R = k - 1; L < R; L++, R--) {
			CommonArrayUtil.swap(ans, L, R);
		}
		return ans;
	}
	
}
