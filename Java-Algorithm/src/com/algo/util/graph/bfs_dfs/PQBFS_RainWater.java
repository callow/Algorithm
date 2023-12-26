package com.algo.util.graph.bfs_dfs;

import java.util.PriorityQueue;

/**
 *  ��ά����ˮ
  ����һ�� m * n �ľ������е�ֵ��Ϊ�Ǹ������������ά�߶�ͼÿ����Ԫ�ĸ߶�
  �����ͼ����״����ܽӶ����������ˮ��
  �������� : https://leetcode.cn/problems/trapping-rain-water-ii/
 */
public class PQBFS_RainWater {

	public static int trapRainWater(int[][] height) {
		int[] move = {-1,0,1,0,-1};
		int n = height.length;
		int m = height[0].length;
		
		// 0: �У� 1: �У� 2: ˮ�� �� ����ˮ�߸߶ȣ�˭С˭�ȵ�����С���ѣ�
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a,b) -> a[2] - b[2]);
		boolean[][] visited= new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || i == n - 1 || j == 0 || j == m - 1) { // �߽�ĸ��ӽ�pq
					// �߽�
					heap.add(new int[] { i, j, height[i][j] });
					visited[i][j] = true;
				} else {
					visited[i][j] = false;
				}
			}
		}
		
		int ans = 0;
		
		while (!heap.isEmpty()) {
			int[] record = heap.poll(); // ��ÿ�ε�������� - pq
			int r = record[0];
			int c = record[1];
			int w = record[2];
			ans += w - height[r][c]; // ����ˮλ
			for (int i = 0, nr, nc; i < 4; i++) { // ���������ĸ�����ʼBFS
				nr = r + move[i];
				nc = c + move[i + 1];
				if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) { // ����Խ�磬�������ظ�
					heap.add(new int[] { nr, nc, Math.max(height[nr][nc], w) }); // �����ҵ�ˮ�ߺ����Լ� Max
					visited[nr][nc] = true;
				}
			}
		}
		return ans;
	}
}
