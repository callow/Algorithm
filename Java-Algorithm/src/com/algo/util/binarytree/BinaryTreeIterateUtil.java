package com.algo.util.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.algo.util.binarytree.model.Morris;
import com.algo.util.common.model.BTNode;

/**
 * 二叉树遍历/序列化工具类<br><br>
 * 前中后序+按层遍历和序列化
 * 
 *
 */
public class BinaryTreeIterateUtil {
	
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
	
	public static void preOrder2(BTNode head) { // O(1) - Space
		if (head == null) {
			return;
		}
		Morris.transverse(head, "pre");
	}
	
	/**
	 * 先序序列化  { null, 1, null, 2, null}
	 */
	
	public static Queue<String> preSerialize(BTNode head) {
		Queue<String> ans = new LinkedList<>();
		pres(head, ans);
		return ans;
	}

	private static void pres(BTNode head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}
	
	/**
	 * 反序列化先序
	 */
	
	public static BTNode preDeserialize(Queue<String> prelist) {
		if (prelist == null || prelist.size() == 0) {
			return null;
		}
		return preb(prelist);
	}

	private static BTNode preb(Queue<String> prelist) {
		String value = prelist.poll();
		if (value == null) {
			return null;
		}
		BTNode head = new BTNode(Integer.valueOf(value));
		head.left = preb(prelist);
		head.right = preb(prelist);
		return head;
	}

	//------------------------------------------------------------------------------------------------------
	
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
	
	public static void inOrder2(BTNode head) {
		if (head == null) {
			return;
		}
		Morris.transverse(head, "in");
	}
	
	/**
	 * 中序序列化 不可能 因为有歧义
	 */
	
	
	//-------------------------------------------------------------------------------------------------
	
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
	
	public static void posOrder2(BTNode head) {
		if (head == null) {
			return;
		}
		Morris.transverse(head, "pos");
	}
	
	/**
	 * 后序序列化
	 */
	
	public static Queue<String> posSerial(BTNode head) {
		Queue<String> ans = new LinkedList<>();
		poss(head, ans);
		return ans;
	}

	private static void poss(BTNode head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			poss(head.left, ans);
			poss(head.right, ans);
			ans.add(String.valueOf(head.value));
		}
	}
	
	/**
	 * 反序列化后序
	 */
	
	public static BTNode PosDeserialize(Queue<String> poslist) {
		if (poslist == null || poslist.size() == 0) {
			return null;
		}
		// 左右中  ->  stack(中右左)
		Stack<String> stack = new Stack<>();
		while (!poslist.isEmpty()) {
			stack.push(poslist.poll());
		}
		return posb(stack);
	}

	private static BTNode posb(Stack<String> posstack) {
		String value = posstack.pop();
		if (value == null) {
			return null;
		}
		BTNode head = new BTNode(Integer.valueOf(value));
		head.right = posb(posstack);
		head.left = posb(posstack);
		return head;
	}
	
	// --------------------------------------------------------------------------------------
	
	/**
	 * 按层遍历打印 : = Q宽
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
	
	/**
	 * 按层序列化
	 */
	
	public static Queue<String> levelSerialize(BTNode head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			Queue<BTNode> queue = new LinkedList<>();
			queue.add(head);
			while (!queue.isEmpty()) {
				head = queue.poll(); // head 父   子
				if (head.left != null) {
					ans.add(String.valueOf(head.left.value));
					queue.add(head.left);
				} else {
					ans.add(null);
				}
				if (head.right != null) {
					ans.add(String.valueOf(head.right.value));
					queue.add(head.right);
				} else {
					ans.add(null);
				}
			}
		}
		return ans;
	}
	
	/**
	 * 反序列化-按曾
	 */
	
	public static BTNode levelDeserialize(Queue<String> levelList) {
		if (levelList == null || levelList.size() == 0) {
			return null;
		}
		BTNode head = generateNode(levelList.poll());
		Queue<BTNode> queue = new LinkedList<>();
		if (head != null) {
			queue.add(head);
		}
		BTNode node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNode(levelList.poll());
			node.right = generateNode(levelList.poll());
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return head;
	}
	
	private static BTNode generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new BTNode(Integer.valueOf(val));
	}
	
}
