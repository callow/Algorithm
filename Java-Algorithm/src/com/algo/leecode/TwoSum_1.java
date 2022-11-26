package com.algo.leecode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {

	/**
	 * 反向索引Map: Key: 值， Value: index
	 */
    public static int[] twoSum(int[] nums, int target) {
    	Map<Integer,Integer> pool = new HashMap<>();
        int[] answer = new int[2];
        for (int i = 0; i < nums.length; i++) {
        	int complement = target - nums[i];
        	if (pool.containsKey(complement)) {
        		answer[0] = i;
        		answer[1] = pool.get(complement);
        		return answer;
        	} else {
        		pool.put(nums[i], i); // 反向索引！！
        	}
        }
        return answer;
    }
}
