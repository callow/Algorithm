package com.algo.util.binarytree;


import java.util.ArrayList;
import java.util.List;

import com.algo.util.common.model.BTNode;
import com.algo.util.common.model.NTNode;
/**
 * 
 * 二叉树的而行为一定要递归来解决
 */
public class BinaryTreeUtil {

	/**
	 * 将N叉树encode成2叉树：<BR>
	 * 每个Node的孩子挂在左树右边界上，同级的挂在大哥的右孩子<BR>
	 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
	 */
	
	public static BTNode NTtoBT(NTNode root) {
		if (root == null) {
			return null;
		}
		BTNode head = new BTNode(root.val);
		head.left = encode(root.children); // 所有孩子往我左树右边界上挂
		return head;
	}
	private static BTNode encode(List<NTNode> children) {
		BTNode head = null;
		BTNode cur = null;
		for (NTNode child : children) {
			BTNode tNode = new BTNode(child.val);
			if (head == null) {
				head = tNode;
			} else {
				cur.right = tNode;
			}
			cur = tNode;
			cur.left = encode(child.children); //再把我孩子的而孩子继续挂：深度优先遍历
		}
		return head;
	}
	/**
	 * 再将2叉树dencode成N叉树：<BR>
	 * 
	 */
	public NTNode BTtoNT(BTNode root) {
		if (root == null) {
			return null;
		}
		return new NTNode(root.value, decode(root.left));
	}

	public static List<NTNode> decode(BTNode root) {
		List<NTNode> children = new ArrayList<>();
		while (root != null) {
			// 不停地往右出溜，然年加到孩子集合中去
			NTNode cur = new NTNode(root.value, decode(root.left));
			children.add(cur);
			root = root.right;
		}
		return children;
	}
	
	
}
