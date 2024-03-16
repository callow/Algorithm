package com.algo.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.algo.util.Monostack.MonotonicStackUtil;
import com.algo.util.PrefixSum.PrefixSum2DUtil;

public class MatrixUtil {
	
	public static void print(int[][] mat) {
		int m = mat.length;
		for (int i = 0; i < m; i++) {
			System.out.println(Arrays.toString(mat[i]));
		}
	}

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

	/**
	 * 提取 Pascal's triangle -> 杨辉三角 的第nth行<br>
	 * 
	 */
	public static List<Integer> getRow(int rowIndex) {
		List<Integer> pre = new ArrayList<>();
		pre.add(1);
		if (rowIndex == 0) {
			return pre;
		}
		List<Integer> cur = new ArrayList<>();
		for (int row = 1; row <= rowIndex; row++) {

			cur.add(1);
			for (int j = 1; j < row; j++) {
				cur.add(pre.get(j - 1) + pre.get(j));
			}
			cur.add(1);
			if (row != rowIndex) {
				pre.clear();
				pre.addAll(cur);
				cur.clear();
			}
		}
		return cur;
	}

	public static boolean isEmpty(int[][] matrix) {
		return matrix == null || matrix.length == 0 || matrix[0].length == 0;
	}

	/**
	 * 
	 * 将一个正方形矩阵，沿着中心点旋转90度，返回旋转后的矩阵<br>
	 * 
	 * 解：原位置和去的位置2个是一个组，一圈里面有d-b组 <br>
	 * * b   d <br>
	 * a ..... <br> 
	 *   .
	 *   .
	 *   .
	 * c .
	 * 
	 * 分圈结构！
	 * 
	 */
	public static void rotate90Degree(int[][] matrix) {
		int a = 0;
		int b = 0;
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a < c) {
			rotateEdge(matrix, a++, b++, c--, d--); // 边一圈一圈的向内遍历变换 -> 剥洋葱
		}
	}

	public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
		int tmp = 0;
		int groups = d - b;
		for (int i = 0; i < groups; i++) {// 0,1,2,3 ..
			tmp = m[a][b + i];
			m[a][b + i] = m[c - i][b];
			m[c - i][b] = m[c][d - i];
			m[c][d - i] = m[a + i][d];
			m[a + i][d] = tmp;
		}
	}
	
	/**
	 * 
	 * 分圈结构 ： 打印内螺旋小星星，每次留一个空格
	 * 
	 * 解：2层一个圈向内打印
	 */
	public static void printStar(int N) {
		int leftUp = 0;
		int rightDown = N - 1;
		char[][] m = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				m[i][j] = ' ';
			}
		}
		while (leftUp <= rightDown) {
			set(m, leftUp, rightDown);
			leftUp += 2;
			rightDown -= 2;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void set(char[][] m, int leftUp, int rightDown) {
		for (int col = leftUp; col <= rightDown; col++) {
			m[leftUp][col] = '*';
		}
		for (int row = leftUp + 1; row <= rightDown; row++) {
			m[row][rightDown] = '*';
		}
		for (int col = rightDown - 1; col > leftUp; col--) {
			m[rightDown][col] = '*';
		}
		for (int row = rightDown - 1; row > leftUp + 1; row--) {
			m[row][leftUp + 1] = '*';
		}
	}
	
	/**
	 * 3* 3 magic matrix： https://www.hackerrank.com/challenges/magic-square-forming/problem
	 * 
	 * 思路：其实 3 * 3 magic matrix总共就只有8种组合可能 ： https://www.geeksforgeeks.org/minimum-changes-needed-to-make-a-33-matrix-magic-square/
	 * 	   每个数字都枚举一遍和标准中的8个比一比然后求最小cost
	 */
	public static int  magicMatrixCost(int[][] arr) {
		
		int[][][] standard = {{{4,3,8},{9,5,1},{2,7,6}},
								  {{2,9,4},{7,5,3},{6,1,8}},
								  {{6,7,2},{1,5,9},{8,3,4}},
								  {{8,1,6},{3,5,7},{4,9,2}},
								  {{8,3,4},{1,5,9},{6,7,2}},
								  {{4,9,2},{3,5,7},{8,1,6}},
								  {{2,7,6},{9,5,1},{4,3,8}},
								  {{6,1,8},{7,5,3},{2,9,4}}
								};
		int tmp;
		int min = Integer.MAX_VALUE;
        for(int i = 0; i < 8; i++) { // standard中有7个元素
            tmp = 0;
            for(int j = 0; j < 3; j++) { // 3 行
                for(int k = 0; k < 3; k++) { // 3 列
                	int cost = standard[i][j][k] - arr[j][k];
                    tmp += Math.abs(cost);        
                }
            }
            if(tmp < min) { 
                min = tmp;
            }
        }
        return min;
	}
	
	
	
	/**
	 * 
	 * 二维Matrix中，子矩阵累加和
	 */
	public static int sum(int[][] matrix, int a, int b, int c, int d) {
		return PrefixSum2DUtil.sumRegion(matrix, a, b, c, d);
	}
	
	/**
	 * 1 1 1 1
	 * 1     1 
	 * 1     1
	 * 1 1 1 1
	 * 判断矩阵是否边框全是1：
	 * 	思路： prefixSum1（外侧） - prefixSum1（内侧） = 周长
	 * 		  k = 当前尝试的边长
	 */
	public static boolean isBorderAllOnes(int[][] prefixSumArray, int a, int b, int c, int d, int k) {
		int outerArea = innerSum(prefixSumArray, a, b, c, d);
		int innerArea = innerSum(prefixSumArray, a + 1, b + 1, c - 1, d - 1);
		int circumference =  (k - 1) * 4;
		return outerArea - innerArea == circumference;
	}
	
	/**
	 * 通过2D前缀和数组，直接获取内部矩形 ∑
	 */
	
	public static int innerSum(int[][] prefixSumArray, int a, int b, int c, int d) {
		return a > c ? 0 : (prefixSumArray[c][d] - get(prefixSumArray, c, b - 1) 
				- get(prefixSumArray, a - 1, d) + get(prefixSumArray, a - 1, b - 1));
	}
	
	/***
	 * 获取matrix中(i,j)位置的值
	 */
	public static int get(int[][] matrix, int i, int j) {
		return (i < 0 || j < 0) ? 0 : matrix[i][j];
	}
	
	
	
	/**
	 *  构建前缀和数组，复用自己，没有申请额外空间sum
	 */
	public static int[][] build(int n, int m, int[][] g) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				g[i][j] += get(g, i, j - 1) + get(g, i - 1, j) - get(g, i - 1, j - 1);
			}
		}
		return g; // 此时g[][] 就是前缀和数组prefixSumArray
	}

}
