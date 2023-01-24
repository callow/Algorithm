package com.algo.util.binarytree.model;

import com.algo.util.common.model.BTNode;

/**
 * 1. cur无左树，cur往右移动（cur -> cur.right）
 * 2. cur有左树，找到左树上最右的节点mostRight:
 * 		A.  若mostRight右指针-> null, 让其指向cur, cur向左移动（cur -> cur.left）
 *  	B.  若mostRight右指针-> cur, 让其指向null, cur向右移动 （cur -> cur.right）
 * 3. cur为null时停止遍历
 *	Space: O(1)
 *  如果节点有左树visit 2次，无左树 visit 1次.
 *  内存吃紧的时候使用，比如阿波罗登月， 128K，嵌入式
 *  Morris 改先序： 对访问自己2次的结点在第1次处理（打印），对访问自己1次的结点直接处理 
 *  Morris 改中序： 对访问自己2次的结点在第2次处理，对访问自己1次的结点直接处理 
 *  Morris 改后序： 对访问自己2次的结点在第2次处理，处理的时候不单打印单个结点，而是逆序的打印左树的右边界，整天跑完后打印整棵树的右边界。（左右中）
 */
public class Morris {
	
	private static final String SPACE = " ";
	
	/**
	 * 
	 * @param order: null(morris序), pre(先序), in(中序) , pos(后序)
	 * Space： O（1） Time: O(M), M是树的高度
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
		使用O（1）空间判断是否是搜索二叉树
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
			if (pre != null && pre >= cur.value) { // 不是升序了， 不是搜索二叉树
				ans = false;
			}
			pre = cur.value;
			cur = cur.right;
		}
		return ans;
	}
	

	private static void printEdge(BTNode head) {
		BTNode tail = reverseEdge(head); // 先链表反转
		BTNode cur = tail;
		while (cur != null) {
			System.out.print(cur.value + SPACE);
			cur = cur.right;
		}
		reverseEdge(tail); // 打印完成后反转回去
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
