package com.algo.util.slidingwindow;

import java.util.LinkedList;

import com.algo.util.common.CommonArrayUtil;
import com.algo.util.common.CommonStringUtil;

/**
 * 
 * 滑动窗口(双端队列配合使用) = 追赶结构 /跟随机构<br><br>
 * 
 * 常用于窗口内 最大/最小/平均/中位数... 等数据的更新和收集<br>
 * 
 * L <= R<br><br>
 *
 * 滑动窗口一般都不需要动L只需要动R，且R的移动是固定的就是R < arr.length. L会根据R的变动来调整
 */
public class SlidingWindowUtil {

	/**
	 * 收集一个大小为w的窗口滑动过程中收集到的最大值数组 ~ O(N)<br><br>
	 * 思路： 滑动窗口 + 双端队列(linkedlist/deque)<br>
	 * while (arr[maxQueue.peekLast()] <= arr[r]){maxQueue.pollLast()} => 只要进队列尾部的都是>小于当前r的，
	 *  因此这个双端队列是从大到小有序的，所以 最后答案都是队列的头部 
	 */
	
	public static int[] getWindowMax(int[] arr, int w) {
		if (CommonArrayUtil.isEmpty(arr) || arr.length < w || w < 1) {
			return null;
		}
		
		int index = 0; // 给response专用的下表变量
		int[] response = new int[arr.length - w + 1];
		LinkedList<Integer> maxQueue = new LinkedList<>(); // 双端队列从大到小有序的
		
		for (int r = 0; r < arr.length; r++) { // 只动 r, r < arr.lnegth
			
			WindowStablizer.removeElementsLessThanMe(maxQueue, arr, r);
			
			// r - w = 最后了
			WindowStablizer.expireUselessElement(maxQueue, r - w); // 检查过期，弹出双端队列没用的元素
			if (r >= w - 1) { // 是否形成一个正常的窗口, 
				response[index++] = arr[maxQueue.peekFirst()];
			}
		}
		return response;
	}
	
	/**
	 * Arr中达标子数组sub 条件： Max(sub) - Min(sub) <= sum, 求arr中达标子数组总数 ? O（N） <br><br>
	 * 
	 * 1. 若 Max(sub) - Min(sub) <= sum, 则sub中的所有孙数组一定都达标。(因为最大值只能变小/不变，最小值只能变大/不变) <br><br>
	 * 2. 若 L ~ R 不达标(Max(sub) - Min(sub) > sum)，则 (L左扩) ~ R 和 L ~ (R右扩) 的子数组一定不达标。(因为最大值只能变大/不变，最小值只能变小/不变) 
	 */
	
	public static int getQualifiedSubArray (int[] arr, int sum) {
		
		if (CommonArrayUtil.isEmpty(arr) || sum < 0) {
			return 0;
		}
		int n = arr.length;
		int count = 0;
		LinkedList<Integer> maxWindow = new LinkedList<>();// 从头->尾： 大 -> 小
		LinkedList<Integer> minWindow = new LinkedList<>();// 从头->尾： 小 -> 大
		int r = 0;
		
		for (int L = 0; L < n; L++) { // 窗口以0，1，2，3....开头的子数组，数量有多少
			// 往右扩，扩到r初次不达标了 停！
			while (r < n) {
				
				WindowStablizer.removeElementsLessThanMe(maxWindow, arr, r);
				WindowStablizer.removeElementsGreaterThanMe(minWindow, arr, r);
				if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) { // 不达标了！
					break;
				} else {
					r++;
				}
			}
			count += r - L;
			WindowStablizer.expireUselessElement(maxWindow, L); // 如果L要过期，则滚蛋
			WindowStablizer.expireUselessElement(minWindow, L);
		}
		return count;
	}
	
	/**
	 * O(N)加油站良好出发点问题，求从哪些加油站出发可以跑完一圈？： https://leetcode.com/problems/gas-station <br><br>
	 * gas [1,1,3,1] 加油站有多少汽油。<br>
	 * cost[2,2,1,1] 距离下一个加油站距离。<br>
	 * combined [-1,-1,2,0] <br>
	 * combinedArr的前缀和数组cb2: [-1,-2,0,0,-1,-2,0,0] <br>	
	 * 2位置出发的所有累加和： Min(cb2[2~5]) - cb2[1] < 0 ? '2位置不是良好出发点' ： '2位置是良好出发点' <br>
	 * 思路： 因为是一个环，如果从环的最后一个点出发会循环从0->从新走过，因此累加和数组要是原数组*2大小，只要在窗口内出现<b>负数</b>就不是良好出发点
	 */
	public static boolean[] canFinishLoop(int[] gas, int[] cost) {
		int N = gas.length;
		int NN = N << 1; // N * 2
		int[] cb2 = new int[NN];
		// 填充cb2,2个2个填快一点
		for (int i = 0; i < N; i++) {
			cb2[i] = gas[i] - cost[i];
			cb2[i + N] = gas[i] - cost[i];
		}
		
		for (int i = 1; i < NN; i++) {
			cb2[i] += cb2[i - 1];
		}
		
		//--------------------------------
		
		LinkedList<Integer> minWindow = new LinkedList<>();
		for (int me = 0; me < N; me++) {
			WindowStablizer.removeElementsGreaterThanMe(minWindow, cb2, me);
		}
		
		boolean[] answer = new boolean[N];
		for (int offset = 0, me = 0, j = N; j < NN; offset = cb2[me++], j++) {
			if (cb2[minWindow.peekFirst()] - offset >= 0) { // Min(cb2[2~5]) - cb2[1] >= 0 ? 2是良好出发点
				answer[me] = true;
			}
			WindowStablizer.expireUselessElement(minWindow, me); // 我用过了，过期我从窗口中
			WindowStablizer.removeElementsGreaterThanMe(minWindow, cb2, j); // 继续滑动窗口，更新元素，方便PeekFirst
		}
		return answer;
	}
	
	
	/**
	 * x轴上一些点，一个长k的绳子，求最多压中几个点？<br>
	 * 
	 * 解：利用双指针，R每次往右走到尽头到R-L<=4时候 收集答案,L向右移动 O(N),指针不回退
	 */
	public static int pointsCoveredByRape(int[] x, int k) {
		int left = 0;
		int right = 0;
		int N = x.length;
		int max = 0;
		while (left < N) {
			while (right < N && x[right] - x[left] <= k) {
				right++;
			}
			max = Math.max(max, right - (left++));
		}
		return max;
	}
	
	/**
	 * 
	 * 一个数组中只有两种字符'G'和'B',相邻才可交换，G要全在左B全在右 || 反之，返回最少的交换次数?
	 * 
	 * 解：双指针，向前移动 O(N)
	 * 
	 * e.g: B B G G B B G
	 *      gi  i
	 */
	public static int swapGB(String s) {
		if (CommonStringUtil.isEmpty(s)) { 
			return 0;
		}
		char[] str = s.toCharArray();
		int step1 = 0;
		int step2 = 0;
		int gi = 0;
		int bi = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == 'G') { // 当前的G，去左边   方案1
				step1 += i - (gi++);
			} else {// 当前的B，去左边   方案2
				step2 += i - (bi++);
			}
		}
		return Math.min(step1, step2);
	}
	
}
