package com.algo.util.floodfill;

/**
 * 
 * flood fill = dfs/bfs
 */
public class FloodFillUtil {
	
	/**
	 * 1�ĵ�����������
	 *  flood fill = dfs = ��Ⱦ = ��1���������Ҹ�Ⱦ��1-> 2��
	 *  
	 *  https://leetcode.com/problems/number-of-islands/description/
	 */
	public static int numOfislands(char[][] board) {
		int island = 0;
		int n = board.length;
		int m = board[0].length;
		
		for(int i =0; i < n; i++) { // ��������
			for(int j =0; j < m; j++) { // ��������
				if(board[i][j] == '1') {
					island++;
					Util.floodFill(board,i,j);
				}
			}
		}
		return island;
	}

	
	/**
	 * ֻҪ��x��Χ��0��������ĳ�0��
	 * xxx    xxx
	 * x0x -> xxx
	 * xxx	  xxx
	 * 
	 * https://leetcode.com/problems/surrounded-regions/description/
	 * 
	 * ˼·��
	 * 	1. ���ĸ��ߵ�0��ͨ�����ˮ��任��w �������ͨ��=ûΧס��
	 *  2. ����board, ������Χ��0���x
	 *  3. �ָ�w��0
	 * 
	 */
	public static void flipSurroundedRegion(char[][] board) {
		int n = board.length; // row
		int m = board[0].length; // col 
		
		for (int j = 0; j < m; j++) {
			if (board[0][j] == 'O') { // ��: 0��1�У�0��2��...
				Util.floodFill2(board, 0, j);
			}
			if (board[n - 1][j] == 'O') { // �£� n��1�У�n��1��...
				Util.floodFill2(board, n - 1, j);
			}
		 }
		
		for (int i = 1; i < n - 1; i++) {
			if (board[i][0] == 'O') { // �� 1��0�У�2��0��...
				Util.floodFill2(board, i, 0);
			}
			if (board[i][m - 1] == 'O') { // �ң�1��m�У�2��m��...
				Util.floodFill2(board, i, m - 1);
			}
		}
		
		// ���ڿ�ʼ��0 --> x
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if (board[i][j] == 'F') {
					board[i][j] = 'O';
				}
			}
		}
	}
	
	
	/**
	 * 0 = water, 1 = island, ֻ����flipһ��0 �����ɵĵ����
	 * 
	 * https://leetcode.com/problems/making-a-large-island/description/
	 * 
	 * 1. ʹ��Floodfill��ÿһƬ����ţ�1��2��3��4. ��ͳ�Ƹ���
	 * 2. ����ʣ�µ�0��ͳ���������ĵ�
	 */
	public static int largestIslandAfterFlip(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int id = 2;
		
		// ��������
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					Util.floodFill3(grid, id, j, id++);
				}
			}
		}
		
		// �ռ�ÿ����ŵ���С
		int[] sizes = new int[id];
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] > 1) {
					ans = Math.max(ans, ++sizes[grid[i][j]]);
				}
			}
		}
		
		// �������е�0�����1���ܴ�������󵺵Ĵ�С
		boolean[] visited = new boolean[id];
		int up, down, left, right, merge; // �������ĸ����ı�ţ��������ĸ����ı��
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) {
					up = i > 0 ? grid[i - 1][j] : 0;
					down = i + 1 < n ? grid[i + 1][j] : 0;
					left = j > 0 ? grid[i][j - 1] : 0;
					right = j + 1 < m ? grid[i][j + 1] : 0;
					visited[up] = true;
					merge = 1 + sizes[up];
					if (!visited[down]) {
						merge += sizes[down];
						visited[down] = true;
					}
					if (!visited[left]) {
						merge += sizes[left];
						visited[left] = true;
					}
					if (!visited[right]) {
						merge += sizes[right];
						visited[right] = true;
					}
					ans = Math.max(ans, merge); // ����ռ����ĵ���
					visited[up] = false;
					visited[down] = false;
					visited[left] = false;
					visited[right] = false;
				}
			}
		}
		return ans;
	}
	
	
	
	
	
	
	
}
