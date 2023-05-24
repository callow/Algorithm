package com.algo.util.suffix_array;

import com.algo.util.common.CommonDPUtil;
import com.algo.util.common.CommonStringUtil;

/**
 *  Dictionary order/Lexicographical order: 字典序
 *  
 *  后缀数组排序问题
 *  
 *  离散化： 对于桶数据巨大很离散的，可以使用encode进行优化 减少桶数量：<br> 
 *  
 *   [100万,5,90万,10亿] -> 转换成 [3,1,2,4]
 *
 *
 */
public class SuffixUtil {

	/**
	 * 找出子数组/子串 中字典序最高的那个
	 */
	public static String lexicographicalMaxSubstring(String s) {
		if (CommonStringUtil.isEmpty(s)) {
			return s;
		}
		
		int n = s.length();
		char[] str = s.toCharArray();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (char cha : str) {
			min = Math.min(min, cha);
			max = Math.max(max, cha);
		}
		
		/**
		 * 桶优化：
		 * 原数组： min=17，max=103 则，没必要准备103个桶 只需要准备桶：1 ~ 86， 即：103-17个桶
		 * 但是dc3的num 要同时变换成[1....86]
		 * 
		 */
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = str[i] - min + 1;
		}
		
		DC3 dc3 = new DC3(arr, max - min + 1);
		
		return s.substring(dc3.sa[n - 1]);
	}
	
	/**
	 * 将s1插入s2， 返回形成最大的字典序的串？<br>
	 * N : s1长度 <br>
	 * M : s2长度 <br>
	 * O(N+M) + O(M^2)  <br>
	 * 
	 * 1. 如果s1的字典序 > s2的， s2根本不用插在s1之前.
	 * 最左的可能插入位：s1中i的位置
	 * 最右的可能插入位：i与s2分出大小的位置
	 * s1 = 999999993299 , s2 = 994
	 *            l r
	 *  e.g: 当来到l处，发现后缀字典序 < 994, 且与s2分出大小的位置在r,则就在l~r的局部尝试即可：
	 *    994993,999493,999943..
	 * 
	 * 设 w = 一个超小ascci code, 组曾一个新串s1+w+s2 -> e.g: aabwab
	 * 则，在新串上求的dc3 
	   注：全局统一+2就可以保证>=1, w = 1
	 */
	public static String lexicographicalMaxInsert(String s1, String s2) {
		if (s1 == null || s1.length() == 0) {
			return s2;
		}
		if (s2 == null || s2.length() == 0) {
			return s1;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int min = str1[0];
		int max = str1[0];
		for (int i = 1; i < N; i++) {
			min = Math.min(min, str1[i]);
			max = Math.max(max, str1[i]);
		}
		for (int i = 0; i < M; i++) {
			min = Math.min(min, str2[i]);
			max = Math.max(max, str2[i]);
		}
		
		// 做隔断了s1+w+s2
		int[] all = new int[N + M + 1];
		int index = 0;
		
		// s1
		for (int i = 0; i < N; i++) {
			all[index++] = str1[i] - min + 2;
		}
		all[index++] = 1; // w
		
		// s2
		for (int i = 0; i < M; i++) {
			all[index++] = str2[i] - min + 2;
		}
		
		
		DC3 dc3 = new DC3(all, max - min + 2); // 调用dc3 搞定字典序
		int[] rank = dc3.rank;
		
		int comp = N + 1;
		for (int i = 0; i < N; i++) { // loop s1的位置
			if (rank[i] < rank[comp]) { // 找到s1中符合条件的最左位置l
				int best = CommonStringUtil.bestSplit(s1, s2, i); // 找到s1中符合条件的最左位置r,并找到s1中最好的插入位置
				return s1.substring(0, best) + s2 + s1.substring(best);
			}
		}
		return s1 + s2;
		
	}
	
	/**
	 * num1 长M， num2 长N， 内元素<=9, 从2者中从左向右选K个数，返回代表最大数字的结果。<br>
	 * 
	 * e.g: [6,8,9,3], [9,6,4] -> 996,因为8在9左不可以选了.<br>
	 * 
	 * 子问题1： 在一个数组中从左往右选M个数，怎么样尽量大？<br>
	 * 	1. dp[2][1] = 9 // 从2位置往后选，智能选1个，最好位置的值为9
	 *  2. 将这个表填好-预处理结构，答案可以直接从表中拿出来. 
	 *  3. 从数组中选2个怎么最好 = dp[0][2] + dp[1][1]
	 * 
	 * 子问题2： 2个数组如何merge在一起尽量大？ DC3
	 */
	
	public static int[] lexicographicalMaxCreation(int[] nums1, int[] nums2, int k) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		if (k < 0 || k > len1 + len2) {
			return null;
		}
		int[] result = new int[k];
		int[][] dp1 = CommonDPUtil.getdp(nums1); // 生成dp1这个表，以后从nums1中，只要固定拿N个数，
		int[][] dp2 = CommonDPUtil.getdp(nums2);
		// get1 从arr1里拿的数量
		// K - get1 从arr2里拿的数量
		for (int get1 = Math.max(0, k - len2); get1 <= Math.min(k, len1); get1++) {
			// arr1 挑 get1个，怎么得到一个最优结果
			int[] pick1 = CommonDPUtil.maxPick(nums1, dp1, get1); // nums1中选get1个如何最好
			int[] pick2 = CommonDPUtil.maxPick(nums2, dp2, k - get1); // nums1中选k - get1个如何最好
			int[] merge = mergeBySuffixArray(pick1, pick2); // 2个合并在一起如何最好
			result = preMoreThanLast(result, merge) ? result : merge; // 枚举所有答案，并用result抓住最好那个答案
		}
		return result;
	}
	
	/**
	 * 两个arr合并在一起，怎么尽量大？返回结果
	 */
	private static int[] mergeBySuffixArray(int[] nums1, int[] nums2) {
		int size1 = nums1.length;
		int size2 = nums2.length;
		// 所有左右数字+2，中间用1隔断开
		int[] nums = new int[size1 + 1 + size2];
		for (int i = 0; i < size1; i++) {
			nums[i] = nums1[i] + 2;
		}
		nums[size1] = 1;
		for (int j = 0; j < size2; j++) {
			nums[j + size1 + 1] = nums2[j] + 2;
		}
		
		// 因为数字是0-9， 最大值是9，应该准备10个bucket, 但是多准备一个而已11
		DC3 dc3 = new DC3(nums, 11);
		int[] rank = dc3.rank;
		int[] ans = new int[size1 + size2];
		int i = 0;
		int j = 0;
		int r = 0;
		while (i < size1 && j < size2) {
			ans[r++] = rank[i] > rank[j + size1 + 1] ? nums1[i++] : nums2[j++];
		}
		while (i < size1) {
			ans[r++] = nums1[i++];
		}
		while (j < size2) {
			ans[r++] = nums2[j++];
		}
		return ans;
	}
	
	/**
	 * 判断
	 */
	private static boolean preMoreThanLast(int[] pre, int[] last) {
		int i = 0;
		int j = 0;
		while (i < pre.length && j < last.length && pre[i] == last[j]) {
			i++;
			j++;
		}
		return j == last.length || (i < pre.length && pre[i] > last[j]);
	}
	
}
