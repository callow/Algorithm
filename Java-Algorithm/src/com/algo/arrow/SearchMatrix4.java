package com.algo.arrow;

import com.algo.util.common.MatrixUtil;

/**
 * 二维数组寻找一个数：https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchMatrix4 {

	/**
	 * 每次搜索矩阵最右上角的数字X，
	 * 	- x <  target => 删除此行
	 *  - x >  target => 删除此列
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
					col--; // col向左删
				} else {
					row++; // row向下删
				}
			}
			return false;
		}
		return false;
        
    }
}
