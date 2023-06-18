package com.algo.util.backtracking;

/**
 * 
 * backtracking 回溯 = 递归 ： 父过程调子过程<br>
 * 
 *  - 任何递归都可以改成非递归
 *  - 递归何时结束 ： Base case
 *	
 */
public class BacktrackUtil {

	/**
	 * 求Array中最大值： 故意用递归实现来理解
	 */
	public static int getArrayMax(int[] arr) {
		return f(arr, 0, arr.length -1);
	}
	
	private static int f(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int mid = L + (L+R) / 2;
		
		// 左侧的Max -> 创建系统栈压入
		int leftMax = f(arr,  L,  mid);
		// 右侧的Max
		int rightMax = f(arr,  mid,  R);
		
		// 比较一下
		return Math.max(leftMax, rightMax);
	}
	
}
