package com.algo.util.AStar.model;

import java.util.PriorityQueue;

import com.algo.util.common.MatrixUtil;

/**
 * A*��
 * �������Դ�㵽Ŀ�����ô������С�� ��ôDijkstraҲ���Խ��, ��������DJ����A*��һ������
 * 
 *  - A -> B ��Ԥ��preEstimate������һ�����룬���������پ���������
 *  	- �����پ���
 *      - ŷ�Ͼ���
 *      - �Խ��߾���
 *  - ���ƾ��루preEstimate��Ҫ <= A ��B��������̾���    => Ԥ������ <= ��ʵ��̾���
 *  - distance���л��Ǽ�¼Դ��A��ʣ������̾��룬����С���ѵĲ�������ľ���Ҫ���� ��ǰ�㵽B�������پ��� -> ������������(����ʱ���Ż��ܴ�)
 *  - A*��DJ������ȫһ����ֻ��С���������ʱ�������Ԥ�����룬 ���ӶȺ�DJһ����ֻ���Ż��˳���ʱ��
 *  - ��������Ϸ��NPC��·��һЩ�㷨
 */
public class AStar {

	// 0:�ϣ�1:�ң�2:�£�3:��
	public static int[] move = new int[] { -1, 0, 1, 0, -1 };
	
	
	// A*�㷨
	// grid[i][j] == 0 �����ϰ�
	// grid[i][j] == 1 �����·
	// ֻ�����ϡ��¡����ң�������б�߷���
	// ���ش�(startX, startY)��(targetX, targetY)����̾���
	public static int minDistanceAStar(int[][] grid, int startX, int startY, int targetX, int targetY) {
		if (grid[startX][startY] == 0 || grid[targetX][targetY] == 0) {
			return -1;
		}
		int n = grid.length;
		int m = grid[0].length;
		int[][] distance = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[startX][startY] = 1;
		boolean[][] visited = new boolean[n][m];
		// 0 : ��
		// 1 : ��
		// 2 : ��Դ��������ﵱǰ��ľ��� + ��ǰ�㵽�յ��Ԥ������
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		heap.add(new int[] { startX, startY, 1 + estimate(startX, startY, targetX, targetY) });
		while (!heap.isEmpty()) {
			int[] cur = heap.poll();
			int x = cur[0];
			int y = cur[1];
			if (visited[x][y]) {
				continue;
			}
			visited[x][y] = true;
			if (x == targetX && y == targetY) {
				return distance[x][y];
			}
			for (int i = 0, nx, ny; i < 4; i++) {
				nx = x + move[i];
				ny = y + move[i + 1];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny]
						&& distance[x][y] + 1 < distance[nx][ny]) {
					distance[nx][ny] = distance[x][y] + 1;
					heap.add(new int[] { nx, ny, distance[x][y] + 1 + estimate(nx, ny, targetX, targetY) });
				}
			}
		}
		return -1;
	}
	
	// �����پ���
	public static int estimate(int x, int y, int targetX, int targetY) {
		return (Math.abs(targetX - x) + Math.abs(targetY - y)); // �в�ֵ + �в�ֵ
	}

	// �Խ��߾���
	public static int estimate2(int x, int y, int targetX, int targetY) {
		return Math.max(Math.abs(targetX - x), Math.abs(targetY - y)); // max(�в�ֵ���в�ֵ)
	}

	// ŷʽ����
	public static double estimate3(int x, int y, int targetX, int targetY) {
		return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
	}
	
	
	
// ------------------------------------���ܲ���-----------------------------------------------------------
	
	
	public static void main(String[] args) {
		int len = 100;
		int testTime = 10000;
		System.out.println("���ܲ��Կ�ʼ");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * len) + 2;
			int[][] grid = MatrixUtil.randomGrid(n);
			int startX = (int) (Math.random() * n);
			int startY = (int) (Math.random() * n);
			int targetX = (int) (Math.random() * n);
			int targetY = (int) (Math.random() * n);
			int ans1 = minDistanceDJ(grid, startX, startY, targetX, targetY);
			int ans2 = minDistanceAStar(grid, startX, startY, targetX, targetY);
			if (ans1 != ans2) {
				System.out.println("������!");
			}
		}
		System.out.println("���ܲ��Խ���");

		System.out.println("���ܲ��Կ�ʼ");
		int[][] grid = MatrixUtil.randomGrid(4000);
		int startX = 0;
		int startY = 0;
		int targetX = 3900;
		int targetY = 3900;
		long start, end;
		start = System.currentTimeMillis();
		int ans1 = minDistanceDJ(grid, startX, startY, targetX, targetY);
		end = System.currentTimeMillis();
		System.out.println("����dijskra�㷨���: " + ans1 + ", ����ʱ��(����) : " + (end - start));
		start = System.currentTimeMillis();
		int ans2 = minDistanceAStar(grid, startX, startY, targetX, targetY);
		end = System.currentTimeMillis();
		System.out.println("����A*�㷨���: " + ans2 + ", ����ʱ��(����) : " + (end - start));
		System.out.println("���ܲ��Խ���");
	}
	
	
	// Dijkstra�㷨
	// grid[i][j] == 0 �����ϰ�
	// grid[i][j] == 1 �����·
	// ֻ�����ϡ��¡����ң�������б�߷���
	// ���ش�(startX, startY)��(targetX, targetY)����̾���
	public static int minDistanceDJ(int[][] grid, int startX, int startY, int targetX, int targetY) {
		if (grid[startX][startY] == 0 || grid[targetX][targetY] == 0) {
			return -1;
		}
		int n = grid.length;
		int m = grid[0].length;
		int[][] distance = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[startX][startY] = 1;
		boolean[][] visited = new boolean[n][m];
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		// 0 : ��
		// 1 : ��
		// 2 : ��Դ��������ﵱǰ��ľ���
		heap.add(new int[] { startX, startY, 1 });
		while (!heap.isEmpty()) {
			int[] cur = heap.poll();
			int x = cur[0];
			int y = cur[1];
			if (visited[x][y]) {
				continue;
			}
			visited[x][y] = true;
			if (x == targetX && y == targetY) {
				return distance[x][y];
			}
			for (int i = 0, nx, ny; i < 4; i++) {
				nx = x + move[i];
				ny = y + move[i + 1];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny]
						&& distance[x][y] + 1 < distance[nx][ny]) {
					distance[nx][ny] = distance[x][y] + 1;
					heap.add(new int[] { nx, ny, distance[x][y] + 1 });
				}
			}
		}
		return -1;
	}
	
	
	
	
}
