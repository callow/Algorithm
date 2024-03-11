package com.algo.util.line_sweep;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * ɨ�����㷨
 */
public class LineSweepUtil {

	
    /**
    	������Ҫ��������ң�����һЩ����Ŀ�ʼ����ʱ�䣬��������Ҫ����������
        	- �Կ�ʼʱ������һ�£� ����С����
        	- ��������ɨ������ʼʱ���Ƿ�����ڽ���ʱ����С֮��������ԾͲ���Ҫ��room����֮��Ҫһ��
        	https://leetcode.com/problems/meeting-rooms-ii/
 */
	public static int minMeetingRooms(int[][] intervals) {
	    Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
	    PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // ����ʱ�����
	
	    // �������ҿ�ʼɨ��
	    for(int i =0; i < intervals.length; i++) {
	
	        
	        // ��������ҿ�������� �Ҳ��ö������뷿��
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
