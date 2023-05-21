package com.algo.util.bitmask_dp.impl;

import java.util.ArrayList;
import java.util.List;

import com.algo.util.bitmask_dp.StateCompressionDPService;

public class DPRecursive implements StateCompressionDPService {

	/**
	 * O(N*N!) 太高了
	 */
	@Override
	public boolean canWin(int choose, int total) {
		if (total == 0) {
			return true;
		}
		if ((choose * (choose + 1) >> 1) < total) {
			return false;
		}
		int[] arr = new int[choose];
		for (int i = 0; i < choose; i++) {
			arr[i] = i + 1;
		}
		// arr[i] != -1 表示arr[i]这个数字还没被拿走
		// arr[i] == -1 表示arr[i]这个数字已经被拿走
		// 集合，arr，1~choose
		return pick(arr, total);
	}

	/**
	 * 
	 * 当前轮到先手拿，先手只能选择在arr中还存在的数字，还剩rest这么值，返回先手会不会赢O(N!)
	 */
	public static boolean pick(int[] arr, int rest) {
		if (rest <= 0) {
			return false; // 先手面对已经满的情况直接认输
		}
		// 先手去尝试所有的情况
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != -1) {
				int cur = arr[i];
				arr[i] = -1; // 拿走
				boolean next = pick(arr, rest - cur); // 先手的后续过程，汇报是谁赢了
				arr[i] = cur; // 做完尝试后，把数字还原回去（恢复现场）
				if (!next) { // 先手的后续过程告诉先手，(是后续过程的后手赢了 = 是当前过程的先手赢了)
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int tsp(int[][] distances) {
		int cities = distances.length; // 0...N-1
		
		// set.get(i) != null i这座城市在集合里
		// set.get(i) == null i这座城市不在集合里
		List<Integer> set = new ArrayList<>();
		for (int i = 0; i < cities; i++) {
			set.add(1);
		}
		return go(distances, set, 0);
	}
	/**
	 * 
	  任何两座城市之间的距离，可以在matrix里面拿到
	  set中表示着哪些城市的集合，
	  start这座城一定在set里，
	  从start出发，要把set中所有的城市过一遍，最终回到0这座城市，最小距离是多少
	  
	  e.g: go(matrix, {null,1,null,1,1,null}, 3)
	  	city:			0   1  2   3 4  5
	 */
	public static int go(int[][] matrix, List<Integer> set, int start) {
		int cityNum = 0; 
		// 看看还剩几座城市，set中还剩几座城市
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) != null) {
				cityNum++;
			}
		}
		
		// 如果只剩1座城了= base case, 返回从当前start直接回归宿点0的距离
		if (cityNum == 1) {
			return matrix[start][0];
		}
		// cityNum > 1  不只start这一座城
		set.set(start, null); // 先把自己从集合中去掉
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i) != null) {
				// start -> i i... -> 0
				int cur = matrix[start][i] + go(matrix, set, i);
				min = Math.min(min, cur);
			}
		}
		set.set(start, 1); // 恢复现场
		return min;
	}

	@Override
	public int paveBricks(int n, int m) {
		// TODO Auto-generated method stub
		return 0;
	}

}
