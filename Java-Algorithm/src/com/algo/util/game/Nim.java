package com.algo.util.game;

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

		if (stones.size() == 1) {
			return "先手";
		}

		if (stones.size() == 2) {
			int second = stones.get(1);
			return first != second ? "先手" : "后手";
		}

		if (stones.size() > 1) {
			for (int i = 2; i < stones.size(); i++) {
				first ^= stones.get(i);
			}
		}
		return first != 0 ? "先手" : "后手";
	}
}
