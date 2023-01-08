package com.algo.util.Monostack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * ����ջ�� Ϊ�ҵ�Array��ÿ��λ��i ���/�ұ�[][]���Լ�����ұ��Լ�С��Ԫ�أ���O(n)<br><br>
 * 
 * 1. ����ջһ���Ǵ�ջ�׵�ջ������(С->��)��ջ <br>
 * 2. ������Ԫ��ʱ(<b>�߹�ʱ��</b>)��  <br>
 * 		A. ��ǰ�����ϳ������� = (�ұ������������С��).  <br>
 *      B. ������ѹ�ŵ��� = (��������������С��).<br> 
 *      C. ����������ջ�в���������������
 */

public class MonoStack {
	
	/**
	 * �ռ�������ÿ��λ�����ұ��Լ�С�ģ� ���ظ�����
	 */
	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		// ֻ��λ�ã�
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) { // ��������iλ�õ�����arr[i]
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
	 * �ռ�������ÿ��λ�����ұ��Լ�С�ģ� ���ظ�<br><br>
	 * 
	 * 1. ջ��λ����һ��С���� [{2,3,5} -> 4] <br>
	 * 2. ������һ��ʱ��λ�úϲ�
	 * 3. ������Ԫ��ʱ(<b>�߹�ʱ��</b>)��  <br>
  	     A. ��ǰ�����ϳ������� = (�ұ������������С��).  <br>
         B. ������ѹ�ŵ�<b>��������һ��</b> = (��������������С��).<br> 
         C. ����������ջ�в���������������
	 */
	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) { // i -> arr[i] ��ջ
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
