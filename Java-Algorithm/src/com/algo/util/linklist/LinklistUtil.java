package com.algo.util.linklist;

import com.algo.util.common.DoubleNode;
import com.algo.util.common.Node;

public class LinklistUtil {

	/**
	 * ��ת������
	 */
	
	public static Node reverse(Node head) {
		Node pre = null;
		Node next = null;
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
	
	public static DoubleNode reverse(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
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
	
	public static Node removeValue(Node head, int num) {
		while(head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
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
}
