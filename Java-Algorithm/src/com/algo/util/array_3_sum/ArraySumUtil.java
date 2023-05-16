package com.algo.util.array_3_sum;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.common.CommonArrayUtil;

/**
 * 
 * 数组累加和相关问题。= 数组3连问题 = 最长子数组长度问题
 *
 */
public class ArraySumUtil {

	/**
	 * 一个+数组，哪个子数组∑ = k且长度最长？<br>
	 * 解：每个位置开头怎样：双指针： 必须以left为开头的子数组累加和是target
	 */
	public static int findLongestSubArrayPositive(int[] arr, int k) {
		if (CommonArrayUtil.isEmpty(arr) || k <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}

	/**
	 * 一个+ - 0 数组，哪个子数组∑ = k且长度最长？<br>
	 * 
	 * 解： 每个位置结尾怎么样：<br>
	 * 
	 * sum - target = 前缀和能推的最长位置：
	 * 
	 * ∵ Σ(0~100) = 200, target = 30, then 某一个前缀和170出现在6位置 <br>
	 * ∴ 从7 ~ 100就是能推的最长，即100结尾最长能向左推到7位置得到答案30
	 * 
	 * 注：变换，e.g: 求子数组-1和1的数量一样多的最长子数组 => 将不是-1 1的数字变成0，即求target=0的时候的本题
	 * 
	 */
	public static int findLongestSubArrayAnySymbol(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key:前缀和
		// value : 0~value这个前缀和是最早出现key这个值的
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important

		int maxLen = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) { // 以i结尾的来审视
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				int prefixSumIndex = map.get(sum - k);
				maxLen = Math.max(i - prefixSumIndex, maxLen);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i); // 将前缀和的index放入map
			}
		}
		return maxLen;
	}

	/**
	 * 一个+ - 0 数组，哪个子数组∑ <= k且长度最长？<br>
	 * 
	 * 解：生成2个辅助数组成对使用,从后往前填：<br>
	 * 
	 * minSum[] : 子数字必须以i开头，取得的最小的累加和 <br>
	 * minSumEnd[] : 子数字必须以i开头子数组，当取得最小累加和时的右边界<br>
	 * 
	 * 思路： <br>
	 * 1. 如果以i开头最小累加和都>k, 那么从0开头任何子数组累加和不可能<=k <br>
	 * 2. 如果以i开头最小累加和<k, 则minSum继续右扩，直到>k停，存储答案<br>
	 * e.g : 0 开头往右在minSum上扩，直到13位置超过K了停 长度14个，这时候换成1开头，看看14位置能不能进来，继续扩，然后2位置...
	 * <br>
	 * 
	 * 窗口的右边界不回退O(N)：<br>
	 * 0............13|14 // minSum从0开头扩到13不能扩了∑(0~13) = sum，这时候弹出0，看一下1开始可以扩到14吗,
	 * 关注是否能把答案推高的可能性<br>
	 * 0|1..........13|14 // ∑(1~13) = sum - minSum[0]<br>
	 * 
	 * Hard！
	 * 
	 *
	 */
	public static int findLongestSubArrayAnySymbolLessThanK(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] minSums = new int[arr.length];
		int[] minSumEnds = new int[arr.length];
		minSums[arr.length - 1] = arr[arr.length - 1];
		minSumEnds[arr.length - 1] = arr.length - 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (minSums[i + 1] < 0) {
				minSums[i] = arr[i] + minSums[i + 1];
				minSumEnds[i] = minSumEnds[i + 1];
			} else {
				minSums[i] = arr[i];
				minSumEnds[i] = i;
			}
		}

		// 开始扩
		int end = 0;// 迟迟扩不进来那一块儿的开头位置，0~13， 则end停在14位置
		int sum = 0; // 窗口累加和
		int ans = 0; // 最大长度/答案
		for (int i = 0; i < arr.length; i++) {
			// while循环结束之后：
			// 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
			// 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；

			while (end < arr.length && sum + minSums[end] <= k) {
				sum += minSums[end];
				end = minSumEnds[end] + 1;
			}
			ans = Math.max(ans, end - i); // end 是第一个进不来的位置 - i现在开头的位置
			if (end > i) { // 还有窗口，哪怕窗口没有数字 [i~end) [4,4)
				sum -= arr[i]; // i要出去了，sum-要出去的
			} else { // i == end, 即将 i++, i > end, 此时窗口概念维持不住了，所以end跟着i一起走
				end = i + 1;
			}
		}
		return ans;

	}

	/**
	 * 求子数组平均值<=k的最长子数组长度？<br>
	 * 
	 * 解：假设k=10， 数组所有数-10， => 求子数组累加和<= 0的最长子数组长度.
	 */
	public static int findLongestSubArrayAverageLessThanK(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] -= k;
		}

		return findLongestSubArrayAnySymbolLessThanK(arr, 0);

	}

}
