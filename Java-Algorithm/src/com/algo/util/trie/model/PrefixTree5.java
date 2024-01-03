package com.algo.util.trie.model;

import java.util.Arrays;

import com.algo.util.bit.BitUtil;

/**
 * 使用静态数组 +  数组分路法节省空间的前缀树 - 2条路
 * 
 * 都不需要节点信息
 * 
 */			 
public class PrefixTree5 {

	    // 准备这么多静态空间就够了，实验出来的
		// 如果测试数据升级了规模，就改大这个值
		public static int MAXN = 3000001;

		public static int[][] tree = new int[MAXN][2]; // 0 / 1

		// 前缀树目前使用了多少空间
		public static int cnt;

		// 数字只需要从哪一位开始考虑
		public static int high;

		public static void build(int[] nums) {
			cnt = 1;
			// 找个最大值
			int max = Arrays.stream(nums).max().getAsInt();
			
			// 计算数组最大值的二进制状态，有多少个前缀的0
			// 可以忽略这些前置的0，从left位开始考虑, 即 31 - 前导0的数量 = 从那一个最高位开始建树（省空间）
			high = 31 - Integer.numberOfLeadingZeros(max);
			for (int num : nums) {
				insert(num);
			}
		}

		public static void insert(int num) {
			int cur = 1;
			for (int i = high, path; i >= 0; i--) {
				path = (num >> i) & 1;
				if (tree[cur][path] == 0) {
					tree[cur][path] = ++cnt;
				}
				cur = tree[cur][path];
			}
		}

		public static int maxXor(int num) {
			// 最终异或的结果(尽量大)
			int ans = 0;
			// 前缀树目前来到的节点编号
			int cur = 1;
			for (int i = high, status, want; i >= 0; i--) {
				// status : num第i位的状态
				status = BitUtil.retrieveBit(num, i);   
				// want : num第i位希望遇到的状态
				want =  BitUtil.flip(status);
				if (tree[cur][want] == 0) { // 询问前缀树，能不能达成
					// 不能达成,再恢复回去
					want ^= 1;
				}
				// want变成真的往下走的路 
								
				ans = BitUtil.setStatusAtIndex(ans, (status ^ want), i); // ans |= (status ^ want) << i;
				cur = tree[cur][want];
			}
			return ans;
		}

		public static void clear() {
			for (int i = 1; i <= cnt; i++) {
				tree[i][0] = tree[i][1] = 0;
			}
		}
}
