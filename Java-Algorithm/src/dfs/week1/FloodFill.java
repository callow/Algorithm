package dfs.week1;
/**
 * 类型: 网格遍历
 */
public class FloodFill {
	
	public static void main(String[] args) {
		char[][] image = {
	            {'1', '1', '0', '0', '0'},
	            {'1', '1', '0', '0', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '1', '1', '0'},
	            {'0', '0', '0', '0', '0'}
	        };
		floodFill(image, 2, 1);
		
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
	public static void floodFill(char[][] board, int i, int j) {
		int n = board.length;
		int m = board[0].length;
		if (i < 0 || i == n || j < 0 || j == m || board[i][j] != '0') {
			return;
		}
		System.out.println("Filling: " + i + ", " + j);
		board[i][j] = 'Z'; // 初始感染点： 感染成随便什么都可以的ascci code
		floodFill(board, i - 1, j); // 上
		floodFill(board, i + 1, j); // 下
		floodFill(board,  i, j - 1); // 左
		floodFill(board, i, j + 1); // 右
	}
	
}
