package com.algo.util.game;

import java.util.List;
/**
 * ICG(公平组合游戏)
 */
public class GameUtil {




	/**
	 * 2堆石子：a个，b个，每次智能拿 1~m 个，谁先拿走最后一个谁赢
	 */
	public static String whoWin(int a, int b, int m) {
		if (m > Math.max(a, b)) { // nim = 自由拿
			return Nim.whoWin(List.of(a, b));
		}

		if (a == b) { // 再另一堆上做copycat strategy
			// 先手不管拿什么，后手总会在另一堆拿同样的把先手逼死
			return "后手";
		}

		// 因为a==b游戏就结束了后手赢，那么我们把 (a与b的差值 看成 1堆)，差值没有了的时候就是局点了
		int diff = Math.max(a, b) - Math.min(a, b);
		return Bash.whoWin(m, diff); // 谁先把差值消除

	}
}
