package com.algo.arrow;

import com.algo.util.common.MatrixUtil;

/**
 * ��ά����Ѱ��һ������https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchMatrix4 {

	/**
	 * ÿ���������������Ͻǵ�����X��
	 * 	- x <  target => ɾ������
	 *  - x >  target => ɾ������
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (!MatrixUtil.isEmpty(matrix)) {
			int row = 0;
			int col = cols -1;
			while(row < rows && col >= 0) {
				if (matrix[row][col] == target) {
					return true;
				} else if (matrix[row][col] > target) {
					col--; // col����ɾ
				} else {
					row++; // row����ɾ
				}
			}
			return false;
		}
		return false;
        
    }
}
