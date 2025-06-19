package dfs.week1;
/**
 * 类型: 网格遍历
 */
public class FloodFill {
	
	static int[] directionMatrix = {-1, 0, 1, 0, -1};
	
	public static void main(String[] args) {
		char[][] image = {
	            {'1', '1', '0', '0', '0'},
	            {'1', '1', '0', '0', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '0', '0', '0'}
	        };
//		dfs(image, 2, 1);
		dfs2(image, 2, 1);
		
		// 打印最终结果：
		for (char[] row : image) {
            for (char pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
	}
	
	/**
	 * flood fill = dfs 
	 * 
	 * 感染==‘0’部分
	 */
	public static void dfs(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != '0') {
			return;
		}
		System.out.println("Filling: " + i + ", " + j);
		board[i][j] = 'Z'; // 初始感染点： 感染成随便什么都可以的ascci code
		dfs(board, i - 1, j); // 上
		dfs(board, i + 1, j); // 下
		dfs(board,  i, j - 1); // 左
		dfs(board, i, j + 1); // 右
	}
	
	/**
	 * 利用方向矩阵 + 1个dfs
	 */
	public static void dfs2(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		
		if (board[i][j] != '0') {
			return;
		}
		System.out.println("Filling2: " + i + ", " + j);
		board[i][j] = 'Z'; // 感染标记
		for (int d = 0; d < 4; d++) {
			int ii = i + directionMatrix[d];
	        int jj = j + directionMatrix[d + 1];
	        
	        if(ii >= 0 && ii < n && jj >= 0 && jj < m && board[ii][jj] == '0') {
	        	dfs2(board, ii, jj);
	        }
		}
	}
	
}
