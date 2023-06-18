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
	 * 每组输入包含4种数据：
	 * 0. 总共有多少test case
	 * 1. n: 网络中总共有多少个nodes
	 * 2. s(source) t(destination) c(总的边数) 组合
	 * 3. 关联关系c行数据：每行3个数字
	 * 	- 第1，2个数：关联的node编号，第3个数：bandwith
	 * e.g : 
	 * 
	 *  2 // 总共2组test case
	 *  4 // 总共4个nodes在网络中
		1 4 5 // 求从node 1 到 node 4的最大网络流，总共5条边
		1 2 20 // 从node 1 到 node 2 的bandwith = 20
		1 3 10 // 从node 1 到 node 3 的bandwith = 10
		2 3 5 // 从node 2 到 node 3 的bandwith = 5
		...
	 *  
	 */
	public static void maxFlowByDinic() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int cases = (int) in.nval; // 有多少test case
			for (int i = 1; i <= cases; i++) {
				in.nextToken();
				int n = (int) in.nval; // node的总数
				in.nextToken();
				int s = (int) in.nval; // source
				in.nextToken();
				int t = (int) in.nval; // target
				in.nextToken();
				int m = (int) in.nval; // 总边数
				
				Dinic dinic = new Dinic(n);
				for (int j = 0; j < m; j++) {
					in.nextToken();
					int from = (int) in.nval; // 从noode 1
					in.nextToken();
					int to = (int) in.nval;  // 到noode 2
					in.nextToken();
					int weight = (int) in.nval; // bandwith/weight是多少
					
					// 无向图 加入2个方向
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
	 * 流式播放连续的数字，中间中断时候就会缓住，直到连续后才打印 -> 视频播放的原理
	 */
	
	public static void streamPlay() {
		MessageBox box = new MessageBox();
		// 1....
		System.out.println("这是2来到的时候");
		box.receive(2,"B"); // - 2"
		System.out.println("这是1来到的时候");
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

