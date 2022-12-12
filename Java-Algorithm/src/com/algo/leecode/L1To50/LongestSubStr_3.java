package com.algo.leecode.L1To50;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStr_3 {
	
	public static void main(String[] args) {
		lengthOfLongestSubstring2("abccccdbc");
	}

	/**
	 * ��ִ�O(N)�� abcabcbb
	 * 		��������: left: ��һ�γ���a��ĸ����һ��λ�ã� right: ����a��ĸ��λ�� -> ����a'+1 ~ a ����Ҫ�ռ�����. �ƶ�i ���Ҳര�� O(n), �ռ���ߵĴ�
	 * 		Map��������
	 * 		��׼ascii���ַ�������128������<br>
	 * 			int[26] for Letters 'a' - 'z' or 'A' - 'Z' <br>
	 * 			int[128] for ASCII <br>
	 * 			int[256] for Extended ASCII <br>
	 */
	 public static int lengthOfLongestSubstring2(String s) {
		Integer[] seen = new Integer[128]; // 128 �� null

        int left = 0, right= 0;

        int ans = 0;
        while (right < s.length()) {
            char r = s.charAt(right); 

            Integer index = seen[r]; // char�����Զ�ת���� int 
            if (index != null && index >= left && index < right) {
                left = index + 1; // left�ƶ�����һ�γ���λ�õ���һ��λ�� abcad -> left ����bλ��
            }

            ans = Math.max(ans, right - left + 1);

            seen[r] = right;
            right++;
        }

        return ans;
	 }
	
	 /**
	  * �����Դ�
	  */
	
	 public static int lengthOfLongestSubstring(String s) {
		 
		 int ans = 0;
		 
		 // key: ��ĸ , value: ���һ�γ��ֵ�λ�� O(1) // current index of character
	     Map<Character, Integer> map = new HashMap<>(); 
	     int start  = 0; // start of window
	     
	     for(int i = 0; i < s.length(); i++) { // i = end of window  O(N)
	    	 
	    	 char c = s.charAt(i);
	    	 
	    	 if (map.containsKey(c)) { // ֮ǰ���ֹ�
	    		 if (map.get(c) >= start) {
	    			 start = map.get(c) + 1; // start һ����һ�γ������һ��λ�õ� ��һ��λ��
	    		 }
	    	 }
	    	 
	    	 ans = Math.max(ans, (i - start) + 1); // �ռ���
	    	 map.put(c, i ); // ������������i �ǳ���c�����һ��λ��
	     }
	     return ans;
	        
	 }
}
