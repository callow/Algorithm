package com.algo.util.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.algo.util.binarytree.model.Morris;
import com.algo.util.common.model.BTNode;

/**
 * 二叉树遍历/序列化工具类<br><br>
 * 前中后序+按层遍历和序列化
 * 
 *
 */
public class BinaryTreeIterateUtil {
	
	/**
	 * 
	 * 二叉树构建
	 */
	public static void main(String[] args) {
		BTNode head = new BTNode(1);
		head.left = new BTNode(2);
		head.right = new BTNode(3);
		head.left.left = new BTNode(4);
		head.left.right = new BTNode(5);
		head.right.left = new BTNode(6);
		head.right.right = new BTNode(7);

		preOrder(head);
		System.out.println();
		System.out.println("先序遍历递归版");

		inOrder(head);
		System.out.println();
		System.out.println("中序遍历递归版");

		posOrder(head);
		System.out.println();
		System.out.println("后序遍历递归版");

	}
	
	
	
	
	
	/**
	 * 先序遍历打印：头 左 右
	 * 
	 * 每个头节点访问3次
	 */
	
	public static void preOrder(BTNode head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value); // 来到头
		preOrder(head.left);
		
		// 又来到头
		preOrder(head.right);
		
		// 又来到头
	}
	
	/**
	 * 非递归实现先序遍历
	 * 
	 * 所谓先序 = 先处理头
	 * 
	 * 先序遍历打印：头(中) 左 右, 栈：先弹出（打印），先压右 再压左， 之所有先压右是因为先弹出左，先压的后弹出.
	 * 
	 * 左弹出后会处理自己的孩子，因此左的后续不完是不会处理压入的右的。所以肯定是 先处理中间，再处理左树，再处理右树
	 * 
	 * 		1
	 * 	  2   3
	 *  4   5 6 7
	 * 
	 */
	public static void preOrderStack(BTNode head) {
		if (head != null) {
			Stack<BTNode> stack = new Stack<>();
			stack.push(head);
			while(!stack.isEmpty()) {
				head = stack.pop();
				System.out.println(head.value);
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
			System.out.println();	
		}
	}
	
	
	
	
	
	public static void preOrderMorris(BTNode head) { // O(1) - Space
		if (head == null) {
			return;
		}
		Morris.transverse(head, "pre");
	}
	
	/**
	 * 先序序列化  { null, 1, null, 2, null}
	 */
	
	public static Queue<String> preSerialize(BTNode head) {
		Queue<String> ans = new LinkedList<>();
		pres(head, ans);
		return ans;
	}

	private static void pres(BTNode head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}
	
	/**
	 * 反序列化先序
	 */
	
	public static BTNode preDeserialize(Queue<String> prelist) {
		if (prelist == null || prelist.size() == 0) {
			return null;
		}
		return preb(prelist);
	}

	private static BTNode preb(Queue<String> prelist) {
		String value = prelist.poll();
		if (value == null) {
			return null;
		}
		BTNode head = new BTNode(Integer.valueOf(value));
		head.left = preb(prelist);
		head.right = preb(prelist);
		return head;
	}

	//------------------------------------------------------------------------------------------------------
	
	/**
	 * 中序遍历打印： 左 头 右
	 * 
	 * 所谓中序 = 在中间处理头
	 */
	
	public static void inOrder(BTNode head) {
		if (head == null) {
			return;
		}
		inOrder(head.left);
		System.out.println(head.value);
		inOrder(head.right);
	}
	
	/**
	 * 
	 * 不用递归实现中序遍历: 所有子树顺序，左子树，头，右子树
	 * 
	 * 弹出就打印
	 *  https://leetcode.com/problems/binary-tree-inorder-traversal/
	 * 
	 */
	public static void inOrderStack(BTNode head) {
		if (head != null) {
			Stack<BTNode> stack = new Stack<>();
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
			System.out.println();
		}
	}
	
	public static void inOrderMorris(BTNode head) {
		if (head == null) {
			return;
		}
		Morris.transverse(head, "in");
	}
	
	/**
	 * 中序序列化 不可能 因为有歧义
	 */
	
	
	//-------------------------------------------------------------------------------------------------
	
	/**
	 * 后序遍历打印： 左 右 头
	 * 
	 * 所谓后续 = 在最后处理头
	 */
	
	public static void posOrder(BTNode head) {
		if (head == null) {
			return;
		}
		posOrder(head.left);
		posOrder(head.right);
		System.out.println(head.value);
	}
	
	public static void posOrderMorris(BTNode head) {
		if (head == null) {
			return;
		}
		Morris.transverse(head, "pos");
	}
	
	/**
	 * 后序序列化
	 */
	
	public static Queue<String> posSerial(BTNode head) {
		Queue<String> ans = new LinkedList<>();
		poss(head, ans);
		return ans;
	}

	private static void poss(BTNode head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			poss(head.left, ans);
			poss(head.right, ans);
			ans.add(String.valueOf(head.value));
		}
	}
	
	/**
	 * 反序列化后序
	 */
	
	public static BTNode PosDeserialize(Queue<String> poslist) {
		if (poslist == null || poslist.size() == 0) {
			return null;
		}
		// 左右中  ->  stack(中右左)
		Stack<String> stack = new Stack<>();
		while (!poslist.isEmpty()) {
			stack.push(poslist.poll());
		}
		return posb(stack);
	}

	private static BTNode posb(Stack<String> posstack) {
		String value = posstack.pop();
		if (value == null) {
			return null;
		}
		BTNode head = new BTNode(Integer.valueOf(value));
		head.right = posb(posstack);
		head.left = posb(posstack);
		return head;
	}
	
	// --------------------------------------------------------------------------------------
	
	/**
	 * 按层遍历打印 : = Q宽： 
	 * 
	 * 	abcdef
	 * 
	 * 1. 队列弹出cur， 打印
	 * 2. cur有左入左，有右入右
	 */
	
	public static void level(BTNode head) {
		if (head == null) {
			return;
		}
		Queue<BTNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			BTNode cur = queue.poll();
			System.out.println(cur.value);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}
	
	/**
	 * 按层序列化
	 */
	
	public static Queue<String> levelSerialize(BTNode head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			Queue<BTNode> queue = new LinkedList<>();
			queue.add(head);
			while (!queue.isEmpty()) {
				head = queue.poll(); // head 父   子
				if (head.left != null) {
					ans.add(String.valueOf(head.left.value));
					queue.add(head.left);
				} else {
					ans.add(null);
				}
				if (head.right != null) {
					ans.add(String.valueOf(head.right.value));
					queue.add(head.right);
				} else {
					ans.add(null);
				}
			}
		}
		return ans;
	}
	
	/**
	 * 反序列化-按曾
	 */
	
	public static BTNode levelDeserialize(Queue<String> levelList) {
		if (levelList == null || levelList.size() == 0) {
			return null;
		}
		BTNode head = generateNode(levelList.poll());
		Queue<BTNode> queue = new LinkedList<>();
		if (head != null) {
			queue.add(head);
		}
		BTNode node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();
			node.left = generateNode(levelList.poll());
			node.right = generateNode(levelList.poll());
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return head;
	}
	
	private static BTNode generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new BTNode(Integer.valueOf(val));
	}
	
		/**
		 * 二叉树是否是对称的？
		    symmetric:
		        1. left head val = right head val
		        2. left.left.val = right.right.val && left.right.val = right.left.val
		 */
		public static boolean isSymmetric(BTNode root) {
		    return isMirror(root, root);
		}
		
		// 一棵树是原始树  head1
		// 另一棵是翻面树  head2
		public static boolean isMirror(BTNode head1, BTNode head2) {
			if (head1 == null && head2 == null) {
				return true;
			}
			if (head1 != null && head2 != null) {
				return head1.value == head2.value 
						&& isMirror(head1.left, head2.right) 
						&& isMirror(head1.right, head2.left);
			}
			// 一个为空，一个不为空  false
			return false;
		}
	
}
