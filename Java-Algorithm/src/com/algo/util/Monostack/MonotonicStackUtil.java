package com.algo.util.Monostack;

import java.util.Stack;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.MatrixUtil;

public class MonotonicStackUtil {
	
	/**
	 * һ������Array,�κ�������sub, ����� A = ��sub * Min(sub) �� �����������������A�����ֵ�Ƕ��٣�<br><br>
	 * https://leetcode.com/problems/maximum-subarray-min-product/<br>
	 * 
	 * ˼·�� һ��Ҫ��i(ÿ��λ��)Ϊ��Сֵ����i���ҷ�Χ��ô������Զ��=> ����ջ 
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
	 * һ��arr��ʾhistogram,����󳤷��������O(n)<br><br>
	 * 
	 * https://leetcode.com/problems/largest-rectangle-in-histogram<br>
	 * 
	 * ˼·��һ��Ҫ��i(ÿ��λ��)Ϊ���ߣ���i���ҷ�Χ��ô������Զ��=> ����ջ <br>
	 * 
	 * �Ż�: ʹ��Arrayʵ�ֵ�ջ�����ϵͳ��Stack
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
	 *  0/1��ɵ�Matrix, ��������1������ľ���1�ĸ�����O(n^2)<br><br>
	 *  ��ÿ����Ϊ�ף��Դ˵���Ϊֱ��ͼʱ�������Σ�����һ�����O(n^2)�Ϳ�����<br>
	 *  https://leetcode.com/problems/maximal-rectangle/<br>
	 *  ˼·�� ѹ��Matrix -> ��������histogram, Ȼ���
	 *  
	 */
	public static int maxMatrix(int[][] matrix) {
		return MatrixUtil.isEmpty(matrix) ? 0 
				: MatrixUtil.condenseToHistogram(matrix,0);
	}
	
	
	/**
	 *  0/1��ɵ�Matrix, ��������1��ɵ��Ӿ��θ�����<br><br>
	 *  https://leetcode.com/problems/count-submatrices-with-all-ones<br>
	 */
	
	public static int noOfSubMatrix(int[][] matrix) {
		
		
		return 0;
	}
}