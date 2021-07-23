package Searching;

public class SearchSortedMatrix {

	public static int[] searchInSortedMatrix(int[][] matrix, int target) {
		int row = 0;
		int col = matrix[0].length - 1; // 从右上角开始往坐下角走
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] > target) { // 当前 > 目标, 目标在左边
				col--;
			} else if (matrix[row][col] < target) {// 当前 < 目标，目标在下面
				row++;
			} else {
				return new int[] { row, col };
			}
		}
		return new int[] { -1, -1 };
	}
}
