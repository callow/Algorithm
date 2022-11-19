package com.algo.util.linklist;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.model.DoubleNode;
import com.algo.util.common.model.Node;
import com.algo.util.linklist.model.DoubleEndsQueue;
import com.algo.util.linklist.model.Queue;
import com.algo.util.linklist.model.Stack;

public class LinklistUtil {

	/**
	 * ��ת������
	 */
	
	public static Node<Integer> reverse(Node<Integer> head) {
		Node<Integer> pre = null;
		Node<Integer> next = null;
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/**
	 * ��ת˫����
	 */
	
	public static DoubleNode<Integer> reverse(DoubleNode<Integer> head) {
		DoubleNode<Integer> pre = null;
		DoubleNode<Integer> next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/**
	 * ɾ��Ԫ��
	 */
	
	public static Node<Integer> removeValue(Node<Integer> head, int num) {
		while(head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node<Integer> pre = head;
		Node<Integer> cur = head;
		while(cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}
	
	/**
	 * ����һ������д��Queue
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<Integer>();
	}
	
	/**
	 * ����һ������д��Stack
	 */
	
	public static Stack<Integer> getStack() {
		return new Stack<Integer>();
	}
	/**
	 * ����һ������д��˫�˶���
	 */
	
	public static DoubleEndsQueue<Integer> DoubleEndsQueue() {
		return new DoubleEndsQueue<Integer>();
	}
	
	/**
	 *  �ж������ǲ��ǻ��Ľṹ<br><br>
	 *   ����1�� �ҵ��е�󣬺�벿�ַ�ת Space: O(1)<br>
	 *   ����2�� ��ջ��˳����룬Ȼ���ٱ���һ�鵯�� Space: O(N)
	 */
	
	public static boolean isPalindrome(Node head, String method) {
		if (method.equals("Stack")) { // ��ջ
			Stack<Node> stack = new Stack<Node>();
			Node cur = head;
			while (cur != null) {
				stack.push(cur);
				cur = cur.next;
			}
			while (head != null) {
				if (head.value != stack.pop().value) {
					return false;
				}
				head = head.next;
			}
			return true;
		} else { // ����ջ
			if (head == null || head.next == null) {
				return true;
			}
			Node n1 = head;
			Node n2 = head;
			while (n2.next != null && n2.next.next != null) { // find mid node
				n1 = n1.next; // n1 -> mid
				n2 = n2.next.next; // n2 -> end
			}
			// n1 �е�
			
			
			n2 = n1.next; // n2 -> right part first node
			n1.next = null; // mid.next -> null
			Node n3 = null;
			while (n2 != null) { // right part convert
				n3 = n2.next; // n3 -> save next node
				n2.next = n1; // next of right node convert
				n1 = n2; // n1 move
				n2 = n3; // n2 move
			}
			n3 = n1; // n3 -> save last node
			n2 = head;// n2 -> left first node
			boolean res = true;
			while (n1 != null && n2 != null) { // check palindrome
				if (n1.value != n2.value) {
					res = false;
					break;
				}
				n1 = n1.next; // left to mid
				n2 = n2.next; // right to mid
			}
			n1 = n3.next;
			n3.next = null;
			while (n1 != null) { // recover list
				n2 = n1.next;
				n1.next = n3;
				n3 = n1;
				n1 = n2;
			}
			return res;
		}
	}
	
	/**
	 *  ����������ĳ��num��֯�� ���С�� �м���ȣ� �ұߴ� <br><br>
	 *  ˼·�� ��������������У�Ȼ������������partition
	 */
	
	public static Node<Integer> sort(Node<Integer> head, int num) {
		if (head == null) {
			return head;
		}
		Node<Integer> cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		Node<Integer>[] nodeArr = new Node[i];
		i = 0;
		cur = head;
		for (i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		// �Ѿ���ɷ������飬���ڿ�ʼ��������������������
		partition(nodeArr, num);
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i]; // ׷��nextָ��һ·����Node[0]
		}
		nodeArr[i - 1].next = null;
		return nodeArr[0];
		
	}
	
	private static void partition(Node<Integer>[] nodeArr, int pivot) {
		int small = -1;
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].value < pivot) {
				CommonArrayUtil.swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				CommonArrayUtil.swap(nodeArr, --big, index);
			}
		}
	}
	
}
