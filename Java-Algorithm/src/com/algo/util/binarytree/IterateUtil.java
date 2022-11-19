package com.algo.util.binarytree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.util.common.model.BTNode;

/**
 * ����������������
 *
 */
public class IterateUtil {
	
	/**
	 * ���������ӡ
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
	 * ���������ӡ
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
	 * ���������ӡ
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
	 * �������
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
