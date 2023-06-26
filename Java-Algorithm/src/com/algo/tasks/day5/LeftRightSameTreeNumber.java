package com.algo.tasks.day5;

import java.security.NoSuchAlgorithmException;

import com.algo.util.common.model.BTNode;
import com.algo.util.hash.HashUtil;

/**
 * 
 * ���һ���ڵ�X���������ṹ�������ṹ��ȫһ������ô����˵��XΪͷ���������������, ����һ�ö�������ͷ�ڵ�head������head���������ж��ٿ��������
 *
 */
public class LeftRightSameTreeNumber {
	
	private static final String SHA256 = "SHA-256";
	
	/**
	 * ���Ž⣺O(N * logN),�����������ȶ��Ǳ��� ���ӶȽϸ�O(N)
	 * 
	 * T(N) = 2T(N/2) + O(N) - Master ��ʽ
	 */
	public static int sameNumber1(BTNode head) {
		if (head == null) {
			return 0;
		}
		return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
	}
	
	
	public static boolean same(BTNode h1, BTNode h2) {
		if (h1 == null ^ h2 == null) { // h1 �� h2 �����෴
			return false;
		}
		if (h1 == null && h2 == null) {
			return true;
		}
		// ��������Ϊ��
		return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
	}
	
	//---------------------------------------------------------------------------------------[
	
	/**
	 * ʹ�ö����������л� ֱ�ӱȽ�O(1), ʡȥ�����Ĺ���,�ܸ��Ӷ�O(N)
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
