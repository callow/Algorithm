package com.algo.util.PrefixSum;

import java.util.HashMap;
import java.util.Map;

import com.algo.util.PrefixSum.model.PrefixSumArray;

/**
 * 前缀和数组技巧
 */
public class PrefixSumUtil {

	/**
	 * 用o(1) 实现 L ~ R的累加和
	 */
	public static int sumRange(int[] arr, int left, int right) {
		PrefixSumArray prefixSum = new PrefixSumArray(arr); // 构建前缀和数组
		
		return prefixSum.sum[right + 1] - prefixSum.sum[left]; // 相减一下
	}
	
	
	/**
	 * 无序数组中，累加和为k的最长子数组. https://www.nowcoder.com/practice/36fb0fd3c656480c92b569258a1223d5
	 * 
	 * 若0 ～i = 1000 k=100，想得到以i结尾的子数组（和 = 100） = 求前缀和=900的最早出现在哪
	 */
	
	public static int longestSubArraySumK(int[] arr, int k) {
		
		// key : 某个前缀和  value : 这个前缀和最早出现的位置
		HashMap<Integer, Integer> map = new HashMap<>();
		map.clear();
		map.put(0, -1); // 重要 : 0这个前缀和，一个数字也没有的时候，就存在了
		int longestLength = 0;
		
		for (int i = 0, sum = 0; i < arr.length; i++) {
			sum += arr[i]; // 前缀和
			if (map.containsKey(sum - k)) { // 找到前缀和=900最早出现位置
				longestLength = Math.max(longestLength, i - map.get(sum - k));
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return longestLength;
	}
	
	
	/**
	 * 最长良好数组： https://leetcode.cn/problems/longest-well-performing-interval/
	 * 
	 * 转化： >8 变 1， <= 8 变 -1，然后求每个位置结尾的情况，整体就个最大值 和上题一样
	 * 若0～i 累加和> 0 ,直接达标。 若累加和sum <= 0, 则只需要找sum -1 最早出现在哪即可
	 * 
	 * 因为累加和增加和减少都是一点一点来的，
	 * 
	 * if 0~i sum = -3, then we need to find a prefixsum = -4 so that prefixsum + 1 ~ i > 0 
	 * why we ignore - 5 -6 -7 ..., as -5 is increased from -4, and -4 must be "left" than -5
	 */
	public static int longestWPI(int[] hours) {
		// 某个前缀和，最早出现的位置
		HashMap<Integer, Integer> map = new HashMap<>();
		// 0这个前缀和，最早出现在-1，一个数也没有的时候
		map.put(0, -1);
		int ans = 0;
		for (int i = 0, sum = 0; i < hours.length; i++) {
			sum += hours[i] > 8 ? 1 : -1;
			if (sum > 0) {
				ans = i + 1;
			} else {
				// sum <= 0
				if (map.containsKey(sum - 1)) {
					ans = Math.max(ans, i - map.get(sum - 1));
				}
			}
			if (!map.containsKey(sum)) { // if sum not appeared before, note down the earliest location
				map.put(sum, i);
			}
		}
		return ans;
	}
	
	 /**
		子数组累加和=k的个数 : https://leetcode.com/problems/subarray-sum-equals-k/
		
	    1.前缀和转换： sum(a ~ b) = k  => prefix[b] - prefix[a-1] = k or prefix[a-1] = prefix[b] - k
	    2. Map(prefix,no.of occurrences of prefix)
	    
	 */
	public static int subarraySum(int[] nums, int k) {
	    
	    Map<Integer,Integer> map = new HashMap<>();
	    int sum = 0;
	    int ans = 0;
	    map.put(0,1); // 处理边界： prefix[b] - k = 0 (之前全部数字的前缀和=0) , 而我们前缀和又没有0，因此要加1个
	    for(int i = 0; i < nums.length; i++) {
	        sum += nums[i];
	
	        if(map.containsKey(sum - k)) { // 是否之前出现过prefix[a-1]
	            ans += map.get(sum-k);  // 出现了几次就+几次
	        }
	
	        map.put(sum, map.getOrDefault(sum,0) + 1);
	    }
	
	    return ans;
	}
	
	
	
	
    /**
     * 	累加平衡原理：
     * 
	    最长子数组，其中0和1的数量相同.
	    思路： 1s = 0s => 1s - 0s = 0
	          0 => -1, 1 => +1, = 求最长的连续子数组，其元素和为 0
	    则是求+1/-1累加数组相同元素最长的部分：
	    e.g： [1,0, 0, 0  0,  1,  1, 1]
	    sum:  [1,0,-1,-2,-3, -2, -1, 0]
	    其中balanced数组就首尾相同的数组：0~0, -1~-1, -2~-2, 最长的就是0～0
	
	    利用map记录counter和首次出现的位置。

	 */
	public static int findMaxLength(int[] nums) {
	    Map<Integer,Integer> map = new HashMap<>(); // 第一次出现的位置：count: index
	    map.put(0,-1);
	    int maxLen = 0, count = 0;
	    for (int i =0; i < nums.length; i++) {
	        count += (nums[i] == 1 ? 1 : -1);
	        if (map.containsKey(count)) { // 遇到相同的了，结算最长位置
	            maxLen = Math.max(maxLen, i - map.get(count));
	        } else {
	            map.put(count,i);
	        }
	    }
	    return maxLen;
	}
}
