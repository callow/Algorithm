package com.algo.util.common;

public class CommonStringUtil {

	/**
	 *  生成大量空格
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
}
