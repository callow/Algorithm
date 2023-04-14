package com.algo.util.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algo.util.Monostack.MonotonicStackUtil;

public class MatrixUtil {

	/**
	 * ������ѹ����ֱ��ͼ����,����ѹ�������У���������������ľ���<br>
	 * ʹ�õ���ջ����maxHistogram
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
	 * 2ά�������
	 */
	public static int[][] multiple(int[][] a, int[][] b) {
		int n = a.length;
		int m = b[0].length;
		int k = a[0].length; // a������ͬʱҲ��b������
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
	 * �Խ��߱��������� -> ����, ���ضԽ�����
	 */

	public static int[] diagonalOrder(int[][] mat) {
		int n = mat.length; // row
		int m = mat[0].length; // col
		int[] result = new int[n * m];
		int k = 0;
		List<Integer> intermediate = new ArrayList<>();
		for (int diagnol = 0; diagnol < n + m - 1; diagnol++) {

			// ÿ����һ���Խ������
			intermediate.clear();

			// �ҵ�������ͷ��The elements in the first row and the last column
			int row = diagnol < m ? 0 : diagnol - m + 1;
			int col = diagnol < m ? diagnol : m - 1;

			// ��ʼ�����Խ���
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
	 * �Խ��߱��������� -> ���� -> ����, ���ضԽ���ZigZag��
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

			// ÿ����һ���Խ������
			intermediate.clear();

			// �ҵ��Խ��߱�����ͷ *��The elements in the first row and the last column
			int row = diagnol < m ? 0 : diagnol - m + 1;
			int col = diagnol < m ? diagnol : m - 1;

			// ��ʼ�����Խ���
			while (row < n && col > -1) {

				intermediate.add(mat[row][col]);
				++row;
				--col;
			}

			// ��תż���Խ���
			if (diagnol % 2 == 0) {
				Collections.reverse(intermediate);
			}

			for (int i = 0; i < intermediate.size(); i++) {
				result[k++] = intermediate.get(i);
			}
		}
		return result;

	}

	public static boolean isEmpty(int[][] matrix) {
		return matrix == null || matrix.length == 0 || matrix[0].length == 0;
	}
}
