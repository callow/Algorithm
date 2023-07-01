package com.algo.tasks.day7;

import com.algo.util.bit.BitUtil;
import com.algo.util.common.CommonArrayUtil;

/**
 * 给定一个正数组成的数组，长度一定大于1，求数组中哪两个数与(&)的结果最大.
 * 
 * 32位逐位考察是否能凑1
 * 
 */
public class MaxAndValue {

	public static int maxAndValue2(int[] arr) {
		// arr[0...M-1]  arr[M....]
		int M = arr.length;
		int ans = 0;
		for (int bit = 30; bit >= 0; bit--) {
			// 有用区： arr[0...M-1]  垃圾区： arr[M...]
			int i = 0;
			int tmp = M;
			
			// 只看 arr[0...M-1]，因为垃圾区的部分不用看了 是被淘汰的
			while (i < M) {
				if (!BitUtil.isOneAtIndex(arr[i], bit)) {
					CommonArrayUtil.swap(arr, i, --M); // 当前数字与垃圾区前一个交换，垃圾区左扩
				} else {
					i++;
				}
			}
			if (M == 2) { // arr[0,1] , 只有0与1位置不在垃圾区
				return arr[0] & arr[1];
			}
			if (M < 2) { // 在第bit位找不到，M跳回去一个数也不删
				M = tmp;
			} else { // > 2个数在bit位上有1，
				ans |= (1 << bit); // BitUtil.set1AtIndex(ans, bit)
			}
		}
		return ans;
	}
	
}
