package com.algo.tasks.day10;
/**
 * 将一个搜索二叉树，转成首尾相连的有序双向链表。
 * 
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class BSTtoDoubleLinkedList {

	// 提交时不要提交这个类： 链表节点
		public static class Node {
			public int value;
			public Node left; // last
			public Node right; // next

			public Node(int data) {
				this.value = data;
			}
		}
		public static class Info {
			public Node start; // 双向链表的头节点
			public Node end; // 双向链表的尾节点

			public Info(Node start, Node end) {
				this.start = start;
				this.end = end;
			}
		}
		
		
		// 提交下面的代码
		public static Node treeToDoublyList(Node head) {
			if (head == null) {
				return null;
			}
			Info allInfo = process(head); // 首尾不相接的双向链表
			allInfo.end.right = allInfo.start;
			allInfo.start.left = allInfo.end;
			return allInfo.start;
		}


		/**
		 * X是当前二叉树节点，-他有左孩子，右孩子
		 */
		public static Info process(Node X) {
			if (X == null) {
				return new Info(null, null);
			}
			Info lInfo = process(X.left); // 向左孩子要LInfo
			Info rInfo = process(X.right); // 向右孩子要LInfo
			
			// 如果左孩子的的最后一个节点不是空，（有）， 它的next指针连我（父）
			if (lInfo.end != null) {
				lInfo.end.right = X;
			}
			X.left = lInfo.end; // 我的last指针连我左孩子结尾
			X.right = rInfo.start; // 我的next指针连我右孩子开头
			if (rInfo.start != null) {
				rInfo.start.left = X;
			}
			// 整体链表的头    lInfo.start != null ? lInfo.start : X -> 左孩子的的开始节点有，其实就是最小的值
			// 整体链表的尾    rInfo.end != null ? rInfo.end : X -> 右树上最后一个节点是有的，他就是整体最大的那个点
			return new Info(lInfo.start != null ? lInfo.start : X, rInfo.end != null ? rInfo.end : X);
		}

}
