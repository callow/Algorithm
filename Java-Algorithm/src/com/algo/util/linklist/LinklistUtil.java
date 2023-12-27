package com.algo.util.linklist;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.model.DoubleNode;
import com.algo.util.common.model.Node;
import com.algo.util.common.model.RandomNode;
import com.algo.util.linklist.model.DoubleEndsQueue;
import com.algo.util.linklist.model.Queue;
import com.algo.util.linklist.model.Stack;

public class LinklistUtil {

	/**
	 * 反转单链表
	 */
	
	public static Node<Integer> reverse(Node<Integer> head) {
		Node<Integer> pre = null;
		Node<Integer> next = null;
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/**
	 * 反转双链表
	 */
	
	public static DoubleNode<Integer> reverse(DoubleNode<Integer> head) {
		DoubleNode<Integer> pre = null;
		DoubleNode<Integer> next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/**
	 * 单链表，把给定值给删掉
	 */
	
	public static Node<Integer> removeValue(Node<Integer> head, int num) {
		while(head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node<Integer> pre = head;
		Node<Integer> cur = head;
		while(cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}
	
	/**
	 * 生成一个链表写的Queue
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<Integer>();
	}
	
	/**
	 * 生成一个链表写的Stack
	 */
	
	public static Stack<Integer> getStack() {
		return new Stack<Integer>();
	}
	/**
	 * 生成一个链表写的双端队列
	 */
	
	public static DoubleEndsQueue<Integer> DoubleEndsQueue() {
		return new DoubleEndsQueue<Integer>();
	}
	
	/**
	 *  判断链表是不是回文结构<br><br>
	 *   方法1： 找到中点后，后半部分反转 Space: O(1)<br>
	 *   方法2： 用栈，顺序加入，然后再遍历一遍弹出 Space: O(N)
	 */
	
	public static boolean isPalindrome(Node head, String method) {
		if (method.equals("Stack")) { // 用栈
			Stack<Node> stack = new Stack<Node>();
			Node cur = head;
			while (cur != null) {
				stack.push(cur);
				cur = cur.next;
			}
			while (head != null) {
				if (head.value != stack.pop().value) {
					return false;
				}
				head = head.next;
			}
			return true;
		} else { // 不用栈
			if (head == null || head.next == null) {
				return true;
			}
			Node n1 = head;
			Node n2 = head;
			while (n2.next != null && n2.next.next != null) { // find mid node
				n1 = n1.next; // n1 -> mid
				n2 = n2.next.next; // n2 -> end
			}
			// n1 中点
			
			
			n2 = n1.next; // n2 -> right part first node
			n1.next = null; // mid.next -> null
			Node n3 = null;
			while (n2 != null) { // right part convert
				n3 = n2.next; // n3 -> save next node
				n2.next = n1; // next of right node convert
				n1 = n2; // n1 move
				n2 = n3; // n2 move
			}
			n3 = n1; // n3 -> save last node
			n2 = head;// n2 -> left first node
			boolean res = true;
			while (n1 != null && n2 != null) { // check palindrome
				if (n1.value != n2.value) {
					res = false;
					break;
				}
				n1 = n1.next; // left to mid
				n2 = n2.next; // right to mid
			}
			n1 = n3.next;
			n3.next = null;
			while (n1 != null) { // recover list
				n2 = n1.next;
				n1.next = n3;
				n3 = n1;
				n1 = n2;
			}
			return res;
		}
	}
	
	/**
	 *  将单链表按照某个num组织成 左边小， 中间相等， 右边大 <br><br>
	 *  思路： 将链表放入数组中，然后在数组上做partition
	 */
	
	public static Node<Integer> sort(Node<Integer> head, int num) {
		if (head == null) {
			return head;
		}
		Node<Integer> cur = head;
		int i = 0;
		while (cur != null) {
			i++;
			cur = cur.next;
		}
		Node<Integer>[] nodeArr = new Node[i];
		i = 0;
		cur = head;
		for (i = 0; i != nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		// 已经完成放入数组，现在开始分区，即在数组中排序
		CommonArrayUtil.partition(nodeArr, num);
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i]; // 追加next指针一路给到Node[0]
		}
		nodeArr[i - 1].next = null;
		return nodeArr[0];
	}
	
	/**
	 * 将一个带随机指针的节点复制一份，要求Space O(1) <br><br>
	 * 思路： 克隆Node(n')挂在old节点的下一个， 最后设置完random指针，然后断开无用的node <br>
	 * https://leetcode.com/problems/copy-list-with-random-pointer/
	 */
	
	public static RandomNode copy(RandomNode head) {
		if (head == null) {
			return null;
		}
		RandomNode cur = head;
		RandomNode next = null;
		// 1 -> 2 -> 3 -> null
		// 1 -> 1' -> 2 -> 2' -> 3 -> 3'
		while (cur != null) {
			next = cur.next;
			cur.next = new RandomNode(cur.val); // 克隆节点挂在old节点的下一个
			cur.next.next = next; // 原old节点下一个挂在克隆节点下一个
			cur = next;
		}
		cur = head;
		RandomNode copy = null;
		// 1 1' 2 2' 3 3'
		// 依次设置 1' 2' 3' random指针
		while (cur != null) { // 一对一对的把random指针设置好
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}
		RandomNode res = head.next;
		cur = head;
		// 老 新 混在一起，next方向上，random正确
		// next方向上，把新老链表分离
		while (cur != null) {
			next = cur.next.next;
			copy = cur.next;
			cur.next = next;
			copy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
		
	}
	
	/**
	 * 2个可能有环可能无环的链表，如果相交，返回第一个相交节点 Space: O(1)
	 */
	
	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = LoopUtil.getEntryPoint(head1);
		Node loop2 = LoopUtil.getEntryPoint(head2);
		if (loop1 == null && loop2 == null) {
			return LoopUtil.noLoopIntersection(head1, head2);
		}
		if (loop1 != null && loop2 != null) {
			return LoopUtil.bothLoopIntersection(head1, loop1, head2, loop2);
		}
		return null;
	}
	
	/**
	 * 找到链表中点位置，快慢指针，快指针走完，慢指针刚好是中点位置 
	 */
	
	public static Node getMiddlePointForward(Node head) {
		Node slow = head;
		Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
	}
	
}
