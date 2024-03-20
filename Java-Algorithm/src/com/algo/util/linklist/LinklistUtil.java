package com.algo.util.linklist;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.model.DoubleNode;
import com.algo.util.common.model.Node;
import com.algo.util.common.model.RandomNode;
import com.algo.util.linklist.model.DoubleEndsQueue;
import com.algo.util.linklist.model.Queue;
import com.algo.util.linklist.model.Stack;

/**
 * 反转单链表
 * 反转双链表
 * 单链表删除节点
 * 判断链表是不是回文结构
 * 找到链表中点位置
 * 生成一个链表写的Queue
 * 生成一个链表写的Stack
 * 
 */
public class LinklistUtil {
	
	
	/**
	 * 将Array转成link list
	 */
	public static Node<Integer> convert(int[]head){
		Node<Integer> dummy = new Node<Integer>(-1);
		Node<Integer> cur= dummy;
	    for (int i = 0; i <head.length ; i++) {
	    	Node<Integer> node=new Node<>(head[i]);
	        cur.next = node;
	        cur = cur.next;
	    }
	    return dummy.next;
	}

	/**
	 * 反转单链表：
	 * 
	 * 1 -> 3 -> 4 -> null
	 * null <- 1 <- 3 <- 4
	 */
	
	public static Node<Integer> reverse(Node<Integer> head) {
		Node<Integer> pre = null;
		Node<Integer> next = null;
		while(head != null) {
			next = head.next;
			head.next = pre; // 反转指针
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
	 * 合并2个有序链表，组成一个大的有序链表
	 * 
	 *  https://leetcode.cn/problems/merge-two-sorted-lists/
	 */
	public static Node<Integer> merge2LinkedList(Node<Integer> head1, Node<Integer> head2) {
		if (head1 ==null || head2 == null) {
			return head1 == null ? head2 : head1;
		}
		// 谁小谁做头
		Node<Integer> head = head1.value <= head2.value ? head1: head2;
		Node<Integer> cur1 = head.next; // 头的下一个节点
		Node<Integer> cur2 = head == head1 ? head2 : head1; // 头的下一个节点的对手节点
		Node<Integer> pre = head; // 挂好的节点
		
		// 2个链表都不空，就PK看看谁小谁就挂在pre下一个上
		while (cur1 != null && cur2 != null) {
			if (cur1.value <= cur2.value) {
				pre.next = cur1; 
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		
		// 一个跳没了，另一个没跳没，最后做收尾工作
		pre.next = cur1 != null ? cur1 : cur2;
		return head; // 因为pre是head的引用，所以pre改的值会影响head
	}
	
	/**
	 * 单链表，把所有=给定值的节点给删掉, 返回新的头节点
	 * 
	 * 2 -> 3 -> 1 -> 3 ->2 ->4 -> null , remove 3 then
	 * 2 -> 1 ->2 -> 4 -> null
	 * 
	 * https://leetcode.com/problems/remove-linked-list-elements/
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
		// 若当前节点val == 要删除的val, 之前节点next指针指向我下一个节点（cur.next), 然后cur右移，pre来到我下一个节点
		// 3334358 => 4 -> 5, 然后 pre从4来到5
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
	 * 有序单链表删除重复元素，结果依然有序
	 * 1 -> 1- >2 ->6 -> 9 -> 9
	 * 1 -> 2 ->6 ->9
	 * 
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
	 */
	
	public Node<Integer> removeDuplicate(Node<Integer> head) {
		Node<Integer> current = head;
		
		while(current != null && current.next != null) {
			// 删除next
			if (current.value == current.next.value) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
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
	 *   
	 *   https://leetcode.com/problems/palindrome-linked-list/
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
	 *  
	 *  左边小 ： 小头，小尾
	 *  右边大 ： 大头， 大尾
	 *  
	 *  最后小尾 连到大头
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
	 * 给你一个链表的头节点 head 和一个特定值 x 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
		https://leetcode.cn/problems/partition-list/
	 *  左边小 ： 小头，小尾
	 *  右边大 ： 大头， 大尾
	 *  
	 *  最后小尾 连到大头
	 */
	public static Node<Integer>  partition(Node<Integer> head, int x) {
		Node<Integer>  leftHead = null, leftTail = null; // < x的区域
		Node<Integer>  rightHead = null, rightTail = null; // >=x的区域
		Node<Integer>  next = null;
		while (head != null) {
			next = head.next;
			head.next = null;
			if (head.value < x) {
				if (leftHead == null) {
					leftHead = head;
				} else {
					leftTail.next = head;
				}
				leftTail = head;
			} else {
				if (rightHead == null) {
					rightHead = head;
				} else {
					rightTail.next = head;
				}
				rightTail = head;
			}
			head = next;
		}
		if (leftHead == null) {
			return rightHead;
		}
		// < x的区域有内容！
		leftTail.next = rightHead;
		return leftHead;
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
	 * https://leetcode.com/problems/intersection-of-two-linked-lists/
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
	
	/**
	 * 链表相加：984 + 9762 = ？  进位问题思路 https://leetcode.cn/problems/add-two-numbers/
	 * 
	 * 4 -》 8 -》 9
	 * 2 -》 6 -》 7 -》 9
	 */
	public static Node<Integer> add2Number(Node<Integer> h1, Node<Integer> h2) {
		Node<Integer> ans = null, cur = null;
		int carry = 0;
		for (int sum, val; // 声明变量 
				h1 != null && h2 != null; // 终止条件
				h1 = h1 == null ? null : h1.next, // h1往下跳，到空就不跳了
				h2 = h2 == null ? null : h2.next //  h2往下跳，到空就不跳了
				) {
			
			// 6 = 4 + 2
			sum = (h1 == null ? 0 : h1.value) + (h2 == null ? 0 : h2.value) + carry;
			val = sum % 10; // 当前数字 e.g sum = 14, val = 4, carry = 1
			carry = sum / 10; // 当前进位
			
			if (ans == null) {
				ans = new Node<Integer>(val); // ans 就是头节点 永远不动了
				cur  = ans;
			} else {
				cur.next = new Node<Integer>(val);
				cur = cur.next;
			}
		}
		if (carry == 1) {
			cur.next = new Node<Integer>(1);
		}
		return ans;
	}
	
	
	
	
	/**
	    删除和 = 0 的节点. 
	
	    - Prefix Sum + Hash Map
	        ~ 前缀和数组中preSum[a] = preSum[b], 则原数组中 a.next ~ b 为0和子数组
	        ~ HashMap: 将preSum存进key，node存进value
	    - 只有+-都有才满足
	    
	    https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
	 */
	public Node<Integer> removeZeroSumSublists(Node<Integer> head) {
	    
		Node<Integer> front = new Node<>(0);
		front.next = head;
	
	    // 计算前缀和，并保存 last occurrence of each sum in a HashMap
		Node<Integer> cur = front;
	    int preSum = 0;
	    Map<Integer, Node<Integer>> preSumMap = new HashMap<>();
	    while(cur != null) {
	        preSum += cur.value;
	        preSumMap.put(preSum,cur);
	        cur = cur.next;
	    }
	
	    // 重置
	    preSum = 0;
	    cur = front;
	
	    // 再遍历一遍，开始删除(next指针移动)
	    while(cur != null) {
	        preSum += cur.value;
	        cur.next = preSumMap.get(preSum).next; // If we have seen this sum before, it means the sublist between the previous occurrence and this one sums to zero.
	        cur = cur.next;
	    }
	
	    return front.next;
	}
	
}
