package com.algo.leecode;

import com.algo.leecode.model.ListNode;

public class AddTwoNum_2 {
	
	/**
	  * 高精度加法模板: carry = 进位, 就是小学数学用算法写的
	  * 
	  * sum = carry + l2 + l1
	  * 
	  * 链表是从左往右,长度不同地话前面补0
	  * 
	  * 取个位： sum % 10
	  * 2个0~9地数相加 / 10 => 结果必为0 或 1
	  */
	
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	       
		 ListNode tail  = new ListNode(0);
		 ListNode dummy = tail;
		 int sum = 0;
		 
		 // 模板: 
		 while(l1 != null || l2 != null || sum > 0) {
			 sum += (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val); // 当前这位地和
			 tail.next = new ListNode(sum % 10); // 如果它 > 10地话，只需要取它的个位数
			 tail = tail.next;
			 if (l1 != null) {
				 l1 = l1.next;
			 }
			 if (l2 != null) {
				 l2 = l2.next;
			 }
			 sum /= 10; // carry = (sum /= 10)， 当 sum < 10 时， carry = 0, 当 sum >= 10 时， carry = 1
		 }
		 return dummy.next;
	 }
 
	 
}
