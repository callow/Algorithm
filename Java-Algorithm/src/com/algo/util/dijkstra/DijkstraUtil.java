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
	 * DJģ�壺
	 * 
		���е��������źŵ��ӳ�ʱ�䣺 https://leetcode.com/problems/network-delay-time
		
		Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
		
		������source����Զ�㻨�Ѷ೤ʱ��
		
	 */
	public static int networkDelayTime(int[][] times, int n, int source) {
		
		// build graph, adjacent table = nexts
		ArrayList<ArrayList<int[]>> nexts = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			nexts.add(new ArrayList<>());
		}
		for (int[] delay : times) {
			int fromPoint = delay[0];
			int toPoint = delay[1];
			int weight = delay[2];
			nexts.get(fromPoint).add(new int[] { toPoint, weight });
		}
		
		// ��ʼDJ�㷨
		int[] distance = new int[n + 1]; 
		Arrays.fill(distance, Integer.MAX_VALUE); // distance[��,��,��,��,..]
		distance[source] = 0;
		boolean[] visited = new boolean[n + 1]; // false false false ...
		// 0: ��ǰ�ڵ�, 1: ԭ�㵽��ǰ�ڵ����
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // С���ѣ�С���ڶѶ�
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
			if (visited[u]) { // ���u��������skip
				continue;
			}
			visited[u] = true; // �״ε���
			for (int[] edge : nexts.get(u)) { // �����u������ÿһ���ߣ�ȥ�� v , Ȩ��: w
				int v = edge[0];
				int w = edge[1];
				if (!visited[v] && distance[u] + w < distance[v]) {
					distance[v] = distance[u] + w;
					heap.add(new int[] { v, distance[u] + w });
				}
			}
		}
		
		// ��Ŀר��Сβ�ͣ��������max�����ź�û�д��䵽ÿ���� -1�� ��֮�У���һ�����ֵ
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
				distance[i][j] = Integer.MAX_VALUE; // distance[��,��,��,��,..]
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
			int x = record[0];
			int	y = record[1]; 
			int	cost = record[2];
			
			if (visited[x][y]) { // ֮ǰ������������
				continue;
			}
			if (x == n - 1 && y == m - 1) {
				// ������֦
				// �����յ�ֱ�ӷ���
				// ���õȶ�����
				return cost;
			}
			visited[x][y] = true;
			
			for (int i = 0; i < 4; i++) { // ���������ƶ�������ͼ
				int nx = x + move[i];
				int	ny = y + move[i + 1]; // ����Ҫȥ��[nx][ny]
				
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					int newCost = Math.max(cost, Math.abs(matrix[x][y] - matrix[nx][ny])); // ����Ҫȥ�����ӵĴ��ۣ�����cost����cost�Ƚ�ȡ���ֵ
					if (newCost < distance[nx][ny]) { // ����С����
						distance[nx][ny] = newCost;
						heap.add(new int[] { nx, ny, newCost });
					}
				}
			}
		}
		return -1;
		
	}
	
	
	/**
	 * ˮλ������Ӿ������Ӿ: https://leetcode.com/problems/swim-in-rising-water/
	 * 
	 * ���� �����Ͻǵ㵽������ƽ̨ (n-1, n-1) ���������ʱ��
	 */
	
	public static int swimInWater(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] distance = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[0][0] = grid[0][0];
		boolean[][] visited = new boolean[n][m];
		// 0 : ���ӵ���
		// 1 : ���ӵ���
		// 2 : Դ�㵽��ǰ���ӵĴ���
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		heap.add(new int[] { 0, 0, grid[0][0] });
		while (!heap.isEmpty()) {
			int x = heap.peek()[0];
			int y = heap.peek()[1];
			int c = heap.peek()[2]; // �������� = ��;·�������߶�
			heap.poll();
			if (visited[x][y]) {
				continue;
			}
			visited[x][y] = true;
			if (x == n - 1 && y == m - 1) {
				// ������֦
				// �����յ�ֱ�ӷ���
				// ���õȶ�����
				return c;
			}
			for (int i = 0, nx, ny, nc; i < 4; i++) {
				nx = x + move[i];
				ny = y + move[i + 1];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					nc = Math.max(c, grid[nx][ny]);
					if (nc < distance[nx][ny]) {
						distance[nx][ny] = nc;
						heap.add(new int[] { nx, ny, nc });
					}
				}
			}
		}
		return -1;
	}
	
	//-----------------------------�ֲ����· / �������· --------------------------------------------
	
	// ����ʵ��λ�ÿ���ͼ�ϵĵ㣬����ʵ��λ�ú���״̬��ϵ���ͼ�ϵĵ�
	
	/**
	 * Կ��������ǰ����һ����������
	 * ������2��Կ�ף�ab, �����ռ�Կ�׵�״̬���Է�4��
	 * 	00 01 10 11
	 *  ab ab ab ab
	 * 
	 * ͼ�ϵĵ��ǣ�λ�ã�״̬�� -> (0,0,11)
	 * 
	 * ��@��ʼ��ʲôʱ������11״̬ʲôʱ����� �ο��� GraphUtil.shortestPathAllKeys ���Լ�д
	 */
	
	 //-------------------------------------------------------------------------------------------
	
	/**
	 * �綯���γ���: https://leetcode.cn/problems/DFPeFJ/
	 * 
	 * С���ĵ綯����������ʱ����ʻ����Ϊ cnt��ÿ��ʻ 1 ��λ�������� 1 ��λ�������һ��� 1 ��λʱ��, ��ͼ�Ϲ��� N �����㣬������Ϊ 0 ~ N-1
	 * ������ͼ��Ϣ�� [���� A ���,���� B ���,�����м����] ��ʽ�������ڶ�ά���� paths ��ʾ���� A��B �����˫��ͨ·
	 * ��ʼ״̬���綯������Ϊ 0��ÿ�����ж����г��׮ charge[i] ��ʾ�� i ������ÿ�� 1 ��λ������Ҫ���ѵĵ�λʱ��
	 * paths = [[1,3,3],[3,2,1],[2,1,3],[0,1,4],[3,0,5]], cnt = 6, start = 1, end = 0, charge = [2,10,4,1]
	 * 
	 * ���磺 ��A�㣬����6�����Ҫ 3 * 2 = 6 ��ʱ��
	 * 
	 * �뷵��С��������Ҫ���Ѷ��ٵ�λʱ��������� start �ִ��յ���� end
	 * 
	 * ˼·�� ����ͼ = ˫������ͼ
	 * ���㣺 (���У�������ǰ���е綯��ʣ�����)
	 * 
	 */
	public static int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
		
		// ��ͼ
		int n = charge.length;
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] path : paths) {
			int from = path[0], to = path[1], weight = path[2];
			graph.get(from).add(new int[] { to, weight });
			graph.get(to).add(new int[] { from, weight });
		}
		// ��ʼ����
		// �������� n : 0 ~ n-1��������ͼ�ϵĵ�
		// (�㣬���������ĵ���)ͼ�ϵĵ㣡
		int[][] distance = new int[n][cnt + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= cnt; j++) {
				distance[i][j] = Integer.MAX_VALUE; // distance[��,��,��,��,..]
			}
		}
		distance[start][0] = 0;
		boolean[][] visited = new boolean[n][cnt + 1];
		// 0 : ��ǰ��
		// 1 : ������ǰ��ĵ���
		// 2 : ����ʱ��
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
		heap.add(new int[] { start, 0, 0 }); 
		while (!heap.isEmpty()) {
			int[] record = heap.poll();
			int cur = record[0];
			int power = record[1];
			int cost = record[2];
			if (visited[cur][power]) {
				continue;
			}
			// �����·�㷨�� �Ҿ�Ȼ�������յ㣬ֱ�Ӿ��Ǵ�
			if (cur == end) {
				// ������֦
				// �����յ�ֱ�ӷ���
				// ���õȶ�����
				return cost;
			}
			visited[cur][power] = true;
			if (power < cnt) { // ��û�е��綯���������� ��
				// ��һ��磬����ԭ�����У����ǵ���+1
				// ��ǰ״̬�� cur, power+1
				if (!visited[cur][power + 1]  // û�б�������
						&& cost + charge[cur] < distance[cur][power + 1]) { // 
					distance[cur][power + 1] = cost + charge[cur];
					heap.add(new int[] { cur, power + 1, cost + charge[cur] });
				}
			}
			for (int[] edge : graph.get(cur)) {
				// �����ȥ��ĳ���
				int nextCity = edge[0];
				int restPower = power - edge[1];
				int nextCost = cost + edge[1]; // Ҫȥ��һ�����е��ܴ���
				if (restPower >= 0 && !visited[nextCity][restPower] && nextCost < distance[nextCity][restPower]) {
					distance[nextCity][restPower] = nextCost;
					heap.add(new int[] { nextCity, restPower, nextCost });
				}
			}
		}
		return -1;
				
	}
	
	

}
