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
	
	
}
