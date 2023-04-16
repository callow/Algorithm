package com.algo.util.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algo.util.Monostack.MonotonicStackUtil;

public class MatrixUtil {

	/**
	 * 将矩阵压缩成直方图数组,并在压缩过程中，求出里面最大面积的矩形<br>
	 * 使用单调栈来求maxHistogram
	 */

	public static int condenseToHistogram(int[][] matrix, int maxArea) {
		int[] height = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
			}
			maxArea = Math.max(MonotonicStackUtil.maxHistogram(height), maxArea);
		}
		return maxArea;
	}

	/**
	 * 2维矩阵相乘
	 */
	public static int[][] multiple(int[][] a, int[][] b) {
		int n = a.length;
		int m = b[0].length;
		int k = a[0].length; // a的列数同时也是b的行数
		int[][] ans = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int c = 0; c < k; c++) {
					ans[i][j] += a[i][c] * b[c][j];
				}
			}
		}
		return ans;
	}

	/**
	 * 对角线遍历，右上 -> 左下, 返回对角线序
	 */

	public static int[] diagonalOrder(int[][] mat) {
		int n = mat.length; // row
		int m = mat[0].length; // col
		int[] result = new int[n * m];
		int k = 0;
		List<Integer> intermediate = new ArrayList<>();
		for (int diagnol = 0; diagnol < n + m - 1; diagnol++) {

			// 每走完一个对角线清空
			intermediate.clear();

			// 找到遍历的头：The elements in the first row and the last column
			int row = diagnol < m ? 0 : diagnol - m + 1;
			int col = diagnol < m ? diagnol : m - 1;

			// 开始遍历对角线
			while (row < n && col > -1) {

				intermediate.add(mat[row][col]);
				++row;
				--col;
			}

			for (int i = 0; i < intermediate.size(); i++) {
				result[k++] = intermediate.get(i);
			}
		}
		return result;
	}

	/**
	 * 对角线遍历，右上 -> 左下 -> 右上, 返回对角线ZigZag序
	 */
	public static int[] DiagonalZigZagOrder(int[][] mat) {
		if (MatrixUtil.isEmpty(mat)) {
			return new int[0];
		}

		int n = mat.length; // row
		int m = mat[0].length; // col
		int[] result = new int[n * m];
		int k = 0;
		List<Integer> intermediate = new ArrayList<>();

		for (int diagnol = 0; diagnol < n + m - 1; diagnol++) {

			// 每走完一个对角线清空
			intermediate.clear();

			// 找到对角线遍历的头 *：The elements in the first row and the last column
			int row = diagnol < m ? 0 : diagnol - m + 1;
			int col = diagnol < m ? diagnol : m - 1;

			// 开始遍历对角线
			while (row < n && col > -1) {

				intermediate.add(mat[row][col]);
				++row;
				--col;
			}

			// 反转偶数对角线
			if (diagnol % 2 == 0) {
				Collections.reverse(intermediate);
			}

			for (int i = 0; i < intermediate.size(); i++) {
				result[k++] = intermediate.get(i);
			}
		}
		return result;

	}

	/**
	 * 内螺旋遍历，返回内螺旋序： <br>
	 * move right: (row, col + 1) <br>
	 * move downwards: (row + 1, col) <br>
	 * move left: (row, col - 1)<br>
	 * move upwards: (row - 1, col)<br>
	 */
	public static List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		int rows = matrix.length, columns = matrix[0].length;
		int up = 0, down = rows - 1, left = 0, right = columns - 1; // index

		// direction : right -> down -> left -> up
		while (result.size() < rows * columns) {

			// right
			for (int col = left; col <= right; col++) {
				result.add(matrix[up][col]);
			}

			// down
			for (int row = up + 1; row <= down; row++) {
				result.add(matrix[row][right]);
			}

			if (up != down) {
				// left.
				for (int col = right - 1; col >= left; col--) {
					result.add(matrix[down][col]);
				}
			}

			if (left != right) {
				// up
				for (int row = down - 1; row > up; row--) {
					result.add(matrix[row][left]);
				}
			}

			// finish one round
			left++;
			right--;
			up++;
			down--;
		}
		return result;

	}

	/**
	 * 生成 Pascal's triangle -> 杨辉三角 <br>
	 * 
	 * 严格表结构填充
	 */

	public static List<List<Integer>> pascalTriangle(int numRows) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();

		triangle.add(new ArrayList<>());
		triangle.get(0).add(1);

		for (int row = 1; row < numRows; row++) {
			List<Integer> cur = new ArrayList<>();
			List<Integer> prev = triangle.get(row - 1);

			cur.add(1);
			for (int j = 1; j < row; j++) {
				cur.add(prev.get(j - 1) + prev.get(j));
			}
			cur.add(1);

			triangle.add(cur);
		}

		return triangle;
	}

	public static boolean isEmpty(int[][] matrix) {
		return matrix == null || matrix.length == 0 || matrix[0].length == 0;
	}
}
