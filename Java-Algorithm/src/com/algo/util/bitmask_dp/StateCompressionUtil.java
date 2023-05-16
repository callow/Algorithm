package com.algo.util.bitmask_dp;

import com.algo.util.bitmask_dp.impl.DPStateCompression;

/**
 * 
 * 状态压缩的动态规划: 利用位运算来压缩空间
 *
 */
public class StateCompressionUtil {

	/**
	 * 100 GAME : 一种游戏，2个选手轮流从maxChoosableInteger拿一个数加，谁最先加到desiredTotal谁就赢<br>
	 * 
	 * maxChoosableInteger(99): 从 1 -99 中不可放回取数<br>
	 * desiredTotal(100): 谁最加先到100，谁就赢
	 * 
	 * 返回先手会不会赢
	 */
	public static boolean canWin100Game(int maxChoosableInteger, int desiredTotal) {
		return new DPStateCompression().canWin(maxChoosableInteger, desiredTotal);
	}
}
