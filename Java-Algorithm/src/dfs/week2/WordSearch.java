package dfs.week2;

import dfs.Util;

/**
 * 
 * 类型： 带路径递归 + 方向矩阵
 * 
 * https://leetcode.com/problems/word-search/description/
 */
public class WordSearch {
	
	public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'},
						  {'S','F','C','S'},
						  {'A','D','E','E'}};
		System.out.println(exist(board,"FCEESEC"));
	}
		
	public static boolean exist(char[][] board, String word) {
		int n = board.length;
		int m = board[0].length;
		
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				boolean finalStatus = dfs2(board, word, i, j, 0);
				if(finalStatus) {
					return finalStatus;
				}
			}
		}
	
        return false;
    }
	
	private static boolean dfs(char[][] board, String word, int i, int j, int index) {
		int n = board.length;
		int m = board[0].length;
		
		System.out.println("Visiting: " + i + "," + j + " -> " + word.charAt(index));

		
		// 越界 or 已匹配失败
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != word.charAt(index)) {
            return false;
        }
		
        // 匹配到最后一个字符，成功
        if (index == word.length() - 1) {
        	return true;	
        }
        
        // 保存当前字符并标记为已访问（避免重复使用）
        char temp = board[i][j];
        board[i][j] = '#';  // 用特殊字符标记,但是这样就修改了原本的board[][], 可以用visited[][]替代
		
		for(int d =0; d < 4; d++) {
			int ii = i + Util.DIRECTION_MATRIX_4[d];
			int jj = j + Util.DIRECTION_MATRIX_4[d + 1];
			boolean found = dfs(board, word, ii, jj, index + 1); // 短路退出机制，节省遍历、避免误判
			if (found) {
	            return true;
	        }
		}
		
		 // 回溯：还原字符
        board[i][j] = temp;	
        return false;
	}
	
	
    private static boolean dfs2(char[][] board, String word, int i, int j, int index) {
        // 1. 越界或字符不匹配，返回 false
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            board[i][j] != word.charAt(index)) {
            return false;
        }

        // 2. 匹配到最后一个字符
        if (index == word.length() - 1) {
            return true;
        }

        // 3. 标记当前格子为访问过（避免重复使用）
        char temp = board[i][j];
        board[i][j] = '#';

        // 4. DFS 四个方向（不使用方向矩阵）
        boolean found = dfs(board, word, i - 1, j, index + 1) || // 上
                        dfs(board, word, i + 1, j, index + 1) || // 下
                        dfs(board, word, i, j - 1, index + 1) || // 左
                        dfs(board, word, i, j + 1, index + 1);   // 右

        // 5. 回溯：还原格子状态
        board[i][j] = temp;

        return found;
    }
}
