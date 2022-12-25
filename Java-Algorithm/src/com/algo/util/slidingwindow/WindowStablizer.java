package com.algo.util.slidingwindow;

import java.util.LinkedList;

public class WindowStablizer {

	/**
	 * window 从头->尾： 大 -> 小： 从windows中把比我小的元素移除掉， 更新元素，方便PeekFirst
	 */
	
	public static void removeElementsLessThanMe(LinkedList<Integer> window, int[] arr, int me) {
		while (!window.isEmpty() && arr[window.peekLast()] <= arr[me]) {
			window.pollLast();
		}
		window.addLast(me);
	}
	
	/**
	 * window 从头->尾： 小 -> 大： 从windows中把比我大的元素移除掉， 更新元素，方便PeekFirst
	 */
	
	public static void removeElementsGreaterThanMe(LinkedList<Integer> window, int[] arr, int me) {
		while (!window.isEmpty() && arr[window.peekLast()] >= arr[me]) {
			window.pollLast();
		}
		window.addLast(me);
	}
	
	/**
	 * 检查过期，弹出双端队列没用的元素
	 */
	
	public static void expireUselessElement(LinkedList<Integer> window, int useless) {
		if (window.peekFirst() == useless) {
			window.pollFirst();
		}
	}

}
