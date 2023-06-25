package com.algo.tasks.day5;

import java.util.Stack;

import com.algo.util.common.model.BTNode;

/**
 * 
 * 给定一个搜索二叉树的先序遍历 节点无重复，还原出整棵树（head）？
 *
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
	
	public static BTNode bstFromPreorder1(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		return f1(pre, 0, pre.length - 1);
	}
	
	/**
	 * 缺点是：必须通过遍历的方式找到firstBig的位置，如果树很高，就不好了
	 */
	public static BTNode f1(int[] pre, int L, int R) {
		if (L > R) {
			return null;
		}
		/**
		 * L是头节点， 如何找到L后的左树：找到第一个>L的位置?，则 L+1 ~ ?-1 就是左树， ? ~ R就是右树.
		 * 
		 */
		int firstBig = L + 1; // ？
		for (; firstBig <= R; firstBig++) {
			if (pre[firstBig] > pre[L]) {
				break;
			}
		}
		
		BTNode head = new BTNode(pre[L]);
		head.left = f1(pre, L + 1, firstBig - 1);
		head.right = f1(pre, firstBig, R);
		return head;
	}
	
//----------------------------------------------------------------------------------------------	
	
	/**
	 * 遍历一便形成单调栈，可以省去递归中的遍历，单调栈
	 */
	public static BTNode bstFromPreorder2(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		
		// 形成单调栈
		int N = pre.length;
		int[] nearBig = new int[N];
		for (int i = 0; i < N; i++) {
			nearBig[i] = -1;
		}
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
				nearBig[stack.pop()] = i;
			}
			stack.push(i);
		}
		return f2(pre, 0, N - 1, nearBig);
	}

	public static BTNode f2(int[] pre, int L, int R, int[] nearBig) {
		if (L > R) {
			return null;
		}
		int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
		BTNode head = new BTNode(pre[L]);
		head.left = f2(pre, L + 1, firstBig - 1, nearBig);
		head.right = f2(pre, firstBig, R, nearBig);
		return head;
	}
	
//---------------------------------------------------------------------------
	/**
	 * 用数组的形式手写栈，代替系统栈， 优化常数时间
	 */
	
	public static BTNode bstFromPreorder3(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		int N = pre.length;
		int[] nearBig = new int[N];
		for (int i = 0; i < N; i++) {
			nearBig[i] = -1;
		}
		int[] stack = new int[N]; // 用数组替代栈
		int size = 0;
		for (int i = 0; i < N; i++) {
			while (size != 0 && pre[stack[size - 1]] < pre[i]) {
				nearBig[stack[--size]] = i;
			}
			stack[size++] = i;
		}
		return f3(pre, 0, N - 1, nearBig);
	}

	public static BTNode f3(int[] pre, int L, int R, int[] nearBig) {
		if (L > R) {
			return null;
		}
		int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
		BTNode head = new BTNode(pre[L]);
		head.left = f3(pre, L + 1, firstBig - 1, nearBig);
		head.right = f3(pre, firstBig, R, nearBig);
		return head;
	}
}
