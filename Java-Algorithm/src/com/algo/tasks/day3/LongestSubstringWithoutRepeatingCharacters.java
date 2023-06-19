package com.algo.tasks.day3;

import com.algo.util.common.CommonStringUtil;

/**
 * 求字符串中最长无重复字串长度？
 * 
	https://leetcode.com/problems/longest-substring-without-repeating-characters/
	
	1. 看到字串：立马想到以i结尾，答案是什么(向左/向右推多远)
	2. 因为是从做往右求，i结尾答案是否可以用i-1的答案加速
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
	
	public static int lengthOfLongestSubstring(String s) {
		if (CommonStringUtil.isEmpty(s)) { 
			return 0;
		}
		char[] str = s.toCharArray();
		
		int[] map = new int[256]; // map[i] = v , i位置这个字符的ascci上次出现在v位置
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		map[str[0]] = 0; 
		int N = str.length;
		int ans = 1;
		int pre = 1; // 上一个位置i-1向左推多长
		for (int i = 1; i < N; i++) {
			
			int p1 = i - map[str[i]]; // case 1: 上次出现此字符的位置能推多远
			int p2 = pre + 1; // case 2: 上一个位置 + 1
			int cur = Math.min(p1, p2);
			ans = Math.max(ans, pre);
			pre = cur;
			map[str[i]] = i;
		}
		return ans;
	}
}
