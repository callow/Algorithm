package com.algo.tasks.day6;

import com.algo.util.bit.BitUtil;

/**
 * 
 * 一个正数数组arr[], 一个query[][], queries[i] = [xi, mi] 
 * 
 *  在arr[] 中，哪个数字，与xi异或最大且要<= mi ?
 *
 */
public class MaximumXorWithAnElementFromArray {
	
	public static class Node {
		public int min; // 在节点上上收集最小值
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
			for (int i = 30; i >= 0; i--) { // 因为题目给的是正数 所以可以省区一位
				int path = BitUtil.retrieveBit(num,i);
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path];
				cur.min = Math.min(cur.min, num); 
			}
		}

		// 这个结构中，已经收集了一票数字
		// 请返回哪个数字与X异或的结果最大，返回最大结果
		// 但是，只有<=m的数字，可以被考虑
		public int maxXorWithXBehindM(int x, int m) {
			if (head.min > m) { // 如果头节点的最小值都>m, 那么肯定没结果，不用往下走了
				return -1;
			}
			// 一定存在某个数可以和x结合
			Node cur = head;
			int ans = 0;
			for (int i = 30; i >= 0; i--) {
				int path = BitUtil.retrieveBit(x, i); 
				// 期待遇到的东西
				int best = (path ^ 1);
				best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;
				// best变成了实际遇到的
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
