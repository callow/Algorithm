package dynamic_program.DD;

import java.util.ArrayList;
import java.util.List;

/**
 * ɾ�����ټ����ַ����Ա����һ���ַ������Ӵ�: ���������ַ���s1��s2 ����s1����ɾ�������ַ����Գ�Ϊs2���Ӵ�
 * 
 * 
 */
public class MinimumDeleteBecomeSubstring10 {

	/**
	 * dp[i][j] = s1ǰi���ַ� ��ƥ�� ���s2ǰj���ַ��ִ��ĺ�׺��
	 * 
	 * 1. s1[i-1] != s2[j-1] ���һ���ַ����� 
	 * 	  �� ����Ҫɾ���� Ȼ��s1[0..i-2] �� 1+ dp[i-1][j]
	 * 2.  s1[i-1] = s2[j-1] ���һ���ַ����
	 * 	  �� ������ ������ʣ�µ�  �� dp[i-1][j-1]
	 * 
	 * 
	 */
	public static int minDelete2(String str1, String str2) {
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int n = s1.length;
		int m = s2.length;
		// dp[len1][len2] :
		// s1[ǰ׺����Ϊi]����ɾ�������ַ������Ա��s2[ǰ׺����Ϊj]�������׺��
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][0] = i;
			for (int j = 1; j <= m; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i - 1][j] + 1;
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int j = 0; j <= m; j++) {
			ans = Math.min(ans, dp[n][j]);
		}
		return ans;
	}
	
	
	
	
	// ��������
	// Ϊ����֤
	public static int minDelete1(String s1, String s2) {
		List<String> list = new ArrayList<>();
		f(s1.toCharArray(), 0, "", list);
		// ���� : ���ȴ���������ȿ���
		// ��Ϊ������ȴ����������s2���Ӵ�
		// ��ô��Ҫɾ�����ַ����� = s1�ĳ��� - s1�����г���
		// �����г���Խ����Ҫɾ�����ַ�������Խ��
		// ���Գ��ȴ���������ȿ���
		list.sort((a, b) -> b.length() - a.length());
		for (String str : list) {
			if (s2.indexOf(str) != -1) {
				// ���s2�У��Ƿ������ǰ��s1������str
				return s1.length() - str.length();
			}
		}
		return s1.length();
	}
	// ����s1�ַ��������������д�
	public static void f(char[] s1, int i, String path, List<String> list) {
		if (i == s1.length) {
			list.add(path);
		} else {
			f(s1, i + 1, path, list);
			f(s1, i + 1, path + s1[i], list);
		}
	}
	
	
	// Ϊ����֤
	// ���ɳ���Ϊn����v���ַ�������ַ���
	public static String randomString(int n, int v) {
		char[] ans = new char[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (char) ('a' + (int) (Math.random() * v));
		}
		return String.valueOf(ans);
	}

	// Ϊ����֤
	// ������
	public static void main(String[] args) {
		// ���Ե��������Ƚ�С
		// ������Ϊ���������ˣ���������������
		// ��������������㹻˵����ʽ��������ȷ��
		int n = 12;
		int v = 3;
		int testTime = 20000;
		System.out.println("���Կ�ʼ");
		for (int i = 0; i < testTime; i++) {
			int len1 = (int) (Math.random() * n) + 1;
			int len2 = (int) (Math.random() * n) + 1;
			String s1 = randomString(len1, v);
			String s2 = randomString(len2, v);
			int ans1 = minDelete1(s1, s2);
			int ans2 = minDelete2(s1, s2);
			if (ans1 != ans2) {
				System.out.println("������!");
			}
		}
		System.out.println("���Խ���");
	}
}
