package com.algo.util.graph.bfs_dfs;

/**
 * 
 * 
 *  地图分析
	你现在手里有一份大小为 n x n 的 网格 grid , n <= 100
	上面的每个 单元格 都用 0 和 1 标记好了其中 0 代表海洋，1 代表陆地。
	请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
	并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
	我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：
	(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
	测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/
 */



public class BFS_MapAnalysis {

	public static int MAXN = 101;

	public static int MAXM = 101;
	
	// (2,3) = 2行3列的数字进队列， 一共会有 n * m 个元素进队列
	public static int[][] queue = new int[MAXN * MAXM][2];
	
	// 新来的数据放在r位置后然后r++， 从队列种拿的是l位置的数据然后l++
	public static int r, l;
	
	public static boolean[][] visited = new boolean[MAXN][MAXM];
	
	// 0:上，1:右，2:下，3:左
	public static int[] move = new int[] { -1, 0, 1, 0, -1 };
	//                                      0  1  2  3   4
	//                                               i
	// (x,y)  i来到0位置 : x + move[i], y + move[i+1] -> x - 1, y
	// (x,y)  i来到1位置 : x + move[i], y + move[i+1] -> x, y + 1
	// (x,y)  i来到2位置 : x + move[i], y + move[i+1] -> x + 1, y
	// (x,y)  i来到3位置 : x + move[i], y + move[i+1] -> x, y - 1
	
	
	public static int maxDistance(int[][] grid) {
		l = r = 0; // 清空队列
		int n = grid.length;
		int m = grid[0].length;
		int seas = 0 ; // 海洋格数量
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][i] == 1) { // 一个陆地
					// 进队列
					visited[i][j] = true;
					queue[r][0] = i;
					queue[r][1] = j;
					
					r++;
				} else {
					visited[i][j] = false;
					seas++;
				}
			}
		}
		
		// 全是海洋/陆地
		if (seas == 0 || seas == n * m) {
			return -1;
		}
		
		// 按层bfs
		int level = 0 ;
		while (l < r) { // 队列里还有东西
			level++;
			int size = r - l;
			for (int k = 0, x, y, nx, ny; k < size; k++) { // (nx，ny) = 由（x,y）扩展出来的格子 
				// 弹出（x,y）
				x = queue[l][0];
				y = queue[l][1];
				l++;
				
				// 然后找上下左右
				for (int i =0; i < 4; i++) {
					nx = x + move[i];
					ny = y + move[i + 1];
					
					if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
						visited[nx][ny] = true;
						queue[r][0] = nx;
						queue[r][1] = ny;
						r++;
					}
				}
			}
		}
		
		return level - 1;
		
	}
	
	
}
