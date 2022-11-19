package com.algo.util.linklist;

import com.algo.util.common.DoubleNode;
import com.algo.util.common.Node;
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
	
}
