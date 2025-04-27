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
	 * DJ模板：
	 * 
		所有点最大接受信号的延迟时间： https://leetcode.com/problems/network-delay-time
		
		Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
		
		即：求source到最远点花费多长时间
		
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
		
		// 开始DJ算法
		int[] distance = new int[n + 1]; 
		Arrays.fill(distance, Integer.MAX_VALUE); // distance[∞,∞,∞,∞,..]
		distance[source] = 0;
		boolean[] visited = new boolean[n + 1]; // false false false ...
		// 0: 当前节点, 1: 原点到当前节点距离
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // 小根堆，小的在堆顶
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
			if (visited[u]) { // 如果u弹出过，skip
				continue;
			}
			visited[u] = true; // 首次弹出
			for (int[] edge : nexts.get(u)) { // 考察从u出发的每一条边，去往 v , 权重: w
				int v = edge[0];
				int w = edge[1];
				if (!visited[v] && distance[u] + w < distance[v]) {
					distance[v] = distance[u] + w;
					heap.add(new int[] { v, distance[u] + w });
				}
			}
		}
		
		// 题目专属小尾巴，如果还是max，则信号没有传输到每个点 -1， 反之有，求一个最大值
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
				distance[i][j] = Integer.MAX_VALUE; // distance[∞,∞,∞,∞,..]
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
			int x = record[0];
			int	y = record[1]; 
			int	cost = record[2];
			
			if (visited[x][y]) { // 之前弹出过，忽略
				continue;
			}
			if (x == n - 1 && y == m - 1) {
				// 常见剪枝
				// 发现终点直接返回
				// 不用等都结束
				return cost;
			}
			visited[x][y] = true;
			
			for (int i = 0; i < 4; i++) { // 上下左右移动，四向图
				int nx = x + move[i];
				int	ny = y + move[i + 1]; // 即将要去的[nx][ny]
				
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					int newCost = Math.max(cost, Math.abs(matrix[x][y] - matrix[nx][ny])); // 计算要去往格子的代价，过往cost和新cost比较取最大值
					if (newCost < distance[nx][ny]) { // 更新小根堆
						distance[nx][ny] = newCost;
						heap.add(new int[] { nx, ny, newCost });
					}
				}
			}
		}
		return -1;
		
	}
	
	
	/**
	 * 水位上升的泳池中游泳: https://leetcode.com/problems/swim-in-rising-water/
	 * 
	 * 返回 从左上角点到的右下平台 (n-1, n-1) 所需的最少时间
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
		// 0 : 格子的行
		// 1 : 格子的列
		// 2 : 源点到当前格子的代价
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		heap.add(new int[] { 0, 0, grid[0][0] });
		while (!heap.isEmpty()) {
			int x = heap.peek()[0];
			int y = heap.peek()[1];
			int c = heap.peek()[2]; // 过往代价 = 沿途路径的最大高度
			heap.poll();
			if (visited[x][y]) {
				continue;
			}
			visited[x][y] = true;
			if (x == n - 1 && y == m - 1) {
				// 常见剪枝
				// 发现终点直接返回
				// 不用等都结束
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
	
	//-----------------------------分层最短路 / 扩点最短路 --------------------------------------------
	
	// 不把实际位置看作图上的点，而把实际位置和它状态组合当作图上的点
	
	/**
	 * 钥匙数量提前遍历一遍数出来。
	 * 假设有2种钥匙：ab, 其中收集钥匙的状态可以分4种
	 * 	00 01 10 11
	 *  ab ab ab ab
	 * 
	 * 图上的点是（位置，状态） -> (0,0,11)
	 * 
	 * 从@开始，什么时候有了11状态什么时候结束 参考： GraphUtil.shortestPathAllKeys 并自己写
	 */
	
	 //-------------------------------------------------------------------------------------------
	
	/**
	 * 电动车游城市: https://leetcode.cn/problems/DFPeFJ/
	 * 
	 * 小明的电动车电量充满时可行驶距离为 cnt，每行驶 1 单位距离消耗 1 单位电量，且花费 1 单位时间, 地图上共有 N 个景点，景点编号为 0 ~ N-1
	 * 他将地图信息以 [城市 A 编号,城市 B 编号,两城市间距离] 格式整理在在二维数组 paths 表示城市 A、B 间存在双向通路
	 * 初始状态，电动车电量为 0。每个城市都设有充电桩 charge[i] 表示第 i 个城市每充 1 单位电量需要花费的单位时间
	 * paths = [[1,3,3],[3,2,1],[2,1,3],[0,1,4],[3,0,5]], cnt = 6, start = 1, end = 0, charge = [2,10,4,1]
	 * 
	 * 比如： 在A点，充满6格电需要 3 * 2 = 6 的时间
	 * 
	 * 请返回小明最少需要花费多少单位时间从起点城市 start 抵达终点城市 end
	 * 
	 * 思路： 无向图 = 双向有向图
	 * 扩点： (城市，来到当前城市电动车剩几格电)
	 * 
	 */
	public static int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
		
		// 建图
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
		// 开始扩点
		// 城市数量 n : 0 ~ n-1，不代表图上的点
		// (点，到达这个点的电量)图上的点！
		int[][] distance = new int[n][cnt + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= cnt; j++) {
				distance[i][j] = Integer.MAX_VALUE; // distance[∞,∞,∞,∞,..]
			}
		}
		distance[start][0] = 0;
		boolean[][] visited = new boolean[n][cnt + 1];
		// 0 : 当前点
		// 1 : 来到当前点的电量
		// 2 : 花费时间
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
			// 在最短路算法中 我竟然遇到了终点，直接就是答案
			if (cur == end) {
				// 常见剪枝
				// 发现终点直接返回
				// 不用等都结束
				return cost;
			}
			visited[cur][power] = true;
			if (power < cnt) { // 还没有到电动车电量上限 则
				// 充一格电，还在原来城市，但是电量+1
				// 当前状态： cur, power+1
				if (!visited[cur][power + 1]  // 没有被弹出过
						&& cost + charge[cur] < distance[cur][power + 1]) { // 
					distance[cur][power + 1] = cost + charge[cur];
					heap.add(new int[] { cur, power + 1, cost + charge[cur] });
				}
			}
			for (int[] edge : graph.get(cur)) {
				// 不充电去别的城市
				int nextCity = edge[0];
				int restPower = power - edge[1];
				int nextCost = cost + edge[1]; // 要去下一个城市的总代价
				if (restPower >= 0 && !visited[nextCity][restPower] && nextCost < distance[nextCity][restPower]) {
					distance[nextCity][restPower] = nextCost;
					heap.add(new int[] { nextCity, restPower, nextCost });
				}
			}
		}
		return -1;
				
	}
	
	

}
