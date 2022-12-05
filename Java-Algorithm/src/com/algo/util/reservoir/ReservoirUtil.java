package com.algo.util.reservoir;

import com.algo.util.reservoir.model.Reservoir;

/**
 * 实验次数增多，球被选中是等概率的
 *
 */

public class ReservoirUtil {

	/**
	 * 全球用户，首次登录都有一次获奖机会抽前100名获奖者，做到不用存储，且流式计算得奖者
	 */
	
	private static Reservoir POOL = new Reservoir(100);
	public static int[] lottery() {
		
		// 假设全球总共有10000个用户，加入蓄水池
		for (int userId = 1; userId <= 10000; userId++) {
			POOL.add(userId);
		}
		
		// 抽取100名获奖者
		return POOL.sampling();
	}
	
}
