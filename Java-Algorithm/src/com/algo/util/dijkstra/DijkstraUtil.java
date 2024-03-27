package com.algo.util.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 图上单源 -> 所有节点的最短路径
 * 
 * 很多题目distance的定义不同所以略做调整, 有些是最小代价cost
 */
public class DijkstraUtil {
	
	// 0:上，1:右，2:下，3:左
	public static int[] move = new int[] { -1, 0, 1, 0, -1 };
	
	/**
	 * 
		所有点最大接受信号的延迟时间： https://leetcode.com/problems/network-delay-time
		
		Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
		
	 */
	public static int networkDelayTime(int[][] times, int n, int source) {
		
		// build graph, adjacent table = nexts
		ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			nexts.add(new ArrayList<>());
		}
		for (int[] delay : times) {
			nexts.get(delay[0]).add(new int[] { delay[1], delay[2] });
		}
		
		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[source] = 0;
		boolean[] visited = new boolean[n + 1];
		// 0: current node, 1: source to current node distance
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		heap.add(new int[] { source, 0 }); // source到自己距离为0
		
		/**
		 * 填充distance[] 即source到每个点的最小距离
		 * 小根堆弹出：
		 * 	1. visited = true 不做处理
		 *  2. visited = false， 将它设置true ，弹出u，考察每条临边，假设某边去v权重w
		 *  	if visited[v] = false && distance[u] + w < distance[v]
		 *  		distance[v] = distance[u] + w , 
		 *  		heap.add({v,distance[u] + w})
		 * note : distance[u] = source到u的距离
		 */
		while(!heap.isEmpty()) {
			int u = heap.poll()[0];
			if (visited[u]) { // 弹出过，skip
				continue;
			}
			visited[u] = true; // 弹出
			for (int[] edge : nexts.get(u)) { // 考察从u出发的每一条边，to v , weight: w
				int v = edge[0];
				int w = edge[1];
				if (!visited[v] && distance[u] + w < distance[v]) {
					distance[v] = distance[u] + w;
					heap.add(new int[] { v, distance[u] + w });
				}
			}
		}
		
		// 题目专属，如果还是max，则信号没有传输到每个点 -1， 反之有，求一个最大值
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			if (distance[i] == Integer.MAX_VALUE) { // 如果有点信号依然没有传到， 返回 -1
				return -1;
			}
			ans = Math.max(ans, distance[i]);
		}
		
		return ans;
		
	}
	
	/**
	 * 路径最小花费 ： https://leetcode.com/problems/path-with-minimum-effort/
	 * 
	 * distance = 相邻格子的绝对值
	 */

	public static int  minimumEffortPath(int[][] matrix) {
		// (0,0)源点 -> (x,y)
		int n = matrix.length;
		int m = matrix[0].length;
		
		int[][] distance = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[0][0] = 0;
		boolean[][] visited = new boolean[n][m];
		// 0 : 格子的行
		// 1 : 格子的列
		// 2 : 源点到当前格子的代价
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
		heap.add(new int[] { 0, 0, 0 });
		
		/**
		 * 填充distance[][] 即source到每个点的最小距离
		 */
		
		while(!heap.isEmpty()) {
			int[] record = heap.poll();
			int x = record[0], y = record[1], cost = record[2];
			if (visited[x][y]) {
				continue;
			}
			if (x == n - 1 && y == m - 1) {
				// 常见剪枝
				// 发现终点直接返回
				// 不用等都结束
				return cost;
			}
			visited[x][y] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + move[i], ny = y + move[i + 1]; // 即将要去的[x][y]
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					int newCost = Math.max(cost, Math.abs(matrix[x][y] - matrix[nx][ny]));
					if (newCost < distance[nx][ny]) {
						distance[nx][ny] = newCost;
						heap.add(new int[] { nx, ny, newCost });
					}
				}
			}
		}
		return -1;
		
	}

}
