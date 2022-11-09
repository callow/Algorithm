package com.algo.util.sort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.algo.util.common.CommonArrayUtil;
/**
 * n*log(n)
 */
public class QuickSortUtil {
	/**
	 * 
	 * �������켼�� : ����ֲ�partitin�� <X�����, =X���м䣬>X���ұ� <br>
	 *  - ���ⷽ���� ������ͷ����2���߽� (<��)  �� (>��)�� ����ָ��i�����ƣ�����һ�����м��ƶ���i��(>��)ײ��ͣ<br>
	 *  	- i < x ? swap(i,next(<��)) <br>
	 *      - i = x ? i++ <br>
	 *      - i > x ? swap(i,pre(>��)) , >��������--���� i = i <br>
	 *      һ�������һ����arr[R]��ΪX <br>
	 *      return �����������ұ߽緶Χ
	 */
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1; // < �� �ұ߽�
		int more = R; // > �� ��߽�
		int index = L; 
		while (index < more) { // ��ǰλ��index�����ܺ� >������߽�ײ��
			if (arr[index] == arr[R]) {
				index++;
			} else if (arr[index] < arr[R]) {
				CommonArrayUtil.swap(arr, index++, ++less);
			} else {
				CommonArrayUtil.swap(arr, index, --more);
			}
		}
		CommonArrayUtil.swap(arr, more, R);  // <[R]   =[R]   >[R]
		return new int[] { less + 1, more };
	}
	
	/**
	 * ��ͣ�غ������������õݹ�ʵ��QuickSort
	 */
	
	public static void recursiveSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		process(arr, 0, arr.length - 1);
	} 
	private static void process(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		CommonArrayUtil.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = netherlandsFlag(arr, L, R); // ������������С���ҷ�Χ
		process(arr, L, equalArea[0] - 1); // ȥ�����
		process(arr, equalArea[1] + 1, R); // ȥ�ұ���
	}
	
	/**
	 * ��Stack + �������� ʵ��QuickSort
	 */
	
	public static void stackSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		int N = arr.length;
		CommonArrayUtil.swap(arr, (int) (Math.random() * N), N - 1);
		int[] equalArea = netherlandsFlag(arr, 0, N - 1);
		int equalLeft = equalArea[0];
		int equalRight = equalArea[1];
		Stack<Range> stack = new Stack<>();
		
		stack.push(new Range(0, equalLeft - 1)); // 0 ~ equalLeft(�ζ�) - ��
		stack.push(new Range(equalRight + 1, N - 1)); // equalRight ~ N (��) - ��
		
		while(!stack.isEmpty()) {
			Range op = stack.pop(); // op.l ... op.r ����ջ����Range�ó���
			if (op.l < op.r) {
				CommonArrayUtil.swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r); // ������������С���ҷ�Χ
				equalLeft = equalArea[0];
				equalRight = equalArea[1];
				stack.push(new Range(op.l, equalLeft - 1)); // (�ζ�) - ��
				stack.push(new Range(equalLeft + 1, op.r)); // (��) - ��
				
			}
		}
	}
	
	/**
	 * ��Queue + �������� ʵ��QuickSort
	 */
	
	public static void queueSort(int[] arr) {
		if (CommonArrayUtil.isEmpty(arr) || CommonArrayUtil.hasOne(arr)) {
			return;
		}
		int N = arr.length;
		CommonArrayUtil.swap(arr, (int) (Math.random() * N), N - 1);
		int[] equalArea = netherlandsFlag(arr, 0, N - 1);
		int equalLeft = equalArea[0];
		int equalRight = equalArea[1];
		Queue<Range> queue = new LinkedList<>();
		
		queue.offer(new Range(0, equalLeft - 1)); // ��1�� ����
		queue.offer(new Range(equalRight + 1, N - 1)); // ��2�� ���ң�
		
		while (!queue.isEmpty()) {
			Range op = queue.poll();
			if (op.l < op.r) {
				CommonArrayUtil.swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r); // ������������С���ҷ�Χ
				equalLeft = equalArea[0];
				equalRight = equalArea[1];
				queue.offer(new Range(op.l, equalLeft - 1)); // ��1�� ����
				queue.offer(new Range(equalRight + 1, op.r)); // ��2�� ���ң�
			}
		}
	}
	
	// Ҫ�������ʲô��Χ�ϵ�����
	private static class Range {
		public int l;
		public int r;

		public Range(int left, int right) {
			l = left;
			r = right;
		}
	}

}
