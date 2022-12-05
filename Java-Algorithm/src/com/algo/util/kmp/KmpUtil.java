package com.algo.util.kmp;

import java.util.Queue;

import com.algo.util.binarytree.BinaryTreeIterateUtil;
import com.algo.util.common.model.BTNode;
import com.algo.util.kmp.model.KMP;

public class KmpUtil {

	/**
	 *  ���������ж�small�� �Ƿ��� big�� ��������<br>
	 *  ���л�2������Ȼ��һ��KMP��ƥ��
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
	 * �ж�2��String a,b�Ƿ��ǻ�Ϊ��ת���� <br><br>
	 * "123456" -> 1ת -> "234561"
	 *         -> 12ת -> "345612"
	 *        -> 123ת -> "456123" <br>
	 *  
	 *  ˼·�� ����b + b�Ĵ�b2����KMP�ж�a�Ƿ���b2��
	 */
	
	public static boolean isRotation(String a, String b) {
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}
		String b2 = b + b;
		return KMP.getIndexOf(b2, a) != -1;
	}
	
}
