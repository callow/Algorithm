package com.algo.util.binarytree.model;

import com.algo.util.common.model.BTNode;

/**
 * 1. cur��������cur�����ƶ���cur -> cur.right��
 * 2. cur���������ҵ����������ҵĽڵ�mostRight:
 * 		A.  ��mostRight��ָ��-> null, ����ָ��cur, cur�����ƶ���cur -> cur.left��
 *  	B.  ��mostRight��ָ��-> cur, ����ָ��null, cur�����ƶ� ��cur -> cur.right��
 * 3. curΪnullʱֹͣ����
 *	Space: O(1)
 *  ����ڵ�������visit 2�Σ������� visit 1��.
 *  �ڴ�Խ���ʱ��ʹ�ã����簢���޵��£� 128K��Ƕ��ʽ
 *  Morris ������ �Է����Լ�2�εĽ���ڵ�1�δ�����ӡ�����Է����Լ�1�εĽ��ֱ�Ӵ��� 
 *  Morris ������ �Է����Լ�2�εĽ���ڵ�2�δ����Է����Լ�1�εĽ��ֱ�Ӵ��� 
 *  Morris �ĺ��� �Է����Լ�2�εĽ���ڵ�2�δ��������ʱ�򲻵���ӡ������㣬��������Ĵ�ӡ�������ұ߽磬����������ӡ���������ұ߽硣�������У�
 */
public class Morris {
	
	private static final String SPACE = " ";
	
	/**
	 * 
	 * @param order: null(morris��), pre(����), in(����) , pos(����)
	 * Space�� O��1�� Time: O(M), M�����ĸ߶�
	 */
	public static void transverse(BTNode head, String order) {
		if (head == null) {
			return;
		}
		BTNode cur = head;
		BTNode mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					if ("pre".contentEquals(order)) {System.out.print(cur.value + SPACE);}
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					if ("pos".contentEquals(order)) {printEdge(cur.left);}
				}
			} else {
				if ("pre".contentEquals(order)) {System.out.print(cur.value + SPACE);}
			}
			if ("in".contentEquals(order)) {System.out.print(cur.value + SPACE);}
			if ("pos".contentEquals(order)) {printEdge(head);}
			cur = cur.right;
		}
		System.out.println();
	}
	
	/**
		ʹ��O��1���ռ��ж��Ƿ�������������
	 */
	public static boolean isBST(BTNode head) {
		if (head == null) {
			return true;
		}
		BTNode cur = head;
		BTNode mostRight = null;
		Integer pre = null;
		boolean ans = true;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null && pre >= cur.value) { // ���������ˣ� ��������������
				ans = false;
			}
			pre = cur.value;
			cur = cur.right;
		}
		return ans;
	}
	

	private static void printEdge(BTNode head) {
		BTNode tail = reverseEdge(head); // ������ת
		BTNode cur = tail;
		while (cur != null) {
			System.out.print(cur.value + SPACE);
			cur = cur.right;
		}
		reverseEdge(tail); // ��ӡ��ɺ�ת��ȥ
	}

	private static BTNode reverseEdge(BTNode from) {
		BTNode pre = null;
		BTNode next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	
}
