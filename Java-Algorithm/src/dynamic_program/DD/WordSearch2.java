package dynamic_program.DD;
/**
 * 二维网格找单词(exist)
 * 
 * https://leetcode.cn/problems/word-search/
 * 
 * 此题是经典问题：带路径的递归无法优化，因为决定返回值的并只是i j k,还有一个二维矩阵char[][] b, 变化状态太多了
 * 带路径的递归：走过了改成0，防止重复走，并还要恢复现场
 */
public class WordSearch2 {

	// 暴力递归
	public static boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (f(board, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	// 因为board会改其中的字符
	// 用来标记哪些字符无法再用
	// 带路径的递归无法改成动态规划或者说没必要
	// 从(i,j)出发，来到k位置，请问后续能不能把word走出来 =>w[..k...]
	public static boolean f(char[][] b, int i, int j, char[] w, int k) {
		if (k == w.length) { // 成功
			return true;
		}
		if (i < 0 || i == b.length || j < 0 || j == b[0].length // 越界
				|| b[i][j] != w[k]) { // 不匹配
			return false;
		}
		
		// 不越界，b[i][j] == w[k]
		char tmp = b[i][j];
		b[i][j] = 0; // 走过了改成0，防止重复走 = 带路径的动态规划
		boolean ans = f(b, i - 1, j, w, k + 1)  // 上下左右走
				|| f(b, i + 1, j, w, k + 1) 
				|| f(b, i, j - 1, w, k + 1)
				|| f(b, i, j + 1, w, k + 1);
		b[i][j] = tmp; // 恢复现场
		return ans;
	}
}
