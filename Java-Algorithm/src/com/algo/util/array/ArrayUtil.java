package com.algo.util.array;

import com.algo.util.array.model.Queue;

public class ArrayUtil {

	/**
	 * 生成一个数组实现的Queue
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<Integer>(100000000);
	}
	
	
}
