package Searching;

public class SearchSortedMatrix {

	public static int[] searchInSortedMatrix(int[][] matrix, int target) {
		int row = 0;
		int col = matrix[0].length - 1; // �����Ͻǿ�ʼ�����½���
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] > target) { // ��ǰ > Ŀ��, Ŀ�������
				col--;
			} else if (matrix[row][col] < target) {// ��ǰ < Ŀ�꣬Ŀ��������
				row++;
			} else {
				return new int[] { row, col };
			}
		}
		return new int[] { -1, -1 };
	}
}
