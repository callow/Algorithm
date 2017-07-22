package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.

   You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
 * 
 *
 */
public class Add_Two_Numbers_2 {
	public static void main(String[] args) {
		
		// �����һ��������
		ListNode l1  = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		// �ڶ���������
		ListNode l2  = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		
		// ����һ�½��
		ListNode result =  Add_Two_Numbers_2.addTwoNumbers(l1, l2);
		System.out.println(result.val);
		System.out.println(result.next.val);
		System.out.println(result.next.next.val);
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		    
		    // ���dummy head ���ǽ���� �������������õ�
		    ListNode dummyHead = new ListNode(0);
		    
		    ListNode p = l1, q = l2, curr = dummyHead;
		    int carry = 0; // �����λ
		    
		    while (p != null || q != null) {
		    	// ������ϴ ��null ��Ϊ 0 , ��ȡ��һλ����Ҳ����val
		        int x = (p != null) ? p.val : 0;
		        int y = (q != null) ? q.val : 0;
		        
		        int sum = carry + x + y; // ��λ�ĺ�
		        carry = sum / 10; // < 10 �Ͳ���λ > 10 �ͽ�λ
		        
		        // ��һ��������Ĺؼ��������ÿһλ���������λ�޹� �Ӹ�λ��ʼ��curr.next ��һ��node����
		        curr.next = new ListNode(sum % 10); // ��һ�����Ҳ�͸���dummyHead����һ��next�ڵ㣬�ʹ�����
		        curr = curr.next;
		        
		        // ��ȡ����ĵڶ�λ��
		        if (p != null) p = p.next;
		        if (q != null) q = q.next;
		    }
		    if (carry > 0) {
		        curr.next = new ListNode(carry);
		    }
		    return dummyHead.next; // ����dummyHead ���Ĳ� ��Ϊ��ʼ����0 û�� ���Դ���һ����ʼ
	}
}

class ListNode {
   int val; // ��ǰֵ
   ListNode next; // ��һ���ڵ�(ֵ)���������
   ListNode(int x) { val = x; } //������
	     
}
/**
 * 7 % 10  = 7  // С �� �� ȡ �Լ�
 * 7 % 3 = 1 // �� �� С ���� ȡ����
 */
