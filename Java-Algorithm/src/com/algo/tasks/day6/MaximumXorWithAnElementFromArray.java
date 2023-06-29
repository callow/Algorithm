package com.algo.tasks.day6;

import com.algo.util.bit.BitUtil;

/**
 * 
 * һ����������arr[], һ��query[][], queries[i] = [xi, mi] 
 * 
 *  ��arr[] �У��ĸ����֣���xi��������Ҫ<= mi ?
 *
 */
public class MaximumXorWithAnElementFromArray {
	
	public static class Node {
		public int min; // �ڽڵ������ռ���Сֵ
		public Node[] nexts;

		public Node() {
			min = Integer.MAX_VALUE;
			nexts = new Node[2];
		}
	}

	public static class NumTrie {
		public Node head = new Node();

		public void add(int num) {
			Node cur = head;
			head.min = Math.min(head.min, num);
			for (int i = 30; i >= 0; i--) { // ��Ϊ��Ŀ���������� ���Կ���ʡ��һλ
				int path = BitUtil.retrieveBit(num,i);
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path];
				cur.min = Math.min(cur.min, num); 
			}
		}

		// ����ṹ�У��Ѿ��ռ���һƱ����
		// �뷵���ĸ�������X���Ľ����󣬷��������
		// ���ǣ�ֻ��<=m�����֣����Ա�����
		public int maxXorWithXBehindM(int x, int m) {
			if (head.min > m) { // ���ͷ�ڵ����Сֵ��>m, ��ô�϶�û�����������������
				return -1;
			}
			// һ������ĳ�������Ժ�x���
			Node cur = head;
			int ans = 0;
			for (int i = 30; i >= 0; i--) {
				int path = BitUtil.retrieveBit(x, i); 
				// �ڴ������Ķ���
				int best = (path ^ 1);
				best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;
				// best�����ʵ��������
				ans |=  BitUtil.retrieveBit((path ^ best), i); 
				cur = cur.nexts[best];
			}
			return ans;
		}
	}
	
	public static int[] maximizeXor(int[] nums, int[][] queries) {
		int N = nums.length;
		NumTrie trie = new NumTrie();
		for (int i = 0; i < N; i++) {
			trie.add(nums[i]);
		}
		int M = queries.length;
		int[] ans = new int[M];
		for (int i = 0; i < M; i++) {
			ans[i] = trie.maxXorWithXBehindM(queries[i][0], queries[i][1]);
		}
		return ans;
	}
}
