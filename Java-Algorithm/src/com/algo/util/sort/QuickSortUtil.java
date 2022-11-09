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
	 * 荷兰国旗技巧 : 三块分层partitin： <X放左边, =X放中间，>X放右边 <br>
	 *  - 解题方法： 数组两头划出2个边界 (<区)  和 (>区)， 随着指针i的右移，他们一起向中间移动。i与(>区)撞上停<br>
	 *  	- i < x ? swap(i,next(<区)) <br>
	 *      - i = x ? i++ <br>
	 *      - i > x ? swap(i,pre(>区)) , >区左扩（--）， i = i <br>
	 *      一般用最后一个数arr[R]作为X <br>
	 *      return 相等区域的左右边界范围
	 */
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) {
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		int less = L - 1; // < 区 右边界
		int more = R; // > 区 左边界
		int index = L; 
		while (index < more) { // 当前位置index，不能和 >区的左边界撞上
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
	 * 不停地荷兰国旗排序，用递归实现QuickSort
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
		int[] equalArea = netherlandsFlag(arr, L, R); // 荷兰国旗来缩小左右范围
		process(arr, L, equalArea[0] - 1); // 去左边排
		process(arr, equalArea[1] + 1, R); // 去右边排
	}
	
	/**
	 * 用Stack + 荷兰国旗 实现QuickSort
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
		
		stack.push(new Range(0, equalLeft - 1)); // 0 ~ equalLeft(次顶) - 左
		stack.push(new Range(equalRight + 1, N - 1)); // equalRight ~ N (顶) - 右
		
		while(!stack.isEmpty()) {
			Range op = stack.pop(); // op.l ... op.r ，把栈顶的Range拿出来
			if (op.l < op.r) {
				CommonArrayUtil.swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r); // 荷兰国旗来缩小左右范围
				equalLeft = equalArea[0];
				equalRight = equalArea[1];
				stack.push(new Range(op.l, equalLeft - 1)); // (次顶) - 左
				stack.push(new Range(equalLeft + 1, op.r)); // (顶) - 右
				
			}
		}
	}
	
	/**
	 * 用Queue + 荷兰国旗 实现QuickSort
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
		
		queue.offer(new Range(0, equalLeft - 1)); // 第1个 （左）
		queue.offer(new Range(equalRight + 1, N - 1)); // 第2个 （右）
		
		while (!queue.isEmpty()) {
			Range op = queue.poll();
			if (op.l < op.r) {
				CommonArrayUtil.swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r); // 荷兰国旗来缩小左右范围
				equalLeft = equalArea[0];
				equalRight = equalArea[1];
				queue.offer(new Range(op.l, equalLeft - 1)); // 第1个 （左）
				queue.offer(new Range(equalRight + 1, op.r)); // 第2个 （右）
			}
		}
	}
	
	// 要处理的是什么范围上的排序
	private static class Range {
		public int l;
		public int r;

		public Range(int left, int right) {
			l = left;
			r = right;
		}
	}

}
