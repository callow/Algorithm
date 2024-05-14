package com.algo.util.AStar.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 弗洛伊德算法：
 * 	- 求A-> B的最短距离，不可以有负环， Time: O(N^3), 空间复杂度 O(N^2)
 *  - 时间复杂度比较高，因此只适用于小图，节点数比较少的情况
 *  - https://www.luogu.com.cn/problem/P2910
 *  - [a,e,d,g], 按照aedg经历4(m)个节点节点走的顺序取走 求路途最小距离, n= 岛屿数量 m=经历几个点拿到宝藏
 */
public class Floyd {
	public static int MAXN = 101;

	public static int MAXM = 10001;
	public static int[] path = new int[MAXM];

	public static int[][] distance = new int[MAXN][MAXN];

	public static int n, m, ans;
	
	/**
	 * O(N^3)的过程
	 * 先枚举跳点，再枚举2点之间的距离
	 */
	public static void floyd() {
		// 枚举每个跳板
		// 注意，跳板要最先枚举！跳板要最先枚举！跳板要最先枚举！
		for (int bridge = 0; bridge < n; bridge++) { // 跳板
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// i -> .....bridge .... -> j
					// distance[i][j]能不能缩短
					// distance[i][j] = min( distance[i][j] , distance[i][bridge] + distance[bridge][j])
					if (distance[i][bridge] != Integer.MAX_VALUE // i到跳点存在
							&& distance[bridge][j] != Integer.MAX_VALUE // j到跳点存在
							&& distance[i][j] > distance[i][bridge] + distance[bridge][j]) { // i到j的距离因为跳点原因变得更小
						distance[i][j] = distance[i][bridge] + distance[bridge][j];
					}
				}
			}
		}
	}
	
	
	

	// 初始时设置任意两点之间的最短距离为无穷大，表示任何路不存在（没有路）
	public static void build() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			in.nextToken();
			m = (int) in.nval;
			for (int i = 0; i < m; i++) {
				in.nextToken();
				path[i] = (int) in.nval - 1;
			}
			// 这道题给的图是邻接矩阵的形式
			// 任意两点之间的边权都会给定
			// 所以显得distance初始化不太必要
			// 但是一般情况下，distance初始化一定要做
			build();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					in.nextToken();
					distance[i][j] = (int) in.nval;
				}
			}
			
			// 开始构建弗洛伊德距离
			floyd();
			ans = 0;
			for (int i = 1; i < m; i++) {
				ans += distance[path[i - 1]][path[i]];
			}
			out.println(ans);
		}
		out.flush();
		out.close();
		br.close();
	}
	
	
	
}
