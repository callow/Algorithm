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
	public int paveBricks(int N, int M) {
		if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
			return 0;
		}
		if (N == 1 || M == 1) {
			return 1;
		}
		int[] pre = new int[M]; // pre代表-1行的状况
		for (int i = 0; i < pre.length; i++) {
			pre[i] = 1;
		}
		return process(pre, 0, N);
	}
	
	// pre 表示level-1行的状态
	// level表示，正在level行做决定
	// N 表示一共有多少行 固定的
	// level-2行及其之上所有行，都摆满砖了
	// level做决定，让所有区域都满，方法数返回
	public int process(int[] pre, int level, int N) {
		if (level == N) { // base case
			for (int i = 0; i < pre.length; i++) {
				if (pre[i] == 0) { // 已经来到Level层，只要上层瓷砖有一个不满 => 0种方法
					return 0;
				}
			}
			return 1;
		}

		// 没到终止行，可以选择在当前的level行摆瓷砖
		int[] op = getOp(pre);
		return dfs(op, 0, level, N);
	}
	
	/**
	 * 根据上一行的状态决定此时可以进行的操作 => 0变1， 1变0
	 * pre: 0001100
	 * op:  1110011
	 */
	private int[] getOp(int[] pre) {
		int[] cur = new int[pre.length];
		for (int i = 0; i < pre.length; i++) {
			cur[i] = pre[i] ^ 1;
		}
		return cur;
	}
	

	/**
	 * op[i] == 0 可以考虑摆砖
	   op[i] == 1 只能竖着向上
	 */
	private int dfs(int[] op, int col, int level, int N) {
		// 在列上自由发挥，玩深度优先遍历，当col来到终止列，i行的决定做完了
		// 轮到i+1行，做决定
		if (col == op.length) { // 当前已来到最后一列，那么去下一行继续
			return process(op, level + 1, N);
		}
		// 当前如果还没来到最后一列
		int ans = 0;
		// col位置不横摆
		ans += dfs(op, col + 1, level, N); // col位置上不摆横转
		// col位置横摆, 向右
		if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
			op[col] = 1;
			op[col + 1] = 1;
			ans += dfs(op, col + 2, level, N);
			op[col] = 0;
			op[col + 1] = 0;
		}
		return ans;
	}


}
