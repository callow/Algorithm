package com.algo.util.disjointset;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.common.model.Dot;
import com.algo.util.disjointset.model.UnionFind;
import com.algo.util.disjointset.model.UnionFinds;
import com.algo.util.disjointset.model.UnionFindz;

public class UnionFindUtil {
	/**
	 * https://leetcode.com/problems/friend-circles/
	 * 朋友圈的数量
	 */
	
	public static int findFriendCircleNum(int[][] M) {
		int N = M.length;
		// {0} {1} {2} {N-1}
		
		// 使用API直接跑，工程上不要用自己的 使用API的 ： 
		//UnionFind unionFind = new UnionFind(Collections.emptySet());
		
		
		UnionFinds disjointSet = new UnionFinds(N);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (M[i][j] == 1) { // i和j互相认识
					disjointSet.union(i, j);
				}
			}
		}
		return disjointSet.numberOfSets();
	}
	
	/**
	 * https://leetcode.com/problems/number-of-islands/
	 * 岛屿联通问题
	 */
	
	public static int numIslands(char[][] board) {
		int row = board.length;
		int col = board[0].length;
		Dot[][] dots = new Dot[row][col];
		List<Dot> dotList = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == '1') {
					dots[i][j] = new Dot();
					dotList.add(dots[i][j]);
				}
			} 
		}
		UnionFind<Dot> uf = new UnionFind<>(dotList);
		for (int j = 1; j < col; j++) {
			// (0,j)  (0,0)跳过了  (0,1) (0,2) (0,3)
			if (board[0][j - 1] == '1' && board[0][j] == '1') {
				uf.union(dots[0][j - 1], dots[0][j]);
			}
		}
		for (int i = 1; i < row; i++) {
			if (board[i - 1][0] == '1' && board[i][0] == '1') {
				uf.union(dots[i - 1][0], dots[i][0]);
			}
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] == '1') {
					if (board[i][j - 1] == '1') {
						uf.union(dots[i][j - 1], dots[i][j]);
					}
					if (board[i - 1][j] == '1') {
						uf.union(dots[i - 1][j], dots[i][j]);
					}
				}
			}
		}
		return uf.numberOfSets();
	}
	
	/**
	 * https://leetcode.com/problems/number-of-islands-ii/
	 * 动态计算有多少个岛屿，每一步都打印
	 */
	
	public static List<Integer> dynamicNumIslands(int m, int n, int[][] positions) {
		UnionFindz uf = new UnionFindz(m, n);
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(uf.connect(position[0], position[1]));
		}
		return ans;
	}
	
}
