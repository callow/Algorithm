package com.algo.util.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.algo.util.array.model.Queue;

public class ArrayUtil {

	/**
	 * ����һ������ʵ�ֵ�Queue
	 */
	
	public static Queue<Integer> getQueue() {
		return new Queue<Integer>(100000000);
	}
	
	/**
	 * �ϲ����า�ǵ�����<br>
	 * intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
	 */
	public static int[][] mergeOverlaps(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, (a,b) -> (a[0] - b[0])); // ��С��������
		
		int[] cur = intervals[0];
		for(int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] > cur[1]) { // ���ص�
				result.add(cur);
				cur = intervals[i];
			} else {
				cur[1] = Math.max(cur[1], intervals[i][1]);  // ���ص� ��combine, ����end �Ƹ���
			}
		}
		result.add(cur);
		return result.toArray(new int[result.size()][2]); // ��ArrayList�������ķ���
	}
	
	
}
