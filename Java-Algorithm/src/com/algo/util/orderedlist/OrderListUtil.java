package com.algo.util.orderedlist;

import com.algo.util.orderedlist.sbtree.SBTreeSet;

public class OrderListUtil {
	
	/**
	 *  �������ۼӺ��Ƿ��ꡣ ��꼴��[lower,upper] ��
	 *  
	 *  ˼·�� �����������1��β�����������Χ���м����� = ������0��ͷ�ж��ٸ�ǰ׺�����������Χ��
	 *  
	 *  0~i ���ۼӺ�S�� [a,b], => �ж��ٸ�ǰ׺������[s-b,s-a]��
	 */
	public static final int countRangeSum(int[] nums, int lower, int upper) {
		// �ںУ��������֣�ǰ׺�ͣ�����ȥ�أ����Խ����ظ�����
		// < num , �м�������
		SBTreeSet treeSet = new SBTreeSet();
		long sum = 0;
		int ans = 0;
		treeSet.add(0);// һ������û�е�ʱ�򣬾��Ѿ���һ��ǰ׺���ۼӺ�Ϊ0��
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			// [sum - upper, sum - lower]
			// [10, 20] ?
			// < 10 ?  < 21 ?   
			long a = treeSet.lessKeySize(sum - lower + 1);
			long b = treeSet.lessKeySize(sum - upper);
			ans += a - b;
			treeSet.add(sum);
		}
		return ans;
		
	}

}
