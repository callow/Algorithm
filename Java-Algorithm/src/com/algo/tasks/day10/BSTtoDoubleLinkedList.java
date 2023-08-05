package com.algo.tasks.day10;
/**
 * ��һ��������������ת����β����������˫������
 * 
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
public class BSTtoDoubleLinkedList {

	// �ύʱ��Ҫ�ύ����ࣺ ����ڵ�
		public static class Node {
			public int value;
			public Node left; // last
			public Node right; // next

			public Node(int data) {
				this.value = data;
			}
		}
		public static class Info {
			public Node start; // ˫�������ͷ�ڵ�
			public Node end; // ˫�������β�ڵ�

			public Info(Node start, Node end) {
				this.start = start;
				this.end = end;
			}
		}
		
		
		// �ύ����Ĵ���
		public static Node treeToDoublyList(Node head) {
			if (head == null) {
				return null;
			}
			Info allInfo = process(head); // ��β����ӵ�˫������
			allInfo.end.right = allInfo.start;
			allInfo.start.left = allInfo.end;
			return allInfo.start;
		}


		/**
		 * X�ǵ�ǰ�������ڵ㣬-�������ӣ��Һ���
		 */
		public static Info process(Node X) {
			if (X == null) {
				return new Info(null, null);
			}
			Info lInfo = process(X.left); // ������ҪLInfo
			Info rInfo = process(X.right); // ���Һ���ҪLInfo
			
			// ������ӵĵ����һ���ڵ㲻�ǿգ����У��� ����nextָ�����ң�����
			if (lInfo.end != null) {
				lInfo.end.right = X;
			}
			X.left = lInfo.end; // �ҵ�lastָ���������ӽ�β
			X.right = rInfo.start; // �ҵ�nextָ�������Һ��ӿ�ͷ
			if (rInfo.start != null) {
				rInfo.start.left = X;
			}
			// ���������ͷ    lInfo.start != null ? lInfo.start : X -> ���ӵĵĿ�ʼ�ڵ��У���ʵ������С��ֵ
			// ���������β    rInfo.end != null ? rInfo.end : X -> ���������һ���ڵ����еģ����������������Ǹ���
			return new Info(lInfo.start != null ? lInfo.start : X, rInfo.end != null ? rInfo.end : X);
		}

}
