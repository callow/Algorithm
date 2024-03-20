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
 * ��ת������
 * ��ת˫����
 * ������ɾ���ڵ�
 * �ж������ǲ��ǻ��Ľṹ
 * �ҵ������е�λ��
 * ����һ������д��Queue
 * ����һ������д��Stack
 * 
 */
public class LinklistUtil {
	
	
	/**
	 * ��Arrayת��link list
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
	 * ��ת������
	 * 
	 * 1 -> 3 -> 4 -> null
	 * null <- 1 <- 3 <- 4
	 */
	
	public static Node<Integer> reverse(Node<Integer> head) {
		Node<Integer> pre = null;
		Node<Integer> next = null;
		while(head != null) {
			next = head.next;
			head.next = pre; // ��תָ��
			pre = head;
			head = next;
		}
		return pre;
	}
	
	/**
	 * ��ת˫����
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
	 * �ϲ�2�������������һ�������������
	 * 
	 *  https://leetcode.cn/problems/merge-two-sorted-lists/
	 */
	public static Node<Integer> merge2LinkedList(Node<Integer> head1, Node<Integer> head2) {
		if (head1 ==null || head2 == null) {
			return head1 == null ? head2 : head1;
		}
		// ˭С˭��ͷ
		Node<Integer> head = head1.value <= head2.value ? head1: head2;
		Node<Integer> cur1 = head.next; // ͷ����һ���ڵ�
		Node<Integer> cur2 = head == head1 ? head2 : head1; // ͷ����һ���ڵ�Ķ��ֽڵ�
		Node<Integer> pre = head; // �ҺõĽڵ�
		
		// 2���������գ���PK����˭С˭�͹���pre��һ����
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
		
		// һ����û�ˣ���һ��û��û���������β����
		pre.next = cur1 != null ? cur1 : cur2;
		return head; // ��Ϊpre��head�����ã�����pre�ĵ�ֵ��Ӱ��head
	}
	
	/**
	 * ������������=����ֵ�Ľڵ��ɾ��, �����µ�ͷ�ڵ�
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
		// ����ǰ�ڵ�val == Ҫɾ����val, ֮ǰ�ڵ�nextָ��ָ������һ���ڵ㣨cur.next), Ȼ��cur���ƣ�pre��������һ���ڵ�
		// 3334358 => 4 -> 5, Ȼ�� pre��4����5
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
	 * ��������ɾ���ظ�Ԫ�أ������Ȼ����
	 * 1 -> 1- >2 ->6 -> 9 -> 9
	 * 1 -> 2 ->6 ->9
	 * 
	 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
	 */
	
	public Node<Integer> removeDuplicate(Node<Integer> head) {
		Node<Integer> current = head;
		
		while(current != null && current.next != null) {
			// ɾ��next
			if (current.value == current.next.value) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
		
		return head;
		
	}
	
	
	/**
	 * ����һ������д��Queue
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<Integer>();
	}
	
	/**
	 * ����һ������д��Stack
	 */
	
	public static Stack<Integer> getStack() {
		return new Stack<Integer>();
	}
	/**
	 * ����һ������д��˫�˶���
	 */
	
	public static DoubleEndsQueue<Integer> DoubleEndsQueue() {
		return new DoubleEndsQueue<Integer>();
	}
	
	/**
	 *  �ж������ǲ��ǻ��Ľṹ<br><br>
	 *   ����1�� �ҵ��е�󣬺�벿�ַ�ת Space: O(1)<br>
	 *   ����2�� ��ջ��˳����룬Ȼ���ٱ���һ�鵯�� Space: O(N)
	 *   
	 *   https://leetcode.com/problems/palindrome-linked-list/
	 */
	
	public static boolean isPalindrome(Node head, String method) {
		if (method.equals("Stack")) { // ��ջ
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
		} else { // ����ջ
			if (head == null || head.next == null) {
				return true;
			}
			Node n1 = head;
			Node n2 = head;
			while (n2.next != null && n2.next.next != null) { // find mid node
				n1 = n1.next; // n1 -> mid
				n2 = n2.next.next; // n2 -> end
			}
			// n1 �е�
			
			
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
	 *  ����������ĳ��num��֯�� ���С�� �м���ȣ� �ұߴ� <br><br>
	 *  ˼·�� ��������������У�Ȼ������������partition
	 *  
	 *  ���С �� Сͷ��Сβ
	 *  �ұߴ� �� ��ͷ�� ��β
	 *  
	 *  ���Сβ ������ͷ
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
		// �Ѿ���ɷ������飬���ڿ�ʼ��������������������
		CommonArrayUtil.partition(nodeArr, num);
		for (i = 1; i != nodeArr.length; i++) {
			nodeArr[i - 1].next = nodeArr[i]; // ׷��nextָ��һ·����Node[0]
		}
		nodeArr[i - 1].next = null;
		return nodeArr[0];
	}
	
	/**
	 * ����һ�������ͷ�ڵ� head ��һ���ض�ֵ x �����������зָ���ʹ������ С�� x �Ľڵ㶼������ ���ڻ���� x �Ľڵ�֮ǰ��
		https://leetcode.cn/problems/partition-list/
	 *  ���С �� Сͷ��Сβ
	 *  �ұߴ� �� ��ͷ�� ��β
	 *  
	 *  ���Сβ ������ͷ
	 */
	public static Node<Integer>  partition(Node<Integer> head, int x) {
		Node<Integer>  leftHead = null, leftTail = null; // < x������
		Node<Integer>  rightHead = null, rightTail = null; // >=x������
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
		// < x�����������ݣ�
		leftTail.next = rightHead;
		return leftHead;
	}
	
	/**
	 * ��һ�������ָ��Ľڵ㸴��һ�ݣ�Ҫ��Space O(1) <br><br>
	 * ˼·�� ��¡Node(n')����old�ڵ����һ���� ���������randomָ�룬Ȼ��Ͽ����õ�node <br>
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
			cur.next = new RandomNode(cur.val); // ��¡�ڵ����old�ڵ����һ��
			cur.next.next = next; // ԭold�ڵ���һ�����ڿ�¡�ڵ���һ��
			cur = next;
		}
		cur = head;
		RandomNode copy = null;
		// 1 1' 2 2' 3 3'
		// �������� 1' 2' 3' randomָ��
		while (cur != null) { // һ��һ�Եİ�randomָ�����ú�
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}
		RandomNode res = head.next;
		cur = head;
		// �� �� ����һ��next�����ϣ�random��ȷ
		// next�����ϣ��������������
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
	 * 2�������л������޻�����������ཻ�����ص�һ���ཻ�ڵ� Space: O(1)
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
	 * �ҵ������е�λ�ã�����ָ�룬��ָ�����꣬��ָ��պ����е�λ�� 
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
	 * ������ӣ�984 + 9762 = ��  ��λ����˼· https://leetcode.cn/problems/add-two-numbers/
	 * 
	 * 4 -�� 8 -�� 9
	 * 2 -�� 6 -�� 7 -�� 9
	 */
	public static Node<Integer> add2Number(Node<Integer> h1, Node<Integer> h2) {
		Node<Integer> ans = null, cur = null;
		int carry = 0;
		for (int sum, val; // �������� 
				h1 != null && h2 != null; // ��ֹ����
				h1 = h1 == null ? null : h1.next, // h1�����������վͲ�����
				h2 = h2 == null ? null : h2.next //  h2�����������վͲ�����
				) {
			
			// 6 = 4 + 2
			sum = (h1 == null ? 0 : h1.value) + (h2 == null ? 0 : h2.value) + carry;
			val = sum % 10; // ��ǰ���� e.g sum = 14, val = 4, carry = 1
			carry = sum / 10; // ��ǰ��λ
			
			if (ans == null) {
				ans = new Node<Integer>(val); // ans ����ͷ�ڵ� ��Զ������
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
	    ɾ���� = 0 �Ľڵ�. 
	
	    - Prefix Sum + Hash Map
	        ~ ǰ׺��������preSum[a] = preSum[b], ��ԭ������ a.next ~ b Ϊ0��������
	        ~ HashMap: ��preSum���key��node���value
	    - ֻ��+-���в�����
	    
	    https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
	 */
	public Node<Integer> removeZeroSumSublists(Node<Integer> head) {
	    
		Node<Integer> front = new Node<>(0);
		front.next = head;
	
	    // ����ǰ׺�ͣ������� last occurrence of each sum in a HashMap
		Node<Integer> cur = front;
	    int preSum = 0;
	    Map<Integer, Node<Integer>> preSumMap = new HashMap<>();
	    while(cur != null) {
	        preSum += cur.value;
	        preSumMap.put(preSum,cur);
	        cur = cur.next;
	    }
	
	    // ����
	    preSum = 0;
	    cur = front;
	
	    // �ٱ���һ�飬��ʼɾ��(nextָ���ƶ�)
	    while(cur != null) {
	        preSum += cur.value;
	        cur.next = preSumMap.get(preSum).next; // If we have seen this sum before, it means the sublist between the previous occurrence and this one sums to zero.
	        cur = cur.next;
	    }
	
	    return front.next;
	}
	
}
