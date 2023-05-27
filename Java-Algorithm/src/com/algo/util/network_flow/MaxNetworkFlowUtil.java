package com.algo.util.network_flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

import com.algo.util.network_flow.model.Dinic;

public class MaxNetworkFlowUtil {
	/**
	 * https://lightoj.com/problem/internet-bandwidth
	 * 
	 * StreamTokenizer : "i am song lei" -> "i","am","songh","lei"
	 * 
	 * ÿ���������4�����ݣ�
	 * 0. �ܹ��ж���test case
	 * 1. n: �������ܹ��ж��ٸ�nodes
	 * 2. s(source) t(destination) c(�ܵı���) ���
	 * 3. ������ϵc�����ݣ�ÿ��3������
	 * 	- ��1��2������������node��ţ���3������bandwith
	 * e.g : 
	 * 
	 *  2 // �ܹ�2��test case
	 *  4 // �ܹ�4��nodes��������
		1 4 5 // ���node 1 �� node 4��������������ܹ�5����
		1 2 20 // ��node 1 �� node 2 ��bandwith = 20
		1 3 10 // ��node 1 �� node 3 ��bandwith = 10
		2 3 5 // ��node 2 �� node 3 ��bandwith = 5
		...
	 *  
	 */
	public static void maxFlowByDinic() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int cases = (int) in.nval; // �ж���test case
			for (int i = 1; i <= cases; i++) {
				in.nextToken();
				int n = (int) in.nval; // node������
				in.nextToken();
				int s = (int) in.nval; // source
				in.nextToken();
				int t = (int) in.nval; // target
				in.nextToken();
				int m = (int) in.nval; // �ܱ���
				
				Dinic dinic = new Dinic(n);
				for (int j = 0; j < m; j++) {
					in.nextToken();
					int from = (int) in.nval; // ��noode 1
					in.nextToken();
					int to = (int) in.nval;  // ��noode 2
					in.nextToken();
					int weight = (int) in.nval; // bandwith/weight�Ƕ���
					
					// ����ͼ ����2������
					dinic.addEdge(from, to, weight);
					dinic.addEdge(to, from, weight);
				}
				int ans = dinic.maxFlow(s, t);
				out.println("Case " + i + ": " + ans);
				out.flush();
			}
		}
	}
}

