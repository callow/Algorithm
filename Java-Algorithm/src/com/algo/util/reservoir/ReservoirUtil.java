package com.algo.util.reservoir;

import com.algo.util.reservoir.model.Reservoir;

/**
 * 实验次数增多，球被选中是等概率的
 *
 */

public class ReservoirUtil {
	
	private static Reservoir POOL = new Reservoir(100);

	/**
	 * 全球用户，首次登录都有一次获奖机会抽前100名获奖者，做到不用存储，且流式计算得奖者。<br>
	 * 
	 * 得奖必须公平 / 即等概率！ <br><br>
	 * 
	 * 则蓄水池在插入的时候就是等概率，刚好符合
	 */
	
	public static int[] lottery() {
		
		// 假设全球总共有10000个用户，加入蓄水池
		for (int userId = 1; userId <= 10000; userId++) {
			POOL.add(userId);
		}
		
		// 抽取100名获奖者
		return POOL.sampling();
	}
	
}
