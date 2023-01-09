package com.algo.util.Monostack;

import java.util.Stack;

import com.algo.util.common.CommonArrayUtil;

public class MonotonicStackUtil {
	
	/**
	 * 一个正数Array,任何子数组sub, 可算出 A = ∑sub * Min(sub) ， 求所有子数组中这个A的最大值是多少？<br><br>
	 * https://leetcode.com/problems/maximum-subarray-min-product/<br>
	 * 
	 * 思路： 一定要以i(每个位置)为最小值，且i左右范围怎么扩的最远！=> 单调栈 
	 */
	public static int getMaxIndicator(int[] arr) {
		int[] sums = CommonArrayUtil.generateSumArray(arr);
		
		int max = Integer.MIN_VALUE;
		int size = arr.length;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < size; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
				int j = stack.pop();
				max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
		}
		return max;
	}
	
	/**
	 * 一个arr表示histogram,求最大长方形面积？<br><br>
	 * 
	 * https://leetcode.com/problems/largest-rectangle-in-histogram<br>
	 * 
	 * 思路：一定要以i(每个位置)为做高，且i左右范围怎么扩的最远！=> 单调栈 <br>
	 * 
	 * 优化: 使用Array实现的栈来替代系统的Stack
	 * 
	 */
	
	public static int maxHistogram(int[] height) {
		if (CommonArrayUtil.isEmpty(height)) { 
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}
	
	/**
	 *  0/1组成的Matrix, 返回里面1组成最大的矩形？<br><br>
	 *  
	 *  思路： 
	 *  
	 */
	public static int maxMatrix(int[][] matrix) {
		return 0;
		
	}
}
