package com.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class Test {
	
	public static int total;
	public static int window;
	public static Deque<Integer> deque = new LinkedList<>();
	public static int maxUnique;
	public static Set<Integer> set = new LinkedHashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			total = (int) in.nval;
			in.nextToken();
			window = (int) in.nval;
			for (int i = 0; i < total; i++) {
				in.nextToken();
				int val = (int) in.nval;
				if (deque.size() < window) {
					deque.add(val);
					set.add(val);
				} else {
					deque.poll();
					deque.add(val);
					set.remove(val);
					set.add(val);
					
					if (set.size() > window) {
						int first = set.stream().findFirst().get();
						set.remove(first);
					}
				}
				int curSize = set.size();
				maxUnique = Math.max(maxUnique, curSize);
			}
			out.println(maxUnique);
		}
		out.flush();
		br.close();
		out.close();
	}
}
