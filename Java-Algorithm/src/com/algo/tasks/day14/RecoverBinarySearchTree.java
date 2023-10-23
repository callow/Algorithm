package com.algo.tasks.day14;


/**
 * ��ԭ������������ https://leetcode.com/problems/recover-binary-search-tree/
 * 
 *  ������������2���ڵ�˳��Ե��ˣ��ҵ������ָ���  
 *  ��Ҫ����������ҵ��Ǹ������
 * 
	������������ÿ���ڵ㣬���ӱ���С���Һ��ӱ�����
 */
public class RecoverBinarySearchTree {

	// ��Ҫ�ύ�����
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	// ����ܹ�leetcode��ֻ��Ҫ�ύ�����������
	// ����ʵrecoverTree2������·��ֻ����leetcodeû����ô��
	public static void recoverTree(TreeNode root) {
		TreeNode[] errors = twoErrors(root);
		if (errors[0] != null && errors[1] != null) {
			int tmp = errors[0].val;
			errors[0].val = errors[1].val;
			errors[1].val = tmp;
		}
	}

	public static TreeNode[] twoErrors(TreeNode head) {
		TreeNode[] ans = new TreeNode[2];
		if (head == null) {
			return ans;
		}
		TreeNode cur = head;
		TreeNode mostRight = null;
		TreeNode pre = null;
		TreeNode e1 = null;
		TreeNode e2 = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			if (pre != null && pre.val >= cur.val) {
				e1 = e1 == null ? pre : e1;
				e2 = cur;
			}
			pre = cur;
			cur = cur.right;
		}
		ans[0] = e1;
		ans[1] = e2;
		return ans;
	}
}
