package com.algo.tasks.day14;


/**
 * ��ȫ�������ڵ����� https://leetcode.cn/problems/count-complete-tree-nodes/
 */
public class CompleteTreeNodeNumber {
	
	// �ύʱ��Ҫ�ύ�����
		public class TreeNode {
			int val;
			TreeNode left;
			TreeNode right;
		}

		// �ύ���µķ���
		public static int countNodes(TreeNode head) {
			if (head == null) {
				return 0;
			}
			return bs(head, 1, mostLeftLevel(head, 1));
		}

		// ��ǰ����node�ڵ㣬node�ڵ���level�㣬�ܲ�����h
		// ����nodeΪͷ������(������ȫ������)���ж��ٸ��ڵ�
		public static int bs(TreeNode node, int Level, int h) {
			if (Level == h) {
				return 1;
			}
			if (mostLeftLevel(node.right, Level + 1) == h) { // �������������ڵ㵽�����һ��
				return (1 << (h - Level)) // ���������ģ���� +
						+ bs(node.right, Level + 1, h); // 2^h-level   �����ж��ٸ��ڵ�
			} else {
				return (1 << (h - Level - 1)) // ���������ģ����Ǳ�������һ�㣩 +
						+ bs(node.left, Level + 1, h); // �����м����ڵ�
			}
		}

		// ���node�ڵ�level�㣬
		// ����nodeΪͷ���������������Ƕ���
		// nodeΪͷ��������һ������ȫ������
		public static int mostLeftLevel(TreeNode node, int level) { 
			while (node != null) {
				level++;
				node = node.left;
			}
			return level - 1;
		}
	
}
