package com.algo.util.network_flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

import com.algo.util.network_flow.model.Dinic;
import com.algo.util.network_flow.model.MessageBox;

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
	
	
	/**
	 * ��ʽ�������������֣��м��ж�ʱ��ͻỺס��ֱ��������Ŵ�ӡ -> ��Ƶ���ŵ�ԭ��
	 */
	
	public static void streamPlay() {
		MessageBox box = new MessageBox();
		// 1....
		System.out.println("����2������ʱ��");
		box.receive(2,"B"); // - 2"
		System.out.println("����1������ʱ��");
		box.receive(1,"A"); // 1 2 -> print, trigger is 1
		box.receive(4,"D"); // - 4
		box.receive(5,"E"); // - 4 5
		box.receive(7,"G"); // - 4 5 - 7
		box.receive(8,"H"); // - 4 5 - 7 8
		box.receive(6,"F"); // - 4 5 6 7 8
		box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
		box.receive(9,"I"); // 9 -> print, trigger is 9
		box.receive(10,"J"); // 10 -> print, trigger is 10
		box.receive(12,"L"); // - 12
		box.receive(13,"M"); // - 12 13
		box.receive(11,"K"); // 11 12 13 -> print, trigger is 11
	}
	
}

