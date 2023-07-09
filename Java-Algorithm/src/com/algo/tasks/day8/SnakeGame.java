package com.algo.tasks.day8;

import java.util.Arrays;

import com.algo.util.common.CommonArrayUtil;

public class SnakeGame {

	public static class Info {
		public int no; // �����ܻ�ȡ�������ֵ
		public int yes; // ���ܻ�ȡ�������ֵ

		public Info(int n, int y) {
			no = n;
			yes = y;
		}
	}
	
	public static int snakeWalk(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				Info cur = go(matrix, i, j);
				ans = Math.max(ans, Math.max(cur.no, cur.yes));
			}
		}
		return ans;
	}
	
	
	/**
	 * �ߴ�ĳһ�������У������ŵĿս��㽵�� ��;�ߵ�(i,j)����ͣ��
	 *  1. ���أ�һ������Ҳ���ã���õ����ɳ�ֵ
	    2.  ���أ�����һ����������õ����ɳ�ֵ
	   ����ߴ�ĳһ�������У������ŵĿս��㽵�䣬������������ô��������(i,j)����ôno = -1
	   ����ߴ�ĳһ�������У������ŵĿս��㽵�䣬����һ����������ô��������(i,j)����ôyes = -1
	 */
	public static Info go(int[][] matrix, int i, int j) {
		
		/**
		 * �տ�ʼ���������� (j = 0)
		 */
		if (j == 0) {
			int no = Math.max(matrix[i][0], -1);
			int yes = Math.max(-matrix[i][0], -1);
			return new Info(no,yes);
		}
		
		int leftNo = -1;
		int leftYes = -1;
		
		/**
		 * ���������У������м� (j > 0)
		 * �п����Ǵ����ϣ����У����£�������ǰλ��(i,j)
		 */
		
		// һ�������
		Info left = go(matrix, i, j -1);
		leftNo = Math.max(leftNo, left.no);
		leftYes = Math.max(leftYes, left.yes);
		
		// һ��������
		if (i > 0) { 
			left = go(matrix, i -1, j - 1);
			leftNo = Math.max(leftNo, left.no);
			leftYes = Math.max(leftYes, left.yes);
		}
		
		 // ����������
		if (i < matrix.length - 1) {
			left = go(matrix, i + 1, j - 1);
			leftNo = Math.max(leftNo, left.no);
			leftYes = Math.max(leftYes, left.yes);
		}
		
		/**
		 * ���������ĳɳ�ֵ�� 
		 * 	p1 : ��֮ǰ��������������(<0)����������ֵҲ��-1.
		 *  p2 : ��֮ǰ�����ܿ��Ե�(>=0)�������ڵ�����ֵ Max(֮ǰ������ֵ+���ڵĸ���, -1), ��Ϊ������Ҳ�п����Ǹ���Ҫ��-1��һ��
		 */
		int no = leftNo == -1 ? -1 : (Math.max(-1, leftNo + matrix[i][j]));
		
		/**
		 * ʹ��������ɳ�ֵ������֮ǰ����/����Ҫ��
		 *  p1 : ֮ǰ�����������Ե�(>=0)�������ڲ�������
		 *  p2 : ֮ǰû���������Ե�(>=0)�����ھ�Ҫ��(ȡ�෴��)
		 */
		int p1 = leftYes == -1 ? -1 : (Math.max(-1, leftYes + matrix[i][j]));
		int p2 = leftNo == -1 ? -1 : (Math.max(-1, leftNo - matrix[i][j]));
		int yes = Math.max(Math.max(p1, p2), -1);
		
		return new Info(no, yes);
	}
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * ���仯����
	 */
	
	public static int snakeWalk2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[][][] dp = new int[matrix.length][matrix[0].length][2];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0][0] = matrix[i][0];
			dp[i][0][1] = -matrix[i][0];
			max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
		}
		for (int j = 1; j < matrix[0].length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				int preUnuse = dp[i][j - 1][0];
				int preUse = dp[i][j - 1][1];
				if (i - 1 >= 0) {
					preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
					preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
				}
				if (i + 1 < matrix.length) {
					preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
					preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
				}
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
				if (preUnuse >= 0) {
					dp[i][j][0] = matrix[i][j] + preUnuse;
					dp[i][j][1] = -matrix[i][j] + preUnuse;
				}
				if (preUse >= 0) {
					dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
				}
				max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		int N = 7;
		int M = 7;
		int V = 10;
		int times = 1000000;
		for (int i = 0; i < times; i++) {
			int r = (int) (Math.random() * (N + 1));
			int c = (int) (Math.random() * (M + 1));
			int[][] matrix = CommonArrayUtil.generateRandomArray(r, c, V);
			int ans1 = snakeWalk(matrix);
			int ans2 = snakeWalk2(matrix);
			if (ans1 != ans2) {
				for (int j = 0; j < matrix.length; j++) {
					System.out.println(Arrays.toString(matrix[j]));
				}
				System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
				break;
			}
		}
		System.out.println("finish");
	}
}