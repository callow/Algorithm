package com.algo.util.AStar;

import com.algo.util.AStar.model.AStar;

public class ComlexGraphUtil {
	
	/**
	 * ����2�����̾���(ֻ���������پ���)�� AStar
	 * 	- ��ȻҲ����ʹ��б���� ֻ��Atar��Ҫ����һ�־��뺯��
	 */
	public static int minDistance(int[][] grid, int x, int y,  int tx, int ty) {
		return AStar.minDistanceAStar(grid, x, y, tx, ty);
	}
	
	
	
}
