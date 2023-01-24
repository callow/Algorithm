package com.algo.util.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.algo.util.binarytree.model.Morris;
import com.algo.util.common.model.BTNode;

/**
 * ����������/���л�������<br><br>
 * ǰ�к���+������������л�
 * 
 *
 */
public class BinaryTreeIterateUtil {
	
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
	
	public static void preOrder2(BTNode head) { // O(1) - Space
		if (head == null) {
			return;
		}
		Morris.transverse(head, "pre");
	}
	
	/**
	 * �������л�  { null, 1, null, 2, null}
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
	 * �����л�����
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
	
	public static void inOrder2(BTNode head) {
		if (head == null) {
			return;
		}
		Morris.transverse(head, "in");
	}
	
	/**
	 * �������л� ������ ��Ϊ������
	 */
	
	
	//-------------------------------------------------------------------------------------------------
	
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
	
	public static void posOrder2(BTNode head) {
		if (head == null) {
			return;
		}
		Morris.transverse(head, "pos");
	}
	
	/**
	 * �������л�
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
	 * �����л�����
	 */
	
	public static BTNode PosDeserialize(Queue<String> poslist) {
		if (poslist == null || poslist.size() == 0) {
			return null;
		}
		// ������  ->  stack(������)
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
	 * ���������ӡ : = Q��
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
	 * �������л�
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
				head = queue.poll(); // head ��   ��
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
	 * �����л�-����
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
