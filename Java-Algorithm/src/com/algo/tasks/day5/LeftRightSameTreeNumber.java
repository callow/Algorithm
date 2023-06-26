package com.algo.tasks.day5;

import java.security.NoSuchAlgorithmException;

import com.algo.util.common.model.BTNode;
import com.algo.util.hash.HashUtil;

/**
 * 
 * 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树, 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
 *
 */
public class LeftRightSameTreeNumber {
	
	private static final String SHA256 = "SHA-256";
	
	/**
	 * 次优解：O(N * logN),左树和右树比对是遍历 复杂度较高O(N)
	 * 
	 * T(N) = 2T(N/2) + O(N) - Master 公式
	 */
	public static int sameNumber1(BTNode head) {
		if (head == null) {
			return 0;
		}
		return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
	}
	
	
	public static boolean same(BTNode h1, BTNode h2) {
		if (h1 == null ^ h2 == null) { // h1 与 h2 布尔相反
			return false;
		}
		if (h1 == null && h2 == null) {
			return true;
		}
		// 两个都不为空
		return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
	}
	
	//---------------------------------------------------------------------------------------[
	
	/**
	 * 使用二叉树的序列化 直接比较O(1), 省去遍历的过程,总复杂度O(N)
	 */
	
	public static int sameNumber2(BTNode head) throws NoSuchAlgorithmException {
		return process(head).ans;
	}
	public static Info process(BTNode head) throws NoSuchAlgorithmException {
		if (head == null) {
			return new Info(0, HashUtil.getHashCode(SHA256, "#,"));
		}
		Info l = process(head.left);
		Info r = process(head.right);
		int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
		
		String str = HashUtil.getHashCode(SHA256, String.valueOf(head.value) + "," + l.str + r.str);
		
		return new Info(ans, str);
	}
	
	public static class Info {
		public int ans;
		public String str;

		public Info(int a, String s) {
			ans = a;
			str = s;
		}
	}
}
