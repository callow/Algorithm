package com.algo.util.common;

public class CommonDPUtil {

	/**
	 * 生成dp预处理数组 
	 */
	public static int[][] getdp(int[] arr) {
		int size = arr.length; // 0~N-1
		int pick = arr.length + 1; // 1 ~ N
		int[][] dp = new int[size][pick];
		// get 不从0开始，因为拿0个无意义
		for (int get = 1; get < pick; get++) { // 1 ~ N
			int maxIndex = size - get;
			// i~N-1
			for (int i = size - get; i >= 0; i--) {
				if (arr[i] >= arr[maxIndex]) {
					maxIndex = i;
				}
				dp[i][get] = maxIndex;
			}
		}
		return dp;
	}
	
	/**
	 * 从arr从左往右中选pick个数 如何尽量大?
	 */
	public static int[] maxPick(int[] arr, int[][] dp, int pick) {
		int[] res = new int[pick];
		for (int resIndex = 0, dpRow = 0; pick > 0; pick--, resIndex++) {
			res[resIndex] = arr[dp[dpRow][pick]];
			dpRow = dp[dpRow][pick] + 1;
		}
		return res;
	}
}
