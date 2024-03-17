package com.algo.util.common;

public class CommonStringUtil {
	
	
	/**
	 * 是否是回文串 palindrome： https://leetcode.com/problems/valid-palindrome
	 */
	
	public static boolean isPalindrome(String s) {
		StringBuilder str1 = new StringBuilder(s);
        str1.reverse();
        return s.equals(str1.toString());
	}
	
	
	/**
	 * 移除所有非字母字符 
	 */
	public static String removeAllNonAlphanumeric(String s) {
		return s.replaceAll("[^a-zA-Z0-9]", "");
	}

	/**
	 * 生成大量空格
	 */

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	/**
	 * 生成Manacher char[], 即将原String以#分割的char[] : 1#2#3#8#7#g#j#c...
	 */

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	/**
	 * 
	 * 反转String数组
	 * 
	 */
	public static void reverseString(char[] s) {
		int i = 0;
		int j = s.length - 1;
		while (i < j) {
			CommonArrayUtil.swap(s, i, j);
			i++;
			j--;
		}
	}

	/**
	 * 生成manacher串 用于manacher算法
	 */

	public static StringBuilder manacherString2(String str) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("#");
		for (int i = 0; i < str.length(); i++) {
			stringBuilder.append(str.charAt(i));
			stringBuilder.append("#");
		}
		return stringBuilder;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static boolean isEqual(String a, String b) {
		if (a == null && b == null) {
			return true;
		} else {
			if (a == null || b == null) {
				return false;
			} else {
				return a.equals(b);
			}
		}
	}
	
	
	public static int bestSplit(String s1, String s2, int first) {
		int N = s1.length();
		int M = s2.length();
		int end = N;
		for (int i = first, j = 0; i < N && j < M; i++, j++) {
			if (s1.charAt(i) < s2.charAt(j)) {
				end = i;
				break;
			}
		}
		String bestPrefix = s2;
		int bestSplit = first;
		for (int i = first + 1, j = M - 1; i <= end; i++, j--) {
			String curPrefix = s1.substring(first, i) + s2.substring(0, j);
			if (curPrefix.compareTo(bestPrefix) >= 0) {
				bestPrefix = curPrefix;
				bestSplit = i;
			}
		}
		return bestSplit;
	}
	
	/**
	 * 判断char是0~9的数字 
	 * 
	 */
    public static boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }
	
	/**
	 * 字符串提取数字: https://leetcode.com/problems/string-to-integer-atoi/description/
	 * 	4193 with words -> 4193 
	 */
    public static int myAtoi(String str) {
        final int len = str.length();
         
         if(len == 0){
             return 0;
         }
         
         // index = 数字起始位置
         int index = 0;
         while(index < len && str.charAt(index) == ' '){
             index++;
         }
         
         if(index == len){
             return 0;
         }
         
         char ch;
         boolean isNegative = (ch = str.charAt(index)) ==  '-';
         
         // 如果发现+、- ， 数字起始位置+1
         if(isNegative || ch == '+'){
             ++index;
         }
             
         final int maxLimit = Integer.MAX_VALUE / 10;
         int result = 0;
         while(index < len && isDigit(ch = str.charAt(index))){
             
             int digit = ch - '0';
             
             if(result > maxLimit || (result == maxLimit && digit > 7)){
                 return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
             }
             
             result = (result * 10) + digit;
             
             ++index;
         }
         
         return isNegative ? -result : result;
     }
}
