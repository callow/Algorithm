package com.algo.util.binarytree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.util.common.CommonStringUtil;
import com.algo.util.common.model.BTNode;
import com.algo.util.common.model.NTNode;
import com.algo.util.common.model.PTNode;
/**
 * 
 * 二叉树的而行为一定要递归来解决
 */
public class BinaryTreeUtil {

	/**
	 * 将N叉树encode成2叉树O(N)：<BR>
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

	private static List<NTNode> decode(BTNode root) {
		List<NTNode> children = new ArrayList<>();
		while (root != null) {
			// 不停地往右出溜，然年加到孩子集合中去
			NTNode cur = new NTNode(root.value, decode(root.left));
			children.add(cur);
			root = root.right;
		}
		return children;
	}
	
	/**
	 * 打印一颗2叉树<br><br>
	 * H: 头节点<br>
	 * ^: 父是左侧上侧距离我最近那个<br>
	 * v:父是左侧下侧距离我最近那个<br>
	 */
	
	public static void print(BTNode head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}
	private static void printInOrder(BTNode head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = CommonStringUtil.getSpace(lenL) + val + CommonStringUtil.getSpace(lenR);
		System.out.println(CommonStringUtil.getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}
	
	/**
	 * 得到最宽地那层节点数<br><br>
	 * Queue对二叉树按层遍历（宽度优先）然后求max. 只要知道当前层什么时候结束就可以了<br>
	 * 参考： BinaryTreeIterateUtil.level(...)
	 */
	
	public static int widestLevel(BTNode head) {
		if (head == null) {
			return 0;
		}
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(head);
		BTNode curEnd = head; // 当前层，最右节点是谁
		BTNode nextEnd = null; // 下一层，最右节点是谁
		int max = 0;
		int curLevelNodes = 0; // 当前层的节点数
		while (!queue.isEmpty()) {
			BTNode cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			curLevelNodes++;
			if (cur == curEnd) { // cur撞上curEnd了，这一层统计结束
				max = Math.max(max, curLevelNodes);
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}
		return max;
	}
	
	/**
	 * 找到带父指针的2叉树的后继节点,后继节点： 中序遍历中当前节点的下一个结点<br><br>
	 * 
	 * X有右树： successor = 右树中最左的孩子. <br>
	 * X无右树： Look up until 不是自己父的右孩子，即左孩子，则那个节点就是x后继<br>
	 * 若都没有，即全树的最右的那个节点找后继，它的后继= null
	 */
	
	public static PTNode getSuccessor(PTNode node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {
			return getLeftmost(node.right);
		} else { // 无右子树
			PTNode parent = node.parent;
			while (parent != null && parent.right == node) { // 当前节点是其父亲节点右孩子
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}
	
	private static PTNode getLeftmost(PTNode node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
}
