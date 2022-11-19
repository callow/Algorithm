package com.algo.util.linklist;

import com.algo.util.common.model.Node;

/**
 * 
 * ���ÿ���ָ���е���ָ�룬�����е㣬�죺һ��2���� �� һ��1���� ע��ż�����ڵ�ʱ��2���е�<br><br>
 * 
 * �е�λ�ã����������� size / 2 => 0001000<br>
 * �е�λ�ã�ż�������� (size / 2) && (size / 2 + 1) => 0001100<br>
 * ���е�λ�ã�ż�������� size / 2  => 001000<br>
 * ���е�λ�ã�ż�������� size / 2 + 1  => 000100<br>
 *
 */
@SuppressWarnings("rawtypes")
public class MiddleLoopupUtil {

	/**
	 * �������ȷ����е㣬ż�����ȷ������е�
	 */
	
	public static Node midOrUpMidNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// ������3���������
		Node slow = head.next;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * �������ȷ����е㣬ż�����ȷ������е�
	 */
	
	public static Node midOrDownMidNode(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node slow = head.next;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * �������ȷ����е�ǰһ����ż�����ȷ������е�ǰһ��
	 */
	
	public static Node midOrUpMidPreNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node slow = head;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * �������ȷ����е�ǰһ����ż�����ȷ������е�ǰһ��
	 */
	
	public static Node midOrDownMidPreNode(Node head) {
		if (head == null || head.next == null) {
			return null;
		}
		if (head.next.next == null) {
			return head;
		}
		Node slow = head;
		Node fast = head.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
}
