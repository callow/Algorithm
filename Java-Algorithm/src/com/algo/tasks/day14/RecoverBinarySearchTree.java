package com.algo.tasks.day14;


/**
 * 还原搜索二叉树： https://leetcode.com/problems/recover-binary-search-tree/
 * 
 *  搜索二叉树有2个节点顺序对掉了，找到他并恢复，  
 *  需要中序遍历，找到那个降序的
 * 
	搜索二叉树：每个节点，左孩子比他小，右孩子比他大
 */
public class RecoverBinarySearchTree {

	// 不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int v) {
			val = v;
		}
	}

	// 如果能过leetcode，只需要提交这个方法即可
	// 但其实recoverTree2才是正路，只不过leetcode没有那么考
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
