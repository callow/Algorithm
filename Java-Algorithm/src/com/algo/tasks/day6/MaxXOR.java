package com.algo.tasks.day6;

import com.algo.util.bit.BitUtil;
/**
 * 前缀树此题要从32分位，高位 -> 低位 挂，因为，高位决定树的大小. 
 * 紧着高位变1 最后结果才是最大值
 *
 */
public class MaxXOR {

	// 前缀树的Node结构
	// nexts[0] -> 0方向的路
	// nexts[1] -> 1方向的路
	// nexts[0] == null 0方向上没路！
	// nexts[0] != null 0方向有路，可以跳下一个节点
	// nexts[1] == null 1方向上没路！
	// nexts[1] != null 1方向有路，可以跳下一个节点
	public static class Node {
		public Node[] nexts = new Node[2];
	}
	
	// 基于本题，定制前缀树的实现 
	public static class NumTrie {
		// 头节点
		public Node head = new Node();

		// 将每个数字的32位挂树上，加入过程固定走32步 O(1)
		public void add(int num) {
			Node cur = head;
			for (int i = 31; i >= 0; i--) {
				int path = BitUtil.retrieveBit(num, i); // 0 or 1
				// 无路新建 有路复用
				cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
				cur = cur.nexts[path]; // 跳下一个节点
			}
		}

		// 该结构之前收集了一票数字，并且建好了前缀树
		// num和 谁 ^ 能出最大的值（把结果返回） O(1)
		// 此处 num 是 0 ~ i的异或和， i 属于(0 ~N)
		// e.g : num = 0110 = 4
		public int maxXor(int num) {
			Node cur = head;
			int ans = 0;
			for (int i = 31; i >= 0; i--) {
				// 取出num中第i位的状态，path只有两种值0就1，整数
				int path = BitUtil.retrieveBit(num, i);
				
				// 期待遇到的东西
				int best = i == 31 ? path : (path ^ 1);
				
				// 实际遇到的东西,因为期待遇到的东西不一定能得到，best会被改掉
				best = cur.nexts[best] != null ? best : (best ^ 1);
				
				// (path ^ best) 当前位位异或完的结果
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
		// 0~i整体异或和
		int xor = 0;
		 // 使用前缀树代替map, 来和历史数据做比较，因为历史前缀和已经挂在树上了
		NumTrie trie = new NumTrie();
		trie.add(0);
		
		// 以i位置结尾的情况下，最大的^和
		for (int i = 0; i < arr.length; i++) {
			xor ^= arr[i]; // 0 ~ i 异或和，e.g 0 ~ 5异或和
			max = Math.max(max, trie.maxXor(xor) ); // O(1)
			trie.add(xor);
		}
		return max;
	}
	
	
	
}
