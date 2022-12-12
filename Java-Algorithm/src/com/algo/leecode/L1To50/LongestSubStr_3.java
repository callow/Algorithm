package com.algo.leecode.L1To50;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStr_3 {
	
	public static void main(String[] args) {
		lengthOfLongestSubstring2("abccccdbc");
	}

	/**
	 * 最长字串O(N)： abcabcbb
	 * 		滑动窗口: left: 上一次出现a字母的下一个位置， right: 现在a字母的位置 -> 所以a'+1 ~ a 就是要收集答案了. 移动i 即右侧窗口 O(n), 收集左边的答案
	 * 		Map反向索引
	 * 		标准ascii码字符集共有128个编码<br>
	 * 			int[26] for Letters 'a' - 'z' or 'A' - 'Z' <br>
	 * 			int[128] for ASCII <br>
	 * 			int[256] for Extended ASCII <br>
	 */
	 public static int lengthOfLongestSubstring2(String s) {
		Integer[] seen = new Integer[128]; // 128 个 null

        int left = 0, right= 0;

        int ans = 0;
        while (right < s.length()) {
            char r = s.charAt(right); 

            Integer index = seen[r]; // char可以自动转换成 int 
            if (index != null && index >= left && index < right) {
                left = index + 1; // left移动到上一次出现位置的下一个位置 abcad -> left 来到b位置
            }

            ans = Math.max(ans, right - left + 1);

            seen[r] = right;
            right++;
        }

        return ans;
	 }
	
	 /**
	  * 启发性答案
	  */
	
	 public static int lengthOfLongestSubstring(String s) {
		 
		 int ans = 0;
		 
		 // key: 字母 , value: 最后一次出现的位置 O(1) // current index of character
	     Map<Character, Integer> map = new HashMap<>(); 
	     int start  = 0; // start of window
	     
	     for(int i = 0; i < s.length(); i++) { // i = end of window  O(N)
	    	 
	    	 char c = s.charAt(i);
	    	 
	    	 if (map.containsKey(c)) { // 之前出现过
	    		 if (map.get(c) >= start) {
	    			 start = map.get(c) + 1; // start 一定上一次出现最后一个位置的 下一个位置
	    		 }
	    	 }
	    	 
	    	 ans = Math.max(ans, (i - start) + 1); // 收集答案
	    	 map.put(c, i ); // 反向索引！！i 是出现c的最后一个位置
	     }
	     return ans;
	        
	 }
}
