package com.algo.util.structure;

import java.util.PriorityQueue;

/**
 * median在数据流中快速找到中位数？
 * 
 * 1. 用大根堆：放较小一半数字
 * 2. 用小跟堆：放较大一半数字
 * 
 * 大小跟堆相差2时，弹出一个，匀称一下
 * 
 * https://leetcode.cn/problems/find-median-from-data-stream/
 */
public class MedianFinder {

	private PriorityQueue<Integer> maxHeap;

	private PriorityQueue<Integer> minHeap;

	public MedianFinder() {
		maxHeap = new PriorityQueue<>((a, b) -> b - a);
		minHeap = new PriorityQueue<>((a, b) -> a - b);
	}

	public void addNum(int num) {
		if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
			maxHeap.add(num);
		} else {
			minHeap.add(num);
		}
		balance();
	}

	public double findMedian() {
		if (maxHeap.size() == minHeap.size()) {
			return (double) (maxHeap.peek() + minHeap.peek()) / 2;
		} else {
			return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
		}
	}

	private void balance() {
		if (Math.abs(maxHeap.size() - minHeap.size()) == 2) {
			if (maxHeap.size() > minHeap.size()) {
				minHeap.add(maxHeap.poll());
			} else {
				maxHeap.add(minHeap.poll());
			}
		}
	}
}
