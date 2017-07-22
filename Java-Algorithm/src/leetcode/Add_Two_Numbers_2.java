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
		
		// 输入第一个数字链
		ListNode l1  = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		// 第二个数字链
		ListNode l2  = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		
		// 测试一下结果
		ListNode result =  Add_Two_Numbers_2.addTwoNumbers(l1, l2);
		System.out.println(result.val);
		System.out.println(result.next.val);
		System.out.println(result.next.next.val);
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		    
		    // 这个dummy head 就是结果集 用来返回数据用的
		    ListNode dummyHead = new ListNode(0);
		    
		    ListNode p = l1, q = l2, curr = dummyHead;
		    int carry = 0; // 定义进位
		    
		    while (p != null || q != null) {
		    	// 数据清洗 把null 换为 0 , 并取第一位数，也就是val
		        int x = (p != null) ? p.val : 0;
		        int y = (q != null) ? q.val : 0;
		        
		        int sum = carry + x + y; // 个位的和
		        carry = sum / 10; // < 10 就不进位 > 10 就进位
		        
		        // 这一步是运算的关键，这个是每一位运算和其他位无关 从个位开始，curr.next 是一个node对象
		        curr.next = new ListNode(sum % 10); // 这一步间接也就给了dummyHead加了一个next节点，问郭俊！
		        curr = curr.next;
		        
		        // 获取输入的第二位数
		        if (p != null) p = p.next;
		        if (q != null) q = q.next;
		    }
		    if (carry > 0) {
		        curr.next = new ListNode(carry);
		    }
		    return dummyHead.next; // 现在dummyHead 有四层 因为开始层是0 没用 所以从下一个开始
	}
}

class ListNode {
   int val; // 当前值
   ListNode next; // 下一个节点(值)对象的引用
   ListNode(int x) { val = x; } //构造器
	     
}
/**
 * 7 % 10  = 7  // 小 余 大 取 自己
 * 7 % 3 = 1 // 大 余 小 正常 取余数
 */
