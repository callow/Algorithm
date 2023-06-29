package com.algo.tasks.day6;

import com.algo.util.bit.BitUtil;
/**
 * ǰ׺������Ҫ��32��λ����λ -> ��λ �ң���Ϊ����λ�������Ĵ�С. 
 * ���Ÿ�λ��1 ������������ֵ
 *
 */
public class MaxXOR {

	// ǰ׺����Node�ṹ
	// nexts[0] -> 0�����·
	// nexts[1] -> 1�����·
	// nexts[0] == null 0������û·��
	// nexts[0] != null 0������·����������һ���ڵ�
	// nexts[1] == null 1������û·��
	// nexts[1] != null 1������·����������һ���ڵ�
	public static class Node {
		public Node[] nexts = new Node[2];
	}
	
	// ���ڱ��⣬����ǰ׺����ʵ�� 
	public static class NumTrie {
		// ͷ�ڵ�
		public Node head = new Node();

		// ��ÿ�����ֵ�32λ�����ϣ�������̶̹���32�� O(1)
		public void add(int num) {
			Node cur = head;
			for (int i = 31; i >= 0; i--) {
				int path = BitUtil.retrieveBit(num, i); // 0 or 1
				// ��·�½� ��·����
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path]; // ����һ���ڵ�
			}
		}

		// �ýṹ֮ǰ�ռ���һƱ���֣����ҽ�����ǰ׺��
		// num�� ˭ ^ �ܳ�����ֵ���ѽ�����أ� O(1)
		// �˴� num �� 0 ~ i�����ͣ� i ����(0 ~N)
		// e.g : num = 0110 = 4
		public int maxXor(int num) {
			Node cur = head;
			int ans = 0;
			for (int i = 31; i >= 0; i--) {
				// ȡ��num�е�iλ��״̬��pathֻ������ֵ0��1������
				int path = BitUtil.retrieveBit(num, i);
				
				// �ڴ������Ķ���
				int best = i == 31 ? path : (path ^ 1);
				
				// ʵ�������Ķ���,��Ϊ�ڴ������Ķ�����һ���ܵõ���best�ᱻ�ĵ�
				best = cur.nexts[best] != null ? best : (best ^ 1);
				
				// (path ^ best) ��ǰλλ�����Ľ��
				ans |= BitUtil.retrieveBit((path ^ best), i);   
				cur = cur.nexts[best];
			}
			return ans;
		}
	}
	
	// O(N)
	public static int maxXorSubarray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		// 0~i��������
		int xor = 0;
		 // ʹ��ǰ׺������map, ������ʷ�������Ƚϣ���Ϊ��ʷǰ׺���Ѿ�����������
		NumTrie trie = new NumTrie();
		trie.add(0);
		
		// ��iλ�ý�β������£�����^��
		for (int i = 0; i < arr.length; i++) {
			xor ^= arr[i]; // 0 ~ i ���ͣ�e.g 0 ~ 5����
			max = Math.max(max, trie.maxXor(xor) ); // O(1)
			trie.add(xor);
		}
		return max;
	}
	
	
	
}
