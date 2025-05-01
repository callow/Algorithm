package com.algo.util.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * �ٽӾ��󷨣�[1,2,4],[2,1,5],[5,3,1]��[x,y,z], ��x->yȨ��z , ȱ�������̫�����ֻ����ͼС���
 * �ڽӱ��� [[1,2],[2,1,6],[x,y]] 2���ھ���x,y or [[[1,3],[2,3]],[[x,3]]] 1���ھ���xȨ��3
 * 
 * 
 * 
 */
public class CreateGraph {
		// ����������
		public static int MAXN = 11;

		// �ߵ��������
		// ֻ����ʽǰ���Ƿ�ʽ��ͼ��Ҫ�������
		// ע���������ͼ�����������m���ߣ�����Ҫ׼��m*2
		// ��Ϊһ�������Ҫ�����������
		public static int MAXM = 21;

		// �ڽӾ���ʽ��ͼ
		public static int[][] graph1 = new int[MAXN][MAXN];

		// �ڽӱ�ʽ��ͼ
		// public static ArrayList<ArrayList<Integer>> graph2 = new ArrayList<>();
		public static ArrayList<ArrayList<int[]>> graph2 = new ArrayList<>();

		// ��ʽǰ���Ƿ�ʽ��ͼ
		public static int[] head = new int[MAXN];

		public static int[] next = new int[MAXM];

		public static int[] to = new int[MAXM];

		// �������Ȩ�أ���ô��Ҫ�������
		public static int[] weight = new int[MAXM];

		public static int cnt;
		
		// ��ͼ֮ǰ������ռ�
		public static void build(int n) {
			// �ڽӾ������
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					graph1[i][j] = 0;
				}
			}
			// �ڽӱ���պ�׼��
			graph2.clear();
			// �±���Ҫ֧��1~n�����Լ���n+1���б�0�±�׼��������
			for (int i = 0; i <= n; i++) {
				graph2.add(new ArrayList<>());
			}
			// ��ʽǰ�������
			cnt = 1;
			Arrays.fill(head, 1, n + 1, 0);
		}

		// ��ʽǰ���Ǽӱ�
		public static void addEdge(int u, int v, int w) {
			// u -> v , ��Ȩ����w
			next[cnt] = head[u];
			to[cnt] = v;
			weight[cnt] = w;
			head[u] = cnt++;
		}

		// ���ַ�ʽ��������ͼ��Ȩͼ
		public static void directGraph(int[][] edges) {
			// �ڽӾ���ͼ
			for (int[] edge : edges) {
				graph1[edge[0]][edge[1]] = edge[2];
			}
			// �ڽӱ�ͼ
			for (int[] edge : edges) {
				// graph2.get(edge[0]).add(edge[1]);
				graph2.get(edge[0]).add(new int[] { edge[1], edge[2] });
			}
			// ��ʽǰ���ǽ�ͼ
			for (int[] edge : edges) {
				addEdge(edge[0], edge[1], edge[2]);
			}
		}

		// ���ַ�ʽ��������ͼ��Ȩͼ
		public static void undirectGraph(int[][] edges) {
			// �ڽӾ���ͼ
			for (int[] edge : edges) {
				graph1[edge[0]][edge[1]] = edge[2];
				graph1[edge[1]][edge[0]] = edge[2];
			}
			// �ڽӱ�ͼ
			for (int[] edge : edges) {
				// graph2.get(edge[0]).add(edge[1]);
				// graph2.get(edge[1]).add(edge[0]);
				graph2.get(edge[0]).add(new int[] { edge[1], edge[2] });
				graph2.get(edge[1]).add(new int[] { edge[0], edge[2] });
			}
			// ��ʽǰ���ǽ�ͼ
			for (int[] edge : edges) {
				addEdge(edge[0], edge[1], edge[2]);
				addEdge(edge[1], edge[0], edge[2]);
			}
		}

		public static void traversal(int n) {
			System.out.println("�ڽӾ������ :");
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					System.out.print(graph1[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("�ڽӱ���� :");
			for (int i = 1; i <= n; i++) {
				System.out.print(i + "(�ھӡ���Ȩ) : ");
				for (int[] edge : graph2.get(i)) {
					System.out.print("(" + edge[0] + "," + edge[1] + ") ");
				}
				System.out.println();
			}
			System.out.println("��ʽǰ���� :");
			for (int i = 1; i <= n; i++) {
				System.out.print(i + "(�ھӡ���Ȩ) : ");
				// ע�����forѭ������ʽǰ���ǵķ�ʽ����
				for (int ei = head[i]; ei > 0; ei = next[ei]) {
					System.out.print("(" + to[ei] + "," + weight[ei] + ") ");
				}
				System.out.println();
			}
		}

		public static void main(String[] args) {
			// ����˴�Ȩͼ�Ľ������̣�Ҳ������˲���Ȩͼ
			// ��ı��Ϊ1...n
			// ����1�Լ���һ��ͼ�������Ȩͼ��Ȼ���ӡ���
			int n1 = 4;
			int[][] edges1 = { { 1, 3, 6 }, { 4, 3, 4 }, { 2, 4, 2 }, { 1, 2, 7 }, { 2, 3, 5 }, { 3, 1, 1 } };
			build(n1); // ����ռ�
			directGraph(edges1); // ��ͼ
			traversal(n1); // ����
			System.out.println("==============================");
			// ����2�Լ���һ��ͼ�������Ȩͼ��Ȼ���ӡ���
			int n2 = 5;
			int[][] edges2 = { { 3, 5, 4 }, { 4, 1, 1 }, { 3, 4, 2 }, { 5, 2, 4 }, { 2, 3, 7 }, { 1, 5, 5 }, { 4, 2, 6 } };
			build(n2);
			undirectGraph(edges2);
			traversal(n2);
		}
}
