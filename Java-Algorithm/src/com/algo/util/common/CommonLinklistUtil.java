package com.algo.util.common;

import java.util.ArrayList;
import java.util.List;

public class CommonLinklistUtil {

	/**
	 * 生成一个单链表
	 */
	
	public static Node<Integer> generateRandomLinkedList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		Node<Integer> head = new Node<Integer>((int) (Math.random() * (value + 1)));
		Node<Integer> pre = head;
		while (size != 0) {
			Node<Integer> cur = new Node<Integer>((int) (Math.random() * (value + 1)));
			pre.next = cur;
			pre = cur;
			size--;
		}
		return head;
	}
	
	/**
	 * 生成一个双链表
	 */
	
	public static DoubleNode<Integer> generateRandomDoubleList(int len, int value) {
		int size = (int) (Math.random() * (len + 1));
		if (size == 0) {
			return null;
		}
		size--;
		DoubleNode<Integer> head = new DoubleNode<Integer>((int) (Math.random() * (value + 1)));
		DoubleNode<Integer> pre = head;
		while (size != 0) {
			DoubleNode<Integer> cur = new DoubleNode<Integer>((int) (Math.random() * (value + 1)));
			pre.next = cur;
			cur.last = pre;
			pre = cur;
			size--;
		}
		return head;
	}
	
	/**
	 * 单链表是否正确反转
	 */
	
	public static boolean checkLinkedListReverse(List<Integer> origin, Node<Integer> head) {
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	/**
	 * 单链表的原始顺序
	 */
	
	public static List<Integer> getLinkedListOriginOrder(Node<Integer> head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}
	
	/**
	 * 双链表的原始顺序
	 */
	
	public static List<Integer> getDoubleListOriginOrder(DoubleNode<Integer> head) {
		List<Integer> ans = new ArrayList<>();
		while (head != null) {
			ans.add(head.value);
			head = head.next;
		}
		return ans;
	}
	
	/**
	 * 双链表是否正确反转
	 */
	
	public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode<Integer> head) {
		DoubleNode<Integer> end = null;
		for (int i = origin.size() - 1; i >= 0; i--) {
			if (!origin.get(i).equals(head.value)) {
				return false;
			}
			end = head;
			head = head.next;
		}
		for (int i = 0; i < origin.size(); i++) {
			if (!origin.get(i).equals(end.value)) {
				return false;
			}
			end = end.last;
		}
		return true;
	}
	
}
