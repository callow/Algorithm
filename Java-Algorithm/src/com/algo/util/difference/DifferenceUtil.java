package com.algo.util.difference;

/**
 * 
 * 数组一维拆分：
 * 	- 在 2 ～ 5 统一 +3 / 再 1 - 6 所有数字 - 2 / ....
 *  - 不支持边改边查，只支持修改一股脑全做完，最后再查 
 *  
 * 等差数列拆分：
 * 	- 不支持边改边查，只支持修改一股脑全做完，最后再查 
 *	- 前提：1～n范围全是0，共m个操作，要求在l-r上，依次加首项为s,末项为e, 公差为d的等差数列
 *  - 操作：
 *  	L位置+s
 *  	L+1位置+（d-s)
 *  	R+1位置- (d+e)
 *  	R+2位置+e
 *      最后进行2遍前缀和
 */
public class DifferenceUtil {

	/**
	 *  一维差分：https://leetcode.cn/problems/corporate-flight-bookings/ =》 [1,5,4] [2,9,3]
	 *  [L] += v
	 *  [R+1] -= v
	 *  最后求前缀和数组！
	 *  
	 *  过程：
	 *   - 若在 L ~ R范围 + v 则 在单在L位置+v, 单在R+1位置-v
	 *   - 从左往右加工出前缀和，组成的前缀和数组就是最终的结果
	 *  
	 */
	public static int[] corpFlightBookings(int[][] bookings, int n) {
		// 差分数组： cnt
		int[] cnt = new int[n + 2];
		// 设置差分数组，每一个操作对应两个设置
		for (int[] book : bookings) {
			cnt[book[0]] += book[2];
			cnt[book[1] + 1] -= book[2];
		}
		// 加工前缀和
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		// 将结果拷贝到ans并返回
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = cnt[i + 1];
		}
		
		return ans;
	}
	
	
	
	
}
