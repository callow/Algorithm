package com.algo.util.binarytree;

import com.algo.util.common.model.BTNode;

/**
 * 二叉树遍历工具类
 *
 */
public class IterateUtil {
	
	/**
	 * 先序打印
	 */
	
	public static void preOrder(BTNode head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		preOrder(head.left);
		preOrder(head.right);
	}
	
	/**
	 * 中序打印
	 */
	
	public static void inOrder(BTNode head) {
		if (head == null) {
			return;
		}
		inOrder(head.left);
		System.out.println(head.value);
		inOrder(head.right);
	}
	
	/**
	 * 后序打印
	 */
	public static void posOrder(BTNode head) {
		if (head == null) {
			return;
		}
		posOrder(head.left);
		posOrder(head.right);
		System.out.println(head.value);
	}
	
}
