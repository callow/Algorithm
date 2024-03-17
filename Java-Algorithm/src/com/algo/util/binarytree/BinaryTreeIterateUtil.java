package com.algo.util.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.algo.util.binarytree.model.Morris;
import com.algo.util.common.model.BTNode;

/**
 * ����������/���л�������<br><br>
 * ǰ�к���+������������л�
 * 
 *
 */
public class BinaryTreeIterateUtil {
	
	/**
	 * 
	 * ����������
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
		System.out.println("��������ݹ��");

		inOrder(head);
		System.out.println();
		System.out.println("��������ݹ��");

		posOrder(head);
		System.out.println();
		System.out.println("��������ݹ��");

	}
	
	
	
	
	
	/**
	 * ���������ӡ��ͷ �� ��
	 * 
	 * ÿ��ͷ�ڵ����3��
	 */
	
	public static void preOrder(BTNode head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value); // ����ͷ
		preOrder(head.left);
		
		// ������ͷ
		preOrder(head.right);
		
		// ������ͷ
	}
	
	/**
	 * �ǵݹ�ʵ���������
	 * 
	 * ��ν���� = �ȴ���ͷ
	 * 
	 * ���������ӡ��ͷ(��) �� ��, ջ���ȵ�������ӡ������ѹ�� ��ѹ�� ֮������ѹ������Ϊ�ȵ�������ѹ�ĺ󵯳�.
	 * 
	 * �󵯳���ᴦ���Լ��ĺ��ӣ������ĺ��������ǲ��ᴦ��ѹ����ҵġ����Կ϶��� �ȴ����м䣬�ٴ����������ٴ�������
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
	 * �������л�  { null, 1, null, 2, null}
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
	 * �����л�����
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
	 * ���������ӡ�� �� ͷ ��
	 * 
	 * ��ν���� = ���м䴦��ͷ
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
	 * ���õݹ�ʵ���������: ��������˳����������ͷ��������
	 * 
	 * �����ʹ�ӡ
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
	 * �������л� ������ ��Ϊ������
	 */
	
	
	//-------------------------------------------------------------------------------------------------
	
	/**
	 * ���������ӡ�� �� �� ͷ
	 * 
	 * ��ν���� = �������ͷ
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
	 * �������л�
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
	 * �����л�����
	 */
	
	public static BTNode PosDeserialize(Queue<String> poslist) {
		if (poslist == null || poslist.size() == 0) {
			return null;
		}
		// ������  ->  stack(������)
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
	 * ���������ӡ : = Q�� 
	 * 
	 * 	abcdef
	 * 
	 * 1. ���е���cur�� ��ӡ
	 * 2. cur����������������
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
	 * �������л�
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
				head = queue.poll(); // head ��   ��
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
	 * �����л�-����
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
		 * �������Ƿ��ǶԳƵģ�
		    symmetric:
		        1. left head val = right head val
		        2. left.left.val = right.right.val && left.right.val = right.left.val
		 */
		public static boolean isSymmetric(BTNode root) {
		    return isMirror(root, root);
		}
		
		// һ������ԭʼ��  head1
		// ��һ���Ƿ�����  head2
		public static boolean isMirror(BTNode head1, BTNode head2) {
			if (head1 == null && head2 == null) {
				return true;
			}
			if (head1 != null && head2 != null) {
				return head1.value == head2.value 
						&& isMirror(head1.left, head2.right) 
						&& isMirror(head1.right, head2.left);
			}
			// һ��Ϊ�գ�һ����Ϊ��  false
			return false;
		}
	
}
