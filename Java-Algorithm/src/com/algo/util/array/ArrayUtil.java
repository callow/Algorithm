package com.algo.util.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algo.util.array.model.Queue;

public class ArrayUtil {

	/**
	 * 生成一个数组实现的Queue
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<Integer>(100000000);
	}
	
	/**
	 * 合并互相覆盖的区间<br>
	 * intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
	 */
	public static int[][] mergeOverlaps(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, (a,b) -> (a[0] - b[0])); // 从小到大排序
		
		int[] cur = intervals[0];
		for(int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] > cur[1]) { // 无重叠
				result.add(cur);
				cur = intervals[i];
			} else {
				cur[1] = Math.max(cur[1], intervals[i][1]);  // 有重叠 可combine, 更新end 推更高
			}
		}
		result.add(cur);
		return result.toArray(new int[result.size()][2]); // 将ArrayList变成数组的方法
	}
	
	/**
	 * 找到最长的连续的1的长度
	 */
	
    public static int findMaxConsecutiveOnes(int[] nums) {
    	int count = 0;
    	int max = 0;
    	for(int i = 0; i < nums.length; i++) {
        	if (nums[i] == 1) {
        		count++;
        	} else {
        		max = Math.max(max, count);
        		count = 0;
        	}
    	}
    	return Math.max(max, count);
    }
    
    /**
     * 检查数组中是否有元素是我的2倍 
     */
    public static boolean hasElement2TimesThanMe(int[] arr) {
       List<Integer> copy = new ArrayList<>();
 	   for (int i =0; i < arr.length; i++) {
 		   copy.add(arr[i] * 2);
 	   }
 	   
 	   for (int i =0; i < arr.length; i++) {
 		   if (copy.contains(arr[i]) && i != copy.indexOf(arr[i])) {
 			   return true;
 		   }
 	   }
 	   return false;
    }
    
    /**
     * 是山形数组 
     */
    
    public static boolean isMountainArray(int[] arr) {
		if (arr.length < 3) {
			return false;
		}
		int turningPoint = -1;
        for (int i = 1; i < arr.length; i++) {
        	if (arr[i] == arr[i-1]) {
        		return false;
        	}
        	if (turningPoint != -1 && arr[i] > arr[i-1]) {
        		return false;
        	}
        	
        	if (arr[i] < arr[i-1]) {
        		turningPoint = i-1;
        	}
        	if (turningPoint == 0) {
        		return false;
        	}
        }
        return turningPoint == -1 ? false : true;
    }
	
}
