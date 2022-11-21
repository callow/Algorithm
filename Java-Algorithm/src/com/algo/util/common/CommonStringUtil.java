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
}
