package com.algo.util.binarytree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.util.common.model.BTNode;

/**
 * 二叉树遍历工具类
 *
 */
public class IterateUtil {
	
	/**
	 * 先序遍历打印
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
	 * 中序遍历打印
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
	 * 后序遍历打印
	 */
	public static void posOrder(BTNode head) {
		if (head == null) {
			return;
		}
		posOrder(head.left);
		posOrder(head.right);
		System.out.println(head.value);
	}
	
	/**
	 * 按层遍历
	 */
	
	public static void level(BTNode head) {
		if (head == null) {
			return;
		}
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			BTNode cur = queue.poll();
			System.out.println(cur.value);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}
	
}
