package com.algo.util.AStar.model;

import java.util.Arrays;

/**
 * 贝尔曼Ford算法：
 * 	M: 边的数量
 *  N ： 点的数量，轮数最多N-1轮
 *  Time: O(M*N)， 复杂度很高 只能用于小图
 *  
 * 
 * 单源最短路： 和DJ一样目的，但是可以有负边 不可以有负环
 *  - 松弛操作：目的A -> S, 若经过P， 且distance[p] + w < distance[s], 即让distance[s]更小的，则P出发这条边对S进行了松弛操作 
 *  - 不停的轮询边很多轮进行松弛操作，直到不再有松弛操作行为了 算法停止
 *  - 初始状态: [0, ∞,∞,∞]  
 *  		  a  b  c d
 *   - 更新过程:[0, ∞, 8,6]
 *            a  b c d
 *  
 *  重要推广： 判断是否有负环，若从A出发到第N轮松弛还存在，说明A出发能够到达一个负环
 *  
 *  https://leetcode.cn/problems/cheapest-flights-within-k-stops/
 *   - 松弛轮数固定：K轮
 */
public class BellmanFord {

	// Bellman-Ford算法
	// 针对此题改写了松弛逻辑，课上讲了细节
	public static int findCheapestPrice(int n, int[][] flights, int start, int target, int k) {
		int[] cur = new int[n];
		Arrays.fill(cur, Integer.MAX_VALUE); // 初始化老表
		cur[start] = 0;
		for (int i = 0; i <= k; i++) {
			int[] next = Arrays.copyOf(cur, n); // 拷贝老表
			for (int[] edge : flights) { // 考察所有航班
				// 有一条边：a -> b , w. 我们从老表中拿看看是否有路(!=∞), 然后更新新表
				if (cur[edge[0]] != Integer.MAX_VALUE) {
					next[edge[1]] = Math.min(next[edge[1]], cur[edge[0]] + edge[2]);
				}
			}
			cur = next;
		}
		return cur[target] == Integer.MAX_VALUE ? -1 : cur[target];
	}
}
