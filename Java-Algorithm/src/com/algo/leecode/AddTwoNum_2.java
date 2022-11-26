package com.algo.leecode;

import com.algo.leecode.model.ListNode;

public class AddTwoNum_2 {
	
	/**
	  * �߾��ȼӷ�ģ��: carry = ��λ, ����Сѧ��ѧ���㷨д��
	  * 
	  * sum = carry + l2 + l1
	  * 
	  * �����Ǵ�������,���Ȳ�ͬ�ػ�ǰ�油0
	  * 
	  * ȡ��λ�� sum % 10
	  * 2��0~9������� / 10 => �����Ϊ0 �� 1
	  */
	
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	       
		 ListNode tail  = new ListNode(0);
		 ListNode dummy = tail;
		 int sum = 0;
		 
		 // ģ��: 
		 while(l1 != null || l2 != null || sum > 0) {
			 sum += (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val); // ��ǰ��λ�غ�
			 tail.next = new ListNode(sum % 10); // ����� > 10�ػ���ֻ��Ҫȡ���ĸ�λ��
			 tail = tail.next;
			 if (l1 != null) {
				 l1 = l1.next;
			 }
			 if (l2 != null) {
				 l2 = l2.next;
			 }
			 sum /= 10; // carry = (sum /= 10)�� �� sum < 10 ʱ�� carry = 0, �� sum >= 10 ʱ�� carry = 1
		 }
		 return dummy.next;
	 }
 
	 
}
