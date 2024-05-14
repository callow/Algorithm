package com.algo.util.AStar;

import com.algo.util.AStar.model.AStar;

public class ComlexGraphUtil {
	
	/**
	 * 矩阵2点的最短距离(只能走曼哈顿距离)： AStar
	 * 	- 当然也可以使用斜线走 只是Atar中要换另一种距离函数
	 */
	public static int minDistance(int[][] grid, int x, int y,  int tx, int ty) {
		return AStar.minDistanceAStar(grid, x, y, tx, ty);
	}
	
	
	
}
