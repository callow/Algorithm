package com.algo.util.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.List;

public class Nim {

	
	/**
	 * 有N堆石子，谁把最后一个拿走谁赢(先手和后手)<br>
	 * 
	 * true = 先手赢<br>
	 * 
	 * 解：谁先面对0，0，0，0... 谁输 若先手面对 ^和 != 0， 先手赢，因为他可以轻易移动石子让后手面对全0<br>
	 * 
	 * 	  ^ != 0 , 必胜态， 因为先手可以动一动，让后手面对 ^==0的状态，直到死为止
	 *    ^ == 0 , 必败态
	 * 
	 * 是否先手会赢？
	 */
	public static String whoWin(List<Integer> stones) {
		int first = stones.get(0);
		
		// 只有1堆石子，先手直接赢
		if (stones.size() == 1) {
			return "先手";
		}
		
		// 有2堆石子，2堆不一样，先手赢
		if (stones.size() == 2) {
			int second = stones.get(1);
			return first != second ? "先手" : "后手";
		}
		
		// 求异或和
		if (stones.size() > 1) {
			for (int i = 2; i < stones.size(); i++) {
				first ^= stones.get(i);
			}
		}
		return first != 0 ? "先手" : "后手";
	}
	
	
	/**
	 * 反Nim博弈：谁拿走最后一块谁输 （反常游戏）
	 * 先手获胜打印John， 后手获胜打印Brother
	 * 解：
	 */
	
	public static int MAXN = 51;
	public static int[] stones = new int[MAXN];
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
			for (int j = 0; j < n; j++) {
				in.nextToken();
				stones[j] = (int) in.nval;
			}
			out.println(compute());
		}
		out.flush();
		out.close();
		br.close();
	}
	public static String compute() {
		int eor = 0, sum = 0;
		for (int i = 0; i < n; i++) {
			eor ^= stones[i];
			sum += stones[i] == 1 ? 1 : 0;
		}
		if (sum == n) {
			return (n & 1) == 1 ? "Brother" : "John";
		} else {
			return eor != 0 ? "John" : "Brother";
		}
	}
	
	
	
	
}
