package com.algo.util.bitmask_dp;

import com.algo.util.bitmask_dp.impl.DPStateCompressionCache;
import com.algo.util.bitmask_dp.impl.DPStateCompressiondp;

/**
 * 
 * 状态压缩的动态规划: 利用位运算来压缩空间 = 利用位压缩的记忆化搜索<br>
 * 
 * 适用范围：要/不要 问题
 *
 */
public class StateCompressionUtil {

	/**
	 * 100 GAME : 一种游戏，2个选手轮流从maxChoosableInteger拿一个数加，谁最先加到desiredTotal谁就赢<br>
	 * 
	 * maxChoosableInteger(99): 从 1 -99 中不可放回取数<br>
	 * desiredTotal(100): 谁最加先到100，谁就赢 (<=300)
	 * 
	 * 返回先手会不会赢
	 */
	public static boolean canWin100Game(int maxChoosableInteger, int desiredTotal) {
		return new DPStateCompressionCache().canWin(maxChoosableInteger, desiredTotal);
	}
	
	/**
	 * 
	 * TSP问题：Travelling salesman problem <br>
	 * 
	 * N个城市无向图，城市点到点距离存在N*N矩阵中，选一个城市，走遍所有且总距离最小<br>
	 * 
	 * 最终答案是一个环，因此从什么城市出发不会影响最终的答案：强行把0作为开始/结束点
	 */
	public static int tsp(int[][] distances) {
		return new DPStateCompressiondp().tsp(distances);
	}
	
	
}
