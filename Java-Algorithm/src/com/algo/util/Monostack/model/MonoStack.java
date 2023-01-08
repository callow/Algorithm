package com.algo.util.Monostack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈： 为找到Array中每个位置i 左边/右边[][]离自己最近且比自己小的元素，且O(n)<br><br>
 * 
 * 1. 单调栈一定是从栈底到栈顶有序(小->大)的栈 <br>
 * 2. 当弹出元素时(<b>高光时刻</b>)：  <br>
 * 		A. 当前把它赶出来的数 = (右边离它最近比它小的).  <br>
 *      B. 它底下压着的数 = (左边离它最近比它小的).<br> 
 *      C. 数组遍历完后，栈中残渣单独弹出即可
 */

public class MonoStack {
	
	/**
	 * 收集数组中每个位置左右比自己小的： 无重复数字
	 */
	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		// 只存位置！
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) { // 当遍历到i位置的数，arr[i]
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int j = stack.pop();
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
				res[j][0] = leftLessIndex;
				res[j][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
			res[j][0] = leftLessIndex;
			res[j][1] = -1;
		}
		return res;
	}
	
	/**
	 * 收集数组中每个位置左右比自己小的： 可重复<br><br>
	 * 
	 * 1. 栈中位置是一个小链表： [{2,3,5} -> 4] <br>
	 * 2. 当数字一样时，位置合并
	 * 3. 当弹出元素时(<b>高光时刻</b>)：  <br>
  	     A. 当前把它赶出来的数 = (右边离它最近比它小的).  <br>
         B. 它底下压着的<b>链表的最后一个</b> = (左边离它最近比它小的).<br> 
         C. 数组遍历完后，栈中残渣单独弹出即可
	 */
	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) { // i -> arr[i] 进栈
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				List<Integer> popIs = stack.pop();
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
				for (Integer popi : popIs) {
					res[popi][0] = leftLessIndex;
					res[popi][1] = i;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(Integer.valueOf(i));
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		while (!stack.isEmpty()) { 
			List<Integer> popIs = stack.pop();
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
			for (Integer popi : popIs) {
				res[popi][0] = leftLessIndex;
				res[popi][1] = -1;
			}
		}
		return res;
	}
}
