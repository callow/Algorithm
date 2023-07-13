package com.algo.tasks.Task9;
/**
 * 无环改灯问题
 */
public class LightProblem {
	
	
	/**
	 * 
	 * 无环改灯问题的递归版本
	 */
	public static int noLoopMinStep(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] ^ 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		// 不变0位置的状态
		int p1 = f(arr, 2, arr[0], arr[1]);
		// 改变0位置的状态
		int p2 = f(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
		if (p2 != Integer.MAX_VALUE) {
			p2++;
		}
		return Math.min(p1, p2);
	}

	/**
	 * 
	 * 当前在哪个位置上，做选择，nextIndex - 1 = cur ，当前！
	   cur - 1 处 ： preStatus
	   cur 处：  curStatus
	   0 ~ cur - 2  全亮的！
	 * curStatus ^ 1 : 0^1 = 1 , 1 ^ 1 = 0
	 */
	public static int f(int[] arr, int nextIndex, int preStatus, int curStatus) {
		if (nextIndex == arr.length) { // 来到最后一个开关的位置N-1
			return preStatus != curStatus ? (Integer.MAX_VALUE) : (curStatus ^ 1);
		}
		/**
		 *  没到最后一个按钮呢！ i < arr.length
		 */
		if (preStatus == 0) { // 之前状态是0 ， 一定要改变, 之前位置只有自己来不久 否则后面就没有人可以改变了， 从左往右
			curStatus ^= 1; // 改变自己
			int cur = arr[nextIndex] ^ 1; // 改变右边
			int next = f(arr, nextIndex + 1, curStatus, cur);
			return next == Integer.MAX_VALUE ? next : (next + 1);
		} else { //  之前状态是1 ， 一定不要改变, 不要按
			return f(arr, nextIndex + 1, curStatus, arr[nextIndex]);
		}
	}
	
	
	/**
	 * 无环改灯问题的迭代版本
	 */
	public static int noLoopMinStep2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		int p1 = traceNoLoop(arr, arr[0], arr[1]);
		int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
		p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
		return Math.min(p1, p2);
	}

	public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
		int i = 2;
		int op = 0;
		while (i != arr.length) {
			if (preStatus == 0) {
				op++;
				preStatus = curStatus ^ 1;
				curStatus = arr[i++] ^ 1;
			} else {
				preStatus = curStatus;
				curStatus = arr[i++];
			}
		}
		return (preStatus != curStatus) ? Integer.MAX_VALUE : (op + (curStatus ^ 1));
	}
	
}
