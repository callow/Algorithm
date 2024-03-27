package com.algo.util.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * ͼ�ϵ�Դ -> ���нڵ�����·��
 * 
 * �ܶ���Ŀdistance�Ķ��岻ͬ������������, ��Щ����С����cost
 */
public class DijkstraUtil {
	
	// 0:�ϣ�1:�ң�2:�£�3:��
	public static int[] move = new int[] { -1, 0, 1, 0, -1 };
	
	/**
	 * 
		���е��������źŵ��ӳ�ʱ�䣺 https://leetcode.com/problems/network-delay-time
		
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
		heap.add(new int[] { source, 0 }); // source���Լ�����Ϊ0
		
		/**
		 * ���distance[] ��source��ÿ�������С����
		 * С���ѵ�����
		 * 	1. visited = true ��������
		 *  2. visited = false�� ��������true ������u������ÿ���ٱߣ�����ĳ��ȥvȨ��w
		 *  	if visited[v] = false && distance[u] + w < distance[v]
		 *  		distance[v] = distance[u] + w , 
		 *  		heap.add({v,distance[u] + w})
		 * note : distance[u] = source��u�ľ���
		 */
		while(!heap.isEmpty()) {
			int u = heap.poll()[0];
			if (visited[u]) { // ��������skip
				continue;
			}
			visited[u] = true; // ����
			for (int[] edge : nexts.get(u)) { // �����u������ÿһ���ߣ�to v , weight: w
				int v = edge[0];
				int w = edge[1];
				if (!visited[v] && distance[u] + w < distance[v]) {
					distance[v] = distance[u] + w;
					heap.add(new int[] { v, distance[u] + w });
				}
			}
		}
		
		// ��Ŀר�����������max�����ź�û�д��䵽ÿ���� -1�� ��֮�У���һ�����ֵ
		int ans = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			if (distance[i] == Integer.MAX_VALUE) { // ����е��ź���Ȼû�д����� ���� -1
				return -1;
			}
			ans = Math.max(ans, distance[i]);
		}
		
		return ans;
		
	}
	
	/**
	 * ·����С���� �� https://leetcode.com/problems/path-with-minimum-effort/
	 * 
	 * distance = ���ڸ��ӵľ���ֵ
	 */

	public static int  minimumEffortPath(int[][] matrix) {
		// (0,0)Դ�� -> (x,y)
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
		// 0 : ���ӵ���
		// 1 : ���ӵ���
		// 2 : Դ�㵽��ǰ���ӵĴ���
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
		heap.add(new int[] { 0, 0, 0 });
		
		/**
		 * ���distance[][] ��source��ÿ�������С����
		 */
		
		while(!heap.isEmpty()) {
			int[] record = heap.poll();
			int x = record[0], y = record[1], cost = record[2];
			if (visited[x][y]) {
				continue;
			}
			if (x == n - 1 && y == m - 1) {
				// ������֦
				// �����յ�ֱ�ӷ���
				// ���õȶ�����
				return cost;
			}
			visited[x][y] = true;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + move[i], ny = y + move[i + 1]; // ����Ҫȥ��[x][y]
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
