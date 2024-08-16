package com.algo.util.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

/**
 * 巴士博弈
 */
public class Bash {

	
	/**
	 * 只有1堆石子n个，每次拿1~m个，谁把最后一个拿走谁赢 <br>
	 * true = 先手赢<br>
	 * 解：先手要维持 m+1的倍数, 因为先手每次都会拿n % (m + 1) != 0 的余数，这样就赢了
	 * 
	 * 	  如果开始的石子数量!= (m+1)整倍 => 先手赢， 因为先手可以让自己每一轮都面对不是（m+1）整倍的状态，即让后手(对手)每一轮都面对是（m+1）整倍的状态，这样到最后，最后一撮棋子终会被先手拿走（必胜态）
	 * 	  如果开始的石子数量== (m+1)整倍 => 后手赢 （必败态）
	 */
	public static String whoWin(int m, int n) {
		return n % (m + 1) != 0 ? "先手" : "后手";
	}
	
	
	
	
	/**
	 *  只有1堆石子n个，每次拿p^k个，谁把最后一个拿走谁赢(p是质数，k是自然数，0算自然数) https://www.luogu.com.cn/problem/P4018
	 *  
	 *  解：观察得到1~5可以拿，但是6就不可以，观察得到6的倍数一定不属于p^k.
	 *  
	 *    不是6的倍数 - 必胜态，因为先手总是可以在1-5里面拿让后手面对是6的整数倍的局面
	 *    6的倍数 - 必败态
	 */
	public static int t, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		t = (int) in.nval;
		for (int i = 0; i < t; i++) {
			in.nextToken();
			n = (int) in.nval;
			out.println(whoWin(n));
		}
		out.flush();
		out.close();
		br.close();
	}
	public static String whoWin(int n) {
		return n % 6 != 0 ? "October wins!" :"Roy wins!";
	}
	
	
	
	
}
