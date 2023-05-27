package com.algo.util.network_flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

import com.algo.util.network_flow.model.Dinic;

public class MaxNetworkFlowUtil {

	public static void maxFlowByDinic() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int cases = (int) in.nval;
			for (int i = 1; i <= cases; i++) {
				in.nextToken();
				int n = (int) in.nval;
				in.nextToken();
				int s = (int) in.nval;
				in.nextToken();
				int t = (int) in.nval;
				in.nextToken();
				int m = (int) in.nval;
				Dinic dinic = new Dinic(n);
				for (int j = 0; j < m; j++) {
					in.nextToken();
					int from = (int) in.nval;
					in.nextToken();
					int to = (int) in.nval;
					in.nextToken();
					int weight = (int) in.nval;
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

