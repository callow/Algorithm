package com.algo.util.slidingwindow;

import java.util.LinkedList;

public class WindowStablizer {

	/**
	 * window ��ͷ->β�� �� -> С�� ��windows�аѱ���С��Ԫ���Ƴ����� ����Ԫ�أ�����PeekFirst
	 */
	
	public static void removeElementsLessThanMe(LinkedList<Integer> window, int[] arr, int me) {
		while (!window.isEmpty() && arr[window.peekLast()] <= arr[me]) {
			window.pollLast();
		}
		window.addLast(me);
	}
	
	/**
	 * window ��ͷ->β�� С -> �� ��windows�аѱ��Ҵ��Ԫ���Ƴ����� ����Ԫ�أ�����PeekFirst
	 */
	
	public static void removeElementsGreaterThanMe(LinkedList<Integer> window, int[] arr, int me) {
		while (!window.isEmpty() && arr[window.peekLast()] >= arr[me]) {
			window.pollLast();
		}
		window.addLast(me);
	}
	
	/**
	 * �����ڣ�����˫�˶���û�õ�Ԫ��
	 */
	
	public static void expireUselessElement(LinkedList<Integer> window, int useless) {
		if (window.peekFirst() == useless) {
			window.pollFirst();
		}
	}

}
