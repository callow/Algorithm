package com.algo.util.network_flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 最大网络流问题： 
 * 1. 必须给出每条线路承载量，一个源点，一个目标点
 *     200
 * 	 b------d
 * 	/100   / 30   => 从a->d最大网络流是130
 * a------c
 *   50
 *
 */
public class Dinic {
	private int N;
	private List<List<Integer>> nexts;
	private List<Edge> edges;
	private int[] depth;
	private int[] cur;

	public Dinic(int nums) {
		N = nums + 1;
		nexts = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			nexts.add(new ArrayList<>());
		}
		edges = new ArrayList<>();
		depth = new int[N];
		cur = new int[N];
	}

	public void addEdge(int u, int v, int r) {
		int m = edges.size();
		edges.add(new Edge(u, v, r));
		nexts.get(u).add(m);
		edges.add(new Edge(v, u, 0));
		nexts.get(v).add(m + 1);
	}

	public int maxFlow(int s, int t) {
		int flow = 0;
		while (bfs(s, t)) {
			Arrays.fill(cur, 0);
			flow += dfs(s, t, Integer.MAX_VALUE);
			Arrays.fill(depth, 0);
		}
		return flow;
	}

	private boolean bfs(int s, int t) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.addFirst(s);
		boolean[] visited = new boolean[N];
		visited[s] = true;
		while (!queue.isEmpty()) {
			int u = queue.pollLast();
			for (int i = 0; i < nexts.get(u).size(); i++) {
				Edge e = edges.get(nexts.get(u).get(i));
				int v = e.to;
				if (!visited[v] && e.available > 0) {
					visited[v] = true;
					depth[v] = depth[u] + 1;
					if (v == t) {
						break;
					}
					queue.addFirst(v);
				}
			}
		}
		return visited[t];
	}

	// 当前来到了s点，s可变
	// 最终目标是t，t固定参数
	// r，收到的任务
	// 收集到的流，作为结果返回，ans <= r
	private int dfs(int s, int t, int r) {
		if (s == t || r == 0) {
			return r;
		}
		int f = 0;
		int flow = 0;
		// s点从哪条边开始试 -> cur[s]
		for (; cur[s] < nexts.get(s).size(); cur[s]++) {
			int ei = nexts.get(s).get(cur[s]);
			Edge e = edges.get(ei);
			Edge o = edges.get(ei ^ 1);
			if (depth[e.to] == depth[s] + 1 && (f = dfs(e.to, t, Math.min(e.available, r))) != 0) {
				e.available -= f;
				o.available += f;
				flow += f;
				r -= f;
				if (r <= 0) {
					break;
				}
			}
		}
		return flow;
	}
	public static class Edge {
		public int from;
		public int to;
		public int available;

		public Edge(int a, int b, int c) {
			from = a;
			to = b;
			available = c;
		}
	}
}
