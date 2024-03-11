package com.algo.util.line_sweep;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * 扫描线算法
 */
public class LineSweepUtil {

	
    /**
    	最少需要几间会议室：给了一些会议的开始结束时间，求最少需要几个会议室
        	- 以开始时间排序一下， 放入小根堆
        	- 从左往右扫，看开始时间是否可以在结束时间最小之后，如果可以就不需要新room，反之需要一间
        	https://leetcode.com/problems/meeting-rooms-ii/
 */
	public static int minMeetingRooms(int[][] intervals) {
	    Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
	    PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 结束时间放入
	
	    // 从左往右开始扫描
	    for(int i =0; i < intervals.length; i++) {
	
	        
	        // 你结束后我可以用你的 我不用额外申请房间
	        if(!minHeap.isEmpty()) {
	            int smallestEndTime = minHeap.peek();
	            int currentStartTime =  intervals[i][0];
	            
	            if ( currentStartTime >= smallestEndTime) {
	                minHeap.poll();
	            }
	            
	        }
	        minHeap.offer(intervals[i][1]);
	    }
	
	    return minHeap.size();
	}
}
