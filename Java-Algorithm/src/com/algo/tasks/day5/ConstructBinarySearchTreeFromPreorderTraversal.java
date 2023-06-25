package com.algo.tasks.day5;

import java.util.Stack;

import com.algo.util.common.model.BTNode;

/**
 * 
 * ����һ��������������������� �ڵ����ظ�����ԭ����������head����
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
	 * ȱ���ǣ�����ͨ�������ķ�ʽ�ҵ�firstBig��λ�ã�������ܸߣ��Ͳ�����
	 */
	public static BTNode f1(int[] pre, int L, int R) {
		if (L > R) {
			return null;
		}
		/**
		 * L��ͷ�ڵ㣬 ����ҵ�L����������ҵ���һ��>L��λ��?���� L+1 ~ ?-1 ���������� ? ~ R��������.
		 * 
		 */
		int firstBig = L + 1; // ��
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
	 * ����һ���γɵ���ջ������ʡȥ�ݹ��еı���������ջ
	 */
	public static BTNode bstFromPreorder2(int[] pre) {
		if (pre == null || pre.length == 0) {
			return null;
		}
		
		// �γɵ���ջ
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
	 * ���������ʽ��дջ������ϵͳջ�� �Ż�����ʱ��
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
		int[] stack = new int[N]; // ���������ջ
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
