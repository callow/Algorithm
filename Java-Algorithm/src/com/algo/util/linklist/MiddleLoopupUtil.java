package com.algo.util.linklist;

import com.algo.util.common.model.Node;

/**
 * 
 * 利用快慢指针中的慢指针，来找中点，快：一次2步， 慢 一次1步。 注：偶数个节点时有2个中点<br><br>
 * 
 * 中点位置（奇数个）： size / 2 => 0001000<br>
 * 中点位置（偶数个）： (size / 2) && (size / 2 + 1) => 0001100<br>
 * 上中点位置（偶数个）： size / 2  => 001000<br>
 * 下中点位置（偶数个）： size / 2 + 1  => 000100<br>
 *
 */
@SuppressWarnings("rawtypes")
public class MiddleLoopupUtil {

	/**
	 * 基数长度返回中点，偶数长度返回上中点
	 */
	
	public static Node midOrUpMidNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return head;
		}
		// 链表有3个点或以上
		Node slow = head.next;
		Node fast = head.next.next;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	/**
	 * 基数长度返回中点，偶数长度返回下中点
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
	 * 基数长度返回中点前一个，偶数长度返回上中点前一个
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
	 * 基数长度返回中点前一个，偶数长度返回下中点前一个
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
