package com.algo.util.common;

public class CommonStringUtil {

	/**
	 * ���ɴ����ո�
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
	 * ����Manacher char[], ����ԭString��#�ָ��char[] : 1#2#3#8#7#g#j#c...
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
	 * ��תString����
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
	 * ����manacher�� ����manacher�㷨
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
}
