package com.algo.util.game;

import java.util.List;

public class GameUtil {

	/**
	 * 有N堆石子，谁把最后一个拿走谁赢(先手和后手)<br>
	 * 
	 * true = 先手赢<br>
	 * 
	 * 解：谁先面对0，0，0，0... 谁输 若先手面对 ^和 != 0， 先手赢，因为他可以轻易移动石子让后手面对全0<br>
	 * 
	 */
	public static boolean nim(List<Integer> stones) {
		int first = stones.get(0);

		if (stones.size() == 1) {
			return true;
		}

		if (stones.size() == 2) {
			int second = stones.get(1);
			return first != second;
		}

		if (stones.size() > 1) {
			for (int i = 2; i < stones.size(); i++) {
				first ^= stones.get(i);
			}
		}
		return first != 0;
	}

	/**
	 * 只有1堆石子n个，每次拿1~m个，谁把最后一个拿走谁赢 <br>
	 * true = 先手赢<br>
	 * 解：先手要维持 m+1的倍数
	 * 
	 */
	public static boolean bash(int m, int n) {
		return n % (m + 1) != 0;
	}

	/**
	 * 2堆石子：a个，b个，每次智能拿 1~m 个，谁先拿走最后一个谁赢
	 */
	public static String whoWin(int a, int b, int m) {
		if (m > Math.max(a, b)) { // nim = 自由拿
			return nim(List.of(a, b)) ? "先手" : "后手";
		}

		if (a == b) { // 再另一堆上做copycat strategy
			// 先手不管拿什么，后手总会在另一堆拿同样的把先手逼死
			return "后手";
		}

		int diff = Math.max(a, b) - Math.min(a, b); // a与b的差值 = 1堆
		return bash(m, diff) ? "先手" : "后手"; // 谁先把差值消除

	}
}
