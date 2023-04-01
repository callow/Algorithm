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
	
	/**
	 * �ҵ����������1�ĳ���
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
     * ����������Ƿ���Ԫ�����ҵ�2�� 
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
     * ��ɽ������ 
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
