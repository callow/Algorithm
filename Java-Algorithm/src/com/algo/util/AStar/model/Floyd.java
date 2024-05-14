package com.algo.util.AStar.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * ���������㷨��
 * 	- ��A-> B����̾��룬�������и����� Time: O(N^3), �ռ临�Ӷ� O(N^2)
 *  - ʱ�临�ӶȱȽϸߣ����ֻ������Сͼ���ڵ����Ƚ��ٵ����
 *  - https://www.luogu.com.cn/problem/P2910
 *  - [a,e,d,g], ����aedg����4(m)���ڵ�ڵ��ߵ�˳��ȡ�� ��·;��С����, n= �������� m=�����������õ�����
 */
public class Floyd {
	public static int MAXN = 101;

	public static int MAXM = 10001;
	public static int[] path = new int[MAXM];

	public static int[][] distance = new int[MAXN][MAXN];

	public static int n, m, ans;
	
	/**
	 * O(N^3)�Ĺ���
	 * ��ö�����㣬��ö��2��֮��ľ���
	 */
	public static void floyd() {
		// ö��ÿ������
		// ע�⣬����Ҫ����ö�٣�����Ҫ����ö�٣�����Ҫ����ö�٣�
		for (int bridge = 0; bridge < n; bridge++) { // ����
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// i -> .....bridge .... -> j
					// distance[i][j]�ܲ�������
					// distance[i][j] = min( distance[i][j] , distance[i][bridge] + distance[bridge][j])
					if (distance[i][bridge] != Integer.MAX_VALUE // i���������
							&& distance[bridge][j] != Integer.MAX_VALUE // j���������
							&& distance[i][j] > distance[i][bridge] + distance[bridge][j]) { // i��j�ľ�����Ϊ����ԭ���ø�С
						distance[i][j] = distance[i][bridge] + distance[bridge][j];
					}
				}
			}
		}
	}
	
	
	

	// ��ʼʱ������������֮�����̾���Ϊ����󣬱�ʾ�κ�·�����ڣ�û��·��
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
			// ��������ͼ���ڽӾ������ʽ
			// ��������֮��ı�Ȩ�������
			// �����Ե�distance��ʼ����̫��Ҫ
			// ����һ������£�distance��ʼ��һ��Ҫ��
			build();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					in.nextToken();
					distance[i][j] = (int) in.nval;
				}
			}
			
			// ��ʼ�����������¾���
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
