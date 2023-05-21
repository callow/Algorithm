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
	 * TSP问题：Travelling salesman problem O（n*2^n）<br>
	 * 
	 * N个城市无向图，城市点到点距离存在N*N矩阵中，选一个城市，走遍所有且总距离最小<br>
	 * 
	 * 最终答案是一个环，因此从什么城市出发不会影响最终的答案：强行把0作为开始/结束点
	 */
	public static int tsp(int[][] distances) {
		return new DPStateCompressiondp().tsp(distances);
	}
	
	/**
	 * 铺瓷砖问题：有1*2的砖，铺满 M * N 区域，有几种不同铺法？<br>
	 * 
	 * 解：限制一下，瓷砖只能向上/向右铺瓷砖, 那么i-2行以上的行一定是塞满的，i-1(上一行)铺砖状态可以用位<br>
	 * 
	 * i-1：0110011 // 0 没铺， 1 铺了
	 * i：  1001100
	 * 
	 */
	public static int paveBricks(int n, int m) {
		return new DPStateCompressiondp().paveBricks(n, m);
	}
	
	
}
