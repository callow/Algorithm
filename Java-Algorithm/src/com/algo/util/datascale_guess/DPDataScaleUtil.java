package com.algo.util.datascale_guess;

import com.algo.util.bit.BitUtil;

/**
 * 打印一些结果，找规律来解题O(1)
 *
 */
public class DPDataScaleUtil {

	/**
	 * 先手和后手，吃草数必须是4的次方(1,4,16,64...),给出草有n份，谁会赢？<br>
	 * 解：发现规律是：后先后先先
	 */

	public static String whoWin(int n) {
		if (n % 5 == 0 || n % 5 == 2) {
			return "后手";
		} else {
			return "先手";
		}
	}

	/**
	 * num是否可以表示一些连续整数的Σ？<br>
	 * 
	 * 解：发现规律是：2的幂是false,其他是true<br>
	 * 
	 * 二进制只有一个1就是2的某次放-> 最右侧1和自己相等就知道结果了
	 */
	public static boolean isContinuousSum(int num) {
		return BitUtil.rightMostOne(num) == num;
	}

}
