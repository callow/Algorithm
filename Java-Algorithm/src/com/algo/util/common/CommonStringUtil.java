package com.algo.util.common;

public class CommonStringUtil {

	/**
	 *  ���ɴ����ո�
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
