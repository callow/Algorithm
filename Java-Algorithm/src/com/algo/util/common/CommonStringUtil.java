package com.algo.util.common;

public class CommonStringUtil {

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
}
