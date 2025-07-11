package dfs.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dfs.Util;

/**
 * 类型： 双 DFS 起点 + visited 交集
 * 
 * https://leetcode.com/problems/pacific-atlantic-water-flow/description/
 * 
 * 找出既可以流到太平洋又可以流到大西洋的格子: 从大西洋和太平洋逆流而上 然后最后找到交集
 */
public class PacificAtlantic {
	
	
	public static void main(String[] args) {
		int[][] sample = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
		int[][] sample2 = {{1,1},{1,1},{1,1}};
		pacificAtlantic(sample2);
	}
	
	public static List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> result = new ArrayList<>();
		int n = heights.length;
		int m = heights[0].length;
        boolean[][] pacificVisited = new boolean[n][m];
        boolean[][] atlanticVisited = new boolean[n][m];
        
     // 从太平洋边界做 DFS（上边 & 左边）
        for (int i = 0; i < n; i++) {
			dfs(heights, pacificVisited, i, 0);
		}
        for (int j = 0; j < m; j++) {
			dfs(heights, pacificVisited, 0, j);
		}
        
        // 从大西洋边界做 DFS（下边 & 右边）
        for (int i = 0; i < n; i++) {
			dfs(heights, atlanticVisited, i, m - 1);
		}
        for (int j = 0; j < m; j++) {
			dfs(heights, atlanticVisited, n - 1, j);
		}
        
     // 收集两个 visited 都为 true 的坐标
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
		
		return result;
    }
	
	
    private static void dfs(int[][] heights, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        int n = heights.length; // 行
        int m = heights[0].length; // 列
        for (int d = 0; d < 4; d++) {
			int ni = i + Util.DIRECTION_MATRIX_4[d];
	        int nj = j + Util.DIRECTION_MATRIX_4[d + 1];
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj] && heights[ni][nj] >= heights[i][j]) {
                dfs(heights, visited, ni, nj);
            }
        }
    }
}
