package com.algo.tasks.day4;

/**
 * 
 * 生成长度为Size的达标数组： 若i < k < j, 则 arr[i] + arr[j] <> 2 * arr[k]
 *
 */
public class MakeNo {

	// 生成长度为size的达标数组
	// 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
	public static int[] makeNo(int size) {
		if (size == 1) {
			return new int[] { 1 }; // 随意给一个种子：这里给1
		}
		// size
		// 一半长达标来
		// 7 : 4
		// 8 : 4
		// [4个奇数] [3个偶]
		int halfSize = (size + 1) / 2; // 向上取整
		int[] base = makeNo(halfSize);
		// base -> 等长奇数达标来
		// base -> 等长偶数达标来
		int[] ans = new int[size];
		int index = 0;
		for (; index < halfSize; index++) {
			ans[index] = base[index] * 2 - 1;
		}
		for (int i = 0; index < size; index++, i++) {
			ans[index] = base[i] * 2;
		}
		return ans;
	}
}
