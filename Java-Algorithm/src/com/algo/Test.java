package com.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class MyRegex {
	public String pattern =  "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
}

public class Test {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			while(in.hasNext()) {
				String IP = in.next();
				System.out.println(IP.matches(new MyRegex().pattern));
			}
		}
	}
	
		
	
	public static int total;
	public static int window;
	public static Deque<Integer> deque = new LinkedList<>();
	public static int maxUnique;
	public static void main2(String[] args) throws IOException {
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
				if (deque.size() < 3) {
					deque.addFirst(val);
				} else {
					deque.pollLast();
					deque.addFirst(val);
				}
				int curSize = (int) deque.stream().distinct().count();
				maxUnique = Math.max(maxUnique, curSize);
			}
			out.println(maxUnique);
		}
		out.flush();
		br.close();
		out.close();
	}
}
