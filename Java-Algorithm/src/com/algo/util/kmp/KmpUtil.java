package com.algo.util.kmp;

import java.util.Queue;

import com.algo.util.binarytree.BinaryTreeIterateUtil;
import com.algo.util.common.model.BTNode;
import com.algo.util.kmp.model.KMP;

public class KmpUtil {

	/**
	 *  二叉树，判断small树 是否是 big树 的子树？<br>
	 *  序列化2棵树，然后看一下KMP的匹配
	 */
	
	public static boolean containsTree(BTNode big, BTNode small) {
		if (small == null) {
			return true;
		}
		if (big == null) {
			return false;
		}
		Queue<String> b = BinaryTreeIterateUtil.preSerialize(big);
		Queue<String> s = BinaryTreeIterateUtil.preSerialize(small);
		
		String[] str = new String[b.size()];
		for (int i = 0; i < str.length; i++) {
			str[i] = b.poll();
		}

		String[] match = new String[s.size()];
		for (int i = 0; i < match.length; i++) {
			match[i] = b.poll();
		}
		return KMP.getIndexOf(str, match) != -1;
	}
	
	/**
	 * 判断2个String a,b是否是互为旋转串： <br><br>
	 * "123456" -> 1转 -> "234561"
	 *         -> 12转 -> "345612"
	 *        -> 123转 -> "456123" <br>
	 *  
	 *  思路： 生成b + b的大串b2，用KMP判断a是否在b2中
	 */
	
	public static boolean isRotation(String a, String b) {
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}
		String b2 = b + b;
		return KMP.getIndexOf(b2, a) != -1;
	}
	
}
